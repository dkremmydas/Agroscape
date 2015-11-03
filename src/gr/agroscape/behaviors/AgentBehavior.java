package gr.agroscape.behaviors;

import gr.agroscape.agents.AgroscapeAgent;
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

	public AgentBehavior(String name, AgroscapeAgent owner, Context<?> bhvContext) {
		super();
		this.bhvContext = bhvContext;
		this.name = name;
		this.owner = owner;
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

	
	
	
	

}
