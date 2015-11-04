package gr.agroscape.dataLoaders;

import gr.agroscape.skeleton.agents.AgroscapeAgent;
import gr.agroscape.skeleton.contexts.FarmersContext;
import gr.agroscape.skeleton.contexts.PlotsContext;
import gr.agroscape.skeleton.contexts.SimulationContext;

/**
 * <p>The interface, defining the loading of all behaviors. The only method that is required ({@link #loadAllBehaviors})
 *  receives the root {@link SimulationContext} and is responsible for adding to {@link AgroscapeAgent}s the 
 *  behaviors of the simulation.</p>
 * 
 * @author Dimitris Kremmydas
 * @version $Revision$
 * @since 2.0
 *
 */
public interface AgroscapeAllBehaviorsDataLoader {

		
	/**
	 * This method should cater the loading of all simulation behaviors into {@link SimulationContext}. 
	 * Since this context holds references to {@link FarmersContext} and {@link PlotsContext}, one can use them
	 * to access all corresponding agents.
	 * @param {@link SimulationContext}
	 */
	void loadAllBehaviors(SimulationContext simulationContext);
	
	
}
