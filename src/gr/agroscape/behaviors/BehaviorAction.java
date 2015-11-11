package gr.agroscape.behaviors;

import repast.simphony.engine.schedule.ScheduleParameters;

public class BehaviorAction {
	
	private String name;
	
	private String method;
	
	private ScheduleParameters params;
	
	private Object object;

	public BehaviorAction(String name, String method,
			ScheduleParameters params, Object object) {
		super();
		this.name = name;
		this.method = method;
		this.params = params;
		this.object = object;
	}
	
	public BehaviorAction(String method,
			ScheduleParameters params, Object object) {
		super();
		this.name = method;
		this.method = method;
		this.params = params;
		this.object = object;
	}

	public String getName() {
		return name;
	}

	public String getMethod() {
		return method;
	}

	public ScheduleParameters getParams() {
		return params;
	}

	public Object getObject() {
		return object;
	}
	
	
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return "["+super.toString()+"] BehaviorAction name:"+this.name+" Object: "+this.object.toString();
	}
	
	
	
}
