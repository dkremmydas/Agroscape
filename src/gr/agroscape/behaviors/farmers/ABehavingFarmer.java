package gr.agroscape.behaviors.farmers;

import gr.agroscape.agents.Farmer;
import gr.agroscape.behaviors.IScheduledBehavior;

/**
 * Every "Behaving Farmer" object contains a reference to the "Farmer" object that holds the 
 * 
 * @author Dimitris Kremmydas
 *
 * @param <T>
 */
public abstract class ABehavingFarmer<T extends Farmer> extends Farmer implements IScheduledBehavior<T> {
	
	/**
	 * A reference to the owner Farmer
	 */
	protected Farmer owner;	
	

	/**
	 * Constructor
	 * @param owner
	 */
	public ABehavingFarmer(Farmer owner) {
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
