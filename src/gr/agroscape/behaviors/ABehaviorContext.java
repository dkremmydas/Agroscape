package gr.agroscape.behaviors;

import gr.agroscape.agents.Farmer;
import gr.agroscape.behaviors.farmers.AFarmerBehavior;
import gr.agroscape.behaviors.farmers.stupido.StupidoBhvContext;
import repast.simphony.context.DefaultContext;
import repast.simphony.engine.environment.RunEnvironment;

/**
 * This class is the context of the behavior, enclosing at least a {@link IScheduledBehaviorDataLoader}
 * and all the {@link AFarmerBehavior} objects. <br />
 * It can also have other attributes and methods that are useful for the operation
 * of the contained individual {@link AFarmerBehavior} objects.<br />
 * For a simple example see {@link StupidoBhvContext}. 
 *  
 * @author Dimitris Kremmydas
 *
 * @param <T>
 */
public abstract class ABehaviorContext<T> extends DefaultContext<IScheduledBehavior<T>>{


	protected IScheduledBehaviorDataLoader<T> objectLoader;

	
	/**
	 * Constructor.
	 * Ensures unique names of a behaviors. 
	 * @param name
	 * @param behavingObjects
	 * @param objectLoader
	 */
	public ABehaviorContext(String name, IScheduledBehaviorDataLoader<T> objectLoader) {
		super(name);
		this.setId(name);
		this.objectLoader = objectLoader;
	}
	
	/**
	 * Returns the behavior object for a requested Farmer. <Br />
	 * It does this by traversing all IScheduledBehavior<T> agents in the contextBhv.
	 * @param f
	 * @return
	 */
	public AFarmerBehavior<T> findByFarmerOwner(Farmer f) {
		@SuppressWarnings("unchecked")
		final Class<? super T> clazz = (Class<? super T>) IScheduledBehavior.class;
		
		for (IScheduledBehavior<T> object : this.getObjects(clazz)) {
			if(object instanceof AFarmerBehavior) {
				if(((AFarmerBehavior<T>)object).getOwner().equals(f)) {
					return (AFarmerBehavior<T>)object;
				}
			}
		}
		
		return null;
	}
	
	
	/**
	 * Load BehavingObjects
	 * @param owners
	 * @param dataFile
	 * @param space
	 */
	protected final void loadBehavingObjects() {
		//get container objects, i.e. behavingObjects
		this.objectLoader.setup(this);
	}
	
	/**
	 * Add any IScheduledBehavior object to schedule
	 */
	protected final void addBehavingObjectsToSchedule() {
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
