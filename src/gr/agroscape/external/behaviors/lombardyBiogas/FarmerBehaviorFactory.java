package gr.agroscape.external.behaviors.lombardyBiogas;

import gr.agroscape.behaviors.Behavior;
import gr.agroscape.behaviors.BehaviorContext;
import gr.agroscape.behaviors.BehaviorFactory;
import gr.agroscape.skeleton.agents.AgroscapeAgent;
import gr.agroscape.skeleton.agents.human.Farmer;
import gr.agroscape.skeleton.contexts.SimulationContext;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Level;

public class FarmerBehaviorFactory extends BehaviorFactory {
	
	private FarmerBehaviorContext bvhContext = new FarmerBehaviorContext();
	
	public FarmerBehaviorFactory() {
		super();
		SimulationContext.logMessage(this.getClass(), Level.DEBUG, "FarmerBehaviorFactory Created");		
	}

	@Override
	public Iterable<? extends Behavior> getBehaviorObjects(SimulationContext simulationContext) {
		Iterable<Farmer> farmers = simulationContext.getFarmersContext().getAllFarmers();	
		ArrayList<Behavior> bhvs = new ArrayList<>();
		//1. assign to all farmers
		for (AgroscapeAgent f : farmers) {
			bhvs.add(new FarmerBehavior(this,f, this.bvhContext));
		}		
		return bhvs;
	}

	@Override
	public void addProperties(SimulationContext simulationContext) {
		Iterable<Farmer> farmers = simulationContext.getFarmersContext().getAllFarmers();	
		//1. assign to all farmers
		for (AgroscapeAgent f : farmers) {
			f.addBehaviorProperty(new FarmerBehaviorProperties());
		}		
	}

	@Override
	public BehaviorContext getBehaviorContext() {
		return bvhContext;
	}

	@Override
	public List<AgroscapeAgent> getNewAgents() {
		return null;
	}

	@Override
	public String getBehaviorInformation() {
		return "LombardyBiogasFactory\n "
				+ "\n Version 1. \n Author Dimitris Kremmydas";
	}

}
