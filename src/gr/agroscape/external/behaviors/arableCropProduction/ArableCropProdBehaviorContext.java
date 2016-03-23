package gr.agroscape.external.behaviors.arableCropProduction;

import gr.agroscape.behaviors.BehaviorContext;
import gr.agroscape.external.classes.production.agriculturalActivities.ArableCropCultivation;
import gr.agroscape.skeleton.contexts.SimulationContext;

import java.util.ArrayList;

import repast.simphony.engine.environment.RunEnvironment;
import repast.simphony.valueLayer.GridValueLayer;

public class ArableCropProdBehaviorContext extends BehaviorContext {
	
	private ArrayList<ArableCropCultivation> availableCrops; 
	
	private GridValueLayer cropsGrid;

	
	protected ArableCropProdBehaviorContext() {
		super("ArableCropProductionBehavior");
		this.cropsGrid = new GridValueLayer("cropsGrid", true, 
				RunEnvironment.getInstance().getParameters().getInteger("gridWidth"),
				RunEnvironment.getInstance().getParameters().getInteger("gridHeight"));
		this.addProjection(SimulationContext.getInstance().getSpace().getSpace());
		this.addValueLayer(this.cropsGrid);
	}


	public ArableCropProdBehaviorContext(ArrayList<ArableCropCultivation> availableCrops) {
		this();
		this.availableCrops = availableCrops;
	}


	public ArrayList<ArableCropCultivation> getAvailableCrops() {
		return availableCrops;
	}


	public void setAvailableCrops(ArrayList<ArableCropCultivation> availableCrops) {
		this.availableCrops = availableCrops;
	}


	public GridValueLayer getCropsGrid() {
		return cropsGrid;
	}
	
	
	
	

}
