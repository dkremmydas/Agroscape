package gr.agroscape.behaviors;

import java.util.List;

import repast.simphony.engine.schedule.DefaultAction;

public interface BehaviorSchedulable {
	
	public List<DefaultAction> getScheduledActions(); 
	
}
