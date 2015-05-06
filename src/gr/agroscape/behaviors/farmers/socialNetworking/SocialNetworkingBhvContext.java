package gr.agroscape.behaviors.farmers.socialNetworking;

import gr.agroscape.agents.Farmer;
import gr.agroscape.behaviors.ABehaviorContext;
import gr.agroscape.behaviors.IScheduledBehavior;
import gr.agroscape.behaviors.IScheduledBehaviorDataLoader;

import java.util.ArrayList;
import java.util.Collection;

import repast.simphony.space.graph.Network;
import repast.simphony.space.projection.Projection;


/**
 * An Context that has only a {@link Network} object, showing a social network between  farmers 
 * 
 * @author Dimitris Kremmydas
 *
 */
public class SocialNetworkingBhvContext extends ABehaviorContext<DefaultSocialNetworkingBhv> {
	//public class StupidoBhvContext extends ABehaviorContext<StupidoBhv> {

	
	private Network<? extends DefaultSocialNetworkingBhv> socialNetwork;
	
	
	public SocialNetworkingBhvContext(Collection<? super Farmer> owners) {
		super("SocialNetworking", new DefaultSocialNetworkingBhvLoader(owners));
		this.loadBehavingObjects();
	}


	public Network<? extends DefaultSocialNetworkingBhv> getSocialNetwork() {
		return socialNetwork;
	}
	

}


/**
 * 
 * Default Data Loader. It takes a bunch of farmers and create a network between the first 2 of them. 
 * 
 * @author Dimitris Kremmydas
 *
 */
class DefaultSocialNetworkingBhvLoader implements IScheduledBehaviorDataLoader<DefaultSocialNetworkingBhv> {

	private Collection<? super Farmer> owners;
	private Projection<? super IScheduledBehavior<DefaultSocialNetworkingBhv>> network;

	public DefaultSocialNetworkingBhvLoader(Collection<? super Farmer> owners) {
		super();
		this.owners = owners;
	}



	@Override
	public void setup(ABehaviorContext<DefaultSocialNetworkingBhv> container) {

		Collection<IScheduledBehavior<DefaultSocialNetworkingBhv>> r = new ArrayList<IScheduledBehavior<DefaultSocialNetworkingBhv>>();

		for (Object f : this.owners) {
			DefaultSocialNetworkingBhv toadd = new DefaultSocialNetworkingBhv((Farmer)f, (SocialNetworkingBhvContext)container);
			r.add(toadd);
		}
		container.addAll(r);
		this.fillNetwork();
		container.addProjection(network);		
	}

	
	private void fillNetwork() {
		//make the first three connected
		
	}


	
}
