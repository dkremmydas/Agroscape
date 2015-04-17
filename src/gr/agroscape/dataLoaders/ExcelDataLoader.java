package gr.agroscape.dataLoaders;

import gr.agroscape.agents.Farmer;
import gr.agroscape.agents.Plot;
import gr.agroscape.authorities.LandPropertyRegistry;
import gr.agroscape.authorities.PaymentAuthority;
import gr.agroscape.behaviors.farmers.production.agriculturalActivities.ArableCropCultivation;
import gr.agroscape.behaviors.farmers.production.arableCropProduction.FarmerArableCropProducer_MP;
import gr.agroscape.behaviors.farmers.production.products.Product;
import gr.agroscape.contexts.CropsContext;
import gr.agroscape.contexts.FarmersContext;
import gr.agroscape.contexts.MainContext;
import gr.agroscape.contexts.PlotsContext;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map.Entry;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

import repast.simphony.space.grid.GridPoint;
import repast.simphony.valueLayer.GridValueLayer;

/**
 * This class loads agents and initial data from an excel file. <br />
 * The Excel file should contain the following worksheets:
 * <ul>
 * <li>"Agents", where non-farmers and farmers are defined. See {@link #loadFarmersContext}</li>
 * <li>"Crops", where the various crops are defined. See {@link #loadCropsContext}</li>
 * <li>"Plots", where in a graphically way the plots are defined. See {@link #loadPlotsContext}</li>
 * <li>"CropSuitability_XXX", where level of suitability (range: (0,1]) 
 * for crop XXX is graphically defined. See {@link #loadCropSuitabilityMap}</li>
 * </ul>
 * 
 * @author Dimitris Kremmydas
 *
 */
public class ExcelDataLoader implements ICanLoadAgroscapeData {
	
	//private XSSFWorkbook excelWB; 
	private Workbook excelWB; 
	
	private ArrayList<Plot> avplots = new ArrayList<Plot>();
	private ArrayList<Farmer> avfarmers = new ArrayList<Farmer>();
	private ArrayList<ArableCropCultivation> avcrops = new ArrayList<ArableCropCultivation>();
	
	
	/**
	 * Constructor where the excel file is defined. The excel file must be in 2007 or in 2003 format.
	 * <br />Take care of problems with the excel file.
	 * @param excel_location
	 * @throws IOException 
	 * @throws InvalidFormatException 
	 */
	public ExcelDataLoader(String excel_location) throws InvalidFormatException, IOException {
		super();
		Workbook wb = WorkbookFactory.create(new File(excel_location));
		//if(! this.isXLSX()) {this.excelWB = (HSSFWorkbook) wb;}
		//else {this.excelWB = (XSSFWorkbook) wb;}
		this.excelWB=wb;
	}
	

	/**
	 * Load all simulation Crops.<br />
	 * Column #1: the integer id of the Crop <br />
	 * Column #2: the name of the Crop <br />
	 */
	@Override
	public void loadCropsContext(CropsContext context) {
		//if(! this.isXLSX()){}
		//else {XSSFSheet sh = (XSSFSheet) this.excelWB.getSheet("Crops");}
		Sheet sh = this.excelWB.getSheet("Crops");
		
		Iterator<Row> rowItr = sh.iterator(); 
		rowItr.next(); //skip first row
		while(rowItr.hasNext()) {
			Row row = rowItr.next();
			Iterator<Cell> cellItr = row.cellIterator();
			
			int crop_id = (int)cellItr.next().getNumericCellValue();
			//System.err.println("ExcelDataLoader::loadCropsContext,  " + crop_id );
			String crop_name = cellItr.next().getStringCellValue();
			ArrayList<Product> possOuts = new ArrayList<>();
			//iterate columns where product are
			while (cellItr.hasNext()) {
				String productName = cellItr.next().getStringCellValue();
				if(! Product.getAvailableProducts().containsKey(productName)) {
					possOuts.add(new Product(productName));
				}
				else {
					possOuts.add(Product.getByName(productName));
				}
			}
			
			this.avcrops.add(new ArableCropCultivation(crop_name,crop_id,possOuts));
		}
		context.addAll(this.avcrops);
		
	}

	/**
	 * Loads the Plots. <br />
	 * The Excel sheet is a representation of the grid, where the first row is the X-coordinate
	 * and the first column is the Y-coordinate. Inside this matrix the number f the x-y coordinates
	 * is the plot_id.
	 */
	@Override
	public void loadPlotsContext(PlotsContext context) {
		Sheet sh = this.excelWB.getSheet("Plots");
		Iterator<Row> rowItr = sh.iterator(); 
		rowItr.next(); //skip first row
		
		HashMap<Integer,ArrayList<GridPoint>> plotsMap= new  HashMap<>();
		int curY=0; 
		while(rowItr.hasNext()) {
			int curX=0;
			Row row = rowItr.next();
			Iterator<Cell> cellItr = row.cellIterator();
			cellItr.next();
			while (cellItr.hasNext()) {
            	Cell cell = cellItr.next();
            	if(cell.getCellType()==Cell.CELL_TYPE_NUMERIC) {
            		int v = (int)cell.getNumericCellValue();
            		if(! plotsMap.containsKey(v)) {
            			plotsMap.put(v, new ArrayList<GridPoint>());
            		}
            		plotsMap.get(v).add(new GridPoint(curX,curY));
            	}
				curX++;
            }
			curY++;
		}
		
		//now add the Plots for the plotsMap
		for (Entry<Integer, ArrayList<GridPoint>> entry : plotsMap.entrySet()) {
		    this.avplots.add(new Plot(entry.getValue(),entry.getKey()));
		}
		context.addAll(this.avplots);
	}

	/**
	 * All type of agents are loaded here. <br />
	 * - Column #1: the integer id of the agent <br />
	 * - Column #2: Is the Agent a farmer? 1=yes, 0=no  <br />
	 * - Column #3: The initial liquidity (in EuroCents)  <br />
	 * Currently only Farmer_MP are initialized. Also all available crops are 
	 * added as cultivation-option to the farmers. <br />
	 * 
	 * //TODO There is a provision for other types of Agents and Farmers and for giving the
	 * option of certain farmers to cultivate a subset of the available crops.
	 */
	@Override
	public void loadFarmersContext(FarmersContext context) {
		if(this.avcrops.isEmpty()) throw new NullPointerException("loadCropsContext should be called before");
		
		Sheet sh = this.excelWB.getSheet("Agents");
		
		Iterator<Row> rowItr = sh.iterator(); 
		rowItr.next(); //skip first row
		while(rowItr.hasNext()) {
			Row row = rowItr.next();
			int agent_id = (int)row.getCell(0).getNumericCellValue();
			int isFarmer = (int)row.getCell(1).getNumericCellValue();
			long liquidity = (long)row.getCell(2).getNumericCellValue();
			
			if(isFarmer==1) {this.avfarmers.add(new FarmerArableCropProducer_MP(liquidity,this.avcrops,agent_id));}
		}
		context.addAll(this.avfarmers);
	}

	/**
	 * Loads the suitability of cultivating a Crop c to a GridPoint x,y. The suitability is a float ranging in [0,1]. <br />
	 * For each Crop c to load, there should be a worksheet named "CropSuitability_XXX", where XXX is the name of Crop c. <br />
	 * If no such worksheet exists, the suitability of every (x,y) is set to 1 for that Crop.
	 */
	@Override
	public void loadCropSuitabilityMap(HashMap<ArableCropCultivation, GridValueLayer> csmap,	MainContext context) {
		if(this.avcrops.isEmpty()) throw new NullPointerException("loadCropsContext should be called before");
		
		for(ArableCropCultivation c : this.avcrops) {
			//check if worksheet exists
			String Sname = "CropSuitability_" + c.getName();
			GridValueLayer gv = new GridValueLayer(Sname, true, MainContext.getInstance().getGridWidth(),MainContext.getInstance().getGridHeight());
			
			if(this.excelWB.getSheet(Sname) != null) {
				//if yes, load suitability map
				Sheet sheet = this.excelWB.getSheet(Sname);
				Iterator<Row> rowItr = sheet.iterator(); 
				rowItr.next(); //skip first row
				
				int curY=0; 
				while(rowItr.hasNext()) {
					int curX=0;
					Row row = rowItr.next();
					Iterator<Cell> cellItr = row.cellIterator();
					cellItr.next();
					while (cellItr.hasNext()) {
		            	Cell cell = cellItr.next();
		            	if(cell.getCellType()==Cell.CELL_TYPE_NUMERIC) {
		            		double v = (double)cell.getNumericCellValue();
		            		gv.set(v, curX,curY);
		            	}
						curX++;
		            }
					curY++;
				}
				
			} //end if sheet exists
			else {
				//if no such sheet, set everything to 1
				for (int i = 0; i < MainContext.getInstance().getGridWidth(); i++) {
					for (int j = 0; j < MainContext.getInstance().getGridHeight(); j++) {
						gv.set(1, i,j);
					}
				}
			} //end if no such sheet
			csmap.put(c, gv);
			//System.err.println("ExcelDataLoader:: Printing CropSuitabilityValueLayer for Crop " + c + "\n" + ValueLayers.getValueLayerAsPrintedMatrix(gv));
			
		} //end loop for crops
		
		
	}

	/**
	 * Loads the {@link LandPropertyRegistry} of the Simulation from sheet named "LandPropertyRegistry". <br />
	 * Column 1: plot_id <br />
	 * Column 2: owner_id <br />
	 *  //TODO Currently only owners are loaded. Tenants should also be loaded. 
	 *  Furthermore it is implied that the id-number is equal to the avplot and avfarmer index.
	 * This should change in the future.
	 */
	@Override
	public void initLandPropertyRegistry(LandPropertyRegistry lpr) {
		if(this.avcrops.isEmpty()) throw new NullPointerException("loadPlotsContext should be called before");
		if(this.avplots.isEmpty())throw new NullPointerException("loadPlotsContext should be called before");
		if(this.avfarmers.isEmpty())throw new NullPointerException("loadFarmersContext should be called before");

		Sheet sh = this.excelWB.getSheet("LandPropertyRegistry");
		
		Iterator<Row> rowItr = sh.iterator(); 
		rowItr.next(); //skip first row
		while(rowItr.hasNext()) {
			Row row = rowItr.next();
			int plot_id = (int)row.getCell(0).getNumericCellValue();
			int owner_id = (int)row.getCell(1).getNumericCellValue();

			lpr.setOwnerEntry(this.avplots.get(plot_id-1), this.avfarmers.get(owner_id-1) );
		}
		
		
	}

	/**
	 * This is currently not imlemented through excel
	 */
	@Override
	public void initPaymentAuthority(PaymentAuthority pa) {
		if(this.avcrops.isEmpty()) throw new NullPointerException("loadCropsContext should be called before");
		HashMap<ArableCropCultivation, Long> coupledPayments=new HashMap<ArableCropCultivation, Long>();
		coupledPayments.put(ArableCropCultivation.getCropByName("maize"), 0l);
		coupledPayments.put(ArableCropCultivation.getCropByName("durum wheat"), 0l);
		coupledPayments.put(ArableCropCultivation.getCropByName("cotton"), 0l);
		coupledPayments.put(ArableCropCultivation.getCropByName("tobbaco"), 0l);


		
		pa.setCoupledPayments(coupledPayments);		
		
	}


	

}
