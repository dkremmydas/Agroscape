package tests;
import gr.agroscape.behaviors.farmers.production.agriculturalActivities.ArableCropCultivation;
import gr.agroscape.contexts.MainContext;
import gr.agroscape.main.ContextManager;
import gr.agroscape.utilities.ValueLayersUtilities;

import java.io.IOException;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.junit.Before;
import org.junit.Test;

import repast.simphony.context.DefaultContext;
import repast.simphony.engine.environment.DefaultScheduleRunner;
import repast.simphony.engine.environment.RunEnvironment;
import repast.simphony.engine.schedule.Schedule;
import repast.simphony.parameter.DefaultParameters;
import repast.simphony.valueLayer.ValueLayer;


public class MainContextTest {

	private ContextManager builder;
	private MainContext mainContext;
	

	@Before
	public void setUp() throws Exception {
		
		DefaultParameters p = new DefaultParameters();
		p.addParameter("gridWidth", "gridWidth", Integer.class, 31, false);
		p.addParameter("gridHeight", "gridHeight", Integer.class, 31, false);
		p.addParameter("ExcelDataFile", "ExcelDataFile", String.class, "freezedried_data\\dataToLoad.xlsx", false);
		
		RunEnvironment.init(new Schedule(), new DefaultScheduleRunner(), p, false);

		builder = new ContextManager ();
		this.mainContext = (MainContext) builder.build(new DefaultContext<Object> ());
		
	}
	
	@Test
	public void test_gvl_CropSuitability() throws InvalidFormatException, IOException {
		
		
		
		//ExcelDataLoader x = new ExcelDataLoader("C:\\Users\\Dimitris\\workspace\\AgroScape\\freezedried_data\\dataToLoad.xlsx") ;
		builder.step();
		this.mainContext.updateValueLayers();
		
		
		//System.err.println("getCropSuitability() works ?");
		//GridValueLayer vl = this.mainContext.getCropSuitability().get(this.mainContext.getCropsContext().getCropByName("maize"));
		//System.err.println(ValueLayers.getValueLayerAsPrintedMatrix(vl));
		
		System.err.println("gvl_CropSuitability works ?");
		
		ArableCropCultivation activeC = MainContext.getInstance().getActiveDisplaySuitabilityCrop();
		System.err.println(activeC);
		ValueLayer vl2 = this.mainContext.getValueLayer("CropSuitability");
		System.err.println("this.mainContext.getValueLayer('CropSuitability')" + " \n" + ValueLayersUtilities.getValueLayerAsPrintedMatrix(vl2));
		
		/*for (Iterator<ValueLayer> iterator = this.mainContext.getValueLayers().iterator(); iterator.hasNext();) {
			ValueLayer type =  iterator.next();
			System.err.println(type.getName());
			System.err.println(ValueLayers.getValueLayerAsPrintedMatrix(type));
		}*/
		
		//System.err.println(this.mainContext.getValueLayer("CropSuitability"));
		
		
		//Crop activeC = MainContext.getInstance().getActiveDisplaySuitabilityCrop();
		
		//System.err.println(activeC);
		//ValueLayer t = this.mainContext.getValueLayer("CropSuitability");
		//System.err.println(ValueLayers.getValueLayerAsPrintedMatrix(t));
		
	}

}
