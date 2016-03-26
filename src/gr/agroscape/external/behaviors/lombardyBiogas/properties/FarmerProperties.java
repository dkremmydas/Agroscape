package gr.agroscape.external.behaviors.lombardyBiogas.properties;

import gr.agroscape.external.classes.production.agriculturalActivities.ArableCropCultivation;

import java.util.HashMap;

public class FarmerProperties {
	
	/**
	 * The yields for each {@link ArableCropCultivation}
	 */
	private HashMap<ArableCropCultivation,Float> yields = new HashMap<>();
	
	
	/**
	 * The variable costs for each {@link ArableCropCultivation}
	 */
	private HashMap<ArableCropCultivation,Float> varCosts = new HashMap<>();
	
	

}
