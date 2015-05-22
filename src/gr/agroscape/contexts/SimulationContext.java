package gr.agroscape.contexts;

import gr.agroscape.agents.Farmer;
import gr.agroscape.agents.Plot;
import gr.agroscape.authorities.LandPropertyRegistry;
import gr.agroscape.authorities.PaymentAuthority;
import gr.agroscape.behaviors.farmers.production.agriculturalActivities.ArableCropCultivation;
import repast.simphony.context.DefaultContext;
import repast.simphony.context.space.grid.GridFactory;
import repast.simphony.context.space.grid.GridFactoryFinder;
import repast.simphony.engine.environment.RunEnvironment;
import repast.simphony.space.grid.Grid;
import repast.simphony.space.grid.GridBuilderParameters;
import repast.simphony.space.grid.SimpleGridAdder;
import repast.simphony.space.grid.StrictBorders;

/**
 * This is the MainContext. Everything is included here. <br />
 * It contains:
 * <ul>
 * <li>The {@link ArableCropCultivation crops} that are available within Agroscape. They are inside the {@link CropsContext}. The Crops Collection can directly be accessed by callling the static {@link #getAvailableCrops()}</li>
 * <li>The {@link Plot plots} that are available. They are inside the {@link PlotsContext}. The Plots Collection can directly be accessed by callling the static {@link #getAvailablePlots()}</li>
 * <li>The available {@link Farmer farmers}. They are inside the {@link FarmersContext}.</li>
 * <li>The {@link LandPropertyRegistry}. It is available through {@link #getLandPropertyRegistry()}.</li>
 * <li>The {@link PaymentAuthority}.  It is available through {@link MainContext#getPaymentAuthority()}.</li>
 * </ul>
 * @author jkr
 *
 * @param <Object>
 */
public class SimulationContext extends DefaultContext<Object> {
	
	private static SimulationContext instance=null;

	private LandPropertyRegistry landPropertyRegistry=new LandPropertyRegistry();
	
	private PaymentAuthority paymentAuthority=new PaymentAuthority();
	
	
	
	/**
	 * The Grid default Width. It is usually altered in the Constructor.
	 */
	private int gridWidth=11;
	
	/**
	 * The Grid default Height. It is usually altered in the Constructor.
	 */
	private int gridHeight=11;
	
	/**
	 * The Grid layer of space
	 */
	private Grid<Object> space;
	
	
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
	private SimulationContext() {
		super("SimulationContext");
		this.setId("SimulationContext");
		
		//set grid width and height
		Integer w = RunEnvironment.getInstance().getParameters().getInteger("gridWidth");
		if(w > 0) this.gridWidth = w;
		
		Integer h = RunEnvironment.getInstance().getParameters().getInteger("gridHeight");
		if(h > 0) this.gridHeight = h;
		
		
		//add Projections
		GridFactory gridFactory = GridFactoryFinder.createGridFactory(null);
		space = gridFactory.createGrid("grid", this,
						new GridBuilderParameters<Object>(new StrictBorders(),
								new SimpleGridAdder<Object>(), false, gridWidth, gridHeight));

	}


	/**
	 * Getter for LandPropertyRegistry
	 * @return
	 */
	public LandPropertyRegistry getLandPropertyRegistry() {
		return landPropertyRegistry;
	}

	/**
	 * Getter for PaymentAuthority
	 * @return
	 */
	public PaymentAuthority getPaymentAuthority() {
		return paymentAuthority;
	}

	
	
	/**
	 * 
	 * @return
	 */
	public int getGridWidth() {
		return gridWidth;
	}


	/**
	 * 
	 * @return
	 */
	public int getGridHeight() {
		return gridHeight;
	}


	public Grid<Object> getSpace() {
		return space;
	}
	
	
	
	public PlotsContext getPlotsContext() {
		if(! this.hasSubContext()) throw new NullPointerException("The PlotsContext does not have any subcontexts yet.");
		return (PlotsContext) this.getSubContext("PlotsContext");
	}
	
	public BehaviorsContext getBehaviorsContext() {
		if(! this.hasSubContext()) throw new NullPointerException("The BehaviorsContext does not have any subcontexts yet.");
		return (BehaviorsContext) this.getSubContext("BehaviorsContext");
	}
		
	public FarmersContext getFarmersContext() {
		if(! this.hasSubContext()) throw new NullPointerException("The MainContext does not have any subcontexts yet.");
		return ((FarmersContext) this.getSubContext("FarmersContext"));
	}	
	

	
	
}
