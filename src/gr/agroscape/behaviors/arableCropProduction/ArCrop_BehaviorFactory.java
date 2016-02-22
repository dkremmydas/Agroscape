package gr.agroscape.behaviors.arableCropProduction;

import gr.agroscape.behaviors.AgentBehavior;
import gr.agroscape.behaviors.BehaviorContext;
import gr.agroscape.behaviors.BehaviorFactory;
import gr.agroscape.behaviors.arableCropProduction.production.agriculturalActivities.ArableCropCultivation;
import gr.agroscape.skeleton.agents.AgroscapeAgent;
import gr.agroscape.skeleton.agents.AgroscapeAgentProperty;
import gr.agroscape.skeleton.agents.human.Farmer;
import gr.agroscape.skeleton.agents.plot.Plot;
import gr.agroscape.skeleton.contexts.SimulationContext;

import java.util.Iterator;
import java.util.List;

public class ArCrop_BehaviorFactory extends BehaviorFactory {
	
	private BehaviorContext bvhContext;
	
	public ArCrop_BehaviorFactory() {
		super();
		this.name = "ArableCropProductionBehavior";
		this.bvhContext = new ArCrop_BehaviorContext("ArableCropProductionBehaviorContext");
	}

	@Override
	public void assignBehaviors(SimulationContext simulationContext) {
		//1. assign to all farmers
		for (Iterator<Farmer> farmers = simulationContext.getFarmersContext().getAllFarmers().iterator(); farmers.hasNext();) {
			Farmer farmer =  farmers.next();
			AgentBehavior ab = new ArCrop_Behavior(this,farmer, this.bvhContext) ;
			farmer.addBehavior(ab);
		}	
	}

@Override
public void addProperties(SimulationContext simulationContext) {
	
	//1. add currentAgricultularActivity to all Plots
	for (Iterator<Plot> plots = simulationContext.getPlotsContext().getAvailablePlots().iterator(); plots.hasNext();) {
		Plot plot =  plots.next();
		AgroscapeAgentProperty<ArableCropCultivation> bp = 
				new AgroscapeAgentProperty<>(ArableCropCultivation.class, "currentAgriculturalActivity");
		plot.addBehaviorProperty(bp);
	}	
	
} //end class


@Override
public BehaviorContext getBehaviorContext() {
	return this.bvhContext;	
}

@Override
public List<AgroscapeAgent> getNewAgents() {
	return null; //no new agents
}

@Override
public String getBehaviorInformation() {
	return this.name + "\n Version 1. \n Author Dimitris Kremmydas";
}


}
