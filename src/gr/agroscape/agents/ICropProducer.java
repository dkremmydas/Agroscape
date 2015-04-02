package gr.agroscape.agents;

import gr.agroscape.crops.Crop;

import java.util.HashMap;

public interface ICropProducer {
	
	
	HashMap<Plot, Crop> makeProductionDecision();
	
}
