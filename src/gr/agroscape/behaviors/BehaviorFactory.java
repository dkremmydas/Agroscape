package gr.agroscape.behaviors;

import gr.agroscape.skeleton.agents.AgroscapeAgent;
import gr.agroscape.skeleton.agents.AgroscapeAgentProperty;
import gr.agroscape.skeleton.contexts.SimulationContext;

import java.util.List;

import javax.media.j3d.Behavior;

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
	private String name;
	
	protected BehaviorFactory() {
		this.name="N/A";
	}
	

	public String getName() {
		return name;
	}
	
	public final void setName(String name) {
		this.name=name;
	}



	/**
	 * <p>It receives the root {@link SimulationContext} and creates the {@link AgentBehavior}
	 * objects</p>
	 * 
	 * @param {@link SimulationContext}
	 * @return Iterable<? extends {@link AgentBehavior}> An iterable of the behavior objects
	 */
	abstract public Iterable<? extends AgentBehavior> getBehaviorObjects(SimulationContext simulationContext);
	
	/**
	 * <p>Its purpose is to add {@link AgroscapeAgentProperty}s to {@link AgroscapeAgent}s
	 * </p>
	 *
	 * @return HashMap<Iterator<AgroscapeAgent>,AgroscapeAgentProperty<?>>
	 */
	abstract public void addProperties(SimulationContext simulationContext);
	
	/**
	 * Create the {@link BehaviorContext} and return it.
	 * @return {@link BehaviorContext}
	 */
	abstract public BehaviorContext getBehaviorContext();
	
	/**
	 * If new SimulationAgents should be created, do it here. They are added in the behaviors context
	 * @return
	 */
	abstract public List<AgroscapeAgent> getNewAgents();
	

	/**
	 * 
	 * @return {@link String}
	 */
	abstract public String getBehaviorInformation();



	
}
