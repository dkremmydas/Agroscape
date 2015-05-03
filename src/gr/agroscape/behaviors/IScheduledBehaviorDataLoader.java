package gr.agroscape.behaviors;

import gr.agroscape.contexts.Space;

import java.nio.file.Path;
import java.util.Collection;

/**
 * An interface declaring actions for a BehaviorDataLoader.<br />
 * It accepts (at minimum) a {@link Collection} of objects, the {@link Space} object
 * and returns a {@link Collection} of objects (possibly subclassing the accepted objects)
 * that implement {@link IScheduledBehavior} interface.
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
	
	/**
	 * Load without data file
	 * @param owners
	 * @param space
	 * @return
	 */
	public Collection<IScheduledBehavior<T>> setup(Collection<? super T> owners, Space space);


}
