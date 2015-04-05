package gr.agroscape.dataLoaders;

import gr.agroscape.agents.Farmer;
import gr.agroscape.agents.Farmer_MP;
import gr.agroscape.agents.Plot;
import gr.agroscape.authorities.LandPropertyRegistry;
import gr.agroscape.authorities.PaymentAuthority;
import gr.agroscape.contexts.CropsContext;
import gr.agroscape.contexts.FarmersContext;
import gr.agroscape.contexts.MainContext;
import gr.agroscape.contexts.PlotsContext;
import gr.agroscape.crops.Crop;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

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
public abstract class ExcelDataLoader implements ISimulationDataLoader {
	
	private XSSFWorkbook excelWB; 
	
	private ArrayList<Plot> avplots = new ArrayList<Plot>();
	private ArrayList<Farmer> avfarmers = new ArrayList<Farmer>();
	private ArrayList<Crop> avcrops = new ArrayList<Crop>();
	
	
	/**
	 * Constructor where the excel file is defined. The excel file must be in 2007 format.
	 * <br />Take care of problems with the excel file.
	 * @param excel_location
	 * @throws IOException 
	 * @throws InvalidFormatException 
	 */
	public ExcelDataLoader(String excel_location) throws InvalidFormatException, IOException {
		super();
		Workbook wb = WorkbookFactory.create(new File(excel_location));
		if(! this.isXLSX()) throw new IllegalArgumentException("Only 2007 excel format is allowed.");
		this.excelWB = (XSSFWorkbook) wb;
	}
	
	private boolean isXLSX() {
		if(this.excelWB instanceof XSSFWorkbook) return true;
		else return false;
	}

	@Override
	public void loadCropsContext(CropsContext context) {
		// TODO Auto-ge nerated method stub
		
	}

	@Override
	public void loadPlotsContext(PlotsContext context) {
		// TODO Auto-generated method stub
		
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
		
		XSSFSheet sh = this.excelWB.getSheet("Agents");
		
		Iterator<Row> rowItr = sh.iterator(); 
		rowItr.next(); //skip first row
		while(rowItr.hasNext()) {
			Row row = rowItr.next();
			int agent_id = (int)row.getCell(0).getNumericCellValue();
			int isFarmer = (int)row.getCell(1).getNumericCellValue();
			long liquidity = (long)row.getCell(2).getNumericCellValue();
			
			if(isFarmer==1) {context.add(new Farmer_MP(liquidity,this.avcrops,agent_id));}
		}
	}

	@Override
	public void loadCropSuitabilityMap(HashMap<Crop, GridValueLayer> csmap,
			MainContext context) {
		if(this.avcrops.isEmpty()) throw new NullPointerException("loadCropsContext should be called before");
		// TODO Auto-generated method stub
		
	}

	@Override
	public void initLandPropertyRegistry(LandPropertyRegistry lpr) {
		if(this.avcrops.isEmpty()) throw new NullPointerException("loadPlotsContext should be called before");
		if(this.avplots.isEmpty())throw new NullPointerException("loadPlotsContext should be called before");
		if(this.avfarmers.isEmpty())throw new NullPointerException("loadFarmersContext should be called before");
		// TODO Auto-generated method stub
		
	}

	@Override
	public void initPaymentAuthority(PaymentAuthority pa) {
		if(this.avcrops.isEmpty()) throw new NullPointerException("loadCropsContext should be called before");
		// TODO Auto-generated method stub
		
	}


	

}
