package gr.agroscape.behaviors.farmers.production.arableCropProduction;

import gr.agroscape.agents.Farmer;
import gr.agroscape.agents.Plot;
import gr.agroscape.behaviors.ABehaviorContext;
import gr.agroscape.behaviors.IScheduledBehavior;
import gr.agroscape.behaviors.IScheduledBehaviorDataLoader;
import gr.agroscape.behaviors.farmers.production.agriculturalActivities.ArableCropCultivation;
import gr.agroscape.behaviors.farmers.production.products.Product;
import gr.agroscape.contexts.Space;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;

import repast.simphony.valueLayer.GridValueLayer;

public class ArableCropProductionBhvContext extends ABehaviorContext<AArableCropProductionBhv> {

	
	private ArrayList<ArableCropCultivation> availableCrops;
	
	private HashMap<ArableCropCultivation, GridValueLayer> cropSuitabilities=new HashMap<ArableCropCultivation, GridValueLayer>();
	
	
	public ArableCropProductionBhvContext(Collection<? super Farmer> owners) {
				this(owners,new DefaultArableProductionBhvContextLoader(owners,Space.getInstance()));	
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

	private Collection<? super Farmer> owners;
	private Space space;
	
	
	
	public DefaultArableProductionBhvContextLoader(Collection<? super Farmer> owners, Space space) {
		super();
		this.owners = owners;
		this.space = space;
	}



	@Override
	public void setup(ABehaviorContext<AArableCropProductionBhv> container) {
			
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

		
		Collection<IScheduledBehavior<AArableCropProductionBhv>> r = new ArrayList<IScheduledBehavior<AArableCropProductionBhv>>();
			
			for (Object f : owners) {
				ArableCropProductionBhv_MP toadd = new ArableCropProductionBhv_MP(((ArableCropProductionBhvContext)container).getAvailableCrops(),1000,(Farmer)f,(ArableCropProductionBhvContext) container);
				r.add(toadd);
			}
			
			container.addAll(r);
	}
	


	
} //end class
