package gr.agroscape.behaviors.risk;

import gr.agroscape.behaviors.BehaviorContext;

public class FarmerBehaviorContext extends BehaviorContext {
	
	private OutpurPriceGenerator pg;
	private long currentPrice;
	private int period = 0;
	
	protected FarmerBehaviorContext() {
		super("RiskFarmerBehaviorContext");
	}

	public long getCurrentPrice() {
		return currentPrice;
	}

	
	
	//scheduled to run every 10
	public void calculateNextPrice() {
		this.currentPrice = pg.getPrice(period);
		this.period++;
	}

}
