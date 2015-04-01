package gr.agroscape.agents.expectations;


import gr.agroscape.crops.Crop;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;


@SuppressWarnings("hiding")
public class ExpectedCropPrices extends AbstractExpectation<Crop, Long> {

	

	public ExpectedCropPrices(HashMap<Crop, Long> values) {
		super(values);
		// TODO Auto-generated constructor stub
	}
	
	public ExpectedCropPrices(ArrayList<Crop> keys) {
		super(keys);
		// TODO Auto-generated constructor stub
	}
	

	/**
	 * For each Crop, there is a random assignment (from a uniform distribution), from 1 to 151 eurocents
	 */
	@Override
	public HashMap<Crop, Long> getDefaultValues(ArrayList<Crop> keys) {
		HashMap<Crop,Long> v = new HashMap<Crop,Long>();
		Random r = new Random();
		
		for (Crop c : keys) {
			v.put(c, (long)(1+r.nextInt(150)));
		}
		
		return v;
	}

	

}
