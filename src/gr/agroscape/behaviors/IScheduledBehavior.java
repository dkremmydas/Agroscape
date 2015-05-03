package gr.agroscape.behaviors;

import repast.simphony.engine.environment.RunEnvironment;
import repast.simphony.engine.schedule.ScheduledMethod;

/**
 * An interface defining operations needed to add an implemented behavior to the simulation. <br />
 * The only operation needed is to return the object that has annotated ScheduleMethod. This object
 * scheduled actions is parsed by the Repast Simphony RunEnvironment Scheduler and the required actions
 * of the behavior are loaded.
 * 
 * @author jkr
 *
 */
public interface IScheduledBehavior<T> {
	
	
	/**
	 * Returns the class that have {@link ScheduledMethod} Annotations and will be added
	 * accordingly to the {@link RunEnvironment} schedule.
	 * @return
	 */
	Object getAnnotatedClass();

}
