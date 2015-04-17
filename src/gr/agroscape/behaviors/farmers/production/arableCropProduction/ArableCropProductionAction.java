package gr.agroscape.behaviors.farmers.production.arableCropProduction;


import gr.agroscape.agents.Farmer;
import gr.agroscape.behaviors.farmers.AFarmerAction;
import gr.agroscape.behaviors.farmers.production.interfaces.AProductionDecision;

import java.util.ArrayList;

import repast.simphony.engine.schedule.ScheduledMethod;

public class ArableCropProductionAction extends AFarmerAction {
	
	
	private FarmerArableCropProducer containerFarmer;
	

	/**
	 * Constructor
	 * @param owner
	 */
	public ArableCropProductionAction(Farmer owner) {
		super(owner);
	}

	
	
	@Override
	public void setUpOwner() {
		
		this.containerFarmer = new FarmerArableCropProducer_MP(
					this.owner.getMainContext().getCropsContext().getAvailableCrops(),
					this.owner
		);
		
		

	}

	@Override
	public Object getAnnotatedClass() {
		// TODO Auto-generated method stub
		return this;
	}
	
	@ScheduledMethod()
	public void handleProduction() {
		ArrayList<AProductionDecision> pd = (ArrayList<AProductionDecision>) this.containerFarmer.makeProductionDecision(this.containerFarmer.getCultivatingPlots());
		
	}

}
