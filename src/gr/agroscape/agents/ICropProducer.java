package gr.agroscape.agents;

import gr.agroscape.crops.Crop;

import java.util.HashMap;

/**
 * The contract that someone that cultivates Crops should fulfill. <br />
 * Basically he should provide an implementation that return a map of {@link Plot}->{@link Crop}, 
 * i.e. deciding for each Plot he will cultivate, what Crop this will be.
 * 
 * @author Dimitris Kremmydas *
 */
public interface ICropProducer {
	
	
	HashMap<Plot, Crop> makeProductionDecision();
	
}
