package gr.agroscape.behaviors.stupido;

import gr.agroscape.behaviors.BehaviorContext;
import gr.agroscape.skeleton.contexts.SimulationContext;
import repast.simphony.valueLayer.GridValueLayer;

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
	
	private GridValueLayer gvl;
	
	public StupidoBehaviorContext(GridValueLayer gvl) {
		super("StupidoBehavior");
		this.setId("StupidoBehavior");
		this.commonProperty = (int) Math.rint(Math.random()*10.0);
		this.gvl = gvl;
		SimulationContext.getInstance().addValueLayer(this.gvl);
	}

	public int getCommonProperty() {
		return commonProperty;
	}

	public void setCommonProperty(int commonProperty) {
		this.commonProperty = commonProperty;
	}

	public GridValueLayer getGvl() {
		return gvl;
	}

	
	
	

}
