package gr.agroscape.behaviors.farmers.stupido;

import gr.agroscape.agents.Farmer;
import gr.agroscape.behaviors.ABehaviorContext;
import gr.agroscape.behaviors.IScheduledBehavior;
import gr.agroscape.behaviors.IScheduledBehaviorDataLoader;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Random;

/**
 * The context for the StupidoBhv
 * 
 * @author Dimitris Kremmydas
 */
public class StupidoBhvContext extends ABehaviorContext<StupidoBhv> {
	
	protected Random randomGenerator ;

	public StupidoBhvContext(Collection<? super Farmer> owners) {
		super("stupidoBehavior", new DefaultStupidoDataLoader(owners));
		this.randomGenerator = new Random(System.currentTimeMillis());
		this.loadBehavingObjects();		
	}
	
	
	public int getRandom() {
		return this.randomGenerator.nextInt();
	}
	
}


/**
 * Inner class to load stupidoBhv ojects
 * @author Dimitris Kremmydas
 */
class DefaultStupidoDataLoader implements IScheduledBehaviorDataLoader<StupidoBhv> {

	private Collection<? super Farmer> owners;	
	
	public DefaultStupidoDataLoader(Collection<? super Farmer> owners) {
		super();
		this.owners = owners;
	}

	@Override
	public void setup( ABehaviorContext<StupidoBhv> container) {
		Collection<IScheduledBehavior<StupidoBhv>> r = new ArrayList<IScheduledBehavior<StupidoBhv>>();
			for (Object f : owners) {
				StupidoBhv toadd = new StupidoBhv((Farmer)f,(StupidoBhvContext)container);
				r.add(toadd);
			}
			container.addAll(r);
	}
	
} //end class