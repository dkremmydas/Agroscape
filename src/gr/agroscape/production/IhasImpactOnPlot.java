package gr.agroscape.production;

import gr.agroscape.agents.Plot;

/**
 * Any class that has some impact on a Plot should respect this interface
 * @author jkr
 *
 */
public interface IhasImpactOnPlot {

	/**
	 * How does the ProductionDecision affect the Plots ?
	 * @param plots
	 */
	public void feedbackToPlot(Plot plots);
	
}
