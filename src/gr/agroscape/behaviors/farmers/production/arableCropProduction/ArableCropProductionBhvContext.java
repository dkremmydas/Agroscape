package gr.agroscape.behaviors.farmers.production.arableCropProduction;

import gr.agroscape.agents.Farmer;
import gr.agroscape.agents.Plot;
import gr.agroscape.behaviors.ABehaviorContext;
import gr.agroscape.behaviors.IScheduledBehavior;
import gr.agroscape.behaviors.IScheduledBehaviorDataLoader;
import gr.agroscape.behaviors.farmers.production.agriculturalActivities.ArableCropCultivation;
import gr.agroscape.behaviors.farmers.production.products.Product;
import gr.agroscape.contexts.SimulationContext;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import repast.simphony.valueLayer.GridValueLayer;

public class ArableCropProductionBhvContext extends ABehaviorContext<AArableCropProductionBhv> {

	
	private ArrayList<ArableCropCultivation> availableCrops;
	
	private HashMap<ArableCropCultivation, GridValueLayer> cropSuitabilities=new HashMap<ArableCropCultivation, GridValueLayer>();
	
	/**
	 * Takes a HashMap of KEY=class extending AArableCropProductionBhv, VALUE=Collection of farmers, and adds them to the BhvContext 
	 * @param owners
	 */
	public ArableCropProductionBhvContext(HashMap<Class<? extends AArableCropProductionBhv>,Collection<Farmer>> owners) {
		super("ArableCropProductionBehavior",null);
		this.availableCrops = new ArrayList<>();
		this.objectLoader = new DefaultArableProductionBhvContextLoader(owners);
		this.loadBehavingObjects();  
	}
	
	public ArableCropProductionBhvContext(Collection<? super Farmer> owners,IScheduledBehaviorDataLoader<AArableCropProductionBhv> objectLoader) {
		super("ArableCropProductionBehavior",objectLoader);
		this.availableCrops =new ArrayList<>();
		this.loadBehavingObjects();
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
		this.space = space;
	}



	@Override
	public void setup(ABehaviorContext<AArableCropProductionBhv> container) {
		
		if(((ArableCropProductionBhvContext)container).getAvailableCrops().isEmpty()) {
			this.loadCrops(container);
		}
		
		this.addAgents(container);	
		
	}
	
	
	
	private void addAgents(ABehaviorContext<AArableCropProductionBhv> container) {
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

	private void loadCrops(ABehaviorContext<AArableCropProductionBhv> container) {
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
	}

	
} //end class
