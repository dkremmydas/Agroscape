package gr.agroscape.contexts;

import gr.agroscape.agents.Farmer;

import org.apache.commons.collections15.Predicate;
import org.apache.commons.collections4.map.HashedMap;

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

	
	/**
	 * Search for a Farmer with a specific id
	 * @param id
	 * @return
	 */
	public Farmer findFarmerById(int id) {
		Iterable<Farmer> iff = this.query(new PredicateFarmerId(id));
		return iff.iterator().next();
	}
	
	
	
}

class PredicateFarmerId implements Predicate<Farmer> {

	private int lookingFor;
	
	public PredicateFarmerId(int lookingFor) {
		super();
		this.lookingFor = lookingFor;
	}

	@Override
	public boolean evaluate(Farmer arg0) {
		if(arg0.getID().equals(lookingFor)) return true;
		return false;
	}
	
}
	
	
	
	
