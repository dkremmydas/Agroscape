package gr.agroscape.behaviors;

import gr.agroscape.dataLoaders.AgroscapeAllBehaviorsDataLoader;
import gr.agroscape.skeleton.contexts.SimulationContext;

import java.util.ArrayList;
import java.util.List;

import repast.simphony.parameter.ParameterSchema;
import repast.simphony.parameter.Schema;

public class DefaultBehaviorsLoader implements AgroscapeAllBehaviorsDataLoader{
	
	List<BehaviorFactory> bhvs;
	

	public DefaultBehaviorsLoader(List<BehaviorFactory> bhvs) {
		super();
		this.bhvs = bhvs;
	}

	public DefaultBehaviorsLoader(Schema bhvsSchema) {
		super();
		this.bhvs = new ArrayList<>();
		ParameterSchema bhvSchema = (ParameterSchema) bhvsSchema.getDetails("Behavior");
		String bs = bhvSchema.getDefaultValue().toString();

		BehaviorFactory bf;
		try {
			bf = (BehaviorFactory) Class.forName(bs).newInstance();
			this.bhvs.add(bf);
		} catch (InstantiationException | IllegalAccessException
				| ClassNotFoundException e) {
			e.printStackTrace();
		}
				
		
	}


	@Override
	public void loadAllBehaviors(SimulationContext simulationContext) {

		for (BehaviorFactory bhv : this.bhvs) {
			simulationContext.addSubContext(bhv.buildBehaviorContext());
			bhv.assignBehavior(simulationContext);	
		}
		
	}
	
	
	
	
}
