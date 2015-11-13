package gr.agroscape.behaviors;

import gr.agroscape.skeleton.agents.AgroscapeAgent;
import repast.simphony.context.Context;

/**
 * <p>The purpose of this class is to:</p>
 * <ul>
 * <li>Hold common properties of all behaviors</li>
 * <ul>
 * @author Dimitris Kremmydas
 * @version %I%
 * @since 2.0
 *
 */
public abstract class AgentBehavior implements BehaviorSchedulable {
	
	private String name;
	
	private Context<?> bhvContext;
	
	private AgroscapeAgent owner;
	
	protected BehaviorFactory bhvFactory;

	protected AgentBehavior(String name, BehaviorFactory bhvFactory, AgroscapeAgent owner, Context<?> bhvContext) {
		super();
		this.bhvContext = bhvContext;
		this.name = name;
		this.owner = owner;
		this.bhvFactory = bhvFactory;
	}

	
	
	protected BehaviorFactory getBhvFactory() {
		return bhvFactory;
	}



	public Context<?> getBehaviorContext() {
		return bhvContext;
	}


	public String getName() {
		return name;
	}


	public AgroscapeAgent getOwner() {
		return owner;
	}




	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return "["+super.toString()+"} Behavior name:"+this.name+" Owner: "+this.owner.toString();
	}

	

}
