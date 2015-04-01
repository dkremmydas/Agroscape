package gr.agroscape.main;

import gr.agroscape.crops.Crop;

import java.util.ArrayList;

public final class AgroscapeConfiguration {
	
	/**
	 * The area of a GridPoint (hectares)
	 */
	private static double gridPointArea = 1d;  
	

	public static double getGridPointArea() {
		return gridPointArea;
	}

	public static void setGridPointArea(double gridPointArea) {
		AgroscapeConfiguration.gridPointArea = gridPointArea;
	}


	
	
	
	

}
