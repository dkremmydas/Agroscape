package gr.agroscape.utilities;

import gr.agroscape.skeleton.agents.plot.Plot;

import java.util.ArrayList;

import repast.simphony.space.grid.GridPoint;

/**
 * Utilities regarding Plot manipulation and creation 
 * 
 * @author Dimitris Kremmydas
 */
public final class PlotUtilities {
	
	
	/*
	 * Private Constructor. This class is a Utility class
	 */
	private  PlotUtilities() {
		
	}
	
	/**
	 * Create a new Plot that contains all {@link GridPoint}s from (leftX,topX) to (rightX,bottomY);
	 * @param leftX
	 * @param topY
	 * @param rightX
	 * @param bottomY
	 * @return
	 */
	public static Plot newRectanglePlot(int leftX, int topY, int rightX, int bottomY) {
		ArrayList<GridPoint> gps = new ArrayList<GridPoint>();
		for(int i=leftX;i <=rightX; i++ ) {
			for(int j=topY;j <=bottomY; j++ ) {
				gps.add(new GridPoint(i,j));
			}
		}
		Plot p = new Plot(gps);
		return p;
	}
	
	
	/**
	 * Returns a new Plot that contains all points of given Plots
	 * @param p1
	 * @param p2
	 * @return
	 * @throws CloneNotSupportedException 
	 */
	public static Plot mergePlots(Plot p1, Plot p2) throws CloneNotSupportedException {
		ArrayList<GridPoint> gps1 = p1.getGridPoints();
		ArrayList<GridPoint> gps2 = p2.getGridPoints();
		ArrayList<GridPoint> gps3 = new ArrayList<GridPoint>();
		gps3.addAll(gps1);
		gps3.addAll(gps2);
		return (new Plot(gps3));
	}
	

}
