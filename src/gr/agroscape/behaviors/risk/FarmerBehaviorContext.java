package gr.agroscape.behaviors.risk;

import repast.simphony.engine.environment.RunEnvironment;
import repast.simphony.random.RandomHelper;
import repast.simphony.valueLayer.GridValueLayer;
import gr.agroscape.behaviors.BehaviorContext;

public class FarmerBehaviorContext extends BehaviorContext {
	
	
	private GridValueLayer yields;
	

	protected FarmerBehaviorContext() {
		super("ProductionRealization");
		this.yields =  new GridValueLayer("cropsYield", true, 
				RunEnvironment.getInstance().getParameters().getInteger("gridWidth"),
				RunEnvironment.getInstance().getParameters().getInteger("gridHeight"));
		this.addValueLayer(this.yields);
	}
	
	
	public void updateYields() {
		RandomHelper.createNormal(0.5d, 0.3d);
		for(int i=0;i<this.yields.getDimensions().getWidth();i++) {
			for(int j=0;j<this.yields.getDimensions().getHeight();j++) {
				double y = RandomHelper.nextDoubleFromTo(0d, 1d);
				this.yields.set((y>=0 && y<=1)?y:.5, i,j);
			}
		}
	}
	
	public GridValueLayer getYields() {
		return this.yields;
	}

}
