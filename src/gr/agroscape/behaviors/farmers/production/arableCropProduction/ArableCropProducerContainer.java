package gr.agroscape.behaviors.farmers.production.arableCropProduction;

import gr.agroscape.agents.Farmer;
import gr.agroscape.behaviors.ABehaviorContainer;
import gr.agroscape.behaviors.IScheduledBehavior;
import gr.agroscape.behaviors.IScheduledBehaviorDataLoader;
import gr.agroscape.behaviors.farmers.production.agriculturalActivities.ArableCropCultivation;
import gr.agroscape.behaviors.farmers.production.products.Product;
import gr.agroscape.contexts.Space;

import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Collection;

public class ArableCropProducerContainer extends ABehaviorContainer<AArableCropProducer> {

	public ArableCropProducerContainer(String name,
			IScheduledBehaviorDataLoader<AArableCropProducer> objectLoader) {
		super(name, objectLoader);
		// TODO Auto-generated constructor stub
	}
	
	public ArableCropProducerContainer(String name) {
		super(name, new DefaultArableProducerLoader());
		// TODO Auto-generated constructor stub
	}

} //end class




/**
 * Inner class to load stupido data
 * @author Dimitris Kremmydas
 *
 */
class DefaultArableProducerLoader implements IScheduledBehaviorDataLoader<AArableCropProducer> {

	

	@Override
	public Collection<IScheduledBehavior<AArableCropProducer>> setup(Collection<? super AArableCropProducer> owners, Space space) {
			
		//create crops
		ArableCropCultivation c1 = new ArableCropCultivation("maize", new Product("maize product"));
		ArableCropCultivation c2 = new ArableCropCultivation("wheat", new Product("wheat product"));
		ArableCropCultivation c3 = new ArableCropCultivation("cotton", new Product("cotton product"));
		
		
		Collection<IScheduledBehavior<AArableCropProducer>> r = new ArrayList<IScheduledBehavior<AArableCropProducer>>();
			
			for (Object f : owners) {
				ArableCropProducer_MP toadd = new ArableCropProducer_MP(ArableCropCultivation.getAvailableCrops(),1000,(Farmer)f);
				r.add(toadd);
			}
			
			return r;
	}
	

	@Override
	public Collection<IScheduledBehavior<AArableCropProducer>> setup(Collection<? super AArableCropProducer> owners, Space space, Path dataFile) {
			return this.setup(owners,space);	
	}
	
} //end class
