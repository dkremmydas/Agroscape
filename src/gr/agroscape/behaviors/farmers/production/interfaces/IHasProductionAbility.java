package gr.agroscape.behaviors.farmers.production.interfaces;

import gr.agroscape.agents.Plot;
import gr.agroscape.behaviors.farmers.production.agriculturalActivities.ArableCropCultivation;

import java.util.Collection;

/**
 * The contract that someone that cultivates Crops should fulfill. <br />
 * Basically he should provide an implementation that return a map of {@link Plot}->{@link ArableCropCultivation}, 
 * i.e. deciding for each Plot he will cultivate, what Crop this will be.
 * 
 * @author Dimitris Kremmydas *
 */
public interface IHasProductionAbility {
	
	/**
	 * 
	 * @return
	 */
	Collection<AProductionDecision> makeProductionDecision(Collection<Plot> plots);
	
}
