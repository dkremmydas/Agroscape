package gr.agroscape.external.behaviors.arableCropProduction;

import gr.agroscape.behaviors.Behavior;
import gr.agroscape.behaviors.BehaviorFactory;
import gr.agroscape.external.behaviors.arableCropProduction.properties.CurrentArableCropActivityProperty;
import gr.agroscape.external.classes.production.agriculturalActivities.ArableCropCultivation;
import gr.agroscape.skeleton.agents.AgroscapeAgent;
import gr.agroscape.skeleton.agents.human.HumanAgent;
import gr.agroscape.skeleton.agents.plot.Plot;
import gr.agroscape.skeleton.contexts.SimulationContext;

import java.util.ArrayList;

import org.apache.log4j.Level;

import repast.simphony.context.Context;
import repast.simphony.random.RandomHelper;

public class ArableCropProdBehavior extends Behavior{

	public ArableCropProdBehavior(BehaviorFactory bhvFactory,
			AgroscapeAgent owner, Context<?> bhvContext) {
		super("ArableCropProductionBehavior", bhvFactory, owner, bhvContext);
		// TODO Auto-generated constructor stub
	}

	
	/**
	 * The production decision making
	 */
	public void makeProductionDecision() {
		//available crops
		ArableCropProdBehaviorContext cont = (ArableCropProdBehaviorContext) this.getBehaviorContext();
		
		//find owned plots
		ArrayList<Plot> plots = SimulationContext.getInstance().getLandPropertyRegistry().getOwnedPlots((HumanAgent) this.getOwner());
		
		//decide in random for each owned plot
		for (Plot plot : plots) {
			CurrentArableCropActivityProperty ca = (CurrentArableCropActivityProperty) plot.getBehaviorProperty(CurrentArableCropActivityProperty.class);
			ArableCropCultivation crop = cont.getAvailableCrops().get(RandomHelper.nextIntFromTo(0, cont.getAvailableCrops().size()-1));
			SimulationContext.logMessage(this.getClass(), Level.DEBUG, "Got the plot : " + plot.toString());
			SimulationContext.logMessage(this.getClass(), Level.DEBUG, "Got the property : " + ca.toString());
			ca.setValue(crop);
			
			//update GridValueLayer
			plot.setGridValueLayer(cont.getCropsGrid(), crop.getId());
		}
		
	}

}
