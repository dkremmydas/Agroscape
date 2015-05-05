package gr.agroscape.behaviors.farmers.stupido;

import gr.agroscape.agents.Farmer;
import gr.agroscape.behaviors.ABehaviorContext;
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
public class StupidoBhvContainer extends ABehaviorContext<StupidoBhv> {
	
	protected Random randomGenerator ;

	public StupidoBhvContainer(Collection<? super Farmer> owners) {
		super("stupidoBehavior", new DefaultStupidoDataLoader());
		this.randomGenerator = new Random(System.currentTimeMillis());
		this.loadBehavingObjects(owners, null, Space.getInstance());		
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
class DefaultStupidoDataLoader implements IScheduledBehaviorDataLoader<StupidoBhv> {

	

	@Override
	public Collection<IScheduledBehavior<StupidoBhv>> setup(Collection<? super Farmer> owners, 
							Space space, ABehaviorContext<StupidoBhv> container) {
		Collection<IScheduledBehavior<StupidoBhv>> r = new ArrayList<IScheduledBehavior<StupidoBhv>>();
			for (Object f : owners) {
				StupidoBhv toadd = new StupidoBhv((Farmer)f,(StupidoBhvContainer)container);
				r.add(toadd);
			}
			return r;
	}
	

	@Override
	public Collection<IScheduledBehavior<StupidoBhv>> setup(Collection<? super Farmer> owners, Space space, ABehaviorContext<StupidoBhv> container, Path dataFile) {
			return this.setup(owners,space,container);	
	}




	
} //end class