package gr.agroscape.behaviors.farmers.production.arableCropProduction;

import gr.agroscape.agents.Farmer;
import gr.agroscape.agents.Plot;
import gr.agroscape.behaviors.ABehaviorContext;
import gr.agroscape.behaviors.IScheduledBehavior;
import gr.agroscape.behaviors.IScheduledBehaviorDataLoader;
import gr.agroscape.behaviors.farmers.production.agriculturalActivities.ArableCropCultivation;
import gr.agroscape.behaviors.farmers.production.products.Product;
import gr.agroscape.contexts.Space;

import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;

import repast.simphony.valueLayer.GridValueLayer;

public class ArableCropProducerContainer extends ABehaviorContext<AArableCropProducer> {

	
	private ArrayList<ArableCropCultivation> availableCrops;
	
	private HashMap<ArableCropCultivation, GridValueLayer> cropSuitabilities=new HashMap<ArableCropCultivation, GridValueLayer>();
	
	
	public ArableCropProducerContainer(Collection<? super AArableCropProducer> owners) {
				this(owners,new DefaultArableProducerLoader());	
	}
	
	public ArableCropProducerContainer(Collection<? super AArableCropProducer> owners,IScheduledBehaviorDataLoader<AArableCropProducer> objectLoader) {
		super("ArableCropProductionBehavior",objectLoader);
		this.availableCrops =new ArrayList<>();
		this.loadBehavingObjects(owners, null,  Space.getInstance());
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
class DefaultArableProducerLoader implements IScheduledBehaviorDataLoader<AArableCropProducer> {

	

	@Override
	public Collection<IScheduledBehavior<AArableCropProducer>> setup(Collection<? super AArableCropProducer> owners, Space space, ABehaviorContext<AArableCropProducer> container) {
			
		//create crops
		ArableCropCultivation c1 = new ArableCropCultivation("maize", new Product("maize product"));
		ArableCropCultivation c2 = new ArableCropCultivation("wheat", new Product("wheat product"));
		ArableCropCultivation c3 = new ArableCropCultivation("cotton", new Product("cotton product"));
		
		//add those crops
		((ArableCropProducerContainer)container).getAvailableCrops().add(c1);
		((ArableCropProducerContainer)container).getAvailableCrops().add(c2);
		((ArableCropProducerContainer)container).getAvailableCrops().add(c3);
		
		
		//load crop suitability, all value equals to 1		
		for (ArableCropCultivation c : ((ArableCropProducerContainer)container).getAvailableCrops()) {
			GridValueLayer gv = new GridValueLayer("CropSuitability_"+c.getName(), true, space.getGridWidth(),space.getGridHeight());
			for (int i = 0; i < gv.getDimensions().getWidth(); i++) {
				for (int j = 0; j <  gv.getDimensions().getHeight(); j++) {
					gv.set(1d, i,j);
				}
			}
			((ArableCropProducerContainer)container).getCropSuitabilities().put(c, gv);
		}
		
		
		//load PaymentAuthority couple payments
		for (ArableCropCultivation c : ((ArableCropProducerContainer)container).getAvailableCrops()) {
			space.getPaymentAuthority().getCoupledPayments().put(c, 0l);
		}

		
		Collection<IScheduledBehavior<AArableCropProducer>> r = new ArrayList<IScheduledBehavior<AArableCropProducer>>();
			
			for (Object f : owners) {
				ArableCropProducer_MP toadd = new ArableCropProducer_MP(((ArableCropProducerContainer)container).getAvailableCrops(),1000,(Farmer)f,(ArableCropProducerContainer) container);
				r.add(toadd);
			}
			
			return r;
	}
	

	@Override
	public Collection<IScheduledBehavior<AArableCropProducer>> setup(Collection<? super AArableCropProducer> owners, 
			Space space, ABehaviorContext<AArableCropProducer> container, Path dataFile) {
			return this.setup(owners,space,container);	
	}


	
} //end class
