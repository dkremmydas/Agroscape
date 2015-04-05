package tests;
import static org.junit.Assert.*;

import java.beans.PropertyChangeListener;
import java.io.IOException;

import gr.agroscape.contexts.MainContext;
import gr.agroscape.dataLoaders.ExcelDataLoader;
import gr.agroscape.main.ContextManager;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.junit.Before;
import org.junit.Test;

import repast.simphony.context.DefaultContext;
import repast.simphony.engine.environment.DefaultScheduleRunner;
import repast.simphony.engine.environment.RunEnvironment;
import repast.simphony.engine.schedule.Schedule;
import repast.simphony.parameter.DefaultParameters;
import repast.simphony.parameter.Parameters;
import repast.simphony.parameter.Schema;


public class DataLoaderTests {

	private ContextManager builder;
	private MainContext mainContext;
	

	@Before
	public void setUp() throws Exception {
		
		DefaultParameters p = new DefaultParameters();
		p.addParameter("gridWidth", "gridWidth", Integer.class, 31, false);
		p.addParameter("gridHeight", "gridHeight", Integer.class, 31, false);
		
		RunEnvironment.init(new Schedule(), new DefaultScheduleRunner(), p, false);

		builder = new ContextManager ();
		this.mainContext = (MainContext) builder.build(new DefaultContext<Object> ());
		
	}
	
	@Test
	public void testExcelDataLoader() throws InvalidFormatException, IOException {
		//ExcelDataLoader x = new ExcelDataLoader("C:\\Users\\Dimitris\\workspace\\AgroScape\\freezedried_data\\dataToLoad.xlsx") ;
		
		System.err.println(MainContext.getInstance().getPlotsContext().getAvailablePlots());
		
	}

}
