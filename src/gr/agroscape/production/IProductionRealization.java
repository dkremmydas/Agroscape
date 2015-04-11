package gr.agroscape.production;

import gr.agroscape.products.Product;

import java.util.Map;

/**
 * Any class that makes production realization
 * @author Dimitris Kremmydas
 *
 */
public interface IProductionRealization {

	/**
	 * What is the actual yield ?
	 * @param producers
	 */
	public Map<Product,Float> productionRealization(IWeatherSuitability w);
	

}
