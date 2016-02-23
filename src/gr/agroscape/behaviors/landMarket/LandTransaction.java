package gr.agroscape.behaviors.landMarket;

import gr.agroscape.skeleton.agents.human.HumanAgent;
import gr.agroscape.skeleton.agents.plot.Plot;

public class LandTransaction {
	
	private Plot thePlot;
	private HumanAgent theBuyer;
	private HumanAgent theSeller;
	
	public LandTransaction(Plot thePlot, HumanAgent theBuyer, HumanAgent theSeller) {
		super();
		this.thePlot = thePlot;
		this.theBuyer = theBuyer;
		this.theSeller = theSeller;
	}

	public Plot getThePlot() {
		return thePlot;
	}

	public HumanAgent getTheBuyer() {
		return theBuyer;
	}

	public HumanAgent getTheSeller() {
		return theSeller;
	} 
	
	
	

}
