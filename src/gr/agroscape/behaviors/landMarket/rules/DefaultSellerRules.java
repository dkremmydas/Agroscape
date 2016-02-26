package gr.agroscape.behaviors.landMarket.rules;

import gr.agroscape.behaviors.landMarket.interfaces.Bid;
import gr.agroscape.behaviors.landMarket.interfaces.SellerRules;
import gr.agroscape.skeleton.agents.human.HumanAgent;
import gr.agroscape.skeleton.agents.plot.Plot;
import repast.simphony.random.RandomHelper;

public class DefaultSellerRules implements SellerRules {
	
	private HumanAgent ruleOwner;

	public DefaultSellerRules(HumanAgent ruleOwner) {
		super();
	}

	@Override
	public Bid getTheSellBid(Plot p) {
		return new Bid(p,RandomHelper.nextIntFromTo(1, 100)*this.getWta(p)/100,this.ruleOwner);
	}

	@Override
	public Long getWta(Plot p) {
		return new Long(RandomHelper.nextIntFromTo(1, 100));
	}

}
