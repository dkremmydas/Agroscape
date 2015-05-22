package gr.agroscape.behaviors;

import javax.help.UnsupportedOperationException;

import repast.simphony.context.DefaultContext;
import repast.simphony.dataLoader.ContextBuilder;

public abstract class ABehaviorContext extends DefaultContext<ABehavior<?>> {
	
	public ABehaviorContext(ContextBuilder<? extends ABehavior<?>> builder,Object name) {
		this(name,name);
		builder.build(this);
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
