package gr.agroscape.behaviors.arableCropProduction;

import gr.agroscape.behaviors.BehaviorContext;
import gr.agroscape.behaviors.arableCropProduction.production.agriculturalActivities.ArableCropCultivation;

import java.util.ArrayList;

public class ArCrop_BehaviorContext extends BehaviorContext {
	
	private ArrayList<ArableCropCultivation> availableCrops; 
	
	

	protected ArCrop_BehaviorContext(String name) {
		super(name);
		// TODO Auto-generated constructor stub
	}

}
