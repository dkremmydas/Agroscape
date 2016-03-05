package gr.agroscape.skeleton.agents;

import gr.agroscape.behaviors.Behavior;
import gr.agroscape.skeleton.contexts.SimulationContext;

import java.util.HashMap;
import java.util.concurrent.atomic.AtomicInteger;

import repast.simphony.context.Context;
import repast.simphony.util.ContextUtils;

import com.google.common.collect.ClassToInstanceMap;
import com.google.common.collect.MutableClassToInstanceMap;

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
	private ClassToInstanceMap<AgroscapeAgentProperty<?>>  behaviorProperties ;
	//private final ClassToInstanceMap<MyObject> instances = MutableClassToInstanceMap.create();
	
	/**
	 * <p>The list of behaviors that are attached to the agent</p>
	 */
	private HashMap<String,Behavior> behaviors = new HashMap<>();
	
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
		this.behaviorProperties = MutableClassToInstanceMap.create();
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
	public void addBehaviorProperty(AgroscapeAgentProperty<?> bp, Class<? extends AgroscapeAgentProperty<?>> clazz) {
		this.behaviorProperties.put(clazz, bp);
	}
	
	@SuppressWarnings("unchecked")
	public void addBehaviorProperty(AgroscapeAgentProperty<?> bp) {
		this.behaviorProperties.put((Class<? extends AgroscapeAgentProperty<?>>) bp.getClass(),bp);
	}
    
	/**
	 * Gets the {@link AgroscapeAgentProperty} of a certain {@link Behavior} and for
	 * the given property name.
	 * @param bf  {@link Behavior}
	 * @param name {@String} the name of the behavior
	 * @return {@link AgroscapeAgentProperty}
	 */
	public AgroscapeAgentProperty<?> getBehaviorProperty(Class<? extends AgroscapeAgentProperty<?>> clazz) {
		return this.behaviorProperties.get(clazz);
	}		
	
	/**
	 * Gets the array of the behaviors attached to the agent
	 * @return List of {@link Behavior}
	 */
	public HashMap<String,Behavior> getBehaviors() {
		return behaviors;
	}
	
	/**
	 * Adds an {@link Behavior} to the agent
	 * @param ab {@link Behavior}
	 */
	public void addBehavior(String name,Behavior ab) {
		this.behaviors.put(name,ab);
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
			for (String abName : behaviors.keySet()) {
				r += sep+behaviors.get(abName).toString();sep=" | ";
			}
		}	
		return r;
	}
	
	
}
