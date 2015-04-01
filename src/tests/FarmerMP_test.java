package tests;

import gr.agroscape.agents.Farmer_MP;
import gr.agroscape.contexts.MainContext;
import gr.agroscape.main.ContextManager;

import org.junit.Before;
import org.junit.Test;

import repast.simphony.context.DefaultContext;

public class FarmerMP_test {
	
	private ContextManager builder;
	private MainContext mainContext;

	@Before
	public void setUp() throws Exception {
		
		builder = new ContextManager ();
		this.mainContext = (MainContext) builder.build(new DefaultContext<Object> ());
		
	}

	@Test
	public void test() {
		System.out.println("Height: " + this.mainContext.getGridHeight() + ", Width:"+this.mainContext.getGridWidth());
		System.out.println((this.mainContext.getCropsContext().getAvailableCrops()).toString());
		
		System.out.println("Payment Authority: " + this.mainContext.getPaymentAuthority().toString());
		
		System.out.println("Is FarmersContext empty ? " + this.mainContext.getFarmersContext().isEmpty());
		
		Farmer_MP f = (Farmer_MP) this.mainContext.getFarmersContext().getRandomObject();
		System.out.println("Get a random farmer: " + f.toString());		
		
		f.calculateExpectations();
		System.out.println("Test expectedCropPrices: " + f.getExpectedCropPrices().toString());
		System.out.println("Test ExpectedPlotCropYield: " + f.getExpectedPlotCropYields().toString());
		System.out.println("Test getExpectedPlotCropVarCost: " + f.getExpectedPlotCropVarCost().toString());
		
		
		System.out.println("Get cultivated plots: " + f.getCultivatingPlots().toString());
		System.out.println("Cultivated plots Area: " + f.getCultivatingPlotArea());
		System.out.println("He can cultivate the following crops: " + f.getPotentialCrops().toString());
		
		System.out.println("His MP production decision tableau is:\n" + f.getMPtablaeu());
		
		System.out.println("He makes a production decision: " + f.makeProductionDecision());
	}

}
