package gr.agroscape.behaviors;

import gr.agroscape.skeleton.contexts.SimulationContext;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import repast.simphony.parameter.ParameterSchema;
import repast.simphony.parameter.Schema;

public class BehaviorsLoader {
	
	Document doc;
	ArrayList<BehaviorBundle> bhvs = new ArrayList<>();
	
	
	public BehaviorsLoader(Document doc) {
		this.doc = doc;
		this.loadBehaviorBundle();
	}
	
	/**
	 * Loads the xml file 
	 */
	private void loadBehaviorBundle() {
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

	/**
	 * Add behaviors to the simulation context
	 * @param simulationContext
	 */
	public void loadAllBehaviors(SimulationContext simulationContext) {

		for (BehaviorFactory bhv : this.bhvs) {
			simulationContext.addSubContext(bhv.getBehaviorContext());
			bhv.assignBehaviors(simulationContext);	
			bhv.addProperties(simulationContext);
		}
		
	}
	
	
	class BehaviorBundle {
		private BehaviorType type;
		private BehaviorContext context;
		private BehaviorFactory factory;
		private AgentBehavior agentClass;
		public BehaviorBundle(BehaviorType btype, BehaviorContext bcontext,
				BehaviorFactory bfactory, AgentBehavior bagentClass) {
			super();
			this.type = btype;
			this.context = bcontext;
			this.factory = bfactory;
			this.agentClass = bagentClass;
		}
		public BehaviorType getBtype() {
			return type;
		}
		public BehaviorContext getBcontext() {
			return context;
		}
		public BehaviorFactory getBfactory() {
			return factory;
		}
		public AgentBehavior getBagentClass() {
			return agentClass;
		}
		
		
	}
	
	enum BehaviorType {
		AgentBehavior, ContextBehavior
	}
	
}
