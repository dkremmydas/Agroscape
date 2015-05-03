package gr.agroscape.behaviors;

import gr.agroscape.contexts.Space;

import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

import repast.simphony.engine.environment.RunEnvironment;


public class ABehaviorContainer<T> {
	
	static ArrayList<String> behaviorNames = new ArrayList<>();
	
	protected String name;
	
	protected Collection<IScheduledBehavior<T>> behavingObjects;
	
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
	final public void loadBehavingObjects(Collection<T> owners, Path dataFile, Space space) {
		
		//get container objects, i.e. behavingObjects
		this.behavingObjects = this.objectLoader.setup(owners, space, dataFile);
		
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
	
	
	
	

}
