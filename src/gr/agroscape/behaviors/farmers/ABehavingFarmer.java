package gr.agroscape.behaviors.farmers;

import gr.agroscape.agents.Farmer;
import gr.agroscape.behaviors.IScheduledBehavior;

public abstract class ABehavingFarmer<T extends Farmer> extends Farmer implements IScheduledBehavior<T> {
	
	/**
	 * A reference to the owner Farmer
	 */
	protected Farmer owner;	
	

	public ABehavingFarmer(Farmer owner) {
		this.owner = owner;
	}
	
	

}
