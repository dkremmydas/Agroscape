package gr.agroscape.production;

import gr.agroscape.agents.Plot;

/**
 * Represents an abstract elementary production decision. <br />
 * It is always referenced to a {@link Plot}. 
 *  
 * @author Dimitris Kremmydas
 */
 public abstract class AProductionDecision  implements IhasImpactOnFarmer,IhasImpactOnPlot {
	
	 private Plot plot;
	
	 protected AProductionDecision(Plot p) {
		 this.plot = p;
	 }
	 
	 public final Plot getPlot() {
		 return this.plot;
	 }
}
