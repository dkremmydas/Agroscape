package gr.agroscape.behaviors.farmers;

import gr.agroscape.agents.human.Farmer;
import gr.agroscape.behaviors.ABehavior;


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
public abstract class AFarmerBehavior extends ABehavior<Farmer> {

	public AFarmerBehavior(Farmer owner) {
		super(owner);
	}
	
	

}
