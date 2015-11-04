package gr.agroscape.behaviors.farmers.production.productionDecisions.copy;

import gr.agroscape.agents.plot.Plot;
import gr.agroscape.behaviors.farmers.production.interfaces.IProductionDecisionImpacts;

/**
 * Represents an abstract elementary production decision. <br />
 * It is always referenced to a {@link Plot}. 
 *  
 * @author Dimitris Kremmydas
 */
 public abstract class AProductionDecision  implements IProductionDecisionImpacts {
	
	 private Plot plot;
	
	 protected AProductionDecision(Plot p) {
		 this.plot = p;
	 }
	 
	 public final Plot getPlot() {
		 return this.plot;
	 }
}
