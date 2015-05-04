package tests;
import gr.agroscape.agents.Farmer;
import gr.agroscape.behaviors.farmers.ABehavingFarmer;
import gr.agroscape.behaviors.farmers.stupido.StupidoFarmer;
import gr.agroscape.contexts.Space;
import gr.agroscape.main.ContextManager;

import org.junit.Before;
import org.junit.Test;

import repast.simphony.context.DefaultContext;
import repast.simphony.engine.environment.DefaultScheduleRunner;
import repast.simphony.engine.environment.RunEnvironment;
import repast.simphony.engine.schedule.Schedule;
import repast.simphony.parameter.DefaultParameters;


public class MainContextTest {

	private ContextManager builder;
	private Space space;
	

	@Before
	public void setUp() throws Exception {
		
		DefaultParameters p = new DefaultParameters();
		p.addParameter("gridWidth", "gridWidth", Integer.class, 31, false);
		p.addParameter("gridHeight", "gridHeight", Integer.class, 31, false);
		p.addParameter("ExcelDataFile", "ExcelDataFile", String.class, "freezedried_data\\dataToLoad.xlsx", false);
		
		RunEnvironment.init(new Schedule(), new DefaultScheduleRunner(), p, false);

		builder = new ContextManager ();
		this.space = (Space) builder.build(new DefaultContext<Object> ());
		
	}
	
	@Test
	public void testContextManager()  {
		System.err.println("testContextManager");
		System.err.println("Everything is loaded");
		System.err.println("Number of loaded objects: " + space.getFarmersContext().size());
		
		System.err.println("Number of Farmers: " + space.getFarmersContext().getObjects(Farmer.class).size());
		System.err.println("Number of Stupido Farmers: " + space.getFarmersContext().getObjects(StupidoFarmer.class).size());
		System.err.println("Number of ABehavingFarmers: " + space.getFarmersContext().getObjects(ABehavingFarmer.class).size());
		
	}
	
	@Test
	public void testStep()  {
		//schdule should by now be loaded
		System.err.println("testStep");
		System.err.println("ContextBuilder Shecdule");
		System.err.println("Current Parameters: " + RunEnvironment.getInstance().getParameters().toString());
		System.err.println("Current Schedule: " + RunEnvironment.getInstance().getCurrentSchedule().toString());
		System.err.println("Current Schedule, Number of Actions Scheduled: " + 
				RunEnvironment.getInstance().getCurrentSchedule().getActionCount());
		
		//StupidoFarmer f = (StupidoFarmer) space.getFarmersContext().getBehavior("stupidoBehavior").getBehavingObject(0);
		//System.err.println("Got stupidofarmer: " + f.toString());
		

		//ArableCropProducer_MP f2 = (ArableCropProducer_MP) space.getFarmersContext().getBehavior("arableCropProducerBehavior").getBehavingObject(1);
		//System.err.println("Got ArableCropProducer: " + f2.toString());

		System.err.println("Advanced 1st step");		
		RunEnvironment.getInstance().getCurrentSchedule().execute();
		
		System.err.println("Advanced 2nd step");
		RunEnvironment.getInstance().getCurrentSchedule().execute();
		
		System.err.println("Advanced 3nd step");
		RunEnvironment.getInstance().getCurrentSchedule().execute();
		
		System.err.println("Advanced 4th step");
		RunEnvironment.getInstance().getCurrentSchedule().execute();

		
	}

}
