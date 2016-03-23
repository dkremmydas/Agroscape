package gr.agroscape.external.behaviors.risk;

import repast.simphony.random.RandomHelper;

public class OutpurPriceGenerator {
	
	
	
	
	public long getPrice(int period) {
		return  new Long(RandomHelper.nextIntFromTo(1, 100));
	}

}
