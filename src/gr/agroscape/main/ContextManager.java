package gr.agroscape.main;

import gr.agroscape.agents.Farmer;
import gr.agroscape.behaviors.farmers.production.agriculturalActivities.ArableCropCultivation;
import gr.agroscape.behaviors.farmers.production.interfaces.AProductionDecision;
import gr.agroscape.behaviors.farmers.production.interfaces.IHasProductionAbility;
import gr.agroscape.contexts.CropsContext;
import gr.agroscape.contexts.FarmersContext;
import gr.agroscape.contexts.MainContext;
import gr.agroscape.contexts.PlotsContext;
import gr.agroscape.dataLoaders.ExcelDataLoader;
import gr.agroscape.dataLoaders.ICanLoadAgroscapeData;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map.Entry;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;

import repast.simphony.context.Context;
import repast.simphony.dataLoader.ContextBuilder;
import repast.simphony.engine.environment.RunEnvironment;
import repast.simphony.engine.schedule.ScheduleParameters;


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
	
	
	private MainContext mainContext;
	

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
		this.mainContext=MainContext.getInstance();
		
		

		
		//step 2, create empty  subContexts
		PlotsContext plots = new PlotsContext(); //create plots' context
		this.mainContext.addSubContext(plots);
		//this.parentContext.add(plots);
		
		FarmersContext farmers = new FarmersContext(); //create farmers' context
		this.mainContext.addSubContext(farmers);
		//this.parentContext.add(farmers);
		
		CropsContext crops = new CropsContext(); //create crops' context
		this.mainContext.addSubContext(crops);	
		//this.parentContext.add(crops);
		
		
		//step 3, create dataLoader
		//ISimulationDataLoader dataLoader = new DefaultDataLoader();
		ICanLoadAgroscapeData dataLoader;
		try {
			String excelFileLocation = RunEnvironment.getInstance().getParameters().getString("ExcelDataFile");
			dataLoader = new ExcelDataLoader(excelFileLocation);
			dataLoader.loadCropsContext(crops);
			dataLoader.loadFarmersContext(farmers);
			dataLoader.loadPlotsContext(plots);
			dataLoader.loadCropSuitabilityMap(this.mainContext.getCropsContext().getCropSuitability(),this.mainContext);

			//GridValueLayer vl = this.mainContext.getCropSuitability().get(this.mainContext.getCropsContext().getCropByName("maize"));
			//System.err.println(ValueLayers.getValueLayerAsPrintedMatrix(vl));
			this.mainContext.setActiveDisplaySuitabilityCrop(this.mainContext.getCropsContext().getCropByName("maize"));
			
			
			
			dataLoader.initLandPropertyRegistry(this.mainContext.getLandPropertyRegistry());
			dataLoader.initPaymentAuthority(this.mainContext.getPaymentAuthority());
			
		} catch (InvalidFormatException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		//step 1.5 add schedule methods
		ScheduleParameters params = ScheduleParameters.createRepeating(1, 1) ;
		RunEnvironment.getInstance().getCurrentSchedule().schedule (params , this , "step");
		
		
		//step 4
		this.mainContext.setActiveDisplaySuitabilityCrop(ArableCropCultivation.getCropByName("maize"));
		
		
		
		System.err.println("Everything is Loaded");
		
		return this.mainContext;
	}

	
	/**
	 * This is the algorithm of simulation. The following actions are taken:<br />
	 * <ol>
	 * <li><strong>Production stage: </strong>For all {@link Farmer} objects, decide the production</li>
	 * </ol>
	 */
	public void step() {
		System.err.println("Do Step");
		
		//1. Production Stage
		
		//1.1 keep decisions of farmers
		HashMap<Farmer,ArrayList<AProductionDecision>> all_decisions = new HashMap<>();
				
		//1.2 select farmers (randomly) and force them to make decisions
		Iterable<IHasProductionAbility> fi = this.mainContext.getFarmersContext().getRandomObjects(Farmer.class, this.mainContext.getFarmersContext().size());
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
		
		
		
		this.mainContext.updateValueLayers();
		
		System.err.println(this.mainContext.get_gvlProductionDecisions().toString());
		
		
		//System.err.println(this.mainContext.get_gvlProductionDecisions().toString());
	}
	

	
	
	
	
	

}
