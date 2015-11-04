package gr.agroscape.behaviors.farmers.production.arableCropProduction;

import gr.agroscape.agents.human.Farmer;
import gr.agroscape.agents.plot.Plot;
import gr.agroscape.behaviors.BehaviorContext;
import gr.agroscape.behaviors.IScheduledBehavior;
import gr.agroscape.behaviors.IScheduledBehaviorDataLoader;
import gr.agroscape.behaviors.farmers.production.agriculturalActivities.ArableCropCultivation;
import gr.agroscape.behaviors.farmers.production.productionDecisions.ArableCropProductionDecision;
import gr.agroscape.behaviors.farmers.production.products.Product;
import gr.agroscape.contexts.SimulationContext;
import gr.agroscape.utilities.GridValueLayerFunction;
import gr.agroscape.utilities.ValueLayersUtilities;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import repast.simphony.engine.schedule.ScheduledMethod;
import repast.simphony.space.grid.GridPoint;
import repast.simphony.space.grid.StrictBorders;
import repast.simphony.valueLayer.GridValueLayer;

public class ArableCropProductionBhvContext extends BehaviorContext<AArableCropProductionBhv> {

	private GridValueLayer gvl_ProductionDecisions;
	
	private ArrayList<ArableCropCultivation> availableCrops;
	
	private HashMap<ArableCropCultivation, GridValueLayer> cropSuitabilities=new HashMap<ArableCropCultivation, GridValueLayer>();
	
	/**
	 * Keeps a record of all current ArableCropCultivation production decisions for each plot
	 */
	private HashMap<Plot,ArableCropProductionDecision> currentProductionDecisions = new HashMap<>();
	
	private HashMap<ArableCropCultivation, Integer> arableCropProductionDecisionAggregate = new HashMap<>();
	
	/**
	 * Takes a HashMap of KEY=class extending AArableCropProductionBhv, VALUE=Collection of farmers, and adds them to the BhvContext 
	 * @param owners
	 */
	public ArableCropProductionBhvContext(IScheduledBehaviorDataLoader<AArableCropProductionBhv> objectLoader) {
		super("ArableCropProductionBehavior",objectLoader);
		this.availableCrops =new ArrayList<>();
		
		this.gvl_ProductionDecisions = new GridValueLayer("ProductionDecisions",true,new StrictBorders(), SimulationContext.getInstance().getGridWidth(),  SimulationContext.getInstance().getGridHeight());
		SimulationContext.getInstance().addValueLayer( gvl_ProductionDecisions );
	}
	
	/**
	 * Constructor with the default dataLoader
	 * @param owners
	 */
	public ArableCropProductionBhvContext(HashMap<Class<? extends AArableCropProductionBhv>,Collection<Farmer>> owners) {
		this(new DefaultArableProductionBhvContextLoader(owners));
	}
	

	public ArrayList<ArableCropCultivation> getAvailableCrops() {
		return this.availableCrops;
	}
	
	public ArableCropCultivation getCropByName(String n) {
		ArrayList<ArableCropCultivation> crops = this.getAvailableCrops();
		for (ArableCropCultivation crop : crops) {
			if(crop.getName().equalsIgnoreCase(n)) return crop;
		}
		return null;
	}
	
	/**
	 * Getter for {@link #cropSuitabilities}
	 * @return
	 */
	public HashMap<ArableCropCultivation, GridValueLayer> getCropSuitabilities() {
		return cropSuitabilities;
	}
	
	/**
	 * Get the average suitability ofr a crop and a {@link Plot}.
	 * @param c
	 * @return
	 */
	public double getAverageCropPlotSuitability(ArableCropCultivation c, Plot p) {
		GridValueLayer gvl = this.getCropSuitabilities().get(c);
		return p.getAverage(gvl);
	}

	/**
	 *  Update production Decisions ValueLayer
	 * @return
	 */
	public void updateContainerWithProductionDecisions(ArrayList<ArableCropProductionDecision> pd) {
		for (ArableCropProductionDecision d : pd) {
			d.getPlot().setGridValueLayer(this.gvl_ProductionDecisions, d.getDecision().getId());
			this.currentProductionDecisions.put(d.getPlot(), d);
		}
		this.updateCropSuitabilityLayer(pd);
		
		
		
	}

	/**
	 * for each valueLayer diminish the CropSuitability by half
	 * @param pd
	 */
	private void updateCropSuitabilityLayer(ArrayList<ArableCropProductionDecision> pd) {
		for (ArableCropProductionDecision d : pd) {
			Plot p = d.getPlot();
			ArableCropCultivation c = d.getDecision();
			
			GridValueLayerFunction decrease = new GridValueLayerFunction() {
				@Override
				public void apply(GridValueLayer vl, ArrayList<GridPoint> appliedPoints) {
					for (GridPoint point : appliedPoints) {
						vl.set(0.8*vl.get(point.getX(),point.getY()), point.getX(),point.getY());
					}					
				}
			};
			
			GridValueLayerFunction increase = new GridValueLayerFunction() {
				@Override
				public void apply(GridValueLayer vl, ArrayList<GridPoint> appliedPoints) {
					for (GridPoint point : appliedPoints) {
						if(1.2*vl.get(point.getX(),point.getY())>1) {
							vl.set(1d, point.getX(),point.getY());
						}
						else {
							vl.set(1.2*vl.get(point.getX(),point.getY()), point.getX(),point.getY());
						}						
					}					
				}
			};
			
			for (ArableCropCultivation cc : this.getAvailableCrops()) {
				if(cc.equals(c)) {p.updateGridValueLayer(this.cropSuitabilities.get(c), decrease);}
				else {p.updateGridValueLayer(this.cropSuitabilities.get(c), increase);}
			}
		
		}
	}
	
	/**
	 * Getter
	 * @return
	 */
	public HashMap<Plot,ArableCropProductionDecision> getCurrentProductionDecisions() {
		return currentProductionDecisions;
	}
	
	@ScheduledMethod(start=1,interval=1,priority=5)
	public void printInfo() {
		
		
		//update aggregate
		arableCropProductionDecisionAggregate.clear();
		ArrayList<ArableCropProductionDecision> decs = new ArrayList<>(this.currentProductionDecisions.values());
		for (ArableCropProductionDecision d : decs) {
			if(arableCropProductionDecisionAggregate.containsKey(d.getDecision())) {
				arableCropProductionDecisionAggregate.put(d.getDecision(), arableCropProductionDecisionAggregate.get(d.getDecision()) + (int) d.getPlot().getArea());
			}
			else {
				arableCropProductionDecisionAggregate.put(d.getDecision(), (int) d.getPlot().getArea());
			}			
		}
		//ArableCropProductionDecisionAggregate
				
		System.err.println("Current Production Decisions Aggregate:");
		System.err.println(arableCropProductionDecisionAggregate);
	}
	
	

} //end class



/**
 * Inner class to load stupido data
 * @author Dimitris Kremmydas
 *
 */
class DefaultArableProductionBhvContextLoader implements IScheduledBehaviorDataLoader<AArableCropProductionBhv> {

	private HashMap<Class<? extends AArableCropProductionBhv>,Collection<Farmer>> owners;
	private SimulationContext space;
	
	
	public DefaultArableProductionBhvContextLoader(HashMap<Class<? extends AArableCropProductionBhv>,Collection<Farmer>> owners) {
		super();
		this.owners = owners;
		this.space = SimulationContext.getInstance();

	}



	@Override
	public void setup(BehaviorContext<AArableCropProductionBhv> container) {
		
		if(((ArableCropProductionBhvContext)container).getAvailableCrops().isEmpty()) {
			this.loadNonAgents(container);
		}
		
		this.addAgents(container);	
		
	}
	
	
	
	private void addAgents(BehaviorContext<AArableCropProductionBhv> container) {
		Collection<IScheduledBehavior<AArableCropProductionBhv>> r = new ArrayList<IScheduledBehavior<AArableCropProductionBhv>>();
		
		for (Map.Entry<Class<? extends AArableCropProductionBhv>, Collection<Farmer>> entry : owners.entrySet()) {
			if(entry.getKey() == ArableCropProductionBhv_MP.class) {
				for(Farmer f : entry.getValue()) {
					r.add(new ArableCropProductionBhv_MP(((ArableCropProductionBhvContext)container).getAvailableCrops(),1000,(Farmer)f,(ArableCropProductionBhvContext) container));
				}
			}
			else if (entry.getKey() == ArableCropProductionBhv_Network.class) {
				for(Farmer f : entry.getValue()) {
					r.add(new ArableCropProductionBhv_Network(((ArableCropProductionBhvContext)container).getAvailableCrops(),1000,(Farmer)f,(ArableCropProductionBhvContext) container));
				}
			}
			else  {
				throw new NullPointerException("Only ArableCropProductionBhv_Network.class and ArableCropProductionBhv_MP.class are currently supported");
			}
		}
		
		container.addAll(r);
	}

	private void loadNonAgents(BehaviorContext<AArableCropProductionBhv> container) {
		//create crops
		ArableCropCultivation c1 = new ArableCropCultivation("maize", new Product("maize product"));
		ArableCropCultivation c2 = new ArableCropCultivation("wheat", new Product("wheat product"));
		ArableCropCultivation c3 = new ArableCropCultivation("cotton", new Product("cotton product"));
		
		//add those crops
		((ArableCropProductionBhvContext)container).getAvailableCrops().add(c1);
		((ArableCropProductionBhvContext)container).getAvailableCrops().add(c2);
		((ArableCropProductionBhvContext)container).getAvailableCrops().add(c3);
		
		//load crop suitability, all value equals to 1		
		for (ArableCropCultivation c : ((ArableCropProductionBhvContext)container).getAvailableCrops()) {
			GridValueLayer gv = new GridValueLayer("CropSuitability_"+c.getName(), true, space.getGridWidth(),space.getGridHeight());
			for (int i = 0; i < gv.getDimensions().getWidth(); i++) {
				for (int j = 0; j <  gv.getDimensions().getHeight(); j++) {
					gv.set(1d, i,j);
				}
			}
			((ArableCropProductionBhvContext)container).getCropSuitabilities().put(c, gv);
		}
		
		//load PaymentAuthority couple payments
		for (ArableCropCultivation c : ((ArableCropProductionBhvContext)container).getAvailableCrops()) {
			space.getPaymentAuthority().getCoupledPayments().put(c, 0l);
		}
		
		//add a CropDecisions Value Layer
		//SimulationContext.getInstance().addValueLayer(new GridValueLayer("ProductionDecisions",true,space.getGridWidth(),space.getGridWidth()));
	}
	
	

	
} //end class
