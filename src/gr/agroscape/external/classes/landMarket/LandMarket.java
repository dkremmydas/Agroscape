package gr.agroscape.external.classes.landMarket;

import org.apache.commons.collections4.multimap.ArrayListValuedHashMap;

public abstract class LandMarket {

	private BuyersSelectionRule buyersSelectionRule;
	private PlotsSelectionRule plotsSelectionRule;
	private PerformTransaction performTransaction;
	
	/**
	 * It holds the correspondence from SellerBid->{BuyBid1, BuyBid2,...,BuyBidN}
	 */
	protected ArrayListValuedHashMap<Bid,Bid> bidRegistry = new ArrayListValuedHashMap<>();
	
	
	public LandMarket(BuyersSelectionRule buyersSelectionRule,
			PlotsSelectionRule plotSelectionRule,
			PerformTransaction clearingMechanism) {
		this();
		this.setLandMarketRules(buyersSelectionRule,plotSelectionRule,clearingMechanism) ;
	}
	
	public LandMarket() {
		super();
	}
	
	public void setLandMarketRules(BuyersSelectionRule buyersSelectionRule,
			PlotsSelectionRule plotSelectionRule,
			PerformTransaction clearingMechanism) {
		this.buyersSelectionRule = buyersSelectionRule;
		this.plotsSelectionRule = plotSelectionRule;
		this.performTransaction = clearingMechanism;
	}
	
	public ArrayListValuedHashMap<Bid,Bid> getBidRegistry() {
		return this.bidRegistry;
	}
	
	
	abstract public void clearMarket();
	abstract public Boolean isCleared();
	
}
