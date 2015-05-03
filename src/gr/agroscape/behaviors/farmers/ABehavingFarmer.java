package gr.agroscape.behaviors.farmers;

import gr.agroscape.agents.Farmer;
import gr.agroscape.behaviors.IScheduledBehavior;


/**
 * An abstract class defining the core operation that a {@link ABehavingFarmer}
 * belongs to an Owner object.
 * 
 * @author Dimitris Kremmydas
 * @param <T>
 *
 */
public abstract class ABehavingFarmer extends Farmer implements IScheduledBehavior<Farmer> {

	/**
	 * The owner of the behavior
	 */
	protected Farmer owner;
	

	/**
	 * Constructor
	 * @param owner
	 */
	public ABehavingFarmer(Farmer owner) {
		super();
		this.owner = owner;
	}

	
	
	
}
