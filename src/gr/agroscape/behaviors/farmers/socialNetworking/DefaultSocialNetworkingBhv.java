package gr.agroscape.behaviors.farmers.socialNetworking;

import gr.agroscape.agents.Farmer;
import gr.agroscape.behaviors.IScheduledBehavior;
import gr.agroscape.behaviors.farmers.AFarmerBehavior;



/**
 * The network behavior that do nothing 
 * 
 * @author Dimitris Kremmydas
 *
 */
public class DefaultSocialNetworkingBhv extends AFarmerBehavior<DefaultSocialNetworkingBhv> implements IScheduledBehavior<DefaultSocialNetworkingBhv> {


	/**
	 * A reference to the container context
	 */
	protected SocialNetworkingBhvContext container;
	
	
	public DefaultSocialNetworkingBhv(Farmer owner, SocialNetworkingBhvContext con) {
		super(owner);
		this.container=con;
	}

	@Override
	public Object getAnnotatedClass() {
		return null;
	}

}
