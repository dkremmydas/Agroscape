package gr.agroscape.skeleton.agents;

import gr.agroscape.behaviors.AgentBehavior;
import gr.agroscape.behaviors.BehaviorFactory;
import gr.agroscape.skeleton.contexts.SimulationContext;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

import repast.simphony.context.Context;
import repast.simphony.util.ContextUtils;

/**
 * <p>This is the root class of every skeleton agent of the simulation.</p>
 * <p>Defines common characteristics for all Simulation Agents. Those characteristics are :</p>
 * <ul>
 * <li>Unique incremental Id</li>
 * <li>Given name. The name could be an id. It is not necessarily unique</li>
 * <li>A reference to the root {@link SimulationContext}</li>
 * <li>A reference to the parent  {@link Context}</li>
 * <li>The behavior properties of the agent. {@see #behaviorProperties}</li>
 * </ul>
 * 
 * @author Dimitris Kremmydas
 * @version %I%
 * @since 2.0
 *
 */
public abstract class AgroscapeAgent {
	
	/**
	 * Gives the unique id for all Simulation Agents. Thread safe.
	 */
	private static AtomicInteger uniqueId=new AtomicInteger();
	
	/**
	 * The unique Id of the current object
	 */
	private int id;
	
	/**
	 * A Human readable name for the agent
	 */
	private String name = "N/A";
	
	/**
	 * <p>A map of behavior properties of the agent</p>
	 * <p>The name of the behavior is constructed in a special way: {NAME OF ORIGINATING BEHAVIOR}_{NAME OF BEHAVIOR}</p>
	 */
	private HashMap<String,AgroscapeAgentProperty<?>> behaviorProperties = new HashMap<String, AgroscapeAgentProperty<?>>();
	
	/**
	 * <p>The list of behaviors that are attached to the agent</p>
	 */
	private List<AgentBehavior> behaviors = new ArrayList<>();
	
	/**
     * A reference to the mainContext. 
     */
    protected SimulationContext mainContext ;
    
    /**
     * A reference to the parentContext. 
     */
    protected Context<?> parentContext ;

	/**
	 * Constructor
	 */
	public AgroscapeAgent() {
		id=uniqueId.getAndIncrement();
		this.mainContext=SimulationContext.getInstance();	
		this.parentContext= ContextUtils.getContext(this);
	}
	
	public AgroscapeAgent(String name) {
		this();
		this.name = name;
	}
	
	public AgroscapeAgent(Integer name) {
		this();
		this.name = name.toString();
	}

	/**
	 * Id getter
	 * @return
	 */
	public Integer getId() {
		return id;
	}

	/**
	 * Name Getter
	 * @return
	 */
	public String getName() {
		return name;
	}

	/**
	 * Name Setter
	 * @param name
	 */
	public void setName(String name) {
		this.name = name;
	} 
	
	/**
	 * Name Setter
	 * @param name
	 */
	public void setName(Integer name) {
		this.name = name.toString();
	}
	
	/**
	 * Get {@link MainContext} object
	 * @return
	 */
	public SimulationContext getMainContext() {
		return this.mainContext;
	}
	
	public Context<?> getParentContext() {
		return parentContext;
	}

	public void setParentContext(Context<?> parentContext) {
		this.parentContext = parentContext;
	}

	/**
	 * Adds a behavior property.
	 * @param bf {@AgentBehavior}
	 * @param bp {@BehaviorProperty}. Be careful !!!  It should be passed as "new BehaviorProperty<?>(...)
	 */
	public void addBehaviorProperty(BehaviorFactory bf, AgroscapeAgentProperty<?> bp) {
		this.behaviorProperties.put(bf.getName() + "_"+name, bp);
		//get(ab.getName() + "_"+name);
	}
    
	/**
	 * Gets the {@link AgroscapeAgentProperty} of a certain {@link AgentBehavior} and for
	 * the given property name.
	 * @param bf  {@link AgentBehavior}
	 * @param name {@String} the name of the behavior
	 * @return {@link AgroscapeAgentProperty}
	 */
	public AgroscapeAgentProperty<?> getBehaviorProperty(BehaviorFactory bf, String name) {
		return this.behaviorProperties.get(bf.getName() + "_"+name);
	}
	
	/**
	 * Gets the Value (i.e. {@link Object}) of a certain {@link AgentBehavior} and for
	 * the given property name. The calling method should take care of the casting.
	 * @param ab
	 * @param name
	 * @return
	 */
	public Object getBehaviorPropertyValue (AgentBehavior ab, String name) {
		AgroscapeAgentProperty<?> bhvP = this.behaviorProperties.get(ab.getName() + "_"+name);
		return bhvP.getValue();
	}
		
	
	/**
	 * Gets the array of the behaviors attached to the agent
	 * @return List of {@link AgentBehavior}
	 */
	public List<AgentBehavior> getBehaviors() {
		return behaviors;
	}
	
	/**
	 * Adds an {@link AgentBehavior} to the agent
	 * @param ab {@link AgentBehavior}
	 */
	public void addBehavior(AgentBehavior ab) {
		this.behaviors.add(ab);
	}

	@Override
	public String toString() {
		return "[uniqID="+this.getId()+", class="+ this.getClass().toString() + "]" + " name="+this.getName();
	}
	
	public String getPropertiesString() {
		String r = "";
		if(this.behaviorProperties.isEmpty()) {
			r = "No BehaviorProperties";
		}
		else {
			String sep="";
			for (AgentBehavior ab : behaviors) {
				r += sep+ab.toString();sep=" | ";
			}
		}	
		return r;
	}
	
	
}
