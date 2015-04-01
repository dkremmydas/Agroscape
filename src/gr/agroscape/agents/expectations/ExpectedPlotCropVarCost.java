package gr.agroscape.agents.expectations;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

import gr.agroscape.agents.Plot;
import gr.agroscape.contexts.MainContext;
import gr.agroscape.crops.Crop;

/**
 * The expectation regarding the variable cost for each Crop in each Plot (Euro/h)
 * @author jkr
 *
 */
public class ExpectedPlotCropVarCost extends AbstractExpectation<Plot, HashMap<Crop,Long>> {

	
	public ExpectedPlotCropVarCost(HashMap<Plot, HashMap<Crop,Long>> values) {
		super(values);
	}
	
	public ExpectedPlotCropVarCost(ArrayList<Plot> keys) {
		super(keys);
	}

	/**
	 * For each Plot and Crop the default VarCost is 50*(1+random(50))
	 */
	@Override
	HashMap<Plot, HashMap<Crop, Long>> getDefaultValues(ArrayList<Plot> plots) {
		
		MainContext mc = MainContext.getInstance();
		ArrayList<Crop> crops = mc.getCropsContext().getAvailableCrops();
		
		HashMap<Plot, HashMap<Crop,Long>> v=new HashMap<Plot, HashMap<Crop,Long>>();
		Random r = new Random();
		
		for (Plot p: plots) {
			HashMap<Crop,Long> tmp=new HashMap<Crop, Long>();
       		for(Crop c: crops) {
       			tmp.put(c, (long)(50*(1+r.nextInt(50))));
       			//tmp.put(c, 50l);
       		}
       		v.put(p, tmp);
    	}
		return v;
	}
	
	
	
	

	

}
