package gr.agroscape.external.classes.landMarket;

import gr.agroscape.external.behaviors.landMarket.properties.BuyBidFormationRuleProperty;
import gr.agroscape.external.behaviors.landMarket.properties.SellBidFormationRuleProperty;
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
