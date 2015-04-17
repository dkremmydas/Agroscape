/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gr.agroscape.agents;

import gr.agroscape.behaviors.farmers.AFarmerAction;
import gr.agroscape.contexts.MainContext;

import java.util.ArrayList;

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
    protected MainContext mainContext ;

    
    /**
	 * The properties of the agent
	 */
    protected ArrayList<AFarmerAction> actions = new ArrayList<>();
    
    
    /**
     * Constructor with a numeric id as a parameter
     * @param int id, the numeric id to assign
     */
	public HumanAgent(int id)  {
		super();
		this.myId = id;
		HumanAgent.next_id=id++;
		this.mainContext=MainContext.getInstance();
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
	 * A getter of behaviors. It returns a reference to the {@link AFarmerAction}.<br /> 
	 * New {@link AFarmerAction} can be added using the returned reference.
	 * @return
	 */
	public ArrayList<AFarmerAction> getBehaviors() {
		return actions;
	}

	/**
	 * Get {@link MainContext} object
	 * @return
	 */
	public MainContext getMainContext() {
		return mainContext;
	}
    

	
 
    
    
}
