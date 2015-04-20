package tests;

import gr.agroscape.behaviors.farmers.production.arableCropProduction.ArableCropFarmer_MP;
import gr.agroscape.behaviors.farmers.production.interfaces.AProductionDecision;
import gr.agroscape.contexts.MainContext;
import gr.agroscape.main.ContextManager;

import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;

import repast.simphony.context.DefaultContext;
import repast.simphony.engine.environment.DefaultScheduleRunner;
import repast.simphony.engine.environment.RunEnvironment;
import repast.simphony.engine.schedule.Schedule;
import repast.simphony.parameter.DefaultParameters;

public class FarmerMP_test {
	
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
	public void test() {
		System.out.println("\nHeight: " + this.mainContext.getGridHeight() + ", Width:"+this.mainContext.getGridWidth());
		System.out.println((this.mainContext.getCropsContext().getAvailableCrops()).toString());
		
		System.out.println("Payment Authority: " + this.mainContext.getPaymentAuthority().toString());
		
		System.out.println("Is FarmersContext empty ? " + this.mainContext.getFarmersContext().isEmpty());
		
		ArableCropFarmer_MP f = (ArableCropFarmer_MP) this.mainContext.getFarmersContext().getRandomObject();
		System.out.println("Get a random farmer: " + f.toString());		
		
		f.calculateExpectations();
		System.out.println("Test expectedCropPrices: " + f.getExpectedCropPrices().toString());
		System.out.println("Test ExpectedPlotCropYield: " + f.getExpectedPlotCropYields().toString());
		System.out.println("Test getExpectedPlotCropVarCost: " + f.getExpectedPlotCropVarCost().toString());
		
		
		System.out.println("Get cultivated plots: " + f.getCultivatingPlots().toString());
		System.out.println("Cultivated plots Area: " + f.getCultivatingPlotArea());
		System.out.println("He can cultivate the following crops: " + f.getPotentialAgriculturalActivity().toString());
		
		System.out.println("His MP production decision tableau is:\n" + f.getMPtablaeu());
		
		ArrayList<AProductionDecision> pr = (ArrayList<AProductionDecision>) f.makeProductionDecision(f.getCultivatingPlots());
		System.out.println("He makes a production decision: " + pr);
		//System.out.println("The aggregate production is: " + f.getThisStepProduction());
		
	}

}
