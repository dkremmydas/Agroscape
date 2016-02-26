package gr.agroscape.production.decisions;

import gr.agroscape.skeleton.agents.plot.Plot;

/**
 * Represents an abstract elementary production decision. <br />
 * It is always referenced to a {@link Plot}. 
 *  
 * @author Dimitris Kremmydas
 */
 public abstract class ProductionDecision   {
	
	 private Plot plot;
	
	 protected ProductionDecision(Plot p) {
		 this.plot = p;
	 }
	 
	 public final Plot getPlot() {
		 return this.plot;
	 }
}
