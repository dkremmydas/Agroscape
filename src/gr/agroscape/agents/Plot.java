package gr.agroscape.agents;

import gr.agroscape.contexts.MainContext;
import gr.agroscape.contexts.PlotsContext;
import gr.agroscape.crops.Crop;
import gr.agroscape.main.AgroscapeConfiguration;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;

import repast.simphony.space.grid.GridPoint;
import repast.simphony.valueLayer.GridValueLayer;

/**
 * This is a Plot. Many GridPoints can constitute a Plot.<br /><br />
 * The concern of this class is:
 * <ul>
 * <li> to give information about the Plot (Area, Average Soil Characteristics, etc.)</li>
 * </ul>
 * 
 * 
 * @author Dimitris Kremmydas
 *
 */

public class Plot {

	private static int next_id=0;
	private int myId;
    private ArrayList<GridPoint> gridPoints=new ArrayList<GridPoint>();
    private PlotsContext space;

    /**
     * 
     * @param points
     * @param id
     */
    public Plot(ArrayList<GridPoint> points, PlotsContext sp, int id) {
		super();
		this.space=sp;
		this.myId=id; Plot.next_id=id++;
		this.gridPoints = points;
	}
    
    /**
     * 
     * @param points
     */
    public Plot(ArrayList<GridPoint> points, PlotsContext sp) {
		this(points,sp,Plot.next_id);
	}
    
    /**
     * 
     * @param point
     */
    public Plot(GridPoint point,PlotsContext sp) {
		this(new ArrayList<GridPoint>(Arrays.asList(point)),sp);
	}
    
    /**
     * 
     * @param point
     * @param id
     */
    public Plot(GridPoint point,PlotsContext sp, int id) {
    	this(new ArrayList<GridPoint>(Arrays.asList(point)),sp,id);
	}
    
    /**
     * 
     * @param points
     * @param id
     */
    public Plot(int[][] points,PlotsContext sp, int id) {
    	super();
    	this.space=sp;
    	this.myId=id; Plot.next_id=id++;
		for(int i=0;i<points.length;i++) {
			int[] gp = new int[2]; gp[0]=points[i][0];gp[1]=points[i][1];
			this.addGridPoint(new GridPoint(gp));
					//(Integer.valueOf(points[i][0]),Integer.valueOf(points[i][1])));
		}		
	}
    
    /**
     * 
     * @param points
     */
    public Plot(int[][] points,PlotsContext sp) {
    	this(points,sp,Plot.next_id);	
	}

    /**
     * Get the Area of the Plot (hectares)
     * @return
     */
	public double getArea() {
        return this.gridPoints.size()*AgroscapeConfiguration.getGridPointArea();
    }
	
	
	/**
	 * The average Value of all contained GridPoints in the {@link GridValueLayer} data <br/>
	 * For example, if someone wants to calculate the Average yield of the Plot for a specific Crop, he just has to pass the
	 * {@link GridValueLayer} that keeps the Yields for that Crop.
	 * 
	 * @return
	 */
	public double getAverage(GridValueLayer data) {
		
		double r = 0d;
		for (Iterator<GridPoint> iterator = this.gridPoints.iterator(); iterator.hasNext();) {
			GridPoint gridPoint = iterator.next();
			r = (r + data.get(gridPoint.getX(),gridPoint.getY()));
		}
		return r/this.gridPoints.size();
	}
	
	/**
	 * Get the expected yield for the plot for a specified {@link Plot}.
	 * @param c
	 * @return
	 */
	public double getSuitability(Crop c) {
		GridValueLayer gvl = (GridValueLayer) (MainContext.getInstance().getCropSuitability()).get(c);
		ArrayList<GridPoint> gps = this.getGridPoints();
		double mean = 0d;
		for (GridPoint gp : gps) {
			mean = (mean + gvl.get(gp.getX(),gp.getY()))/2; 
		}
		return mean;
	}
	
	/**
	 * Get the GridPoints of the Plot
	 * @return
	 */
	public ArrayList<GridPoint> getGridPoints() {
		return gridPoints;
	}

	private void addGridPoint(GridPoint p) {
		this.gridPoints.add(p);
	}

	@Override
	public String toString() {
		String r = "["+super.toString()+"]";
		r += " ID=" + this.myId
			 + " / Gridpoints: "
			+ Arrays.toString(this.gridPoints.toArray(new GridPoint[this.gridPoints.size()]));
		return r;
	}
	
	
	
   

	
}
