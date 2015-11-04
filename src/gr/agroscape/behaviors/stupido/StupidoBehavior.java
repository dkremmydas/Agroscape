package gr.agroscape.behaviors.stupido;

import gr.agroscape.behaviors.AgentBehavior;
import gr.agroscape.skeleton.agents.AgroscapeAgent;

import java.util.ArrayList;
import java.util.List;

import repast.simphony.context.Context;
import repast.simphony.engine.schedule.DefaultAction;
import repast.simphony.engine.schedule.IAction;
import repast.simphony.engine.schedule.ScheduleParameters;

public class StupidoBehavior extends AgentBehavior {
	

	public StupidoBehavior(AgroscapeAgent owner, Context<?> bhvContext, StupidoBehaviorFactory bhvFactory) {
		super("Stupido", bhvFactory, owner, bhvContext);
	}

	@Override
	public List<DefaultAction> getScheduledActions() {
		List<DefaultAction> actions= new ArrayList<DefaultAction>();
		
		ScheduleParameters p= ScheduleParameters.createRepeating(1, 360);
		IAction action =  this.getBhvFactory().getActionFactory().createAction(this, "printHappiness");
		
		actions.add(new DefaultAction(p, action,1));		
			
		return actions;
	}
	
	public void printHappiness() {
		System.out.println("I am a happy behavior of agent: " + this.getOwner());
	}

}
