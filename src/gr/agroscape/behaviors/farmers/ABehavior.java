package gr.agroscape.behaviors.farmers;

import gr.agroscape.agents.Farmer;
import gr.agroscape.behaviors.IScheduledBehavior;

/**
 * Every "Behavior" object contains a reference to the "Farmer" object that contains it.<br />
 * In this way, the Farmer does not need to know anything about the behaviors that he owns,
 * but the {@link ABehavior} objects know for him, and so have access to his attributes.<br />
 * Bhv is a shortcut for "Behavior". So all classes that end with "Bhv" are actually extending 
 * {@link ABehavior}.
 * 
 *   //TODO int he FarmersContext, have a Map of Farmer->ArrayList<ABehavior>
 *   
 * 
 * @author Dimitris Kremmydas
 *
 * @param <T>
 */
public abstract class ABehavior<T> implements IScheduledBehavior<T> {
	
	/**
	 * A reference to the owner Farmer
	 */
	protected Farmer owner;	
	

	/**
	 * Constructor
	 * @param owner
	 */
	public ABehavior(Farmer owner) {
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
