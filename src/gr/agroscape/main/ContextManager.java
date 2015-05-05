package gr.agroscape.main;

import gr.agroscape.agents.Farmer;
import gr.agroscape.behaviors.farmers.production.arableCropProduction.ArableCropProductionBhvContext;
import gr.agroscape.behaviors.farmers.stupido.StupidoBhvContext;
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
		
		ArrayList<Farmer> ff=new ArrayList<Farmer>();
		CollectionUtils.addAll(ff, farmers.getRandomObjects(Farmer.class,2));
		StupidoBhvContext sfc = new StupidoBhvContext(ff);
		farmers.attachBehavior(sfc);
		
		
		
		//step 4, Attach Behavior (ArableCropFarmer_MP)
		
		ArrayList<Farmer> ff2=new ArrayList<Farmer>();
		CollectionUtils.addAll(ff2, farmers.getRandomObjects(Farmer.class,1));		
		ArableCropProductionBhvContext acpc = new ArableCropProductionBhvContext(ff2);
		farmers.attachBehavior(acpc);
		
		
		//step 5, Attach Behavior (SocialNetworking)


		return this.space;
	}


} //end class
