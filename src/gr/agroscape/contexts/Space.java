package gr.agroscape.contexts;

import gr.agroscape.agents.Farmer;
import gr.agroscape.agents.Plot;
import gr.agroscape.authorities.LandPropertyRegistry;
import gr.agroscape.authorities.PaymentAuthority;
import gr.agroscape.behaviors.farmers.production.agriculturalActivities.ArableCropCultivation;

import java.util.ArrayList;
import java.util.Iterator;

import repast.simphony.context.DefaultContext;
import repast.simphony.context.space.grid.GridFactory;
import repast.simphony.context.space.grid.GridFactoryFinder;
import repast.simphony.engine.environment.RunEnvironment;
import repast.simphony.space.grid.Grid;
import repast.simphony.space.grid.GridBuilderParameters;
import repast.simphony.space.grid.GridPoint;
import repast.simphony.space.grid.SimpleGridAdder;
import repast.simphony.space.grid.StrictBorders;
import repast.simphony.valueLayer.GridValueLayer;

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
public class Space extends DefaultContext<Object> {
	
	private static Space instance=null;

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
	 * What Crop should we show in {@link gvl_CropSuitability} ?
	 */
	private ArableCropCultivation activeDisplaySuitabilityCrop;
	
	/**
	 * The Grid layer of space
	 */
	private Grid<Object> grid;
	

	//The value layer of the Owners_id
	private GridValueLayer gvl_Owners;
	
	//The ValueLayer of the Cultivator_id
	private GridValueLayer gvl_Cultivators;
	
	//The ValueLayer of the Cultivator_id
	//private GridValueLayer gvl_Tenants;
	
	//The ValueLayer showing the production decisions of the farmers
	private GridValueLayer gvl_ProductionDecisions;
	
	
	
	
	/**
	 * Only one instance of MainContext exists. 
	 * <br />Singleton Design Pattern (?)
	 * @return
	 */
	public static Space getInstance() {
		if (Space.instance==null) {Space.instance=new Space();}
		Space.instance.setId("MainContext");
		return Space.instance;
	}
	
	/**
	 * Private Constructor, so the existence of a unique instance of MainContext is enforced. 
	 */
	private Space() {
		super("MainContext");
		
		//set grid width and height
		Integer w = RunEnvironment.getInstance().getParameters().getInteger("gridWidth");
		if(w > 0) this.gridWidth = w;
		
		Integer h = RunEnvironment.getInstance().getParameters().getInteger("gridHeight");
		if(h > 0) this.gridHeight = h;
		
		
		//add Projections
		GridFactory gridFactory = GridFactoryFinder.createGridFactory(null);
		grid = gridFactory.createGrid("grid", this,
						new GridBuilderParameters<Object>(new StrictBorders(),
								new SimpleGridAdder<Object>(), false, gridWidth, gridHeight));


		this.gvl_Owners =  new GridValueLayer("Owners",true,new StrictBorders(), gridWidth, gridHeight);
		this.addValueLayer( gvl_Owners );
		
		this.gvl_Cultivators = new GridValueLayer("Cultivators",true,new StrictBorders(), gridWidth, gridHeight);
		this.addValueLayer( gvl_Cultivators );
		
		this.gvl_ProductionDecisions = new GridValueLayer("ProductionDecisions",true,new StrictBorders(), gridWidth, gridHeight);
		this.addValueLayer( gvl_ProductionDecisions );
		
	
		
	}
	
	
	/**
	 * Getter for {@link #gvl_ProductionDecisions}
	 * @return
	 */
	public GridValueLayer get_gvlProductionDecisions() {
		return gvl_ProductionDecisions;
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
	
	
	public static ArrayList<ArableCropCultivation> getAvailableCrops() {
		if(! Space.getInstance().hasSubContext()) throw new NullPointerException("The MainContext does not have any subcontexts yet.");
		return (ArrayList<ArableCropCultivation>)Space.getInstance().getCropsContext().getAvailableCrops();
	}
	
	public static ArrayList<Plot> getAvailablePlots() {
		if(! Space.getInstance().hasSubContext()) throw new NullPointerException("The MainContext does not have any subcontexts yet.");
		return (ArrayList<Plot>)Space.getInstance().getPlotsContext().getAvailablePlots();
	}
	
	public FarmersContext getFarmersContext() {
		if(! this.hasSubContext()) throw new NullPointerException("The MainContext does not have any subcontexts yet.");
		return ((FarmersContext) this.getSubContext("FarmersContext"));
	}	
	
	/**
	 * Update Value Layers
	 */
	public void updateValueLayers() {
		this.landPropertyRegistry.updateOwnerValueLayer(gvl_Owners);
		
		//copy CropSuitability Value Layers
		GridValueLayer from = this.getCropsContext().getCropSuitability().get(this.activeDisplaySuitabilityCrop);
		//ValueLayersUtilities.copyGridValueLayers(from, this.gvl_CropSuitability);
		
		//update cultivation valuelayer
		//APlot
		
		for (Iterator<Plot> iterator = landPropertyRegistry.getAllPlots().iterator(); iterator.hasNext();) {
			Plot p = iterator.next();
			for(GridPoint gp: p.getGridPoints()) {
				//this.gvl_ProductionDecisions.set(p.getAgriculturalLandUse().getId(), gp.getX(),gp.getY());
			}
		}
	}

	
	
	
}
