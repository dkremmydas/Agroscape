package gr.agroscape.behaviors.arableCropProduction;

import java.util.ArrayList;
import java.util.List;

import repast.simphony.context.Context;
import repast.simphony.engine.schedule.ScheduleParameters;
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
		ArrayList<BehaviorAction> r = new ArrayList<>();
		BehaviorAction ba = new BehaviorAction("decideOnArableCrop", "makeProductionDecision", ScheduleParameters.createRepeating(1, 360),this);
		r.add(ba);
		return r;
	}
	
	/**
	 * The production decision making
	 */
	public void makeProductionDecision() {
		
	}

}
