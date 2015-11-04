package gr.agroscape.skeleton.agents.human;

import java.util.HashMap;


/**
 * A Farmer agent of Agroscape. <p />
 * <p>This is the abstract class that gives some basic functionality:
 *  <ul>
 *  <li>Every farmer has some common properties:
 *  <ul>
 *  
 *  <li>{@link #liquidity Liquidity} (synonym for working capital)</li>
 *  <li>{@link #potentialCrops Potential Crops}, since some farmers might not even consider 
 *  cultivating some of the crops considered in the simulation</li>
 *  <li>thisStepProduction 
 *  //TODO decide if this class is a good place  for the property to be
 *  </li>
 *  </ul>
 *  </li>
 *  
 *  <li>There are also here some helper functions:</li>
 *  <ul>
 *  <li>Get a List of Cultivating (owned+rented) Plots ({@link #getCultivatingPlots()})</li>
 *  <li>Calculate the Cultivating Plot Area ({@link #getCultivatingPlotArea()})</li>
 *  <li>Add a production to the current thisStepProduction ({@link #aggregateProduction(HashMap)})</li>
 *  </ul>
 *  
 *  </ul>
 *  </p>
 *  <p></p>
 * @author Dimitris Kremmydas
  */
public class Farmer extends HumanAgent  {


	public Farmer(int id) {
		super();
		this.setName(id);
	}
	
	public Farmer() {
		super();
	}
	
	
	
	

} //end class
