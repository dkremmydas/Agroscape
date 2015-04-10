package gr.agroscape.production;

import gr.agroscape.agents.Farmer;
import gr.agroscape.agents.Plot;
import gr.agroscape.products.Product;

import java.util.Map;

public class TreeProductionDecision extends AProductionDecision {
	
	private int labour;
	private int fertilizers;
	private int petsicides;
	
	
	private TreeProductionDecision(Plot p) {
		super(p);
	}
	
	public TreeProductionDecision(Plot p, int labour, int fertilizers,
			int petsicides) {
		super(p);
		this.labour = labour;
		this.fertilizers = fertilizers;
		this.petsicides = petsicides;
	}


	@Override
	public void feedbackToFarmer(Farmer producers) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void feedbackToPlot(Plot p) {
		// do something here		
	}

	@Override
	public Map<Product, Float> productionRealization(IWeatherSuitability w) {
		// TODO Auto-generated method stub
		return null;
	}

	
	
	
}
