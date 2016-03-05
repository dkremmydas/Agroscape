package gr.agroscape.behaviors.landMarket;

import gr.agroscape.behaviors.Behavior;
import gr.agroscape.behaviors.BehaviorContext;
import gr.agroscape.behaviors.BehaviorFactory;
import gr.agroscape.skeleton.agents.AgroscapeAgent;
import gr.agroscape.skeleton.contexts.SimulationContext;

import java.util.List;

/**
 * <p>The purposes of this class are:</p>
 * <ul>
 * <li></li>
 * </ul>
 * 
 * @author Dimitris Kremmydas
 * @version %G%
 * @since 2.1
 *
 */
public class LandMarketFactory extends BehaviorFactory {


	@Override
	public void addProperties(SimulationContext simulationContext) {
		// TODO Auto-generated method stub

	}

	@Override
	public BehaviorContext getBehaviorContext() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<AgroscapeAgent> getNewAgents() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getBehaviorInformation() {
		return "This is the LandMarket Behavior.";
	}

	@Override
	public Iterable<? extends Behavior> getBehaviorObjects(SimulationContext simulationContext) {
		return null;
	}
	
	

	

}
