/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gr.agroscape.agents;

import repast.simphony.engine.schedule.ScheduledMethod;
import gr.agroscape.contexts.MainContext;


/**
 * ShortName: A
 * @author jkr
 */
public abstract class Agent {
    
    private static int next_id;
    private int myId;
    protected MainContext mainContext ;
    
    
    /**
     * Constructor
     * @param id
     */
	public Agent(int id)  {
		super();
		this.myId = id;
		Agent.next_id=id++;
		this.mainContext=MainContext.getInstance();
	}
	
	public Agent()  {
		this(Agent.next_id++);
	}
	

	@Override
	public String toString() {
		return "["+super.toString()+"]" + " ID="+this.myId;
	}
    
	
	public Integer getID() {
		return this.myId;
	}
    

 
    
    
}
