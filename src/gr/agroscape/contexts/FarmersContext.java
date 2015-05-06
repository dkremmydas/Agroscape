package gr.agroscape.contexts;

import gr.agroscape.agents.Farmer;
import gr.agroscape.behaviors.ABehaviorContext;
import gr.agroscape.behaviors.farmers.AFarmerBehavior;

import org.apache.commons.collections4.map.HashedMap;

import repast.simphony.context.Context;
import repast.simphony.context.DefaultContext;
import repast.simphony.space.graph.Network;


public class FarmersContext extends DefaultContext<Farmer> {

	
	private static FarmersContext instance=null;
	
	
	/**
	 * A Collection of networks
	 */
	private HashedMap<String,Network<Farmer>> networks = new HashedMap<>();

	/**
	 * Constructor
	 */
	public FarmersContext() {
		super("FarmersContext");
		this.setId("FarmersContext");
		FarmersContext.instance=this;
	}
	
	/**
	 * Return a reference to the created instance. For easiness purposes.
	 * @return
	 */
	public static FarmersContext getInstance() {
		if (FarmersContext.instance==null) {throw new NullPointerException("FarmersContext has not yet be created");}
		return FarmersContext.instance;
	}


	/**
	 * Add a network in the {@link #networks} map. The ke is the name of the {@link Network} object
	 * @param n
	 */
	public void addNetwork(Network<Farmer> n)	{
		this.networks.put(n.getName(), n);
	}
	
	/**
	 * Retrieves a {@link Network}
	 * @param name
	 * @return
	 */
	public Network<Farmer> getNetwork(String name) {
		return this.networks.get(name);
	}

	@SuppressWarnings("unchecked")
	public void attachBehavior(ABehaviorContext<? extends AFarmerBehavior<?>> behaviorContainer) {
		this.addSubContext((Context<? extends Farmer>) behaviorContainer);
	}
	
	
	
}
	
	
	
	
