package gr.agroscape.contexts;

import java.util.ArrayList;
import java.util.HashMap;

import gr.agroscape.agents.Plot;
import gr.agroscape.authorities.LandPropertyRegistry;
import gr.agroscape.authorities.PaymentAuthority;
import gr.agroscape.crops.Crop;
import repast.simphony.context.DefaultContext;
import repast.simphony.context.space.grid.GridFactory;
import repast.simphony.context.space.grid.GridFactoryFinder;
import repast.simphony.space.grid.Grid;
import repast.simphony.space.grid.GridBuilderParameters;
import repast.simphony.space.grid.SimpleGridAdder;
import repast.simphony.space.grid.StrictBorders;
import repast.simphony.valueLayer.GridValueLayer;

/**
 * This is the MainContext. Everything is included here. <br />
 * It contains:
 * <ul>
 * <li>The {@link Crop crops} that are available within Agroscape. They are inside the {@link CropsContext}. The Crops Collection can directly be accessed by callling the static {@link #getAvailableCrops()}</li>
 * <li>The {@link Plot plots} that are available. They are inside the {@link PlotsContext}. The Plots Collection can directly be accessed by callling the static {@link #getAvailablePlots()}</li>
 * <li>The available {@link Farmer farmers}. They are inside the {@link FarmersContext}.</li>
 * <li>The {@link LandPropertyRegistry}. It is available through {@link #getLandPropertyRegistry()}.</li>
 * <li>The {@link PaymentAuthority}.  It is available through {@link MainContext#getPaymentAuthority()}.</li>
 * </ul>
 * @author jkr
 *
 * @param <Object>
 */
public class MainContext extends DefaultContext<Object> {
	
	private static MainContext instance=null;

	private LandPropertyRegistry landPropertyRegistry=new LandPropertyRegistry();
	private PaymentAuthority paymentAuthority=new PaymentAuthority();
	private HashMap<Crop, GridValueLayer> cropSuitability=new HashMap<Crop, GridValueLayer>();
	private int gridWidth=11;
	private int gridHeight=11;
	
	private Grid<Object> grid;
	

	//The value layer of the Owners_id
	private GridValueLayer gvl_Owners;
	
	//The ValueLayer of the Cultivator_id
	private GridValueLayer gvl_Cultivators;
	
	//The ValueLayer of the Cultivator_id
	private GridValueLayer gvl_Tenants;
	
	/**
	 * Only one instance of MainContext exists. 
	 * <br />Singleton Design Pattern (?)
	 * @return
	 */
	public static MainContext getInstance() {
		if (MainContext.instance==null) {MainContext.instance=new MainContext();}
		return MainContext.instance;
	}
	
	/**
	 * Private Constructor, so the existence of a unique instance of MainContext is enforced. 
	 */
	private MainContext() {
		super("MainContext");
		//add Projections
		GridFactory gridFactory = GridFactoryFinder.createGridFactory(null);
		grid = gridFactory.createGrid("grid", this,
						new GridBuilderParameters<Object>(new StrictBorders(),
								new SimpleGridAdder<Object>(), false, gridWidth, gridHeight));


		this.gvl_Owners =  new GridValueLayer("Owners",true,new StrictBorders(), gridWidth, gridHeight);
		this.addValueLayer( gvl_Owners );
		
		this.gvl_Cultivators = new GridValueLayer("Cultivators",true,new StrictBorders(), gridWidth, gridHeight);
		this.addValueLayer( gvl_Cultivators );
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

	public HashMap<Crop, GridValueLayer> getCropSuitability() {
		return cropSuitability;
	}
	

	public Grid<Object> getGrid() {
		return grid;
	}
	
	
	public CropsContext getCropsContext() {
		if(! this.hasSubContext()) throw new NullPointerException("The MainContext does not have any subcontexts yet.");
		return (CropsContext) this.getSubContext("CropsContext");
	}
	
	public PlotsContext getPlotsContext() {
		if(! this.hasSubContext()) throw new NullPointerException("The MainContext does not have any subcontexts yet.");
		return (PlotsContext) this.getSubContext("PlotsContext");
	}
	
	
	public static ArrayList<Crop> getAvailableCrops() {
		if(! MainContext.getInstance().hasSubContext()) throw new NullPointerException("The MainContext does not have any subcontexts yet.");
		return (ArrayList<Crop>)MainContext.getInstance().getCropsContext().getAvailableCrops();
	}
	
	public static ArrayList<Plot> getAvailablePlots() {
		if(! MainContext.getInstance().hasSubContext()) throw new NullPointerException("The MainContext does not have any subcontexts yet.");
		return (ArrayList<Plot>)MainContext.getInstance().getPlotsContext().getAvailablePlots();
	}
	
	public FarmersContext getFarmersContext() {
		if(! this.hasSubContext()) throw new NullPointerException("The MainContext does not have any subcontexts yet.");
		return (FarmersContext) this.getSubContext("FarmersContext");
	}
	
	
	
}
