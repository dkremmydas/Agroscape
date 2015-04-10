package gr.agroscape.production;

import gr.agroscape.agents.Farmer;
import gr.agroscape.agents.Plot;
import gr.agroscape.products.Product;

import java.util.Map;

/**
 * Any class that affects a farmer should respect this interface
 * @author Dimitris Kremmydas
 *
 */
public interface IProductionDecisionImpacts {

	
	/**
	 * How does the ProductionDecision affect the Plots ?
	 * @param plots
	 */
	public void feedbackToPlot(Plot plots);
	
	
	/**
	 * How does a farmer is affected
	 * @param producers
	 */
	public void feedbackToFarmer(Farmer producers);
	
	
	/**
	 * How does a farmer is affected
	 * @param producers
	 */
	public Map<Product,Float> productionRealization(IWeatherSuitability w);
	
}
