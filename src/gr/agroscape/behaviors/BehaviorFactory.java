package gr.agroscape.behaviors;

import gr.agroscape.agents.AgroscapeAgent;
import gr.agroscape.agents.AgroscapeAgentProperty;

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
 * @author Dimitris Kremmydas
 * @version %G%
 *
 */
public abstract class BehaviorFactory {
	
	private String name;
	
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
	 * <p>It receives a list of {@link AgroscapeAgent}s and assign to them a {@link AgentBehavior}</p>
	 * <p>The assignment should be given with a "new Behavior" created each time, and not by
	 * passing a reference to a Behavior that was created once</p>
	 * 
	 * @param List of {@link AgroscapeAgent}. It is the reference to the agents that 
	 * a {@link AgentBehavior} will be assigned 
	 */
	abstract void assignBehavior(List<AgroscapeAgent> agents);
	
	/**
	 * 
	 * @return {@link BehaviorContext}
	 */
	abstract BehaviorContext getBehvaviorContext();
	
	
	abstract List<AgroscapeAgent> getNewAgents();

	/**
	 * 
	 * @return {@link String}
	 */
	abstract String getBehaviorInformation();
	
}
