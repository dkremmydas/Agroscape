package gr.agroscape.behaviors.arableCropProduction;

import gr.agroscape.behaviors.AgentBehavior;
import gr.agroscape.behaviors.BehaviorAction;
import gr.agroscape.behaviors.BehaviorFactory;
import gr.agroscape.behaviors.arableCropProduction.agentProperties.CurrentArableCropActivityProperty;
import gr.agroscape.production.agriculturalActivities.ArableCropCultivation;
import gr.agroscape.skeleton.agents.AgroscapeAgent;
import gr.agroscape.skeleton.agents.human.HumanAgent;
import gr.agroscape.skeleton.agents.plot.Plot;
import gr.agroscape.skeleton.contexts.SimulationContext;

import java.util.ArrayList;
import java.util.List;

import repast.simphony.context.Context;
import repast.simphony.engine.schedule.ScheduleParameters;
import repast.simphony.random.RandomHelper;

public class ArableCropProdBehavior extends AgentBehavior{

	public ArableCropProdBehavior(BehaviorFactory bhvFactory,
			AgroscapeAgent owner, Context<?> bhvContext) {
		super("ArableCropProductionBehavior", bhvFactory, owner, bhvContext);
		// TODO Auto-generated constructor stub
	}

	@Override
	public List<BehaviorAction> getScheduledActions() {
		ArrayList<BehaviorAction> r = new ArrayList<>();
		BehaviorAction ba = new BehaviorAction("makeProductionDecision", ScheduleParameters.createRepeating(1, 360),this);
		r.add(ba);
		return r;
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
			CurrentArableCropActivityProperty ca = (CurrentArableCropActivityProperty) plot.getBehaviorProperty(CurrentArableCropActivityProperty.class).getValue();
			ArableCropCultivation crop = cont.getAvailableCrops().get(RandomHelper.nextIntFromTo(0, cont.getAvailableCrops().size()-1));
			ca.setValue(crop);
			
			//update GridValueLayer
			plot.setGridValueLayer(cont.getCropsGrid(), crop.getId());
		}
		
	}

}
