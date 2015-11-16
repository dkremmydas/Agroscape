package gr.agroscape.behaviors.arableCropProduction;

import java.util.List;

import repast.simphony.context.Context;
import gr.agroscape.behaviors.AgentBehavior;
import gr.agroscape.behaviors.BehaviorAction;
import gr.agroscape.behaviors.BehaviorFactory;
import gr.agroscape.skeleton.agents.AgroscapeAgent;

public class ArCrop_Behavior extends AgentBehavior{

	public ArCrop_Behavior(BehaviorFactory bhvFactory,
			AgroscapeAgent owner, Context<?> bhvContext) {
		super("ArableCropProductionBehavior", bhvFactory, owner, bhvContext);
		// TODO Auto-generated constructor stub
	}

	@Override
	public List<BehaviorAction> getScheduledActions() {
		// TODO Auto-generated method stub
		return null;
	}

}
