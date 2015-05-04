package gr.agroscape.behaviors;

import gr.agroscape.contexts.Space;

import java.nio.file.Path;
import java.util.Collection;

import repast.simphony.context.DefaultContext;
import repast.simphony.engine.environment.RunEnvironment;

/**
 * A Behavior is actually a Context<IScheduledBehavior<T>> containing 
 * one {@link IScheduledBehaviorDataLoader} object.<br />
 * During the constructor call, the {@link IScheduledBehaviorDataLoader} is used
 * in order to load all objects* 
 * 
 * 
 * @author Dimitris Kremmydas
 *
 * @param <T>
 */
public abstract class ABehaviorContainer<T> extends DefaultContext<IScheduledBehavior<T>>{


	protected IScheduledBehaviorDataLoader<T> objectLoader;

	
	/**
	 * Constructor.
	 * Ensures unique names of a behaviors. 
	 * @param name
	 * @param behavingObjects
	 * @param objectLoader
	 */
	public ABehaviorContainer(String name, IScheduledBehaviorDataLoader<T> objectLoader,
								Collection<? super T> owners, Path dataFile, Space space) {
		super(name);
		this.setId(name);
		this.objectLoader = objectLoader;
		this.loadBehavingObjects(owners, dataFile, space);
	}
	
	/**
	 * Load BehavingObjects
	 * @param owners
	 * @param dataFile
	 * @param space
	 */
	private void loadBehavingObjects(Collection<? super T> owners, Path dataFile, Space space) {
		
		//get container objects, i.e. behavingObjects
		this.addAll(this.objectLoader.setup(owners, space, dataFile));
		
		
		//add their scheduled behavior to the current schedule
		@SuppressWarnings("unchecked")
		final Class<? super T> clazz = (Class<? super T>) IScheduledBehavior.class;
//System.err.println("# this.getObjects(clazz) objects found " + this.getObjects(clazz).size());		
		for (IScheduledBehavior<T> object : this.getObjects(clazz)) {
//System.err.println("Object " + object.toString() + " / annotadedClass: " + object.getAnnotatedClass().toString());
			RunEnvironment.getInstance().getCurrentSchedule().schedule(object.getAnnotatedClass());			
		}
	}

	

}
