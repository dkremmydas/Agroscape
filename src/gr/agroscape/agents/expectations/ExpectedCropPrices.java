package gr.agroscape.agents.expectations;


import gr.agroscape.landUse.ArableCrop;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

/**
 * 
 * @author Dimitris Kremmydas
 *
 */
public class ExpectedCropPrices extends AbstractExpectation<ArableCrop, Long> {

	

	public ExpectedCropPrices(HashMap<ArableCrop, Long> values) {
		super(values);
		// TODO Auto-generated constructor stub
	}
	
	public ExpectedCropPrices(ArrayList<ArableCrop> keys) {
		super(keys);
		// TODO Auto-generated constructor stub
	}
	

	/**
	 * For each Crop, there is a random assignment (from a uniform distribution), from 1 to 151 eurocents
	 */
	@Override
	public HashMap<ArableCrop, Long> getDefaultValues(ArrayList<ArableCrop> keys) {
		HashMap<ArableCrop,Long> v = new HashMap<ArableCrop,Long>();
		Random r = new Random();
		
		for (ArableCrop c : keys) {
			v.put(c, (long)(1+r.nextInt(150)));
		}
		
		return v;
	}

	

}
