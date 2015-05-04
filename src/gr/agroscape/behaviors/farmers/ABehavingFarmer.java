package gr.agroscape.behaviors.farmers;

import gr.agroscape.agents.Farmer;
import gr.agroscape.behaviors.IScheduledBehavior;

public abstract class ABehavingFarmer<T extends Farmer> extends Farmer implements IScheduledBehavior<T> {
	
	protected Farmer owner;	
	
	@SuppressWarnings("unchecked")
	public ABehavingFarmer(Farmer owner) {
		this.owner = owner;
	}
	
	

}
