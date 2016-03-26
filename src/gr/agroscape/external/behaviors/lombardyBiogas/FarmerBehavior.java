package gr.agroscape.external.behaviors.lombardyBiogas;

import gr.agroscape.behaviors.Behavior;
import gr.agroscape.behaviors.BehaviorFactory;
import gr.agroscape.skeleton.agents.AgroscapeAgent;
import gr.agroscape.skeleton.agents.human.Farmer;
import repast.simphony.context.Context;

public class FarmerBehavior extends Behavior{

	private Farmer f;
	
	public FarmerBehavior(BehaviorFactory bhvFactory,
			AgroscapeAgent owner, Context<?> bhvContext) {
		super("LombardyBiogasBehavior", bhvFactory, owner, bhvContext);
		this.f = (Farmer)this.getOwner();
	}



}
