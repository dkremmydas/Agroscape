package gr.agroscape.external.classes.landMarket;

import gr.agroscape.skeleton.agents.human.HumanAgent;
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
	private HumanAgent theAgent;
	
	
	public Bid(Plot thePlot, Long theBid, HumanAgent agent) {
		super();
		this.thePlot = thePlot;
		this.theBid = theBid;
		this.theAgent = agent;
	}


	public Plot getThePlot() {
		return thePlot;
	}


	public Long getTheBid() {
		return theBid;
	}
	
	public HumanAgent getTheAgent() {
		return theAgent;
	}
	
	

}
