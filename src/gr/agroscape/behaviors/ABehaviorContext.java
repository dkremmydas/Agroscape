package gr.agroscape.behaviors;

import javax.help.UnsupportedOperationException;

import repast.simphony.context.DefaultContext;
import repast.simphony.dataLoader.ContextBuilder;

public abstract class ABehaviorContext<T extends ABehavior<?>> extends DefaultContext<T> {
	

	public ABehaviorContext(ContextBuilder<T> builder,Object name) {
		this(name,name);
		builder.build(this);
		//this.addContextListener(addScheduledBehavior);		
	}

	protected ABehaviorContext() {
		throw new  UnsupportedOperationException("You cannot construct a ABehaviorContext without a name");
	}

	protected ABehaviorContext(Object name, Object typeID) {
		super(name, typeID);
	}

	protected ABehaviorContext(Object name) {
		super(name);
	}

	
	
	
	

}
