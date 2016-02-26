package gr.agroscape.behaviors.landMarket.interfaces;

import gr.agroscape.skeleton.agents.plot.Plot;

import org.apache.commons.collections4.MultiMap;
import org.apache.commons.collections4.multimap.ArrayListValuedHashMap;

public abstract class LandMarket {

	private BuyersSelectionRule buyersSelectionRule;
	private PlotsSelectionRule plotsSelectionRule;
	private ClearingMechanism clearingMechanism;
	
	protected ArrayListValuedHashMap<Plot,Bid> bidRegistry = new ArrayListValuedHashMap<>();
	
	
	public LandMarket(BuyersSelectionRule buyersSelectionRule,
			PlotsSelectionRule plotSelectionRule,
			ClearingMechanism clearingMechanism) {
		this();
		this.setLandMarketRules(buyersSelectionRule,plotSelectionRule,clearingMechanism) ;
	}
	
	public LandMarket() {
		super();
	}
	
	public void setLandMarketRules(BuyersSelectionRule buyersSelectionRule,
			PlotsSelectionRule plotSelectionRule,
			ClearingMechanism clearingMechanism) {
		this.buyersSelectionRule = buyersSelectionRule;
		this.plotsSelectionRule = plotSelectionRule;
		this.clearingMechanism = clearingMechanism;
	}
	
	public ArrayListValuedHashMap<Plot,Bid> getBidRegistry() {
		return this.bidRegistry;
	}
	
	
	abstract public void clearMarket();
	abstract public Boolean isCleared();
	
}
