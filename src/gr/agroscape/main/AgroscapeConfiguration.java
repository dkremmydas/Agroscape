package gr.agroscape.main;


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
