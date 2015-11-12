package gr.agroscape.behaviors.stupido;

import gr.agroscape.behaviors.BehaviorContext;
import gr.agroscape.behaviors.BehaviorFactory;
import gr.agroscape.skeleton.agents.AgroscapeAgent;
import gr.agroscape.skeleton.agents.human.Farmer;
import gr.agroscape.skeleton.agents.plot.Plot;
import gr.agroscape.skeleton.contexts.SimulationContext;

import java.util.ArrayList;
import java.util.List;

import repast.simphony.engine.environment.RunEnvironment;
import repast.simphony.space.grid.StrictBorders;
import repast.simphony.valueLayer.GridValueLayer;

public class StupidoBehaviorFactory extends BehaviorFactory {
	
	private StupidoBehaviorContext bhvContext;
	

	 
	public StupidoBehaviorFactory() {
		super();
		Integer w = RunEnvironment.getInstance().getParameters().getInteger("gridWidth");
		Integer h = RunEnvironment.getInstance().getParameters().getInteger("gridHeight");
		
		this.name = "Stupido Behavior Factory";
		this.bhvContext = new StupidoBehaviorContext(
								new GridValueLayer("StupidoGridValues", 0.0, true, new StrictBorders() ,w ,h));
	}

	@Override
	public void assignBehavior(SimulationContext simulationContext) {
		
		//add the behavior to farmer agents
		Iterable<Farmer> farmers = simulationContext.getFarmersContext().getAllFarmers();	
		for (AgroscapeAgent f : farmers) {
			f.addBehavior(new StupidoBehavior(f, this.bhvContext, this));
		}
		
		//add properties to plot
		Iterable<Plot> plots = simulationContext.getPlotsContext().getAgentLayer(Plot.class);
		for (Plot p : plots) {
			p.addBehaviorProperty(new StupidoPlotIntegerProperty());
		}
		
	}


	
	@Override
	public BehaviorContext buildBehaviorContext() {
		this.bhvContext.addProjection(SimulationContext.getInstance().getSpace().getSpace());
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
