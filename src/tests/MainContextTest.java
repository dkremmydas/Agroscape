package tests;
import gr.agroscape.agents.Farmer;
import gr.agroscape.agents.Plot;
import gr.agroscape.behaviors.farmers.AFarmerBehavior;
import gr.agroscape.behaviors.farmers.stupido.StupidoBhv;
import gr.agroscape.contexts.FarmersContext;
import gr.agroscape.contexts.SimulationContext;
import gr.agroscape.main.ContextManager;

import org.junit.Before;
import org.junit.Test;

import repast.simphony.context.DefaultContext;
import repast.simphony.engine.environment.DefaultScheduleRunner;
import repast.simphony.engine.environment.RunEnvironment;
import repast.simphony.engine.schedule.Schedule;
import repast.simphony.parameter.DefaultParameters;
import repast.simphony.space.graph.Network;


public class MainContextTest {

	private ContextManager builder;
	private SimulationContext simulationContext;
	

	@Before
	public void setUp() throws Exception {
		
		DefaultParameters p = new DefaultParameters();
		p.addParameter("gridWidth", "gridWidth", Integer.class, 30, false);
		p.addParameter("gridHeight", "gridHeight", Integer.class, 30, false);
		p.addParameter("ExcelDataFile", "ExcelDataFile", String.class, "freezedried_data\\dataToLoad.v3.xlsx", false);
		
		RunEnvironment.init(new Schedule(), new DefaultScheduleRunner(), p, false);

		builder = new ContextManager ();
		this.simulationContext = (SimulationContext) builder.build(new DefaultContext<Object> ());
		
	}
	
	

	@Test
	public void testContextManager()  {
		System.err.println("testContextManager");
		System.err.println("Everything is loaded");
		System.err.println("Number of loaded objects: " + simulationContext.getFarmersContext().size());
		
		System.err.println("Number of Farmers: " + simulationContext.getFarmersContext().getObjects(Farmer.class).size());
		System.err.println("Number of Stupido Farmers: " + simulationContext.getFarmersContext().getObjects(StupidoBhv.class).size());
		System.err.println("Number of ABehavingFarmers: " + simulationContext.getFarmersContext().getObjects(AFarmerBehavior.class).size());
		
		System.err.println("Printing all farmers:");
		System.err.println("Farmer with ID=1, " + simulationContext.getFarmersContext().findFarmerById(1).toString());
		System.err.println("Farmer with ID=2, " + simulationContext.getFarmersContext().findFarmerById(2).toString());
		System.err.println("Farmer with ID=3, " + simulationContext.getFarmersContext().findFarmerById(3).toString());
		System.err.println("Farmer with ID=4, " + simulationContext.getFarmersContext().findFarmerById(4).toString());
		System.err.println("Farmer with ID=5, " + simulationContext.getFarmersContext().findFarmerById(5).toString());
		
		System.err.println("Testing network behavior");
		Network<Farmer> network = FarmersContext.getInstance().getNetwork("productionNetwork");
		
		Farmer ft = simulationContext.getFarmersContext().getObjects(Farmer.class).get(1);
		
		Iterable<Farmer> connections =  network.getAdjacent(ft);
		System.err.println("Farmer " + ft.toString() + ", has connections to:");
		for (Farmer f : connections) {
			System.err.println(f.toString());
		}
		
		
	}
	
	@Test
	public void testStep()  {
		//schedule should by now be loaded
		System.err.println("testStep");
		System.err.println("ContextBuilder Shecdule");
		System.err.println("Current Parameters: " + RunEnvironment.getInstance().getParameters().toString());
		System.err.println("Current # of Farmers in context: " + FarmersContext.getInstance().getObjects(Farmer.class).size());
		System.err.println("Current Schedule: " + RunEnvironment.getInstance().getCurrentSchedule().toString());
		System.err.println("Current Schedule, Number of Actions Scheduled: " + 
				RunEnvironment.getInstance().getCurrentSchedule().getActionCount());
		
		//RunEnvironment.getInstance().setScheduleTickDelay(10);
		
		//StupidoFarmer f = (StupidoFarmer) space.getFarmersContext().getBehavior("stupidoBehavior").getBehavingObject(0);
		//System.err.println("Got stupidofarmer: " + f.toString());
		

		//ArableCropProducer_MP f2 = (ArableCropProducer_MP) space.getFarmersContext().getBehavior("arableCropProducerBehavior").getBehavingObject(1);
		//System.err.println("Got ArableCropProducer: " + f2.toString());
		System.err.println("");
		System.err.println("Advanced 1st step, tick " + RunEnvironment.getInstance().getCurrentSchedule().getTickCount());		
		RunEnvironment.getInstance().getCurrentSchedule().execute();
		System.err.println("");
		
		System.err.println("Advanced 2nd step, tick " + RunEnvironment.getInstance().getCurrentSchedule().getTickCount());
		RunEnvironment.getInstance().getCurrentSchedule().execute();
		System.err.println("");
		
		System.err.println("Advanced 3nd step, tick " + RunEnvironment.getInstance().getCurrentSchedule().getTickCount());
		RunEnvironment.getInstance().getCurrentSchedule().execute();
		System.err.println("");
		
		System.err.println("Advanced 4th step, tick " + RunEnvironment.getInstance().getCurrentSchedule().getTickCount());
		RunEnvironment.getInstance().getCurrentSchedule().execute();
		System.err.println("");
		
		System.err.println("Advanced 5th step, tick " + RunEnvironment.getInstance().getCurrentSchedule().getTickCount());
		RunEnvironment.getInstance().getCurrentSchedule().execute();
		System.err.println("");
		
		System.err.println("Advanced 6th step, tick " + RunEnvironment.getInstance().getCurrentSchedule().getTickCount());
		RunEnvironment.getInstance().getCurrentSchedule().execute();
		System.err.println("");
	}
	
	
	@Test
	public void testPlotContext()  {
		Plot p = simulationContext.getPlotsContext().findPlotById(13);
		System.err.println(simulationContext.getPlotsContext().plotIdsStringMap());
		System.err.println(p.getCorners().toString());
		System.err.println("Adjacent" + simulationContext.getPlotsContext().findAdjacentPlots(p,2).toString());
	}

}
