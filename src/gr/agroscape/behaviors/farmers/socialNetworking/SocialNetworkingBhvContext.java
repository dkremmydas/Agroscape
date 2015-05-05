package gr.agroscape.behaviors.farmers.socialNetworking;

import java.util.Collection;

import gr.agroscape.agents.Farmer;
import gr.agroscape.behaviors.ABehaviorContext;
import gr.agroscape.behaviors.IScheduledBehavior;
import gr.agroscape.behaviors.IScheduledBehaviorDataLoader;
import repast.simphony.space.graph.Network;
import repast.simphony.space.projection.Projection;


/**
 * An Context that has only a {@link Network} object, showing a social network between  farmers 
 * 
 * @author Dimitris Kremmydas
 *
 */
public class SocialNetworkingBhvContext extends ABehaviorContext<Farmer> {

	
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
	private Projection<DefaultSocialNetworkingBhv> network;

	public DefaultSocialNetworkingBhvLoader(Collection<? super Farmer> owners) {
		super();
		this.owners = owners;
	}



	@Override
	public void setup(ABehaviorContext<DefaultSocialNetworkingBhv> container) {
		container.addProjection(network);		
	}

	
	private void fillNetwork() {
		//make the first three connected
		
	}


	
}
