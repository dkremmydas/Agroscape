package gr.agroscape.behaviors.space;

import gr.agroscape.agents.Farmer;
import gr.agroscape.behaviors.IScheduledBehavior;
import gr.agroscape.contexts.Space;

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
	protected Space owner;
	

	/**
	 * Constructor
	 * @param owner
	 */
	public ASpaceBehavior(Space owner) {
		super();
		this.owner = owner;
	}

	
	
	
}
