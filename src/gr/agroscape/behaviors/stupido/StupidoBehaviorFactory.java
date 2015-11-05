package gr.agroscape.behaviors.stupido;

import gr.agroscape.behaviors.BehaviorContext;
import gr.agroscape.behaviors.BehaviorFactory;
import gr.agroscape.skeleton.agents.AgroscapeAgent;
import gr.agroscape.skeleton.agents.plot.Plot;
import gr.agroscape.skeleton.contexts.FarmersContext;
import gr.agroscape.skeleton.contexts.SimulationContext;

import java.util.ArrayList;
import java.util.List;

public class StupidoBehaviorFactory extends BehaviorFactory {
	
	private StupidoBehaviorContext bhvContext;

	 
	public StupidoBehaviorFactory() {
		super();
		this.name = "Stupido Behavior Factory";
		this.bhvContext = new StupidoBehaviorContext();
	}

	//abstract void assignBehavior(List<? extends AgroscapeAgent> agents);
	
	@Override
	public void assignBehavior(SimulationContext simulationContext) {
		
		//add the behavior to farmer agents
		FarmersContext farmers = (FarmersContext) simulationContext.getFarmersContext().getAllFarmers();	
		for (AgroscapeAgent f : farmers) {
			f.addBehavior(new StupidoBehavior(f, this.bhvContext, this));
		}
		
		//add properties to plot
		Iterable<Plot> plots = simulationContext.getPlotsContext().getAgentLayer(Plot.class);
		for (Plot p : plots) {
			p.addBehaviorProperty(this, new StupidoPlotIntegerProperty());
		}
		
	}


	
	@Override
	public BehaviorContext buildBehaviorContext() {
		return this.bhvContext;
	}

	@Override
	public List<AgroscapeAgent> getNewAgents() {
		return new ArrayList<AgroscapeAgent>();
	}

	@Override
	public String getBehaviorInformation() {
		// TODO Auto-generated method stub
		return "This is the Stupido Behavior. A test behavior";
	}





}
