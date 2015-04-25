package gr.agroscape.behaviors;

import gr.agroscape.contexts.Space;
import repast.simphony.engine.environment.RunEnvironment;
import repast.simphony.engine.schedule.ScheduledMethod;

/**
 * An interface defining operations needed to add an implemented behavior to the simulation
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
