package gr.agroscape.behaviors.farmers.socialNetworking;

import gr.agroscape.agents.Farmer;
import gr.agroscape.behaviors.farmers.AFarmerBehavior;



/**
 * The network behavior that do nothing 
 * 
 * @author Dimitris Kremmydas
 *
 */
public class DefaultSocialNetworkingBhv extends AFarmerBehavior<DefaultSocialNetworkingBhv> {

	public DefaultSocialNetworkingBhv(Farmer owner) {
		super(owner);
	}

	@Override
	public Object getAnnotatedClass() {
		return null;
	}

}
