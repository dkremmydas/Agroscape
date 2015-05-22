package gr.agroscape.main;

import gr.agroscape.agents.Farmer;
import gr.agroscape.behaviors.ABehaviorContext;
import gr.agroscape.behaviors.farmers.stupido.StupidoBhvContext;
import gr.agroscape.contexts.BehaviorsContext;
import gr.agroscape.contexts.FarmersContext;
import gr.agroscape.contexts.PlotsContext;
import gr.agroscape.contexts.SimulationContext;
import gr.agroscape.dataLoaders.ExcelDataLoader;
import gr.agroscape.dataLoaders.IAgroscapeDataLoader;

import java.io.IOException;
import java.util.ArrayList;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;

import repast.simphony.context.Context;
import repast.simphony.dataLoader.ContextBuilder;
import repast.simphony.engine.environment.RunEnvironment;


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
	
	
	private SimulationContext simulationContext;
	

	/**
	 * It builds the Contexts of Agroscape. <br />
	 * The steps that this method does, are: <br />
	 * 1. Create the MainContext as the parentContext <br />
	 * 2. Create empty SubContexts and load them to parentContext <br />
	 * 3. Create {@link IAgroscapeDataLoader dataLoader} and load data
	 * 
	 */
	@Override
	public Context<Object> build(Context<Object> context) {
		
		//step 1. keep a reference		
		this.simulationContext=SimulationContext.getInstance();
		
		
		//step 2, create empty  subContexts
		PlotsContext plots = new PlotsContext(); //create plots' context
		this.simulationContext.addSubContext(plots);
		
		FarmersContext farmers = new FarmersContext(); //create farmers' context
		this.simulationContext.addSubContext(farmers);
		
		BehaviorsContext bhvContext = new BehaviorsContext();
		this.simulationContext.addSubContext(bhvContext);
		
		
		//step 3, create dataLoader
		/*
		 IAgroscapeDataLoader dataLoader = new DefaultDataLoader();
		
		dataLoader.loadFarmersContext(farmers);
		dataLoader.loadPlotsContext(plots);
		dataLoader.initLandPropertyRegistry(this.simulationContext.getLandPropertyRegistry());
		 */
		
		String excelFileLocation = RunEnvironment.getInstance().getParameters().getString("ExcelDataFile");
		IAgroscapeDataLoader dataLoader;
		try {
			dataLoader = new ExcelDataLoader(excelFileLocation);
			dataLoader.loadFarmersContext(farmers);
			dataLoader.loadPlotsContext(plots);
			dataLoader.initLandPropertyRegistry(this.simulationContext.getLandPropertyRegistry());
		} catch (InvalidFormatException | IOException e) {
			e.printStackTrace();
		}
		
		
		//dataLoader.initPaymentAuthority(this.space.getPaymentAuthority());
		
		//step 4, Attach Behavior (Stupido)
		
		ArrayList<Farmer> ff=new ArrayList<Farmer>();
		CollectionUtils.addAll(ff, farmers.getRandomObjects(Farmer.class,2));
		//bhvContext.addSubContext(new StupidoBhvContext(ff));
		bhvContext.addSubContext(new StupidoBhvContext(ff));
		
		
		
		//step 4, Attach Behavior (ArableCropFarmer_MP & ArableCropFarmer_Network)
		//ONLY FOR DEFAULT DATA LOADER
		/*
		HashMap<Class<? extends AArableCropProductionBhv>,Collection<Farmer>> arableCropFarmers=new HashMap<>();		
		ArrayList<Farmer> ff1=new ArrayList<Farmer>();
		ff1.add(farmers.findFarmerById(1));
		ff1.add(farmers.findFarmerById(2));
		ff1.add(farmers.findFarmerById(3));
		ArrayList<Farmer> ff2=new ArrayList<Farmer>();
		ff2.add(farmers.findFarmerById(4));
		ff2.add(farmers.findFarmerById(5));		
		arableCropFarmers.put(ArableCropProductionBhv_Network.class, ff2);
		arableCropFarmers.put(ArableCropProductionBhv_MP.class, ff1);
		ArableCropProductionBhvContext acpc = new ArableCropProductionBhvContext(arableCropFarmers);
		*/
		
		/*
		//FOR EXCEL DATA LOADER OF BEHAVIOR
		Workbook excelWB;
		try {
			excelWB = WorkbookFactory.create(new File(RunEnvironment.getInstance().getParameters().getString("ExcelDataFile")));
			ArableCropProductionBhvContext acpc = new ArableCropProductionBhvContext(new gr.agroscape.behaviors.farmers.production.arableCropProduction.dataLoaders.ExcelDataLoader(excelWB));
			farmers.attachBehavior(acpc);
		} catch (InvalidFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		*/


		return this.simulationContext;
	}


} //end class
