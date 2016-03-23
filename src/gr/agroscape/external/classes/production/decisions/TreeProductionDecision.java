package gr.agroscape.external.classes.production.decisions;

import gr.agroscape.skeleton.agents.plot.Plot;

public class TreeProductionDecision extends ProductionDecision {
	
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


	
	
	
	
}
