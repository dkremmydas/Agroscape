package gr.agroscape.behaviors.farmers;

import gr.agroscape.agents.Farmer;
import gr.agroscape.behaviors.IScheduledBehavior;

/**
 * Every "Farmer Behavior" object contains a reference to the "Farmer" object that contains it.<br />
 * Bhv is a shortcut for "Behavior". So all classes that end with "Bhv" are actually extending 
 * {@link AFarmerBehavior}.
 * 
 *   //TODO int he FarmersContext, have a Map of Farmer->ArrayList<ABehavior>
 *   
 * 
 * @author Dimitris Kremmydas
 *
 * @param <T>
 */
public abstract class AFarmerBehavior<T> implements IScheduledBehavior<T> {
	
	/**
	 * A reference to the owner Farmer
	 */
	protected Farmer owner;	
	

	/**
	 * Constructor
	 * @param owner
	 */
	public AFarmerBehavior(Farmer owner) {
		this.owner = owner;
	}


	/**
	 * Getter
	 * @return
	 */
	public Farmer getOwner() {
		return owner;
	}

}
