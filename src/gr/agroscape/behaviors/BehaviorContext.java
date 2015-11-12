package gr.agroscape.behaviors;

import repast.simphony.context.DefaultContext;

/**
 * <p>The BehaviorContext holds common properties that are specific to the behavior's logic.</p>
 * <p>For example in the ArableCropProductionBehavior, the BehavrioContext will include the ListOfArableCrops</p>
 * 
 * @author Dimitris Kremmydas
 * @version %I%
 * @since 2.0
 *
 */
public abstract class BehaviorContext extends DefaultContext<Object> {
	

	protected String name;

	protected BehaviorContext(String name) {
		super(name,name);
		this.name=name;
	}
	
	


}
