package gr.agroscape.behaviors.farmers.stupido;

import gr.agroscape.agents.human.Farmer;
import gr.agroscape.behaviors.BehaviorContext;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Random;

import repast.simphony.context.Context;
import repast.simphony.dataLoader.ContextBuilder;


/**
 * The context for the StupidoBhv
 * 
 * @author Dimitris Kremmydas
 */
public class StupidoBhvContext extends BehaviorContext<StupidoBhv>  {
	
	private Random randomGenerator ;

	public StupidoBhvContext(Collection<Farmer> owners) {
		super("stupidoBehavior", new DefaultStupidoDataLoader(owners));
		this.randomGenerator = new Random(System.currentTimeMillis());	
	}
	
	public StupidoBhvContext(ContextBuilder<StupidoBhv> builder) {
		super("stupidoBehavior",builder);
	}
		
	public int getRandom() {
		return this.randomGenerator.nextInt();
	}
	
}


/**
 * Inner class to load stupidoBhv objects
 * @author Dimitris Kremmydas
*/ 
class DefaultStupidoDataLoader implements ContextBuilder<StupidoBhv> {

	private Collection<? super Farmer> owners;	
	
	public DefaultStupidoDataLoader(Collection<? super Farmer> owners) {
		super();
		this.owners = owners;
	}

	@Override
	public Context<StupidoBhv> build(Context<StupidoBhv> context) {
		
		StupidoBhvContext sbhCon = (StupidoBhvContext)context;
		
		Collection<StupidoBhv> r = new ArrayList<StupidoBhv>();
		for (Object f : owners) {
			StupidoBhv toadd = new StupidoBhv((Farmer)f,sbhCon);
			r.add(toadd);
		}
		context.addAll(r);
		return context;
		
	}


	

	
	
	
} //end class
