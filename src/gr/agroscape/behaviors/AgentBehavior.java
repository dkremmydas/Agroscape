package gr.agroscape.behaviors;

import com.google.common.collect.ArrayListMultimap;

import gr.agroscape.agents.AgroscapeAgent;
import repast.simphony.context.Context;

public abstract class AgentBehavior implements BehaviorSchedulable {
	
	private String name;
	
	private Context<?> bhvContext;
	
	private AgentBehavior agentBhv;
	
	private ArrayListMultimap<Class<AgroscapeAgent>,BehaviorProperty<?>> properties =  ArrayListMultimap.create();
	


	public AgentBehavior(Context<?> bhvContext, AgentBehavior agentBhv, 
			ArrayListMultimap<Class<AgroscapeAgent>,BehaviorProperty<?>> properties) {
		super();
		this.bhvContext = bhvContext;
		this.agentBhv = agentBhv;
		this.properties = properties;
	}


	public Context<?> getBhvContext() {
		return bhvContext;
	}


	public BehaviorProperties getProperties(Class<AgroscapeAgent> key) {
		this.properties.getProperties(key);
	}


	public String getName() {
		return name;
	}

	
	
	
	

}
