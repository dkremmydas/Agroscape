package gr.agroscape.behaviors.farmers;

import org.apache.commons.collections4.map.HashedMap;

import gr.agroscape.agents.Farmer;
import gr.agroscape.behaviors.ScheduledBehavior;

/**
 * An abstract class defining basic properties for an implementation of a FarmerBehavior 
 * 
 * @author Dimitris Kremmydas
 *
 */
public abstract class AFarmerBehavior implements ScheduledBehavior {

	/**
	 * The owner of the behavior
	 */
	protected Farmer owner;
	
	protected HashedMap<String,Object> owner_properties;

	/**
	 * Constructor
	 * @param owner
	 */
	public AFarmerBehavior(Farmer owner) {
		super();
		this.owner = owner;
		owner_properties = this.owner.getProperties();
	}

	
	
	
}
