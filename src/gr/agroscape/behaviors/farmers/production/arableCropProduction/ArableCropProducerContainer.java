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
import java.util.HashMap;

import repast.simphony.context.DefaultContext;
import repast.simphony.valueLayer.GridValueLayer;

public class ArableCropProducerContainer extends ABehaviorContainer<AArableCropProducer> {

	private CropsContext availableCrops;
	
	
	public ArableCropProducerContainer(Collection<? super AArableCropProducer> owners) {
				super("ArableCropProductionBehavior",new DefaultArableProducerLoader(),owners,null,Space.getInstance());
				this.loadBehavingObjects(owners, dataFile, space);
				
	}
	
	public ArableCropProducerContainer(Collection<? super AArableCropProducer> owners,IScheduledBehaviorDataLoader<AArableCropProducer> objectLoader) {
		super("ArableCropProductionBehavior",objectLoader,owners,null,Space.getInstance());
		this.loadBehavingObjects(owners, dataFile, space);
		
	}
	

	public CropsContext getCropsContext() {
		if(! this.hasSubContext()) throw new NullPointerException("The MainContext does not have any subcontexts yet.");
		return this.availableCrops;
	}
	
	


} //end class



/**
* It contains information regarding Crops
* 
* @author Dimitris Kremmydas
*
*/
class CropsContext extends DefaultContext<ArableCropCultivation> {

	/**
	 * For each available Crop, the true Crop biophysical Suitability
	 */
	private HashMap<ArableCropCultivation, GridValueLayer> cropSuitability=new HashMap<ArableCropCultivation, GridValueLayer>();
	
	
	public CropsContext() {
		super("CropsContext");
	}
	
	/**
	 * Get all available Crops
	 * @return
	 */
	public ArrayList<ArableCropCultivation> getAvailableCrops() {
		ArrayList<ArableCropCultivation> r = new ArrayList<ArableCropCultivation>();
		
		Iterable<ArableCropCultivation> crops = this.getAgentLayer(ArableCropCultivation.class);
		for (ArableCropCultivation crop : crops) {
			r.add(crop);
		}
		
		return r;
	}
	
	/**
	 * Get a Crop object by its name (Case insensitive). <br />
	 * @param n String Name of Crop
	 * @return if a crop with that name exists returns a {@link ArableCropCultivation}. Otherwise returns null.
	 */
	public ArableCropCultivation getCropByName(String n) {
		ArrayList<ArableCropCultivation> crops = this.getAvailableCrops();
		for (ArableCropCultivation crop : crops) {
			if(crop.getName().equalsIgnoreCase(n)) return crop;
		}
		return null;
	}
	
	/**
	 * Getter for {@link #cropSuitability}
	 * @return
	 */
	public HashMap<ArableCropCultivation, GridValueLayer> getCropSuitability() {
		return cropSuitability;
	}
	
	
	
}



/**
 * Inner class to load stupido data
 * @author Dimitris Kremmydas
 *
 */
class DefaultArableProducerLoader implements IScheduledBehaviorDataLoader<AArableCropProducer> {

	

	@Override
	public Collection<IScheduledBehavior<AArableCropProducer>> setup(Collection<? super AArableCropProducer> owners, Space space, ABehaviorContainer<AArableCropProducer> container) {
			
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
				ArableCropProducer_MP toadd = new ArableCropProducer_MP(ArableCropCultivation.getAvailableCrops(),1000,(Farmer)f,(ArableCropProducerContainer) container);
				r.add(toadd);
			}
			
			return r;
	}
	

	@Override
	public Collection<IScheduledBehavior<AArableCropProducer>> setup(Collection<? super AArableCropProducer> owners, 
			Space space, ABehaviorContainer<AArableCropProducer> container, Path dataFile) {
			return this.setup(owners,space,container);	
	}


	
} //end class
