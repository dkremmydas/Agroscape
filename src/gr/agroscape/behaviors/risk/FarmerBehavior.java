package gr.agroscape.behaviors.risk;

import gr.agroscape.behaviors.Behavior;
import gr.agroscape.behaviors.BehaviorFactory;
import gr.agroscape.skeleton.agents.AgroscapeAgent;
import gr.agroscape.skeleton.agents.human.Farmer;
import repast.simphony.context.Context;

public class FarmerBehavior extends Behavior{

	private long budgetConstraint;
	private Farmer f;
	
	
	public FarmerBehavior(BehaviorFactory bhvFactory,
			AgroscapeAgent owner, Context<?> bhvContext) {
		super("RiskFarmerBehavior", bhvFactory, owner, bhvContext);

		this.f = (Farmer)this.getOwner();
		this.budgetConstraintCalculation();
		
	}

	
	
	/**
	 * Calculates the new budget constraint
	 */
	public void budgetConstraintCalculation() {
		this.budgetConstraint = f.getAccount().getCash() / 2;
	}

	/**
	 * Getter
	 * @return
	 */
	public long getBudgetConstraint() {
		return budgetConstraint;
	}
	
	

}
