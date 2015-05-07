package gr.agroscape.behaviors.space;

import gr.agroscape.behaviors.IScheduledBehavior;
import gr.agroscape.contexts.SimulationContext;

/**
 * An abstract class defining basic properties for an implementation of a SpaceBehavior 
 * 
 * @author Dimitris Kremmydas
 *
 */
public abstract class ASpaceBehavior implements IScheduledBehavior {

	/**
	 * The owner of the behavior
	 */
	protected SimulationContext owner;
	

	/**
	 * Constructor
	 * @param owner
	 */
	public ASpaceBehavior(SimulationContext owner) {
		super();
		this.owner = owner;
	}

	
	
	
}
