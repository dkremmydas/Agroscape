package gr.agroscape.main;

import gr.agroscape.agents.Farmer;
import gr.agroscape.behaviors.farmers.production.arableCropProduction.ArableCropProducerContainer;
import gr.agroscape.contexts.FarmersContext;
import gr.agroscape.contexts.PlotsContext;
import gr.agroscape.contexts.Space;
import gr.agroscape.dataLoaders.DefaultDataLoader;
import gr.agroscape.dataLoaders.ICanLoadAgroscapeData;

import java.util.ArrayList;

import org.apache.commons.collections4.CollectionUtils;

import repast.simphony.context.Context;
import repast.simphony.dataLoader.ContextBuilder;


/**
 * This is the "main" class. 
 * <br />Everything is loaded here through Overriding {@link #build()} method.
 * This is directly related to the definition of Agroscape.rs/
 * <br />Also the workflow of the AgroScape simulation is defined here, in the {@link #step()} method.
 * 
 * @author Dimitris Kremmydas
 *
 */
public class ContextManager implements ContextBuilder<Object> {
	
	
	private Space space;
	

	/**
	 * It builds the Contexts of Agroscape. <br />
	 * The steps that this method does, are: <br />
	 * 1. Create the MainContext as the parentContext <br />
	 * 2. Create empty SubContexts and load them to parentContext <br />
	 * 3. Create {@link ICanLoadAgroscapeData dataLoader} and load data
	 * 
	 */
	@Override
	public Context<Object> build(Context<Object> context) {
		
		//step 1. keep a reference		
		this.space=Space.getInstance();
		
		

		
		//step 2, create empty  subContexts
		PlotsContext plots = new PlotsContext(); //create plots' context
		this.space.addSubContext(plots);
		
		FarmersContext farmers = new FarmersContext(); //create farmers' context
		this.space.addSubContext(farmers);
		
		
		//step 3, create dataLoader
		ICanLoadAgroscapeData dataLoader = new DefaultDataLoader();
//		try {
			//String excelFileLocation = RunEnvironment.getInstance().getParameters().getString("ExcelDataFile");
			//dataLoader = new ExcelDataLoader(excelFileLocation);
			//dataLoader.loadCropsContext(crops);
			dataLoader.loadFarmersContext(farmers);
			dataLoader.loadPlotsContext(plots);
			//dataLoader.loadCropSuitabilityMap(this.space.getCropsContext().getCropSuitability(),this.space);

			//GridValueLayer vl = this.mainContext.getCropSuitability().get(this.mainContext.getCropsContext().getCropByName("maize"));
			//System.err.println(ValueLayers.getValueLayerAsPrintedMatrix(vl));
			//this.space.setActiveDisplaySuitabilityCrop(this.space.getCropsContext().getCropByName("maize"));
			dataLoader.initLandPropertyRegistry(this.space.getLandPropertyRegistry());
			//dataLoader.initPaymentAuthority(this.space.getPaymentAuthority());
			
//		} catch (InvalidFormatException | IOException e) {
			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
		
		
		//step 4, Attach Behavior (Stupido)
		/*
		ArrayList<Farmer> ff=new ArrayList<Farmer>();
		CollectionUtils.addAll(ff, farmers.getRandomObjects(Farmer.class,3));
		StupidoFarmersContainer sfc = new StupidoFarmersContainer(ff);
		farmers.attachBehavior(sfc);
		*/
		
		
		//step 4, Attach Behavior (ArableCropFarmer_MP)
		///*
		ArrayList<Farmer> ff2=new ArrayList<Farmer>();
		CollectionUtils.addAll(ff2, farmers.getRandomObjects(Farmer.class,2));		
		ArableCropProducerContainer acpc = new ArableCropProducerContainer(ff2);
		farmers.attachBehavior(acpc);
		//*/
		
		

		return this.space;
	}

	
	/**
	 * This is the algorithm of simulation. The following actions are taken:<br />
	 * <ol>
	 * <li><strong>Production stage: </strong>For all {@link Farmer} objects, decide the production</li>
	 * </ol>
	 */
	/*
	public void step() {
		System.err.println("Do Step");
		
		//1. Production Stage
		
		//1.1 keep decisions of farmers
		HashMap<Farmer,ArrayList<AProductionDecision>> all_decisions = new HashMap<>();
				
		//1.2 select farmers (randomly) and force them to make decisions
		Iterable<IHasProductionAbility> fi = this.space.getFarmersContext().getRandomObjects(Farmer.class, this.space.getFarmersContext().size());
		for (IHasProductionAbility f : fi) {
			if (f instanceof Farmer) {
				Farmer ff = (Farmer) f;
				all_decisions.put(ff, (ArrayList<AProductionDecision>) ff.makeProductionDecision(ff.getCultivatingPlots()));
			}
			//System.err.println(f.toString() + " | ProductpcionDecision: " + pc);
		}
		
		//1.3 given their decision, apply the feedback to Plots and Farmers
		for (Entry<Farmer, ArrayList<AProductionDecision>> entry : all_decisions.entrySet()) {
			Farmer f = entry.getKey();
			ArrayList<AProductionDecision> decs = entry.getValue();
		    for(AProductionDecision dec : decs) {
		    	dec.feedbackToPlot(dec.getPlot());
		    	dec.feedbackToFarmer(f);
		    }
		}
		
		
		
		this.space.updateValueLayers();
		
		System.err.println(this.space.get_gvlProductionDecisions().toString());
		
		
		//System.err.println(this.mainContext.get_gvlProductionDecisions().toString());
	}
	*/
	

	
	
	
	
	

}
