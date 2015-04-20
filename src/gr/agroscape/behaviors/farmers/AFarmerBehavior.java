package gr.agroscape.behaviors.farmers;

import gr.agroscape.agents.Farmer;
import gr.agroscape.behaviors.IScheduledBehavior;

/**
 * An abstract class defining basic properties for an implementation of a FarmerBehavior. <br />
 * A {@link AFarmerBehavior} is applied to individual agent. This is the reason that the
 * "owner" agent is included in this class.
 * 
 * @author Dimitris Kremmydas
 *
 */
public abstract class AFarmerBehavior implements IScheduledBehavior<Farmer> {

	/**
	 * The owner of the behavior
	 */
	protected Farmer owner;
	

	/**
	 * Constructor
	 * @param owner
	 */
	public AFarmerBehavior(Farmer owner) {
		super();
		this.owner = owner;
	}

	
	
	
}
