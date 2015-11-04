package gr.agroscape.skeleton.contexts;





import gr.agroscape.skeleton.agents.human.Farmer;

import org.apache.commons.collections15.Predicate;

import repast.simphony.context.DefaultContext;


public class FarmersContext extends DefaultContext<Farmer> {

	
	private static FarmersContext instance=null;
	

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
	 * Search for a Farmer with a specific id
	 * @param id
	 * @return
	 */
	public Farmer findFarmerById(int id) {
		Iterable<Farmer> iff = this.query(new PredicateFarmerId((Integer)id));
		return iff.iterator().next();
	}
	
	/**
	 * Get all farmers
	 * @return
	 */
	public Iterable<Farmer> getAllFarmers() {
		return this.getAgentLayer(Farmer.class);
	}
	
	
	
}

class PredicateFarmerId implements Predicate<Farmer> {

	private Integer lookingFor;
	
	public PredicateFarmerId(Integer lookingFor) {
		super();
		this.lookingFor = lookingFor;
	}

	@Override
	public boolean evaluate(Farmer arg0) {
		if(arg0.getId().equals(lookingFor)) return true;
		return false;
	}
	
}
	
	
	
	
