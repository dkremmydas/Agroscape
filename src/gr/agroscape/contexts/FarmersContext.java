package gr.agroscape.contexts;

import gr.agroscape.behaviors.IScheduledBehavior;
import gr.agroscape.behaviors.IScheduledBehaviorDataLoader;
import gr.agroscape.behaviors.farmers.AFarmerBehavior;
import gr.agroscape.behaviors.farmers.production.interfaces.IHasProductionAbility;

import java.util.ArrayList;

import org.apache.commons.collections4.map.HashedMap;

import repast.simphony.context.DefaultContext;


public class FarmersContext extends DefaultContext<IHasProductionAbility> {

	protected HashedMap<String,Class<? extends AFarmerBehavior>> behaviors = new HashedMap<>();
	protected HashedMap<String,Class<? extends IScheduledBehaviorDataLoader<AFarmerBehavior>>> behaviorsLoader = new HashedMap<>();
	    

	public FarmersContext() {
		super("FarmersContext");
	}
	
	

	/**
	 * Gets an ArrayList of all {@link ICropProducer} within context
	 * @return
	 */
	public ArrayList<IHasProductionAbility> getCropProducers() {
		
		ArrayList<IHasProductionAbility> r = new ArrayList<IHasProductionAbility>();
		Iterable<IHasProductionAbility> list = this.getAgentLayer(IHasProductionAbility.class);
		for (IHasProductionAbility f : list) {
			r.add(f);
		}
		return r;
	}
	
	
	
	
	
}


/**
 * A behavior attache to a subset of agents within context
 * 
 * @author Dimitris Kremmydas
 *
 */
class ContextBehavior {
	
	private String name;
	private IScheduledBehavior<AFarmerBehavior> behavior;
	private IScheduledBehaviorDataLoader<AFarmerBehavior> dataLoader;
}
