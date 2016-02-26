package gr.agroscape.behaviors.landMarket.interfaces;

import gr.agroscape.skeleton.agents.human.HumanAgent;
import gr.agroscape.skeleton.agents.plot.Plot;

public class LandTransaction {
	
	private Plot thePlot;
	private HumanAgent theBuyer;
	private HumanAgent theSeller;
	private Long thePrice;
	
	public LandTransaction(Plot thePlot, HumanAgent theBuyer, HumanAgent theSeller,Long thePrice) {
		super();
		this.thePlot = thePlot;
		this.theBuyer = theBuyer;
		this.theSeller = theSeller;
		this.thePrice = thePrice;
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
	
	public Long getThePrice() {
		return thePrice;
	} 
	
	
	

}
