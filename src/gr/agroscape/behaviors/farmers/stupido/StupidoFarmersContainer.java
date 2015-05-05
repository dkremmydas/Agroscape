package gr.agroscape.behaviors.farmers.stupido;

import gr.agroscape.agents.Farmer;
import gr.agroscape.behaviors.ABehaviorContainer;
import gr.agroscape.behaviors.IScheduledBehavior;
import gr.agroscape.behaviors.IScheduledBehaviorDataLoader;
import gr.agroscape.contexts.Space;

import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Random;

/**
 * The container for the StupidoFarmer
 * @author Dimitris Kremmydas
 *
 */
public class StupidoFarmersContainer extends ABehaviorContainer<StupidoFarmer> {
	
	protected Random randomGenerator = new Random(System.currentTimeMillis());

	public StupidoFarmersContainer(Collection<? super StupidoFarmer> owners) {
		super("stupidoBehavior", new DefaultStupidoDataLoader(),owners,null,Space.getInstance());
		//this.randomGenerator = new Random(System.currentTimeMillis());
	}
	
	
	public int getRandom() {
		return this.randomGenerator.nextInt();
	}
	
}


/**
 * Inner class to load stupido data
 * @author Dimitris Kremmydas
 *
 */
class DefaultStupidoDataLoader implements IScheduledBehaviorDataLoader<StupidoFarmer> {

	

	@Override
	public Collection<IScheduledBehavior<StupidoFarmer>> setup(Collection<? super StupidoFarmer> owners, 
							Space space, ABehaviorContainer<StupidoFarmer> container) {
		Collection<IScheduledBehavior<StupidoFarmer>> r = new ArrayList<IScheduledBehavior<StupidoFarmer>>();
			for (Object f : owners) {
				StupidoFarmer toadd = new StupidoFarmer((Farmer)f,(StupidoFarmersContainer)container);
				r.add(toadd);
			}
			return r;
	}
	

	@Override
	public Collection<IScheduledBehavior<StupidoFarmer>> setup(Collection<? super StupidoFarmer> owners, Space space, ABehaviorContainer<StupidoFarmer> container, Path dataFile) {
			return this.setup(owners,space,container);	
	}




	
} //end class