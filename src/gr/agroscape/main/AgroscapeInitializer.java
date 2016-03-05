package gr.agroscape.main;

import gr.agroscape.behaviors.BehaviorsLoader;
import gr.agroscape.skeleton.contexts.FarmersContext;
import gr.agroscape.skeleton.contexts.PlotsContext;
import gr.agroscape.skeleton.contexts.SimulationContext;
import gr.agroscape.skeleton.dataLoaders.AgroscapeSkeletonDataLoader;

import java.io.File;
import java.io.IOException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.apache.log4j.Level;
import org.w3c.dom.Document;
import org.xml.sax.SAXException;

import repast.simphony.context.Context;
import repast.simphony.dataLoader.ContextBuilder;
import repast.simphony.engine.environment.RunEnvironment;


/**
 * This is the "main" class. The purpose is to:
 * <ul>
 * <li>create the empty {@link SimulationContext}</li>
 * <li>create the empty {@link FarmersContext}</li> 
 * <li>create the empty {@link PlotsContext}</li> 
 * </ul> 
 *
 * @author Dimitris Kremmydas
 * @version $Revision$
 * @since 2.0
 *
 */
public class AgroscapeInitializer implements ContextBuilder<Object> {
	
	
	private SimulationContext simulationContext;
	
	private AgroscapeSkeletonDataLoader skeletonDataLoader;
	
	private BehaviorsLoader behaviorsDataLoader ;

	
	public AgroscapeInitializer() {
		this.simulationContext = SimulationContext.getInstance();
		SimulationContext.logMessage(this.getClass(), Level.DEBUG, "Created AgroscapeInitializer");
	}

	


	/**
	 * It builds the Contexts of Agroscape. <br />
	 * The steps that this method does, are: <br />
	 * 1. //TODO complete documentation
	 * 
	 */

	@Override
	public Context<Object> build(Context<Object> context)  {
		SimulationContext.logMessage(this.getClass(), Level.DEBUG, "Start building SimulationContet");
			
		//1. set dataLoaders
		this.setSkeletonDataLoaderFromParametersXML();
		this.setBehaviorsDataLoaderFromParametersXML();
		
		//2. Initialize space
		this.skeletonDataLoader.initSimulationSpace(this.simulationContext.getSpace());
		
		//3. create plots
		PlotsContext plots = new PlotsContext(); //create plots' context
		this.skeletonDataLoader.loadPlotsContext(plots);
		this.simulationContext.addSubContext(plots);
		
		//4. create farmers
		FarmersContext farmers = new FarmersContext(); 
		this.skeletonDataLoader.loadFarmersContext(farmers);
		this.simulationContext.addSubContext(farmers);
		
		//5. landPropertyRegistry
		this.skeletonDataLoader.initLandPropertyRegistry(this.simulationContext.getLandPropertyRegistry());
		
		//6.load behaviors
		try {
			this.behaviorsDataLoader.loadAllBehaviors(this.simulationContext);
		} catch (InstantiationException | IllegalAccessException
				| ClassNotFoundException e) {
			SimulationContext.logMessage(this.getClass(), Level.INFO, "Fatal error loading behaviors");
			e.printStackTrace();
			System.exit(5);			
		}
		
		//7. Return the created SimulationContext
		return this.simulationContext;
	}

	
	/**
	 * Set skeletonDataLoader according to the value of "skeletonDataLoaderClass" in the parameters.xml
	 */
	private void setSkeletonDataLoaderFromParametersXML() {
		
		String loaderName = RunEnvironment.getInstance().getParameters().getString("skeletonDataLoaderClass");
		try {
			this.skeletonDataLoader = (AgroscapeSkeletonDataLoader) Class.forName(loaderName).newInstance();

		} catch (InstantiationException | IllegalAccessException
				| ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new NullPointerException();
		}

	
	}
	
	/**
	 * Initialize behaviorsDataLoader with the xml document in the file
	 * that the parameter "behaviorXMLfile" is pointing
	 */
	private void setBehaviorsDataLoaderFromParametersXML() {
		
		String behaviorXMLfile = RunEnvironment.getInstance().getParameters().getString("behaviorXMLfile");
		DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
		DocumentBuilder dBuilder;
		Document doc = null;
		try {
			dBuilder = dbFactory.newDocumentBuilder();
			 doc = dBuilder.parse(new File(behaviorXMLfile));
		} catch (ParserConfigurationException e1) {
			e1.printStackTrace();
		}
		catch (SAXException | IOException e) {
			e.printStackTrace();
		}
		this.behaviorsDataLoader = new BehaviorsLoader(doc);
		
		
	}


} //end class
