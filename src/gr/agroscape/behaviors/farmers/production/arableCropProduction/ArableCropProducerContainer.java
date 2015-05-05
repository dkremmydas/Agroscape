package gr.agroscape.behaviors.farmers.production.arableCropProduction;

import gr.agroscape.agents.Farmer;
import gr.agroscape.behaviors.ABehaviorContainer;
import gr.agroscape.behaviors.IScheduledBehavior;
import gr.agroscape.behaviors.IScheduledBehaviorDataLoader;
import gr.agroscape.behaviors.farmers.production.agriculturalActivities.ArableCropCultivation;
import gr.agroscape.behaviors.farmers.production.products.Product;
import gr.agroscape.contexts.CropsContext;
import gr.agroscape.contexts.Space;

import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Collection;

public class ArableCropProducerContainer extends ABehaviorContainer<AArableCropProducer> {

	
	
	
	public ArableCropProducerContainer(Collection<? super AArableCropProducer> owners) {
				super("ArableCropProductionBehavior",new DefaultArableProducerLoader(),owners,null,Space.getInstance());
				
	}
	
	public ArableCropProducerContainer(Collection<? super AArableCropProducer> owners,IScheduledBehaviorDataLoader<AArableCropProducer> objectLoader) {
		super("ArableCropProductionBehavior",objectLoader,owners,null,Space.getInstance());
		
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
		
		//add a crop context
		CropsContext cc= new CropsContext();
		space.addSubContext(cc);
		cc.add(c1);cc.add(c2);cc.add(c3);
		
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
