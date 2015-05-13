package gr.agroscape.behaviors;

import gr.agroscape.agents.Farmer;
import gr.agroscape.behaviors.farmers.AFarmerBehavior;
import gr.agroscape.behaviors.farmers.stupido.StupidoBhvContext;
import repast.simphony.context.DefaultContext;

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
		/*
		//add a contextListener that adds automatically the annotated class
		this.addContextListener(new ContextListener<IScheduledBehavior<T>>() {
			@Override
			public void eventOccured(ContextEvent<IScheduledBehavior<T>> ev) {
				if(ev.equals(EventType.AGENT_ADDED)) {
					RunEnvironment.getInstance().getCurrentSchedule().schedule(ev.getTarget().getAnnotatedClass());
				}
				
			}
		});*/
	
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
	 * Load BehavingObjects. They are added to the context collection and also parsed for schedule additions
	 *  //TODO give directly the IScheduledBehaviorDataLoader
	 * @param owners
	 * @param dataFile
	 * @param space
	 */
	public final void loadBehavingObjects() {
		this.objectLoader.setup(this);		
	}


	

}
