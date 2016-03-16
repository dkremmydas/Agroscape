package gr.agroscape.behaviors.risk;

import gr.agroscape.behaviors.Behavior;
import gr.agroscape.behaviors.BehaviorFactory;
import gr.agroscape.skeleton.agents.AgroscapeAgent;
import gr.agroscape.skeleton.agents.human.Farmer;

import java.util.ArrayList;

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
	
	
	
	class Prospect {
		// 0: -80%, 1: -50%, 2: -25%, 3: -1-%
		// 4: 0, 5: +10%, 6: +20%, 7: +50% 8: +60%, 9: +80%
		private double probs[] = new double[9];
		private double incomeDiffs[] = {-0.8,-0.5,-0.25,-0.1,0.0,0.1,0.25,0.35,0.5,0.8};

		public Prospect() {
			//initialize all with equal probability
			for(int i=0;i<9;i++) this.probs[i] = .1;
		}
		
		public void update(ArrayList<Long> history) {
			
		}
		
		public double getExpectedIncomeDiff() {
			double est = 0 ;
			for(int i=0;i<9;i++) {
				est =+ (probs[i]*incomeDiffs[i]);
			}		
			return est;
		}
		
	}
	
	/**
	 * From 0 - 100
	 * 
	 * @author Dimitris Kremmydas
	 *
	 */
	class Utility {
		
		
		public int getUtility(Prospect p) {
			return 100;
		}
	}
	
	

}
