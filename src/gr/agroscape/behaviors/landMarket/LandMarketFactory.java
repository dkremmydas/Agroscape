package gr.agroscape.behaviors.landMarket;

import java.util.List;

import javax.media.j3d.Behavior;

import gr.agroscape.behaviors.BehaviorContext;
import gr.agroscape.behaviors.BehaviorFactory;
import gr.agroscape.skeleton.agents.AgroscapeAgent;
import gr.agroscape.skeleton.agents.AgroscapeAgentProperty;
import gr.agroscape.skeleton.contexts.SimulationContext;

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
	public void assignBehaviors(SimulationContext simulationContext) {
		//Nothing assigned here
	}

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

}
