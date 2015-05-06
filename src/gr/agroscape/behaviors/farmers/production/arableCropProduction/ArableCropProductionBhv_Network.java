package gr.agroscape.behaviors.farmers.production.arableCropProduction;

import gr.agroscape.agents.Farmer;
import gr.agroscape.agents.Plot;
import gr.agroscape.behaviors.farmers.production.agriculturalActivities.ArableCropCultivation;
import gr.agroscape.behaviors.farmers.production.productionDecisions.AProductionDecision;
import gr.agroscape.behaviors.farmers.production.productionDecisions.ArableCropProductionDecision;
import gr.agroscape.contexts.FarmersContext;

import java.util.ArrayList;
import java.util.Collection;

import repast.simphony.engine.schedule.ScheduledMethod;
import repast.simphony.space.graph.Network;

/**
 * The farmer decides an arable crop according to what his connection to the "productionNetwork"@FarmersContext has decided
 * 
 * @author Dimitris Kremmydas 
 */
public class ArableCropProductionBhv_Network extends AArableCropProductionBhv {
	
	/**
	 * The network context
	 */
	private Network<Farmer> network;

	public ArableCropProductionBhv_Network(ArrayList<ArableCropCultivation> pC, long liquidity, Farmer f,ArableCropProductionBhvContext container) {
		super(pC, liquidity, f, container);
		this.network = FarmersContext.getInstance().getNetwork("productionNetwork");
		if(this.network == null) {throw new NullPointerException("'productionNetwork' not found in FarmersContext");}
	}

	/**
	 * His production decision is an imitation of the first connection of his network. <Br />
	 * @param plots
	 * @return
	 */
	@Override
	public Collection<? extends AProductionDecision> makeProductionDecision(Collection<Plot> plots) {
		
		ArrayList<ArableCropProductionDecision> r=new ArrayList<ArableCropProductionDecision>(); 
		
		//find the farmer's connection
		Iterable<Farmer> connections =  network.getAdjacent(this.owner);
		
		AArableCropProductionBhv connection_bhv = (AArableCropProductionBhv) this.container.findByFarmerOwner(connections.iterator().next());
		
		ArableCropCultivation decision = connection_bhv.lastProductionDecisions.get(0).getDecision();
		
		for (Plot p : plots) {
			r.add(new ArableCropProductionDecision(p, decision));
		}
		return r;
	}

	/**
	 * Do nothing in this behavior
	 */
	@Override
	void calculateExpectations() {
	}

	/*
	 * do production
	 */
	@ScheduledMethod (start=1,interval = 1, priority=1)
	public void handleProduction() {
		
		@SuppressWarnings("unchecked")
		ArrayList<ArableCropProductionDecision> pd =  (ArrayList<ArableCropProductionDecision>)this.makeProductionDecision(this.getCultivatingPlots());
		this.lastProductionDecisions =pd;
		
		System.err.println("Farmer(Net) " + this.owner.toString() + " Decisions:");
		System.err.println(pd.toString());
	}
	

	
	

}
