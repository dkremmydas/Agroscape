package gr.agroscape.production;

import gr.agroscape.agents.Farmer;
import gr.agroscape.agents.Plot;
import gr.agroscape.landUse.ArableCrop;

public class ArableCropProductionDecision extends AProductionDecision {
	
	private ArableCrop decision;
	
	
	private ArableCropProductionDecision(Plot p) {
		super(p);
		// TODO Auto-generated constructor stub
	}
	
	public ArableCropProductionDecision(Plot p, ArableCrop c) {
		this(p);
		this.decision = c;
	}

	

	@Override
	public void feedbackToFarmer(Farmer producers) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void feedbackToPlot(Plot p) {
		p.setLanduse(this.decision);		
	}

	@Override
	public String toString() {
		return super.toString() + ": Crop=" + this.decision.toString();
	}
	
	
}
