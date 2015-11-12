package gr.agroscape.skeleton.dataLoaders;

import gr.agroscape.skeleton.authorities.LandPropertyRegistry;
import gr.agroscape.skeleton.contexts.FarmersContext;
import gr.agroscape.skeleton.contexts.PlotsContext;
import gr.agroscape.skeleton.projections.SimulationSpace;

/**
 * The interface, defining what is needed to load minimal data 
 * and initialize Agroscape. <br />
 * This data is Plots, Farmers and LandPropertyRegistry.
 * <br />See {@link DefaultDataLoader}.
 * 
 * @author Dimitris Kremmydas
 *
 */
public interface AgroscapeSkeletonDataLoader {

		
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
	 * Initialize the {@link SimulationSpace}
	 * @param sp
	 */
	void initSimulationSpace(SimulationSpace sp);
	
	/**
	 * 
	 * @param pa
	 */
	//void initPaymentAuthority(PaymentAuthority pa);
}
