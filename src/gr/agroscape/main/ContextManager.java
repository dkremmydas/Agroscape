package gr.agroscape.main;

import gr.agroscape.contexts.CropsContext;
import gr.agroscape.contexts.FarmersContext;
import gr.agroscape.contexts.MainContext;
import gr.agroscape.contexts.PlotsContext;
import gr.agroscape.dataLoaders.DefaultDataLoader;
import gr.agroscape.dataLoaders.ISimulationDataLoader;
import repast.simphony.context.Context;
import repast.simphony.dataLoader.ContextBuilder;
import repast.simphony.engine.schedule.ScheduledMethod;

public class ContextManager implements ContextBuilder<Object> {
	
	
	private MainContext parentContext;
	

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
		this.parentContext=MainContext.getInstance();

		
		//step 2, create empty  subContexts
		PlotsContext plots = new PlotsContext(); //create plots' context
		this.parentContext.addSubContext(plots);
		//this.parentContext.add(plots);
		
		FarmersContext farmers = new FarmersContext(); //create farmers' context
		this.parentContext.addSubContext(farmers);
		//this.parentContext.add(farmers);
		
		CropsContext crops = new CropsContext(); //create crops' context
		this.parentContext.addSubContext(crops);	
		//this.parentContext.add(crops);
		
		//step 3, create dataLoader
		ISimulationDataLoader dataLoader = new DefaultDataLoader();

		dataLoader.loadCropsContext(crops);
		dataLoader.loadFarmersContext(farmers);
		dataLoader.loadPlotsContext(plots);
		dataLoader.loadCropSuitabilityMap(this.parentContext.getCropSuitability(),this.parentContext);
		dataLoader.initLandPropertyRegistry(this.parentContext.getLandPropertyRegistry());
		dataLoader.initPaymentAuthority(this.parentContext.getPaymentAuthority());
			
		
		return this.parentContext;
	}

	
	/**
	 * This is the algorithm of simulation. The following actions are taken:<br />
	 * <ol>
	 * <li>The {</li>
	 * </ol>
	 */
	@ScheduledMethod(start = 1, interval = 1)
	public void step() {
		
	}
	

	
	
	
	
	

}
