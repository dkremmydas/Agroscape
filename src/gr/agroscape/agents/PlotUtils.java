package gr.agroscape.agents;

import java.util.ArrayList;

import repast.simphony.space.grid.GridPoint;

/**
 * Utilities regarding Plot manipulation and creation 
 * 
 * @author Dimitris Kremmydas
 */
public class PlotUtils {
	
	/**
	 * Create a new Plot that contains all {@link GridPoint}s from (leftX,topX) to (rightX,bottomY);
	 * @param leftX
	 * @param topY
	 * @param rightX
	 * @param bottomY
	 * @return
	 */
	public Plot newRectanglePlot(int leftX, int topY, int rightX, int bottomY) {
		// public Plot(ArrayList<GridPoint> points, PlotsContext sp)
		ArrayList<GridPoint> gps = new ArrayList<GridPoint>();
		for(int i=leftX;i <=rightX; i++ ) {
			for(int j=bottomY;j <=topY; i++ ) {
				gps.add(new GridPoint(i,j));
			}
		}
		
		return new Plot(gps);
	}
	
	
	/**
	 * Returns a new Plot that contains all points of given Plots
	 * @param p1
	 * @param p2
	 * @return
	 * @throws CloneNotSupportedException 
	 */
	public Plot mergePlots(Plot p1, Plot p2) throws CloneNotSupportedException {
		ArrayList<GridPoint> gps1 = p1.getGridPoints();
		ArrayList<GridPoint> gps2 = p2.getGridPoints();
		ArrayList<GridPoint> gps3 = new ArrayList<GridPoint>();
		gps3.addAll(gps1);
		gps3.addAll(gps2);
		return (new Plot(gps3));
	}
	

}
