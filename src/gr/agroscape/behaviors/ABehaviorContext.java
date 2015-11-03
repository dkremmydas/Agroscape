package gr.agroscape.behaviors;

import javax.help.UnsupportedOperationException;

import repast.simphony.context.DefaultContext;
import repast.simphony.dataLoader.ContextBuilder;

/**
 * <p>The BehaviorContext holds common properties that are specific to the behavior's logic.</p>
 * <p>For example in the ArableCropProductionBehavior, the BehavrioContext will include the ListOfArableCrops</p>
 * 
 * @author Dimitris Kremmydas
 * @version %I%
 * @since 2.0
 *
 */
public abstract class ABehaviorContext extends DefaultContext<T> {
	

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
