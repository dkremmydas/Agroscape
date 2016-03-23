package gr.agroscape.external.behaviors.landMarket.properties;

import gr.agroscape.external.classes.landMarket.BuyBidFormationRule;
import gr.agroscape.skeleton.agents.AgroscapeAgentProperty;

public class BuyBidFormationRuleProperty extends AgroscapeAgentProperty<BuyBidFormationRule> {

	public BuyBidFormationRuleProperty(BuyBidFormationRule value) {
		super(BuyBidFormationRule.class, "BuyBidFormationRule", value);
	}

	BuyBidFormationRuleProperty() {
		super(BuyBidFormationRule.class, "BuyBidFormationRule", null);
	}
}
