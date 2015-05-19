package gr.agroscape.behaviors.farmers.production.arableCropProduction;

import gr.agroscape.agents.Farmer;
import gr.agroscape.agents.Plot;
import gr.agroscape.behaviors.farmers.production.agriculturalActivities.ArableCropCultivation;
import gr.agroscape.behaviors.farmers.production.productionDecisions.AProductionDecision;
import gr.agroscape.behaviors.farmers.production.productionDecisions.ArableCropProductionDecision;
import gr.agroscape.contexts.SimulationContext;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

import repast.simphony.engine.schedule.ScheduledMethod;

/**
 * This farmer decides in the following way: For each plot he owns, he plants whatever the plots close to this plots are.
 * 
 * @author Dimitris Kremmydas 
 */
public class ArableCropProductionBhv_Immitator extends AArableCropProductionBhv {
	



	public ArableCropProductionBhv_Immitator(ArrayList<ArableCropCultivation> pC, long liquidity, Farmer f,ArableCropProductionBhvContext container) {
		super(pC, liquidity, f, container);
	}

	/**
	 * For each plot he owns, he plants whatever the plots close to this plots are.
	 * @param plots
	 * @return
	 */
	@Override
	public Collection<? extends AProductionDecision> makeProductionDecision(Collection<Plot> plots) {
		
		ArrayList<ArableCropProductionDecision> r=new ArrayList<ArableCropProductionDecision>(); 
		
		
		for (Plot p : plots) {
			//for one plot, find adjacent plots
			ArrayList<Plot> adjPl = SimulationContext.getInstance().getPlotsContext().findAdjacentPlots(p,2);
			adjPl.removeAll(Collections.singleton(null));
			
			//get production decisions
			ArrayList<ArableCropProductionDecision> possiblePd = new ArrayList<>();
			for (Plot aplot : adjPl) {
				possiblePd.add(container.getCurrentProductionDecisions().get(aplot));			
			}		
			r.add(new ArableCropProductionDecision(p, AArableCropProductionBhv.findMostPopular(possiblePd)));
		}
		return r;
	}


	/*
	 * do production
	 */
	@ScheduledMethod (start=1,interval = 1, priority=10)
	public void handleProduction() {
		
		@SuppressWarnings("unchecked")
		ArrayList<ArableCropProductionDecision> pd =  (ArrayList<ArableCropProductionDecision>)this.makeProductionDecision(this.getCultivatingPlots());
		this.lastProductionDecisions =pd;
		this.container.updateContainerWithProductionDecisions(pd);
		
		System.err.println("Farmer(Immitator) " + this.owner.toString() + " Decisions:");
		System.err.println(pd.toString());
	}
	
	

}
