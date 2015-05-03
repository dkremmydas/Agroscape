package gr.agroscape.contexts;

import gr.agroscape.agents.Farmer;
import gr.agroscape.behaviors.ABehaviorContainer;

import java.nio.file.Path;
import java.util.Collection;

import org.apache.commons.collections4.map.HashedMap;

import repast.simphony.context.DefaultContext;


public class FarmersContext extends DefaultContext<Farmer> {

	private Space space;
	
	private HashedMap<String,ABehaviorContainer<? extends Farmer>> behaviors = 
			new HashedMap<>();
	
	
	    

	public FarmersContext() {
		super("FarmersContext");
		this.space = Space.getInstance();
	}
	
	
	public void attachBehavior(ABehaviorContainer<? extends Farmer> behaviorContainer) {
		this.behaviors.put(behaviorContainer.getName(),behaviorContainer);
	}
	
	
	//Initialize behavior
	public void initializeBehavior(ABehaviorContainer<? extends Farmer> behavior, Collection<Farmer> farmers,Path dataFile) {
		this.initializeBehavior(behavior.getName(), farmers, dataFile);
	}
	
	public void initializeBehavior(String behaviorName, Collection<Farmer> farmers,Path dataFile) {
		this.behaviors.get(behaviorName).loadBehavingObjects(farmers, dataFile, this.space);
	}

	public ABehaviorContainer<? extends Farmer> getBehavior(String name) {
		return this.behaviors.get(name);
	}

	
}
	
	
	
	
