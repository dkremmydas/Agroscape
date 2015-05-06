package gr.agroscape.behaviors.farmers.production.arableCropProduction;

import gr.agroscape.agents.Farmer;
import gr.agroscape.agents.Plot;
import gr.agroscape.behaviors.farmers.production.agriculturalActivities.ArableCropCultivation;
import gr.agroscape.behaviors.farmers.production.expectations.ExpectedCropPrices;
import gr.agroscape.behaviors.farmers.production.expectations.ExpectedPlotCropVarCost;
import gr.agroscape.behaviors.farmers.production.expectations.ExpectedPlotCropYield;
import gr.agroscape.behaviors.farmers.production.interfaces.ArableCropProductionDecision;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;

import org.apache.commons.math3.optim.PointValuePair;
import org.apache.commons.math3.optim.linear.LinearConstraint;
import org.apache.commons.math3.optim.linear.LinearConstraintSet;
import org.apache.commons.math3.optim.linear.LinearObjectiveFunction;
import org.apache.commons.math3.optim.linear.NonNegativeConstraint;
import org.apache.commons.math3.optim.linear.Relationship;
import org.apache.commons.math3.optim.linear.SimplexSolver;
import org.apache.commons.math3.optim.nonlinear.scalar.GoalType;

import repast.simphony.engine.schedule.ScheduledMethod;




/**
 * Farmer_MP = Farmer Mathematical Programming. <br />
 * This is an implementation of a Farmer that uses Mathematical Programming to make his ProductionDecision. 
 * <p>
 * The objective function is: 
 * </p>
 * 
 * @author Dimitris Kremmydas
 */
public class ArableCropProductionBhv_MP extends AArableCropProductionBhv {
	

	
	
	public ArableCropProductionBhv_MP(ArrayList<ArableCropCultivation> pC,
			long liquidity, Farmer f, ArableCropProductionBhvContext c) {
		super(pC, liquidity, f,c);
		
	}

	/**
     * The Solver that will attack the LP problem (Apache Commons Math 3)
     */

    static SimplexSolver ss = new SimplexSolver();
    
    /**
     * The Decoupled Payment Rights (�cent/h)
     */
    private long singlePaymentValue; // 
    
    /**
     * The expected yield for each Crop on each Plot (Crop->kg/h)
     */
    private ExpectedPlotCropYield expectedPlotCropYields;
    
    /**
     * The expected prices for each Crop (Crop->�cent)
     */
    private ExpectedCropPrices expectedCropPrices ;
    
    /**
     * The amount of coupled payment (Crop->�cent/h)
     */
    private HashMap<ArableCropCultivation,Long> coupledPayments = new HashMap<ArableCropCultivation,Long>();  
    
    /**
     * The expected variable cost of cultivating Crop to a Plot (Crop->�cent/h)
     */
    private ExpectedPlotCropVarCost expectedPlotCropVarCost;
    
    
      
    
    /**
     * Returns the mathematical programming Tableau of the potential decision. <br />
     * This method is actually doing the same steps with {@link #makeProductionDecision()}.  
     * @return
     */
    public String getMPtablaeu() {
    	String r ="";
    	this.calculateExpectations();
    	
    	//calculate objective function coefficients
		ArrayList<Plot> myPlots = this.getCultivatingPlots();
		double[] c = new double[this.potentialAgriculturalActivity.size()*myPlots.size()];
		
		for (int i = 0,n=0; i < this.potentialAgriculturalActivity.size(); i++) {
			for (int j = 0; j < myPlots.size(); j++) {
					double yield=this.expectedPlotCropYields.get(myPlots.get(j)).get(this.potentialAgriculturalActivity.get(i));
					double price=this.expectedCropPrices.get(this.potentialAgriculturalActivity.get(i));
					double coupled_subsidy = this.coupledPayments.get(this.potentialAgriculturalActivity.get(i));
					double varcost = this.expectedPlotCropVarCost.get(myPlots.get(j)).get(this.potentialAgriculturalActivity.get(i));
					c[n++]= ((yield*price)+coupled_subsidy-varcost);						
			}
			
		}
		
    	r += "obj:  " + Arrays.toString(c) + "\n";

    	//create land constraint
		double[] la = new double[this.potentialAgriculturalActivity.size()*myPlots.size()] ;
		Arrays.fill(la, 1);		
		r +="land: " + Arrays.toString(la) + " <= " + this.getCultivatingPlotArea() + "\n";
		
		//create liquidity constraint
		double[] ca = new double[this.potentialAgriculturalActivity.size()*myPlots.size()] ;
		for (int i = 0,n=0; i < this.potentialAgriculturalActivity.size(); i++) {
			for (int j = 0; j < myPlots.size(); j++) {
				double varcost = this.expectedPlotCropVarCost.get(myPlots.get(j)).get(this.potentialAgriculturalActivity.get(i));
				ca[n++]=varcost;
			}
		}
		r +="liq:  " + Arrays.toString(ca) + " <= " + this.liquidity + "\n";
		
    	return r;
    }
    
    /**
	 * Implementation of a Mathematical Programming Decision<br />
	 * The variables are the X(i,j) how much of each crop to put to a plot
	 * The objective function 
	 * 
	 * TODO: complete documentation
	 */    
	@Override
	public Collection<ArableCropProductionDecision> makeProductionDecision(Collection<Plot> plots) {
		
		this.calculateExpectations();
		
		ArrayList<Plot> myPlots = (ArrayList<Plot>) plots;
		
		//calculate objective function coefficients
		double[] c = new double[this.potentialAgriculturalActivity.size()*myPlots.size()];
		
		for (int i = 0,n=0; i < this.potentialAgriculturalActivity.size(); i++) {
			for (int j = 0; j < myPlots.size(); j++) {
					double yield=this.expectedPlotCropYields.get(myPlots.get(j)).get(this.potentialAgriculturalActivity.get(i));
					double price=this.expectedCropPrices.get(this.potentialAgriculturalActivity.get(i));
					double coupled_subsidy = this.coupledPayments.get(this.potentialAgriculturalActivity.get(i));
					double varcost = this.expectedPlotCropVarCost.get(myPlots.get(j)).get(this.potentialAgriculturalActivity.get(i));
					c[n++]= ((yield*price)+coupled_subsidy-varcost);						
			}
			
		}
		LinearObjectiveFunction f = new LinearObjectiveFunction(c, 0);
		//end calculation of c
		
		//constraints
		Collection<LinearConstraint> constraints = new
                ArrayList<LinearConstraint>();
		
		//create land constraint
		double[] la = new double[this.potentialAgriculturalActivity.size()*myPlots.size()] ;
		Arrays.fill(la, 1);		
		constraints.add(new LinearConstraint(la, Relationship.LEQ, this.getCultivatingPlotArea()));
		
		//create liquidity constraint
		double[] ca = new double[this.potentialAgriculturalActivity.size()*myPlots.size()] ;
		for (int i = 0,n=0; i < this.potentialAgriculturalActivity.size(); i++) {
			for (int j = 0; j < myPlots.size(); j++) {
				double varcost = this.expectedPlotCropVarCost.get(myPlots.get(j)).get(this.potentialAgriculturalActivity.get(i));
				ca[n++]=varcost;
			}
		}
		constraints.add(new LinearConstraint(ca, Relationship.LEQ, this.liquidity));
		//end constraints
		
		System.err.println(this.getMPtablaeu());
		
		//solve
	
		PointValuePair optSolution;
		ArrayList<ArableCropProductionDecision> r=new ArrayList<ArableCropProductionDecision>(); 
		
		try {
			optSolution = ArableCropProductionBhv_MP.ss.optimize( f,new LinearConstraintSet(constraints),GoalType.MAXIMIZE,new NonNegativeConstraint(true));
			
		    double[] solution= new double[this.potentialAgriculturalActivity.size()*myPlots.size()];
		    solution = optSolution.getPoint();
			
		    for (int j = 0,n=0; j < myPlots.size(); j++) {
		    	Plot pp = myPlots.get(j);
		    	double[] xc = new double[this.potentialAgriculturalActivity.size()];
		    	
		    	for (int i = 0; i < this.potentialAgriculturalActivity.size(); i++) {
					xc[i] = solution[n++];
				}
		    	int maxindex = this.findMaxIndex(xc);
		    	r.add(new ArableCropProductionDecision(pp, this.potentialAgriculturalActivity.get(maxindex)) );
		    }
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		
	    return r;		
	
	}
	
	
	private int findMaxIndex(double[] array) {
		double largest = array[0]; int index = 0;
		for (int i = 1; i < array.length; i++) {
		  if ( array[i] > largest ) {
		      largest = array[i];
		      index = i;
		   }
		}
		return index;
	}

	
	
	@Override
	public void calculateExpectations() {
		
		this.coupledPayments = this.owner.getMainContext().getPaymentAuthority().getCoupledPayments();
		this.singlePaymentValue = this.owner.getMainContext().getPaymentAuthority().getSinglePaymentValue(); 
		
		//load Expected Crop Prices
		this.expectedCropPrices = new ExpectedCropPrices(this.potentialAgriculturalActivity);		
		
		//Load Expected Plot-Crop Yield
		this.expectedPlotCropYields = new ExpectedPlotCropYield(this.getCultivatingPlots());
		
		//Load Expected Plot-Crop varCost
		this.expectedPlotCropVarCost = new ExpectedPlotCropVarCost(this.getCultivatingPlots());
	}
	
	
	public ExpectedCropPrices getExpectedCropPrices() {
		return this.expectedCropPrices;
	}

	public ExpectedPlotCropYield getExpectedPlotCropYields() {
		return expectedPlotCropYields;
	}

	public ExpectedPlotCropVarCost getExpectedPlotCropVarCost() {
		return expectedPlotCropVarCost;
	}


	@ScheduledMethod (start=1,interval = 360)
	public void handleProduction() {
		
		ArrayList<ArableCropProductionDecision> pd =  (ArrayList<ArableCropProductionDecision>)this.makeProductionDecision(this.getCultivatingPlots());
		this.lastProductionDecisions =pd;
		
		System.err.println(pd.toString());
	}
	
	
}
