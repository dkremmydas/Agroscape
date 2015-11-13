package gr.agroscape.behaviors;

import gr.agroscape.dataLoaders.AgroscapeAllBehaviorsDataLoader;
import gr.agroscape.skeleton.contexts.SimulationContext;

import java.util.HashSet;
import java.util.Set;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import repast.simphony.parameter.ParameterSchema;
import repast.simphony.parameter.Schema;

public class DefaultBehaviorsLoader implements AgroscapeAllBehaviorsDataLoader{
	
	Set<BehaviorFactory> bhvs;
	

	public DefaultBehaviorsLoader(Set<BehaviorFactory> bhvs) {
		super();
		this.bhvs = bhvs;
	}

	public DefaultBehaviorsLoader(Schema bhvsSchema) {
		super();
		this.bhvs = new HashSet<>();
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
	
	public DefaultBehaviorsLoader(Document doc) {
		super();
		this.bhvs = new HashSet<>();
		
		doc.getDocumentElement().normalize();

		NodeList nList = doc.getElementsByTagName("Behavior");
		
		for (int temp = 0; temp < nList.getLength(); temp++) {

			Node nNode = nList.item(temp);
			if (nNode.getNodeType() == Node.ELEMENT_NODE) {
				Element eElement = (Element) nNode;
				String bNAme = eElement.getElementsByTagName("Name").item(0).getTextContent();
				String bClass = eElement.getElementsByTagName("ClassString").item(0).getTextContent();
				
				BehaviorFactory bf;
				try {
					bf = (BehaviorFactory) Class.forName(bClass).newInstance();
					this.bhvs.add(bf);
				} catch (InstantiationException | IllegalAccessException
						| ClassNotFoundException e) {
					e.printStackTrace();
				}
				
				
			}
		}
				
		
	}


	@Override
	public void loadAllBehaviors(SimulationContext simulationContext) {

		for (BehaviorFactory bhv : this.bhvs) {
			simulationContext.addSubContext(bhv.getBehaviorContext());
			bhv.assignBehavior(simulationContext);	
		}
		
	}
	
	
	
	
}
