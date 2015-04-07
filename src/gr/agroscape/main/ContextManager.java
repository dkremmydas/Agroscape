package gr.agroscape.main;

import java.io.IOException;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;

import gr.agroscape.agents.Farmer;
import gr.agroscape.contexts.CropsContext;
import gr.agroscape.contexts.FarmersContext;
import gr.agroscape.contexts.MainContext;
import gr.agroscape.contexts.PlotsContext;
import gr.agroscape.crops.Crop;
import gr.agroscape.dataLoaders.DefaultDataLoader;
import gr.agroscape.dataLoaders.ExcelDataLoader;
import gr.agroscape.dataLoaders.ISimulationDataLoader;
import repast.simphony.context.Context;
import repast.simphony.dataLoader.ContextBuilder;
import repast.simphony.engine.environment.RunEnvironment;
import repast.simphony.engine.schedule.ScheduleParameters;


/**
 * This is the "main" class. 
 * <br />Everything is loaded here through Overriding {@link #build()} method.
 * This is directly related to the definition of Agroscape.rs/
 * <br />Also the workflow of the AgroScape simulation is defined here, in the {@link #step()} method.
 * 
 * @author Dimitris Kremmydas
 *
 */
public class ContextManager implements ContextBuilder<Object> {
	
	
	private MainContext mainContext;
	

	/**
	 * It builds the Contexts of Agroscape. <br />
	 * The steps that this method does, are: <br />
	 * 1. Create the MainContext as the parentContext <br />
	 * 2. Create empty SubContexts and load them to parentContext <br />
	 * 3. Create {@link ISimulationDataLoader dataLoader} and load data
	 * 
	 */
	@Override
	public Context<Object> build(Context<Object> context) {
		
		//step 1. keep a reference		
		this.mainContext=MainContext.getInstance();
		
		

		
		//step 2, create empty  subContexts
		PlotsContext plots = new PlotsContext(); //create plots' context
		this.mainContext.addSubContext(plots);
		//this.parentContext.add(plots);
		
		FarmersContext farmers = new FarmersContext(); //create farmers' context
		this.mainContext.addSubContext(farmers);
		//this.parentContext.add(farmers);
		
		CropsContext crops = new CropsContext(); //create crops' context
		this.mainContext.addSubContext(crops);	
		//this.parentContext.add(crops);
		
		
		//step 3, create dataLoader
		//ISimulationDataLoader dataLoader = new DefaultDataLoader();
		ISimulationDataLoader dataLoader;
		try {
			String excelFileLocation = RunEnvironment.getInstance().getParameters().getString("ExcelDataFile");
			dataLoader = new ExcelDataLoader(excelFileLocation);
			dataLoader.loadCropsContext(crops);
			dataLoader.loadFarmersContext(farmers);
			dataLoader.loadPlotsContext(plots);
			dataLoader.loadCropSuitabilityMap(this.mainContext.getCropSuitability(),this.mainContext);
			dataLoader.initLandPropertyRegistry(this.mainContext.getLandPropertyRegistry());
			dataLoader.initPaymentAuthority(this.mainContext.getPaymentAuthority());
			
		} catch (InvalidFormatException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		//step 1.5 add schedule methods
		ScheduleParameters params = ScheduleParameters.createRepeating(1, 1) ;
		RunEnvironment.getInstance().getCurrentSchedule().schedule (params , this , "step");
		
		
		//step 4
		this.mainContext.setActiveDisplaySuitabilityCrop(Crop.getCropByName("maize"));
		
		
		
		System.err.println("Everything is Loaded");
		
		return this.mainContext;
	}

	
	/**
	 * This is the algorithm of simulation. The following actions are taken:<br />
	 * <ol>
	 * <li><strong>Production stage: </strong>For all {@link Farmer} objects, decide the production</li>
	 * </ol>
	 */
	public void step() {
		System.err.println("Do Step");
		//1. Production Stage
		Iterable<Farmer> fi = this.mainContext.getFarmersContext().getRandomObjects(Farmer.class, this.mainContext.getFarmersContext().size());
		for (Farmer f : fi) {
			this.mainContext.handleProductionDecision(f, f.makeProductionDecision());
			System.err.println(f.toString() + " | ProductionDecision: " + f.getThisStepProduction());
		}
		this.mainContext.updateValueLayers();
		
		System.err.println(this.mainContext.get_gvlProductionDecisions().toString());
		
		
		//System.err.println(this.mainContext.get_gvlProductionDecisions().toString());
	}
	

	
	
	
	
	

}
