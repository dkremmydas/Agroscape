package gr.agroscape.dataLoaders;

import gr.agroscape.authorities.LandPropertyRegistry;
import gr.agroscape.authorities.PaymentAuthority;
import gr.agroscape.contexts.CropsContext;
import gr.agroscape.contexts.FarmersContext;
import gr.agroscape.contexts.MainContext;
import gr.agroscape.contexts.PlotsContext;
import gr.agroscape.crops.Crop;
import gr.agroscape.main.ContextManager;

import java.util.HashMap;

import repast.simphony.valueLayer.GridValueLayer;

/**
 * The interface, defining what is needed to load data 
 * and initialize Agroscape. 
 * <br />See {@link DefaultDataLoader}.
 * 
 * @author Dimitris Kremmydas
 *
 */
public interface ISimulationDataLoader {

	/**
	 * Load available Crops into {@link CropsContext}
	 * @param {@link CropsContext} context
	 */
	void loadCropsContext(CropsContext context);
	
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
	 * Load the Crop Suitability map. {@link ContextManager.cropSuitability}
	 * @param HashMap<Crop, GridValueLayer> csmap
	 */
	void loadCropSuitabilityMap(HashMap<Crop, GridValueLayer> csmap,MainContext context);
	
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
	void initPaymentAuthority(PaymentAuthority pa);
}
