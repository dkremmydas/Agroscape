package gr.agroscape.landMarket;

import gr.agroscape.behaviors.landMarket.BuyBidFormationRuleProperty;
import gr.agroscape.behaviors.landMarket.SellBidFormationRuleProperty;
import gr.agroscape.skeleton.agents.human.HumanAgent;

/**
 * 
 * @author Dimitis Kremmydas
 *
 */
public class LandMarketUtilities {

	static public BuyBidFormationRule getBuyBidFormationRuleFromHumanAgent(HumanAgent ha) {
		BuyBidFormationRule r = (BuyBidFormationRule) ha.getBehaviorProperty(BuyBidFormationRuleProperty.class);
		return r;
	}
	
	static public SellBidFormationRule getSellBidFormationRuleFromHumanAgent(HumanAgent ha) {
		SellBidFormationRule r = (SellBidFormationRule) ha.getBehaviorProperty(SellBidFormationRuleProperty.class);
		return r;
	}
	
}
