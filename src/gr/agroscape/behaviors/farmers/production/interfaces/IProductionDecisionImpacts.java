package gr.agroscape.behaviors.farmers.production.interfaces;

import gr.agroscape.agents.Farmer;
import gr.agroscape.agents.Plot;

/**
 * Any class that affects a farmer should respect this interface
 * @author Dimitris Kremmydas
 *
 */
public interface IProductionDecisionImpacts {

	
	/**
	 * How does the ProductionDecision affect the Plots ? <br />
	 * Mainly dedicated to biophysical feedback
	 * @param plot
	 */
	public void feedbackToPlot(Plot plot);
	
	
	/**
	 * How does a farmer is affected
	 * @param producers
	 */
	public void feedbackToFarmer(Farmer producers);
	

}
