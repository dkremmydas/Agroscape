package gr.agroscape.agents;

import gr.agroscape.behaviors.ABehavior;
import gr.agroscape.contexts.SimulationContext;

import java.util.concurrent.atomic.AtomicInteger;

import org.apache.commons.collections4.map.MultiKeyMap;

/**
 * Defines common characteristics for all Agroscape Agents. <br />
 * Those characteristics are :
 * <ul>
 * <li>Unique incremental Id</li>
 * 
 * @author Dimitris Kremmydas
 *
 */
public abstract class AgroscapeAgent {
	
	/**
	 * Gives the unique id for all Agroscape Agents. Thread safe.
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
	
	
	private Table BehaviorProperties<Class<ABehavior<T>>>
	
	/**
     * A reference to the mainContext. 
     */
    protected SimulationContext mainContext ;

	/**
	 * Constructor
	 */
	public AgroscapeAgent() {
		id=uniqueId.getAndIncrement();
		this.mainContext=SimulationContext.getInstance();	
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
	public int getId() {
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
    
	
	@Override
	public String toString() {
		return "[uniqID="+this.getId()+", class="+ this.getClass().toString() + "]" + " name="+this.getName();
	}
	
	
}
