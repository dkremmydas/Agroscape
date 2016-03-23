package gr.agroscape.external.behaviors.risk;

import gr.agroscape.behaviors.Behavior;
import gr.agroscape.behaviors.BehaviorFactory;
import gr.agroscape.skeleton.agents.AgroscapeAgent;
import gr.agroscape.skeleton.agents.human.Farmer;

import java.util.ArrayList;

import repast.simphony.context.Context;

public class FarmerBehavior extends Behavior{

	private long budgetConstraint;
	private Farmer f;
	private Prospect p;
	
	private long[][] payoff = new long[9][5];
	
	
	public FarmerBehavior(BehaviorFactory bhvFactory,
			AgroscapeAgent owner, Context<?> bhvContext) {
		super("RiskFarmerBehavior", bhvFactory, owner, bhvContext);

		this.f = (Farmer)this.getOwner();
		this.p = new Prospect();
		
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
	
	public long getCash() {
		return this.f.getAccount().getCash();
	}
	
	class PayoffCalculator {
		
	}
	
	
	class Prospect {
		// 0: -80%, 1: -50%, 2: -25%, 3: -1-%
		// 4: 0, 5: +10%, 6: +20%, 7: +50% 8: +60%, 9: +80%
		private double probs[] = new double[9];
		private double incomes[] = {1,2,3,4,5,6,7,8,9,10};

		public Prospect() {
			//initialize all with equal probability
			for(int i=0;i<9;i++) this.probs[i] = .1;
		}
		
		public void update(ArrayList<Long> history) {
			
		}
		
		public double getExpectedIncomeDiff() {
			double est = 0 ;
			for(int i=0;i<9;i++) {
				est =+ (probs[i]*incomes[i]);
			}		
			return est;
		}

		public double[] getProbs() {
			return probs;
		}
		
		
		
	}
	
	

}
