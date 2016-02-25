package gr.agroscape.behaviors.landMarket.rules;

import gr.agroscape.behaviors.landMarket.Bid;
import gr.agroscape.behaviors.landMarket.BuyerRules;
import gr.agroscape.skeleton.agents.human.Farmer;
import gr.agroscape.skeleton.agents.plot.Plot;
import repast.simphony.random.RandomHelper;

public class DefaultBuyerRules implements BuyerRules {
	

	public DefaultBuyerRules(Farmer ruleOwner) {
		super();
	}

	/**
	 * Returns a random fraction of the WTP
	 */
	@Override
	public Bid getTheBuyBid(Plot p) {
		return new Bid(p,RandomHelper.nextIntFromTo(1, 100)*this.getWtp(p)/100);
	}

	/**
	 * Returns a random number from 1 to 100
	 */
	@Override
	public Long getWtp(Plot p) {
		return new Long(RandomHelper.nextIntFromTo(1, 100));
	}

}
