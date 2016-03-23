package gr.agroscape.external.behaviors.arableCropProduction.properties;

import gr.agroscape.external.classes.production.agriculturalActivities.ArableCropCultivation;
import gr.agroscape.skeleton.agents.AgroscapeAgentProperty;

public class CurrentArableCropActivityProperty extends AgroscapeAgentProperty<ArableCropCultivation> {

	public CurrentArableCropActivityProperty() {
		super(ArableCropCultivation.class, "CurrentArableCropActivity", null);
	}
	
	public CurrentArableCropActivityProperty(ArableCropCultivation value) {
		super(ArableCropCultivation.class, "CurrentArableCropActivity", value);
	}

}
