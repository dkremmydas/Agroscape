package gr.agroscape.main;

import gr.agroscape.dataLoaders.AgroscapeSkeletonDataLoader;
import gr.agroscape.skeleton.contexts.FarmersContext;
import gr.agroscape.skeleton.contexts.PlotsContext;
import gr.agroscape.skeleton.contexts.SimulationContext;
import repast.simphony.context.Context;
import repast.simphony.dataLoader.ContextBuilder;


/**
 * This is the "main" class. The purpose is to:
 * <ul>
 * <li>create the empty {@link SimulationContext}</li>
 * <li>create the empty {@link FarmersContext}</li> 
 * <li>create the empty {@link PlotsContext}</li> 
 * </ul> 
 *
 * @author Dimitris Kremmydas
 * @version %I%
 *
 */
public class ContextManager implements ContextBuilder<Object> {
	
	
	private SimulationContext simulationContext;
	

	/**
	 * It builds the Contexts of Agroscape. <br />
	 * The steps that this method does, are: <br />
	 * 1. Create the MainContext as the parentContext <br />
	 * 2. Create empty SubContexts and load them to parentContext <br />
	 * 3. Create {@link AgroscapeSkeletonDataLoader dataLoader} and load data
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
		
		
		return this.simulationContext;
	}


} //end class
