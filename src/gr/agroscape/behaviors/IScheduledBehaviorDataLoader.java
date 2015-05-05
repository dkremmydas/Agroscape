package gr.agroscape.behaviors;

import gr.agroscape.agents.Farmer;
import gr.agroscape.contexts.Space;

import java.nio.file.Path;
import java.util.Collection;

/**
 * An interface declaring actions for a BehaviorDataLoader.<br />
 * It takes as an argument the {@link ABehaviorContext} and should make any changes to it accordingly.<br />
 * It follows the Functor design pattern
 * 
 * @author Dimitris Kremmydas
 *
 */
public interface IScheduledBehaviorDataLoader<T> {
	
	/**
	 * Setups owners and space
	 * 
	 * @param df
	 */
	public void setup(ABehaviorContext<T> container);
	

}
