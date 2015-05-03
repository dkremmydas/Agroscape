package gr.agroscape.behaviors;

import gr.agroscape.contexts.Space;

import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Collection;

import repast.simphony.engine.environment.RunEnvironment;

/**
 * A Behavior is actually a Container that has many {@link IScheduledBehavior} objects
 * and one {@link IScheduledBehaviorDataLoader} object.
 * 
 * 
 * 
 * @author Dimitris Kremmydas
 *
 * @param <T>
 */
public class ABehaviorContainer<T> {
	
	static ArrayList<String> behaviorNames = new ArrayList<>();
	
	protected String name;
	
	protected ArrayList<IScheduledBehavior<T>> behavingObjects;
	
	protected IScheduledBehaviorDataLoader<T> objectLoader;

	
	/**
	 * Constructor.
	 * Ensures unique names of a behaviors. 
	 * @param name
	 * @param behavingObjects
	 * @param objectLoader
	 */
	public ABehaviorContainer(String name, IScheduledBehaviorDataLoader<T> objectLoader) {
		super();
		if(ABehaviorContainer.behaviorNames.contains(name)) {
			throw new IllegalArgumentException("The name of the proposed behavior exists !");
		}else {
			this.name = name;
			this.objectLoader = objectLoader;
		}
		
	}
	
	/**
	 * Load BehavingObjects
	 * @param owners
	 * @param dataFile
	 * @param space
	 */
	final public void loadBehavingObjects(Collection<? super T> owners, Path dataFile, Space space) {
		
		//get container objects, i.e. behavingObjects
		this.behavingObjects = (ArrayList<IScheduledBehavior<T>>) this.objectLoader.setup(owners, space, dataFile);
		
		//add their scheduled behavior to the current schedule
		for (IScheduledBehavior<T> object : this.behavingObjects) {
			RunEnvironment.getInstance().getCurrentSchedule().schedule(object.getAnnotatedClass());			
		}
	}

	/**
	 * Getter of name
	 * @return
	 */
	final public String getName() {
		return name;
	}
	
	
	@SuppressWarnings("unchecked")
	public T getBehavingObject(int index) {
		return (T) this.behavingObjects.get(index);
	}
	
	

}
