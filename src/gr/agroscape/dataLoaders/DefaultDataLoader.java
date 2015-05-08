package gr.agroscape.dataLoaders;

import gr.agroscape.agents.Farmer;
import gr.agroscape.agents.HumanAgent;
import gr.agroscape.agents.Plot;
import gr.agroscape.authorities.LandPropertyRegistry;
import gr.agroscape.contexts.FarmersContext;
import gr.agroscape.contexts.PlotsContext;

import java.util.ArrayList;
import java.util.HashMap;

import repast.simphony.context.space.graph.NetworkBuilder;
import repast.simphony.space.graph.Network;
import repast.simphony.space.grid.GridPoint;

public class DefaultDataLoader implements IAgroscapeDataLoader {
	
	private ArrayList<Plot> avplots = new ArrayList<Plot>();
	private ArrayList<Farmer> avfarmers = new ArrayList<Farmer>();


	public DefaultDataLoader() {
		super();	
	}


	@Override
	public void loadPlotsContext(PlotsContext context) {
		this.avplots.add(new Plot(new GridPoint(new int[] {1,1}),1));
		this.avplots.add(new Plot(new int[][] {{1,2},{1,3}},2));
		this.avplots.add(new Plot(new int[][] {{1,4},{1,5},{1,6},{1,7}},3));
		this.avplots.add(new Plot(new int[][] {{0,8},{1,8},{1,9},{1,10}},4));
		this.avplots.add(new Plot(new int[][] {{2,1},{2,2},{2,3},{3,1},{3,2},{3,3}},5));
		this.avplots.add(new Plot(new int[][] {{4,1},{4,2},{4,3},{5,1},{5,2},{5,3},{6,1},{6,2},{6,3},{7,1},{7,2},{7,3}},6));
		this.avplots.add(new Plot(new int[][] {{4,4},{4,4},{4,5},{5,4},{5,5},{5,6},{6,4},{6,5},{6,6},{7,4},{7,5},{7,6}},7));
		context.addAll(this.avplots);
	}


	@Override
	public void loadFarmersContext(FarmersContext context) {

		//add agents
		this.avfarmers.add(new Farmer(1));
		this.avfarmers.add(new Farmer(2));
		this.avfarmers.add(new Farmer(3));
		this.avfarmers.add(new Farmer(4));
		context.addAll(this.avfarmers);
		
		//create network
		NetworkBuilder<Farmer> builder = new NetworkBuilder<Farmer>("productionNetwork", context, true);
		Network<Farmer> network = builder.buildNetwork();
		network.addEdge(context.getObjects(Farmer.class).get(0), context.getObjects(Farmer.class).get(1));
		//network.addEdge(context.getObjects(Farmer.class).get(2), context.getObjects(Farmer.class).get(3));
		context.addNetwork(network);
		
	}


	@Override
	public void initLandPropertyRegistry(LandPropertyRegistry lpr) {
		//prerequisute
		if(this.avplots.isEmpty())throw new NullPointerException("loadPlotsContext should be called before");
		if(this.avfarmers.isEmpty())throw new NullPointerException("loadFarmersContext should be called before");
		
		HashMap<Plot,HumanAgent> r = new HashMap<Plot, HumanAgent>();
		r.put(this.avplots.get(0), this.avfarmers.get(0));
		r.put(this.avplots.get(1), this.avfarmers.get(0));
		r.put(this.avplots.get(2), this.avfarmers.get(1));
		r.put(this.avplots.get(3), this.avfarmers.get(1));
		r.put(this.avplots.get(4), this.avfarmers.get(2));
		r.put(this.avplots.get(5), this.avfarmers.get(3));
		r.put(this.avplots.get(6), this.avfarmers.get(3));
		
		lpr.setOwnerRegistry(r);
		
		//load tenants
		HashMap<Plot,Farmer> r2 = new HashMap<Plot, Farmer>();
		lpr.setTenantRegistry(r2);
		
	}


	
	

}
