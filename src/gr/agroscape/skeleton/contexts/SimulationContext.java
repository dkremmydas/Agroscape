package gr.agroscape.skeleton.contexts;

import gr.agroscape.skeleton.agents.human.Farmer;
import gr.agroscape.skeleton.agents.plot.Plot;
import gr.agroscape.skeleton.authorities.LandPropertyRegistry;
import gr.agroscape.skeleton.projections.SimulationSpace;

import org.apache.log4j.Level;

import repast.simphony.context.DefaultContext;
import simphony.util.messages.MessageCenter;

/**
 * This is the MainContext. Everything is included here. <br />
 * It contains:
 * <ul>
 * <li>The {@link Plot plots} that are available. They are inside the {@link PlotsContext}. The Plots Collection can directly be accessed by callling the static {@link #getAvailablePlots()}</li>
 * <li>The available {@link Farmer farmers}. They are inside the {@link FarmersContext}.</li>
 * <li>The {@link LandPropertyRegistry}. It is available through {@link #getLandPropertyRegistry()}.</li>
 * </ul>
 * @author jkr
 *
 * @param <Object>
 */
public class SimulationContext extends DefaultContext<Object> {
	
	private static SimulationContext instance=null;

	private LandPropertyRegistry landPropertyRegistry=new LandPropertyRegistry();
		
	/**
	 * The Space of the simulation
	 */
	private SimulationSpace space ;
	
	
	/**
	 * Only one instance of MainContext exists. 
	 * <br />Singleton Design Pattern (?)
	 * @return
	 */
	public static SimulationContext getInstance() {
		if (SimulationContext.instance==null) {SimulationContext.instance=new SimulationContext();}
		return SimulationContext.instance;
	}
	
	/**
	 * Private Constructor, so the existence of a unique instance of MainContext is enforced. 
	 */
	public SimulationContext() {
		super("SimulationContext","SimulationContext");
		this.space = new SimulationSpace();
		SimulationContext.instance = this;
	}


	/**
	 * Getter for LandPropertyRegistry
	 * @return
	 */
	public LandPropertyRegistry getLandPropertyRegistry() {
		return landPropertyRegistry;
	}


	public SimulationSpace getSpace() {
		return this.space;
	}
	
	public PlotsContext getPlotsContext() {
		if(! this.hasSubContext()) throw new NullPointerException("The PlotsContext does not have any subcontexts yet.");
		return (PlotsContext) this.getSubContext("PlotsContext");
	}
	
		
	public FarmersContext getFarmersContext() {
		if(! this.hasSubContext()) throw new NullPointerException("The MainContext does not have any subcontexts yet.");
		return ((FarmersContext) this.getSubContext("FarmersContext"));
	}	
	

	
	public static void logMessage(Class<?> clazz, Level level,Object message) {
		MessageCenter mc = MessageCenter.getMessageCenter(clazz);
		mc.fireMessageEvent(level, message, null);
	}
	

	
}
