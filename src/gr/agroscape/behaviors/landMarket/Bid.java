package gr.agroscape.behaviors.landMarket;

import gr.agroscape.skeleton.agents.plot.Plot;


/**
 * <p>It represents a bid for a plot</p>
 * 
 * @author Dimitris Kremmydas
 * @version %G%
 * @since 2.1
 *
 */
public class Bid {
	
	private Plot thePlot;
	private Long theBid;
	
	
	public Bid(Plot thePlot, Long theBid) {
		super();
		this.thePlot = thePlot;
		this.theBid = theBid;
	}


	public Plot getThePlot() {
		return thePlot;
	}


	public Long getTheBid() {
		return theBid;
	}
	
	

}
