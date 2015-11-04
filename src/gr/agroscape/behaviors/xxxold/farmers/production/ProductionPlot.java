package gr.agroscape.behaviors.farmers.production;

import gr.agroscape.agents.plot.Plot;
import gr.agroscape.behaviors.farmers.production.agriculturalActivities.AAgriculturalActivity;

/**
 * A {@link Plot} where an ArableCropCultivation Activity is taking place.<br />
 * Composition (and not inheritance) is used
 * 
 * @author jkr
 *
 */
public class ProductionPlot {
		
	private Plot plot;
	private AAgriculturalActivity agriculturalLandUse = null;
	
	
	
	
	public ProductionPlot(Plot plot) {
		super();
		this.plot = plot;
	}
	
	public AAgriculturalActivity getAgriculturalLandUse() {
		return agriculturalLandUse;
	}
	public void setAgriculturalLandUse(AAgriculturalActivity agriculturalLandUse) {
		this.agriculturalLandUse = agriculturalLandUse;
	}
	public Plot getPlot() {
		return plot;
	}
		
	
	
}
