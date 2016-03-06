package gr.agroscape.skeleton.dataLoaders;

import gr.agroscape.skeleton.agents.human.Farmer;
import gr.agroscape.skeleton.agents.human.FarmerAccounting;
import gr.agroscape.skeleton.authorities.LandPropertyRegistry;
import gr.agroscape.skeleton.contexts.FarmersContext;
import gr.agroscape.skeleton.contexts.PlotsContext;
import gr.agroscape.skeleton.projections.SimulationSpace;
import repast.simphony.engine.environment.RunEnvironment;

public class RandomizedDataLoader implements AgroscapeSkeletonDataLoader {
	
	private int numberOfFarmers;
	private int numberOfPlots;
	
	
	
	public RandomizedDataLoader() {
		super();
		this.numberOfFarmers =  Integer.parseInt(RunEnvironment.getInstance().getParameters().getString("NumberOfFarmers"));
		this.numberOfPlots =  Integer.parseInt(RunEnvironment.getInstance().getParameters().getString("NumberOfPlots"));
	}


	@Override
	public void loadPlotsContext(PlotsContext context) {
		// TODO Auto-generated method stub

	}

	@Override
	public void loadFarmersContext(FarmersContext context) {
		for(int i=0;i<this.numberOfFarmers;i++) {
			context.add(this.createRandomFarmer());
		}

	}

	@Override
	public void initLandPropertyRegistry(LandPropertyRegistry lpr) {
		// TODO Auto-generated method stub

	}

	@Override
	public void initSimulationSpace(SimulationSpace sp) {
		// TODO Auto-generated method stub

	}
	
	private Farmer createRandomFarmer() {
		Farmer f = new Farmer();
		FarmerAccounting fa = f.getAccount();
		fa.addCash(500l);fa.addMachinery(500l);fa.addEquity(1000l);
		return f;
	}

}
