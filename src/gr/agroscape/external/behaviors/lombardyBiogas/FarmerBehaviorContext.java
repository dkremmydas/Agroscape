package gr.agroscape.external.behaviors.lombardyBiogas;

import gr.agroscape.behaviors.BehaviorContext;
import gr.agroscape.external.classes.production.agriculturalActivities.ArableCropCultivation;

import java.util.ArrayList;

public class FarmerBehaviorContext extends BehaviorContext {
	
	/**
	 * Available Crops
	 */
	ArrayList<ArableCropCultivation> crops = new ArrayList<>();
	
	
	
	protected FarmerBehaviorContext() {
		super("LombardyBiogasContext");
	}
	



}
