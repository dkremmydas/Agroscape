package gr.agroscape.skeleton.dataLoaders;

import gr.agroscape.skeleton.agents.human.Farmer;
import gr.agroscape.skeleton.agents.human.FarmerAccounting;
import gr.agroscape.skeleton.agents.plot.Plot;
import gr.agroscape.skeleton.authorities.LandPropertyRegistry;
import gr.agroscape.skeleton.contexts.FarmersContext;
import gr.agroscape.skeleton.contexts.PlotsContext;
import gr.agroscape.skeleton.projections.SimulationSpace;
import gr.agroscape.utilities.PlotUtilities;

import java.util.ArrayList;

import repast.simphony.engine.environment.RunEnvironment;

public class RandomizedDataLoader implements AgroscapeSkeletonDataLoader {
	
	private int numberOfFarmers;
	private int numberOfPlots;
	
	private ArrayList<Farmer> farmers = new ArrayList<>();
	private ArrayList<Plot> plots = new ArrayList<>();
	
	private int plotsPerFarmer;
	private int gridH, gridW;
	
	
	public RandomizedDataLoader() {
		super();
		this.numberOfFarmers =  Integer.parseInt(RunEnvironment.getInstance().getParameters().getString("NumberOfFarmers"));
		this.numberOfPlots =  Integer.parseInt(RunEnvironment.getInstance().getParameters().getString("NumberOfPlots"));
		gridH = Integer.parseInt(RunEnvironment.getInstance().getParameters().getString("gridHeight"));
		gridW = Integer.parseInt(RunEnvironment.getInstance().getParameters().getString("gridWidth"));
		plotsPerFarmer=numberOfFarmers/numberOfPlots;
	}


	@Override
	public void loadPlotsContext(PlotsContext context) {
		for(int i=1;i<=10;i++) {
			for(int j=1;j<=50;j++) {
				int topY=j*2-1;int bottomY=topY+1;
				int rightX=i*10; int leftX=rightX-9;
				Plot p = PlotUtilities.newRectanglePlot(leftX, topY, rightX, bottomY);
				this.plots.add(p);
				context.add(p);
			}
		}
	}

	@Override
	public void loadFarmersContext(FarmersContext context) {
		for(int i=0;i<this.numberOfFarmers;i++) {
			Farmer f = this.createRandomFarmer();
			this.farmers.add(f);
			context.add(f);
		}
	}

	@Override
	public void initLandPropertyRegistry(LandPropertyRegistry lpr) {
		int lastPlot = 0;
		for (Farmer f : farmers) {
			for(int j=0;j<this.plotsPerFarmer;j++) {
				lpr.setOwnerEntry(plots.get(lastPlot++), f);
			}
		}
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
