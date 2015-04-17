package gr.agroscape.behaviors.farmers.production;

import gr.agroscape.agents.Farmer;
import gr.agroscape.behaviors.farmers.AFarmerBehavior;
import gr.agroscape.behaviors.farmers.production.agriculturalActivities.ArableCropCultivation;
import gr.agroscape.behaviors.farmers.production.expectations.ExpectedCropPrices;
import gr.agroscape.behaviors.farmers.production.expectations.ExpectedPlotCropVarCost;
import gr.agroscape.behaviors.farmers.production.expectations.ExpectedPlotCropYield;

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
		owner_properties.put(new String("potentialCrops"), new ArrayList<ArableCropCultivation>());
		
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
