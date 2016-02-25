package gr.agroscape.behaviors.landMarket.rules;

import gr.agroscape.behaviors.landMarket.Bid;
import gr.agroscape.behaviors.landMarket.SellerRules;
import gr.agroscape.skeleton.agents.human.Farmer;
import gr.agroscape.skeleton.agents.plot.Plot;
import repast.simphony.random.RandomHelper;

public class DefaultSellerRules implements SellerRules {
	

	public DefaultSellerRules(Farmer ruleOwner) {
		super();
	}

	@Override
	public Bid getTheSellBid(Plot p) {
		return new Bid(p,RandomHelper.nextIntFromTo(1, 100)*this.getWta(p)/100);
	}

	@Override
	public Long getWta(Plot p) {
		return new Long(RandomHelper.nextIntFromTo(1, 100));
	}

}
