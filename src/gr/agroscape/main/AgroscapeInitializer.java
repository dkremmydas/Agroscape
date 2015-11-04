package gr.agroscape.main;

import gr.agroscape.behaviors.AgentBehavior;
import gr.agroscape.dataLoaders.AgroscapeAllBehaviorsDataLoader;
import gr.agroscape.dataLoaders.AgroscapeSkeletonDataLoader;
import gr.agroscape.skeleton.agents.AgroscapeAgent;
import gr.agroscape.skeleton.contexts.FarmersContext;
import gr.agroscape.skeleton.contexts.PlotsContext;
import gr.agroscape.skeleton.contexts.SimulationContext;

import java.util.ArrayList;

import repast.simphony.context.Context;
import repast.simphony.dataLoader.ContextBuilder;
import repast.simphony.engine.environment.RunEnvironment;
import repast.simphony.engine.schedule.DefaultAction;
import repast.simphony.engine.schedule.ISchedule;


/**
 * This is the "main" class. The purpose is to:
 * <ul>
 * <li>create the empty {@link SimulationContext}</li>
 * <li>create the empty {@link FarmersContext}</li> 
 * <li>create the empty {@link PlotsContext}</li> 
 * </ul> 
 *
 * @author Dimitris Kremmydas
 * @version $Revision$
 * @since 2.0
 *
 */
public class AgroscapeInitializer implements ContextBuilder<Object> {
	
	
	private SimulationContext simulationContext;
	
	private AgroscapeSkeletonDataLoader skeletonDataLoader;
	
	private AgroscapeAllBehaviorsDataLoader behaviorsDataLoader;

	
	
	
	public AgroscapeInitializer(AgroscapeSkeletonDataLoader dataLoader, 
									AgroscapeAllBehaviorsDataLoader behaviorLoader) {
		super();
		this.skeletonDataLoader = dataLoader;
		this.behaviorsDataLoader = behaviorLoader;
	}



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
		this.skeletonDataLoader.loadPlotsContext(plots);
		this.simulationContext.addSubContext(plots);
		
		FarmersContext farmers = new FarmersContext(); //create farmers' context
		this.skeletonDataLoader.loadFarmersContext(farmers);
		this.simulationContext.addSubContext(farmers);
		
		this.skeletonDataLoader.initLandPropertyRegistry(this.simulationContext.getLandPropertyRegistry());
		
		this.behaviorsDataLoader.loadAllBehaviors(this.simulationContext);
		
		//add behaviors to schedule for each agent
		this.addAgroscapeAgentsBehaviorToSchedule(farmers.getAllFarmers());
		
		
		return this.simulationContext;
	}
	
	
	private void addAgroscapeAgentsBehaviorToSchedule(Iterable<? extends AgroscapeAgent> agents) {
		ISchedule timeline = RunEnvironment.getInstance().getCurrentSchedule();
		
		for (AgroscapeAgent ag : agents) {
			ArrayList<AgentBehavior> bhvs = (ArrayList<AgentBehavior>) ag.getBehaviors();
			for (AgentBehavior ab : bhvs) {
				timeline.schedule(ab);
				//ArrayList<DefaultAction> actions =  (ArrayList<DefaultAction>) ab.getScheduledActions();
				//for (DefaultAction ac : actions) {
				//	//System.err.println(ac.);
				//	timeline.schedule(ac);
				//}
			}
		}
	}


} //end class