package gr.agroscape.landMarket;

import gr.agroscape.skeleton.agents.plot.Plot;

public interface SellBidFormationRule {
	
	public Bid getTheSellBid(Plot p);
}
