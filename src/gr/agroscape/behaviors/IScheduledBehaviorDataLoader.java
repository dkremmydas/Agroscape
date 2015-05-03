package gr.agroscape.behaviors;

import gr.agroscape.contexts.Space;

import java.nio.file.Path;
import java.util.Collection;

/**
 * An interface declaring actions for a BehaviorDataLoader
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
	public Collection<IScheduledBehavior<T>> setup(Collection<? super T> owners, Space space, Path dataFile);


}
