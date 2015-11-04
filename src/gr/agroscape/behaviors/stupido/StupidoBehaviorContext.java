package gr.agroscape.behaviors.stupido;

import gr.agroscape.behaviors.BehaviorContext;

/**
 * This BehaviorContext is in order to simulate the sharing of a common Behavior property
 * 
 * @author Dimitris Kremmydas
 * @version %I%
 * @since 2.0
 *
 */
public class StupidoBehaviorContext extends BehaviorContext {

	private int commonProperty;
	
	protected StupidoBehaviorContext() {
		super("Stupido Behavior Context");
		this.commonProperty = (int) Math.rint(Math.random()*10.0);
	}

	public int getCommonProperty() {
		return commonProperty;
	}

	public void setCommonProperty(int commonProperty) {
		this.commonProperty = commonProperty;
	}	
	
		

}
