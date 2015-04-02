package gr.agroscape.agents.expectations;

import gr.agroscape.agents.Plot;
import gr.agroscape.contexts.MainContext;
import gr.agroscape.crops.Crop;

import java.util.ArrayList;
import java.util.HashMap;


public class ExpectedPlotCropYield extends AbstractExpectation<Plot, HashMap<Crop,Float>> {

	
	public ExpectedPlotCropYield(HashMap<Plot, HashMap<Crop,Float>> values) {
		super(values);
		// TODO Auto-generated constructor stub
	}
	
	public ExpectedPlotCropYield(ArrayList<Plot> keys) {
		super(keys);
	}

	@Override
	HashMap<Plot, HashMap<Crop, Float>> getDefaultValues(ArrayList<Plot> plots) {
		HashMap<Plot, HashMap<Crop,Float>> v=new HashMap<Plot, HashMap<Crop,Float>>();
		for (Plot p: plots) {
       		HashMap<Crop,Float> tmp=new HashMap<Crop, Float>();
       		for(Crop c: MainContext.getAvailableCrops()) {
       			tmp.put(c, (float)p.getSuitability(c));
       			//tmp.put(c,1f);
       		}
       		v.put(p, tmp);
    	}
		return v;
	}

	
	
	

	

}
