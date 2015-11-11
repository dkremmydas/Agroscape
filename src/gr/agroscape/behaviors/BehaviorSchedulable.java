package gr.agroscape.behaviors;

import java.util.List;

import repast.simphony.engine.schedule.DefaultAction;
import repast.simphony.engine.schedule.ScheduleParameters;

/**
 * The classes that implement this interface are able to insert schedulable
 * actions to the simulation timeline
 * 
 * @author Dimitris Kremmydas
 * @version %I%
 * @since 2.0
 *
 */
public interface BehaviorSchedulable {
	
	/**
	 * <p>the {@link ScheduleParameters param} could be created with the following code:</p>
	 * <pre>
	 * ScheduleParameters  params = ScheduleParameters.*;
	 * </pre>
	 * where * are various methods for sheduling an action. More can be found at 
	 * {@link ScheduleParameters} class.
	 * @return List of {@link DefaultAction}
	 */
	public List<BehaviorAction> getScheduledActions(); 
	
}
