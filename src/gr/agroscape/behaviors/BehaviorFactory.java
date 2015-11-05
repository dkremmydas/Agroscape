package gr.agroscape.behaviors;

import gr.agroscape.skeleton.agents.AgroscapeAgent;
import gr.agroscape.skeleton.agents.AgroscapeAgentProperty;
import gr.agroscape.skeleton.contexts.SimulationContext;

import java.util.List;

import javax.media.j3d.Behavior;

import repast.simphony.engine.schedule.DefaultActionFactory;

/**
 * <p>The purposes of this class are:</p>
 * <ul>
 * <li>To create a new {@link Behavior} and assign it to a bunch of {@link AgroscapeAgent}s</li>
 * <li>To get the {@link AgroscapeAgentProperty} that are defined for various skeleton {@link AgroscapeAgent}</li>
 * <li>To get general information on the Behavior class</li>
 * </ul>
 * <p>For every {@link Behavior} class, a {@link BehaviorFactory} should be crafted, extendind the abstract methods.</p>
 * <p>It follows the Singleton design pattern, since only one Factory for every behavior can exist.</p>
 * 
 * @author Dimitris Kremmydas
 * @version %G%
 * @since 2.0
 *
 */
public abstract class BehaviorFactory {
	
	/**
	 * The name of the behavior that this factory is serving.
	 */
	protected String name;
	
	/**
	 * The factory that creates the IAction
	 */
	private DefaultActionFactory actionFactory = new DefaultActionFactory();
	
	
	protected BehaviorFactory() {
		this.name="N/A";
	}

		
	
	public DefaultActionFactory getActionFactory() {
		return actionFactory;
	}


	public String getName() {
		return name;
	}



	/**
	 * <p>It receives the root {@link SimulationContext} and caters for
	 * the assignment of {@link AgentBehavior}s and {@link AgroscapeAgentProperty}s
	 *  to {@link AgroscapeAgent}s</p>
	 * <p>The assignment should be given with a "new Behavior" created each time, and not by
	 * passing a reference to a Behavior that was created once</p>
	 * 
	 * @param {@link SimulationContext}
	 */
	abstract public void assignBehavior(SimulationContext simulationContext);
	
	/**
	 * Create the {@link BehaviorContext} and return it.
	 * @return {@link BehaviorContext}
	 */
	abstract public BehaviorContext buildBehaviorContext();
	
	/**
	 * If new SimulationAgents should be created, do it here
	 * @return
	 */
	abstract public List<AgroscapeAgent> getNewAgents();
	

	/**
	 * 
	 * @return {@link String}
	 */
	abstract public String getBehaviorInformation();



	
}
