package gr.agroscape.behaviors.productionRealization;

import gr.agroscape.behaviors.Behavior;
import gr.agroscape.behaviors.BehaviorContext;
import gr.agroscape.behaviors.BehaviorFactory;
import gr.agroscape.skeleton.agents.AgroscapeAgent;
import gr.agroscape.skeleton.contexts.SimulationContext;

import java.util.List;

public class ProductionRealizationBehaviorFactory extends BehaviorFactory {

	@Override
	public Iterable<? extends Behavior> getBehaviorObjects(SimulationContext simulationContext) {
		//do nothing
		return null;
	}

	@Override
	public void addProperties(SimulationContext simulationContext) {
		// do nothing
	}

	@Override
	public BehaviorContext getBehaviorContext() {
		return new ProductionRealizationBehaviorContext();
	}

	@Override
	public List<AgroscapeAgent> getNewAgents() {
		return null;
	}

	@Override
	public String getBehaviorInformation() {
		return null;
	}

}
