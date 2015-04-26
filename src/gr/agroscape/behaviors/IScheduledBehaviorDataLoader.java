package gr.agroscape.behaviors;

import gr.agroscape.contexts.Space;

import java.nio.file.Path;
import java.util.Collection;

/**
 * An interface declaring actions for a BehaviorDataLoader
 * 
 * @author jkr
 *
 */
public interface IScheduledBehaviorDataLoader<T> {
	
	/**
	 * Setups owners and space
	 * 
	 * @param df
	 */
	public Collection<? extends ABehavedObject<T>> setup(Collection<T> owners, Space space, Path dataFile);


}
