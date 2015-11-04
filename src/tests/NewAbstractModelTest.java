package tests;

import gr.agroscape.main.ContextManager;
import gr.agroscape.skeleton.agents.human.Farmer;
import gr.agroscape.skeleton.contexts.FarmersContext;
import gr.agroscape.skeleton.contexts.SimulationContext;

import org.junit.Before;
import org.junit.Test;

import repast.simphony.context.DefaultContext;
import repast.simphony.engine.environment.DefaultScheduleRunner;
import repast.simphony.engine.environment.RunEnvironment;
import repast.simphony.engine.schedule.Schedule;
import repast.simphony.parameter.DefaultParameters;

public class NewAbstractModelTest {
	
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
	public void test() {
		System.err.println("NewAbstractModelTest");
		
		//schedule should by now be loaded
		System.err.println("testStep");
		System.err.println("ContextBuilder Shecdule");
		System.err.println("Current Parameters: " + RunEnvironment.getInstance().getParameters().toString());
		System.err.println("Current # of Farmers in context: " + FarmersContext.getInstance().getObjects(Farmer.class).size());
		System.err.println("Current Schedule: " + RunEnvironment.getInstance().getCurrentSchedule().toString());
		System.err.println("Current Schedule, Number of Actions Scheduled: " + 
				RunEnvironment.getInstance().getCurrentSchedule().getActionCount());
				
		
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
	}

}
