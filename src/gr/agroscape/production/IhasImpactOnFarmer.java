package gr.agroscape.production;

import gr.agroscape.agents.Farmer;

/**
 * Any class that affects a farmer should respect this interface
 * @author Dimitris Kremmydas
 *
 */
public interface IhasImpactOnFarmer {

	
	/**
	 * How does a farmer is affected
	 * @param producers
	 */
	public void feedbackToFarmer(Farmer producers);
	
}
