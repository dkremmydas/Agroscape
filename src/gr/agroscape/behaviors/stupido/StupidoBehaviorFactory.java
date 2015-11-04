package gr.agroscape.behaviors.stupido;

import gr.agroscape.agents.AgroscapeAgent;
import gr.agroscape.behaviors.BehaviorContext;
import gr.agroscape.behaviors.BehaviorFactory;

import java.util.ArrayList;
import java.util.List;

public class StupidoBehaviorFactory extends BehaviorFactory {
	
	private StupidoBehaviorContext bhvContext;

	 
	public StupidoBehaviorFactory() {
		super();
		this.name = "Stupido Behavior Factory";
		this.bhvContext = new StupidoBehaviorContext();
	}

	//abstract void assignBehavior(List<? extends AgroscapeAgent> agents);
	
	@Override
	public void assignBehavior(Iterable<? extends AgroscapeAgent> farmers) {
		for (AgroscapeAgent f : farmers) {
			f.addBehavior(new StupidoBehavior(f, this.bhvContext, this));
		}
	}


	
	@Override
	public BehaviorContext buildBehaviorContext() {
		return this.bhvContext;
	}

	@Override
	public List<AgroscapeAgent> getNewAgents() {
		return new ArrayList<AgroscapeAgent>();
	}

	@Override
	public String getBehaviorInformation() {
		// TODO Auto-generated method stub
		return "This is the Stupido Behavior. A test behavior";
	}





}
