package gr.agroscape.dataLoaders;

import gr.agroscape.agents.human.Farmer;
import gr.agroscape.agents.plot.Plot;
import gr.agroscape.authorities.LandPropertyRegistry;
import gr.agroscape.contexts.FarmersContext;
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

import repast.simphony.context.space.graph.NetworkBuilder;
import repast.simphony.space.graph.Network;
import repast.simphony.space.grid.GridPoint;
/**
 * This class loads agents and initial data from an excel file. <br />
 * The Excel file should contain the following worksheets:
 * <ul>
 * <li>"Farmers", where farmers are defined. See {@link #loadFarmersContext}</li>
 * <li>"Plots", where in a graphically way the plots are defined. See {@link #loadPlotsContext}</li>
 * </ul>
 * 
 * @author Dimitris Kremmydas
 *
 */
public class ExcelDataLoader implements AgroscapeSkeletonDataLoader {

	private Workbook excelWB; 
	
	private ArrayList<Plot> avplots = new ArrayList<Plot>();
	private ArrayList<Farmer> avfarmers = new ArrayList<Farmer>();
	
	/**
	 * Constructor
	 * @param excel_location
	 * @throws InvalidFormatException
	 * @throws IOException
	 */
	public ExcelDataLoader(String excel_location) throws InvalidFormatException, IOException {
		super();
		Workbook wb = WorkbookFactory.create(new File(excel_location));
		this.excelWB=wb;
	}
	
	
	@Override
	/**
	 * Loads the Plots. <br />
	 * The Excel sheet is a representation of the grid, where the first row is the X-coordinate
	 * and the first column is the Y-coordinate. Inside this matrix the number f the x-y coordinates
	 * is the plot_id.
	 */
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

	
	@Override
	/**
	 * Load Farmers with their id
	 */
	public void loadFarmersContext(FarmersContext context) {
		
		//load farmer agents
		Sheet sh = this.excelWB.getSheet("Farmers");
		
		Iterator<Row> rowItr = sh.iterator(); 
		rowItr.next(); //skip first row
		while(rowItr.hasNext()) {
			Row row = rowItr.next();
			int agent_id = (int)row.getCell(0).getNumericCellValue();
			int isFarmer = (int)row.getCell(1).getNumericCellValue();			
			if(isFarmer==1) {this.avfarmers.add(new Farmer(agent_id));}
		}
		context.addAll(this.avfarmers);		
		
		//load production Network
		this.loadProductionNetwork(context);
		
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
	 * Load "productionNetwork" network. Found in "productionNetwork" sheet.
	 * @param context
	 */
	private void loadProductionNetwork(FarmersContext context) {
		
		//load excel
		Sheet sh = this.excelWB.getSheet("productionNetwork");

		//create network
		NetworkBuilder<Farmer> builder = new NetworkBuilder<Farmer>("productionNetwork", context, true);
		Network<Farmer> network = builder.buildNetwork();
		
		//add edges from sheet
		Iterator<Row> rowItr = sh.iterator(); 
		rowItr.next(); //skip first row
		while(rowItr.hasNext()) {
			Row row = rowItr.next();
			int leader_id = (int)row.getCell(0).getNumericCellValue();
			int follower_id = (int)row.getCell(1).getNumericCellValue();			
			Farmer leader_f = context.findFarmerById(leader_id);
			Farmer follower_f = context.findFarmerById(follower_id);
			network.addEdge(follower_f, leader_f);
		}
		context.addNetwork(network);
	}

}
