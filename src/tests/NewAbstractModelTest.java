package tests;

import static org.junit.Assert.fail;
import gr.agroscape.contexts.SimulationContext;
import gr.agroscape.main.ContextManager;

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
	}

}
