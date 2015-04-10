package gr.agroscape.production;

/**
 * Returns a number from [0,1] giving the suitability of the period 
 * regarding the success of the production and the transformation
 * of Crop->Product
 * 
 * @author Dimitris Kremmydas
 *
 */
public interface IWeatherSuitability {
	
	float getWeatherSuitability(AProductionDecision pd);
	
}
