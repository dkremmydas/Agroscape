package gr.agroscape.dataLoaders;

import gr.agroscape.authorities.LandPropertyRegistry;
import gr.agroscape.contexts.FarmersContext;
import gr.agroscape.contexts.PlotsContext;

/**
 * The interface, defining what is needed to load minimal data 
 * and initialize Agroscape. <br />
 * This data is Plots, Farmers and LandPropertyRegistry.
 * <br />See {@link DefaultDataLoader}.
 * 
 * @author Dimitris Kremmydas
 *
 */
public interface ICanLoadAgroscapeData {

		
	/**
	 * Load Plots into a {@link PlotsContext}
	 * @param {@link PlotsContext} context
	 */
	void loadPlotsContext(PlotsContext context);
	
	/**
	 * Load Farmers into a {@link FarmersContext}
	 * @param {@link FarmersContext} context
	 */
	void loadFarmersContext(FarmersContext context);
	
		
	/**
	 * Initialize a {@link LandPropertyRegistry}. <br />
	 * It deletes all existing data;
	 * @param {@link LandPropertyRegistry} lpr
	 */
	void initLandPropertyRegistry(LandPropertyRegistry lpr);
	
	/**
	 * 
	 * @param pa
	 */
	//void initPaymentAuthority(PaymentAuthority pa);
}
