package gr.agroscape.behaviors.farmers.socialNetworking;

import gr.agroscape.agents.Farmer;
import gr.agroscape.behaviors.ABehaviorContext;
import gr.agroscape.behaviors.IScheduledBehavior;
import gr.agroscape.behaviors.IScheduledBehaviorDataLoader;
import gr.agroscape.contexts.Space;

import java.nio.file.Path;
import java.util.Collection;

import repast.simphony.space.graph.Network;


/**
 * An Context that has only a {@link Network} object, showing a social network between  farmers 
 * 
 * @author Dimitris Kremmydas
 *
 */
public class SocialNetworkingBhvContext extends ABehaviorContext<Farmer> {

	
	private Network<Farmer> socialNetwork;
	
	
	public SocialNetworkingBhvContext(Network<Farmer> network) {
		super("SocialNetworking", new SocialNetworkingBhvLoader());
		this.socialNetwork = network;
	}


	public Network<Farmer> getSocialNetwork() {
		return socialNetwork;
	}


	@Override
	protected void loadBehavingObjects(Collection<? super Farmer> owners,
			Path dataFile, Space space) {
		throw new UnsupportedOperationException();
	}
	
	



}


/**
 * 
 * Default Data Loader
 * 
 * @author Dimitris Kremmydas
 *
 */
class SocialNetworkingBhvLoader implements IScheduledBehaviorDataLoader<Farmer> {

	@Override
	public Collection<IScheduledBehavior<Farmer>> setup(
			Collection<? super Farmer> owners, Space space,
			ABehaviorContext<Farmer> container, Path dataFile) {
		throw new UnsupportedOperationException();
	}

	@Override
	public Collection<IScheduledBehavior<Farmer>> setup(
			Collection<? super Farmer> owners, Space space,
			ABehaviorContext<Farmer> container) {
		throw new UnsupportedOperationException();
	}

	
}
