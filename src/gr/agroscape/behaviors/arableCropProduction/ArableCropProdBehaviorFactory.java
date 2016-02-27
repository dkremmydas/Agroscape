package gr.agroscape.behaviors.arableCropProduction;

import gr.agroscape.behaviors.AgentBehavior;
import gr.agroscape.behaviors.BehaviorContext;
import gr.agroscape.behaviors.BehaviorFactory;
import gr.agroscape.behaviors.arableCropProduction.agentProperties.CurrentArableCropActivityProperty;
import gr.agroscape.production.agriculturalActivities.ArableCropCultivation;
import gr.agroscape.production.products.Product;
import gr.agroscape.skeleton.agents.AgroscapeAgent;
import gr.agroscape.skeleton.agents.human.Farmer;
import gr.agroscape.skeleton.agents.plot.Plot;
import gr.agroscape.skeleton.contexts.SimulationContext;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.log4j.Level;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

import repast.simphony.engine.environment.RunEnvironment;

public class ArableCropProdBehaviorFactory extends BehaviorFactory {
	
	private BehaviorContext bvhContext;
	
	public ArableCropProdBehaviorFactory() {
		super();
		this.name = "ArableCropProductionBehavior";
		
	}

	@Override
	public void assignBehaviors(SimulationContext simulationContext) {
		//1. assign to all farmers
		for (Iterator<Farmer> farmers = simulationContext.getFarmersContext().getAllFarmers().iterator(); farmers.hasNext();) {
			Farmer farmer =  farmers.next();
			AgentBehavior ab = new ArableCropProdBehavior(this,farmer, this.bvhContext) ;
			farmer.addBehavior(ab);
		}	
	}

	@Override
	public void addProperties(SimulationContext simulationContext) {
		
		//1. add currentAgricultularActivity to all Plots
		for (Iterator<Plot> plots = simulationContext.getPlotsContext().getAvailablePlots().iterator(); plots.hasNext();) {
			Plot plot =  plots.next();
			plot.addBehaviorProperty(new CurrentArableCropActivityProperty());
		}	
		
	} //end class
	
	/**
	 * Load the BehaviorContext from excel file that is defined in scenario property "ExcelDataFile"
	 */
	@Override
	public BehaviorContext getBehaviorContext() {
		//load available crops
		String excelLocation =  RunEnvironment.getInstance().getParameters().getString("ExcelDataFile");
		try {
			this.bvhContext = new ArableCropProdBehaviorContext(this.loadCrops(excelLocation));
		} catch (InvalidFormatException | IOException e) {
			e.printStackTrace();
			System.err.println("Could not load Behavior Context");
			System.exit(1);
		}
		return this.bvhContext;	
	}
	
	private ArrayList<ArableCropCultivation> loadCrops(String excelLocation) throws InvalidFormatException, IOException {
		ArrayList<ArableCropCultivation> crops = new ArrayList<>();
		
		Workbook wb = WorkbookFactory.create(new File(excelLocation));
		Sheet sh =wb.getSheet("Crops");
		Iterator<Row> rowItr = sh.iterator(); 
		rowItr.next(); //skip first row
		while(rowItr.hasNext()) {
			Row row = rowItr.next();
			int crop_id = (int)row.getCell(0).getNumericCellValue();
			String crop_name = row.getCell(1).getStringCellValue();			
			crops.add(new ArableCropCultivation(crop_name, crop_id, new ArrayList<Product>()));
		}	
		SimulationContext.logMessage(this.getClass(), Level.INFO, "Loaded Crops:"+crops.toString());
		return crops;		
	}
	
	@Override
	public List<AgroscapeAgent> getNewAgents() {
		return null; //no new agents
	}
	
	@Override
	public String getBehaviorInformation() {
		return this.name + "\n Version 1. \n Author Dimitris Kremmydas";
	}


}
