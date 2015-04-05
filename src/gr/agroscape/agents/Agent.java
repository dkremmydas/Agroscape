/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gr.agroscape.agents;

import gr.agroscape.contexts.MainContext;


/**
 * A human agent of AgroScape.
 * <p>This abstract class defines the basic operations shared by all implemented classes: 
 * <ul>
 * <li>Automatic assignment of ID to each agent (see static {@link #myId})</li>
 * <li>Having a reference to the MainContext (see {@link #mainContext})
 * </ul>
 * </p> 
 * @author Dimitris Kremmydas
 */
public abstract class Agent {
    
    private static int next_id;
    
    /**
     * An incrementing integer, identified uniquely the Agent
     */
    private int myId;
    protected MainContext mainContext ;
    
    
    /**
     * Constructor with a numeric id as a parameter
     * @param int id, the numeric id to assign
     */
	public Agent(int id)  {
		super();
		this.myId = id;
		Agent.next_id=id++;
		this.mainContext=MainContext.getInstance();
	}
	
	/**
	 * Constructor
	 */
	public Agent()  {
		this(Agent.next_id++);
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
    

 
    
    
}
