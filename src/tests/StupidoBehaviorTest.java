package tests;

import static org.junit.Assert.assertTrue;
import gr.agroscape.behaviors.stupido.StupidoBehaviorFactory;
import gr.agroscape.main.ContextManager;
import gr.agroscape.skeleton.agents.human.Farmer;
import gr.agroscape.skeleton.agents.plot.Plot;
import gr.agroscape.skeleton.agents.plot.PlotUtils;
import gr.agroscape.skeleton.contexts.FarmersContext;
import gr.agroscape.skeleton.contexts.SimulationContext;

import org.junit.Before;
import org.junit.Test;

import repast.simphony.context.DefaultContext;
import repast.simphony.engine.environment.DefaultScheduleRunner;
import repast.simphony.engine.environment.RunEnvironment;
import repast.simphony.engine.schedule.Schedule;
import repast.simphony.parameter.DefaultParameters;

public class StupidoBehaviorTest {
	
	private ContextManager builder;
	private SimulationContext simulationContext;
	

	@Before
	public void setUp() throws Exception {
		
		DefaultParameters p = new DefaultParameters();
		p.addParameter("gridWidth", "gridWidth", Integer.class, 30, false);
		p.addParameter("gridHeight", "gridHeight", Integer.class, 30, false);
		
		RunEnvironment.init(new Schedule(), new DefaultScheduleRunner(), p, false);

		builder = new ContextManager ();
		this.simulationContext = (SimulationContext) builder.build(new DefaultContext<Object> ());
		
	}

	@Test
	public void simulationContextCreation() {
		assertTrue("Simulation Context Created !", true);
	}
	
	@Test
	public void newRectanglePlotTest() {
		Plot p = PlotUtils.newRectanglePlot(1, 1, 3, 3);
		System.out.println(p.toString());
	}
	
	@Test
	public void addSkeletonAgents() {
			
		this.simulationContext.getFarmersContext().add( new Farmer());
		this.simulationContext.getFarmersContext().add( new Farmer());
		this.simulationContext.getFarmersContext().add( new Farmer());
		
		this.simulationContext.getPlotsContext().add(
				PlotUtils.newRectanglePlot(1, 1, 3, 3)
		);
		this.simulationContext.getPlotsContext().add(
				PlotUtils.newRectanglePlot(1, 4, 2, 6)
		);
		
		System.out.println(this.simulationContext.getFarmersContext().toString());
		System.out.println(this.simulationContext.getPlotsContext().toString());
		
		
		assertTrue("Agents added !", true);
	}
	
	@Test
	public void addStupidoBehavior() {
			
		this.simulationContext.getFarmersContext().add( new Farmer(1));
		this.simulationContext.getFarmersContext().add( new Farmer(2));
		this.simulationContext.getFarmersContext().add( new Farmer(3));
		
		this.simulationContext.getPlotsContext().add(
				PlotUtils.newRectanglePlot(1, 1, 3, 3)
		);
		this.simulationContext.getPlotsContext().add(
				PlotUtils.newRectanglePlot(1, 4, 2, 6)
		);
		
		System.out.println(this.simulationContext.getFarmersContext().toString());
		System.out.println(this.simulationContext.getPlotsContext().toString());
		
		
		StupidoBehaviorFactory sbf = new StupidoBehaviorFactory();		
		sbf.assignBehavior(this.simulationContext.getFarmersContext().getAgentLayer(Farmer.class));
		
		//test simulation environment
		
		//init simulation
		System.err.println("Current Parameters: " + RunEnvironment.getInstance().getParameters().toString());
		System.err.println("Current # of Farmers in context: " + FarmersContext.getInstance().getObjects(Farmer.class).size());
		System.err.println("Current Schedule: " + RunEnvironment.getInstance().getCurrentSchedule().toString());
		System.err.println("Current Schedule, Number of Actions Scheduled: " + 
				RunEnvironment.getInstance().getCurrentSchedule().getActionCount());
		
		//1st step
//		System.err.println("");
//		System.err.println("Advanced 1st step, tick " + RunEnvironment.getInstance().getCurrentSchedule().getTickCount());		
//		RunEnvironment.getInstance().getCurrentSchedule().execute();
//		System.err.println("");
		
		
		
		assertTrue("Agents added !", true);
	}
	

}
