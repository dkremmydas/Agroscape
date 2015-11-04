package gr.agroscape.behaviors.farmers.production.expectations;


import gr.agroscape.behaviors.farmers.production.agriculturalActivities.ArableCropCultivation;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

/**
 * 
 * @author Dimitris Kremmydas
 *
 */
public class ExpectedCropPrices extends AbstractExpectation<ArableCropCultivation, Long> {

	

	public ExpectedCropPrices(HashMap<ArableCropCultivation, Long> values) {
		super(values);
		// TODO Auto-generated constructor stub
	}
	
	public ExpectedCropPrices(ArrayList<ArableCropCultivation> keys) {
		super(keys);
		// TODO Auto-generated constructor stub
	}
	

	/**
	 * For each Crop, there is a random assignment (from a uniform distribution), from 1 to 151 eurocents
	 */
	@Override
	public HashMap<ArableCropCultivation, Long> getDefaultValues(ArrayList<ArableCropCultivation> keys) {
		HashMap<ArableCropCultivation,Long> v = new HashMap<ArableCropCultivation,Long>();
		Random r = new Random();
		
		for (ArableCropCultivation c : keys) {
			v.put(c, (long)(1+r.nextInt(150)));
		}
		
		return v;
	}

	

}
