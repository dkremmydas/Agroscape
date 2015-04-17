package gr.agroscape.behaviors.farmers.production.arableCropProduction;


import gr.agroscape.agents.Farmer;
import gr.agroscape.agents.HumanAgentProperty;
import gr.agroscape.behaviors.farmers.AFarmerBehavior;

import java.util.ArrayList;
import java.util.HashMap;

public class ArableCropProductionBehavior extends AFarmerBehavior {
	
	
	

	
	public ArableCropProductionBehavior(Farmer owner) {
		super(owner);
	}

	
	
	
	
	
	@Override
	public void setUpOwner() {
		//add properties
		
		/**
		 * Potential Arable Crops
		 */
o		owner_properties.put("potentialCrops", 
			new HumanAgentProperty<ArrayList<ArableCropCultivation>>(new ArableCropProductionBehavior(owner)) );
		
		 /**
	     * The Decoupled Payment Rights (�cent/h)
	     */
		owner_properties.put(new String("singlePaymentValue"), null);
		
	    /**
	     * The expected yield for each Crop on each Plot (Crop->kg/h)
	     */
	    owner_properties.put(new String("expectedPlotCropYields"), null);

	    
	    /**
	     * The expected prices for each Crop (Crop->�cent)
	     */
	    owner_properties.put(new String("expectedCropPrices"), null);

	    
	    /**
	     * The amount of coupled payment (Crop->�cent/h)
	     */
	    private HashMap<ArableCropCultivation,Long> coupledPayments = new HashMap<ArableCropCultivation,Long>();  
	    
	    /**
	     * The expected variable cost of cultivating Crop to a Plot (Crop->�cent/h)
	     */
	    private ExpectedPlotCropVarCost expectedPlotCropVarCost;

	}

	@Override
	public Object getAnnotatedClass() {
		// TODO Auto-generated method stub
		return null;
	}

}
