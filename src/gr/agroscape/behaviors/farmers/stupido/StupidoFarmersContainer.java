package gr.agroscape.behaviors.farmers.stupido;

import gr.agroscape.agents.Farmer;
import gr.agroscape.behaviors.ABehaviorContainer;
import gr.agroscape.behaviors.IScheduledBehavior;
import gr.agroscape.behaviors.IScheduledBehaviorDataLoader;
import gr.agroscape.contexts.Space;

import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Collection;

/**
 * The container for the StupidoFarmer
 * @author Dimitris Kremmydas
 *
 */
public class StupidoFarmersContainer extends ABehaviorContainer<StupidoFarmer> {
	
	

	public StupidoFarmersContainer() {
		super("stupidoBehavior", new DefaultStupidoDataLoader());
	}

}




/**
 * Inner class to load stupido data
 * @author Dimitris Kremmydas
 *
 */
class DefaultStupidoDataLoader implements IScheduledBehaviorDataLoader<StupidoFarmer> {

	
	@Override
	public Collection<IScheduledBehavior<StupidoFarmer>> setup(Collection<? super StupidoFarmer> owners, Space space, Path dataFile) {

		Collection<IScheduledBehavior<StupidoFarmer>> r = new ArrayList<IScheduledBehavior<StupidoFarmer>>();
		
		for (Object f : owners) {
			StupidoFarmer toadd = new StupidoFarmer((Farmer)f);
			r.add(toadd);
		}
		
		return r;
	}
	





	
	
	
	
}