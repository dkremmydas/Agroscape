package gr.agroscape.agents;

import gr.agroscape.crops.Crop;

import java.util.HashMap;

import repast.simphony.engine.schedule.ScheduledMethod;

public interface ICropProducer {
	
	
	HashMap<Plot, Crop> makeProductionDecision();
	
}
