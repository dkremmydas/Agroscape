package gr.agroscape.behaviors.farmers.stupido;

import gr.agroscape.agents.Farmer;
import gr.agroscape.behaviors.ABehaviorContainer;
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
public class StupidoFarmersContainer extends ABehaviorContainer<Farmer> {
	
	

	public StupidoFarmersContainer() {
		super("stupidoBehavior", new DefaultStupidoDataLoader());
	}

}




/**
 * Inner class to load stupido data
 * @author Dimitris Kremmydas
 *
 */
class DefaultStupidoDataLoader implements IScheduledBehaviorDataLoader<Farmer> {

	
	@Override
	public Collection<StupidoFarmer> setup(
			Collection<Farmer> owners, Space space, Path dataFile) {

		Collection<StupidoFarmer> r = new ArrayList<StupidoFarmer>();
		for (Farmer f : owners) {
			r.add(new StupidoFarmer(f));
		}
		
		return r;
	}
	
	
	
	
}