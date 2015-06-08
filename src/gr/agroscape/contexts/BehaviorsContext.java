package gr.agroscape.contexts;

import repast.simphony.context.ContextEvent;
import repast.simphony.context.ContextListener;
import repast.simphony.context.DefaultContext;
import repast.simphony.context.ContextEvent.EventType;
import repast.simphony.engine.environment.RunEnvironment;

public class BehaviorsContext extends DefaultContext<Object>  {

	private ContextListener<?> addScheduledBehavior = new ContextListener<Object>() {
		@Override
		public void eventOccured(ContextEvent<Object> ev) {
			if (ev.getType() ==	EventType.AGENT_ADDED)	 {
				RunEnvironment.getInstance().getCurrentSchedule().schedule(ev.getTarget());
			}
		}
	};
	
	/**
	 * A full constructor
	 * @param dataLoader
	 * @param valuelayers
	 * @param lpr
	 */
	@SuppressWarnings("unchecked")
	public BehaviorsContext() {
		super("BehaviorsContext");
		this.setId("BehaviorsContext");
		this.addContextListener((ContextListener<Object>) addScheduledBehavior);
	}



	
	
	
	

} //end class



