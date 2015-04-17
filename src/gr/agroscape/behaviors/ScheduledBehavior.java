package gr.agroscape.behaviors;

import repast.simphony.engine.environment.RunEnvironment;
import repast.simphony.engine.schedule.ScheduledMethod;

/**
 * An interface defining operations needed to add an implemented behavior to the simulation
 * @author jkr
 *
 */
public interface ScheduledBehavior {
	
	/**
	 * Make any necessary actions to setup owner of behavior (e.g. add properties). <br />
	 * A pre-condition is that there is an owner field set in the implementing class.
	 * 
	 */
	void setUpOwner(); 
	
	/**
	 * Returns the class that have {@link ScheduledMethod} Annotations and will be added
	 * accordingly to the {@link RunEnvironment} schedule.
	 * @return
	 */
	Object getAnnotatedClass();

}
