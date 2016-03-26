package gr.agroscape.external.behaviors.lombardyBiogas;

import gr.agroscape.behaviors.BehaviorContext;
import gr.agroscape.external.behaviors.lombardyBiogas.classes.Municipalities;
import gr.agroscape.external.classes.production.agriculturalActivities.ArableCropCultivation;
import gr.agroscape.skeleton.agents.human.Farmer;

import java.util.ArrayList;

import org.apache.commons.collections4.multimap.ArrayListValuedHashMap;

public class FarmerBehaviorContext extends BehaviorContext {
	
	/**
	 * Available Crops
	 */
	ArrayList<ArableCropCultivation> crops = new ArrayList<>();
	
	/**
	 * Map of municipalities to Farmers
	 */
	ArrayListValuedHashMap<Municipalities,Farmer> munToFarmers = new ArrayListValuedHashMap<Municipalities, Farmer>();
	
	/**
	 * Constructor
	 */
	protected FarmerBehaviorContext() {
		super("LombardyBiogasContext");
	}
	

	/**
	 * 
	 */
	public void  calculateFarmersDecisions() {
		
	}


}
