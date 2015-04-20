/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gr.agroscape.agents;

import gr.agroscape.behaviors.farmers.AFarmerBehavior;
import gr.agroscape.contexts.Space;

import java.util.Map;

import org.apache.commons.collections4.map.HashedMap;


/**
 * A human agent of AgroScape.
 * <p>This abstract class defines the data shared by all implemented classes: 
 * <ul>
 * <li>Automatic assignment of ID to each agent (see static {@link #myId})</li>
 * <li>Having a reference to the MainContext (see {@link #mainContext})
 * <li>A {@link HashedMap} of properties</li>
 * </ul>
 * </p> 
 * @author Dimitris Kremmydas
 */
public abstract class HumanAgent {
    
    private static int next_id;
    
    /**
     * An incrementing integer, identified uniquely the Agent
     */
    protected int myId;
    
    /**
     * A reference to the mainContext. 
     */
    protected Space mainContext ;

    
    /**
	 * The properties of the agent
	 */
    protected HashedMap<Class<? extends AFarmerBehavior>,AFarmerBehavior> behaviors = new HashedMap<>();
    
    
    /**
     * Constructor with a numeric id as a parameter
     * @param int id, the numeric id to assign
     */
	public HumanAgent(int id)  {
		super();
		this.myId = id;
		HumanAgent.next_id=id++;
		this.mainContext=Space.getInstance();
	}
	
	/**
	 * Constructor
	 */
	public HumanAgent()  {
		this(HumanAgent.next_id++);
	}
	

	@Override
	public String toString() {
		return "["+super.toString()+"]" + " ID="+this.myId;
	}
    
	/**
	 * Getter for Id of agent
	 * @return
	 */
	public Integer getID() {
		return this.myId;
	}

	

	/**
	 * A getter of behaviors. It returns a reference to the {@link AFarmerBehavior}.<br /> 
	 * New {@link AFarmerBehavior} can be added using the returned reference.
	 * @return
	 */
	public HashedMap<Class<? extends AFarmerBehavior>,AFarmerBehavior> getBehaviors() {
		return this.behaviors;
	}

	/**
	 * Get a Behavior of a specific Class
	 * @param c
	 * @return
	 */
	public AFarmerBehavior getBehavior(Class<? extends AFarmerBehavior> c) {
		
		for (Map.Entry<Class<? extends AFarmerBehavior>, AFarmerBehavior> entry : this.behaviors.entrySet()) {
	    	if(entry.getKey().equals(c)) {
	    		return entry.getValue();
	    	}
		}
		
		return null;
		
	}
	
	
	/**
	 * Get {@link MainContext} object
	 * @return
	 */
	public Space getMainContext() {
		return this.mainContext;
	}
    

	
 
    
    
}
