package gr.agroscape.behaviors;

import gr.agroscape.skeleton.contexts.SimulationContext;

import java.util.ArrayList;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import repast.simphony.engine.environment.RunEnvironment;
import repast.simphony.engine.schedule.ISchedule;
import repast.simphony.engine.schedule.ScheduleParameters;

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
				String behaviorName = eElement.getElementsByTagName("Name").item(0).getTextContent();
				String behaviorType = eElement.getElementsByTagName("Type").item(0).getTextContent();
				String factoryClass = eElement.getElementsByTagName("FactoryClass").item(0).getTextContent();
				NodeList scheduledActions = (NodeList) eElement.getElementsByTagName("ScheduledActions").item(0);
				this.bhvs.add(new BehaviorBundle(behaviorName,behaviorType,factoryClass, scheduledActions));
			}
		}
	}

	/**
	 * Add behaviors to the simulation context
	 * @param simulationContext
	 * @throws ClassNotFoundException 
	 * @throws IllegalAccessException 
	 * @throws InstantiationException 
	 */
	public void loadAllBehaviors(SimulationContext simulationContext) throws InstantiationException, IllegalAccessException, ClassNotFoundException {
		ISchedule timeline = RunEnvironment.getInstance().getCurrentSchedule();	
		Iterable<ScheduledAction> actions = new ArrayList<>();
		
		for (BehaviorBundle bhv : this.bhvs) {
			
			actions = bhv.getActions();			
			
			//create the factory
			BehaviorFactory fact = (BehaviorFactory) Class.forName(bhv.factory).newInstance();
			fact.setName(bhv.name);
			
			//add bhv context
			simulationContext.addSubContext(fact.getBehaviorContext());
			
			if(bhv.type.equals(BehaviorType.AgentBehavior)) {
	
				//assign behavior to agents
				Iterable<? extends Behavior> behaviorObjects = fact.getBehaviorObjects(simulationContext);	
				for (Behavior ab : behaviorObjects) {
					ab.getOwner().addBehavior(bhv.name, ab);
					//add any actions that are of AgentAction Type to timeline
					for (ScheduledAction sa : actions) {
						if(sa.getType().equals(ActionType.AgentAction)) {
							timeline.schedule(sa.getParams(), ab, sa.getMethod());
						}						
					}
				}
				
				//add any actions that are of ContextAction Type to timeline
				for (ScheduledAction sa : actions) {
					if(sa.getType().equals(ActionType.ContextAction)) {
						timeline.schedule(sa.getParams(), fact.getBehaviorContext(), sa.getMethod());
					}						
				}
				
				//add properties to agents
				fact.addProperties(simulationContext);

				
			} 
			else if (bhv.type.equals(BehaviorType.ContextBehavior)) {
				//add only the context timeline
				for (ScheduledAction sa : actions) {
					timeline.schedule(sa.getParams(), fact.getBehaviorContext(), sa.getMethod());
				}
			}

			
		} //end loop of behaviors.xml
		
	}
	
	
	
	class BehaviorBundle {
		private String name;
		private BehaviorType type;
		private String factory;
		private ArrayList<ScheduledAction> actions = new ArrayList<>();
		
		public BehaviorBundle(String name, String type, String factory,NodeList scheduledActions) {
			this.name = name;
			this.type = BehaviorType.valueOf(type);
			this.factory = factory;
			this.extractActions(scheduledActions);
		}
		
		private void extractActions(NodeList scheduledActions) {
			for (int temp = 0; temp < scheduledActions.getLength(); temp++) {
				Node nNode = scheduledActions.item(temp);
				if (nNode.getNodeType() == Node.ELEMENT_NODE) {
					Element eElement = (Element) nNode;
					String actionType = eElement.getElementsByTagName("Type").item(0).getTextContent();
					String method = eElement.getElementsByTagName("Method").item(0).getTextContent();
					int start = Integer.parseInt(eElement.getElementsByTagName("Start").item(0).getTextContent());
					int interval = Integer.parseInt(eElement.getElementsByTagName("Interval").item(0).getTextContent());
					int priority = Integer.parseInt(eElement.getElementsByTagName("Priority").item(0).getTextContent());
					this.actions.add(new ScheduledAction(ActionType.valueOf(actionType),method,ScheduleParameters.createRepeating(start, interval, priority)));
				}
			}
		}

		public Iterable<ScheduledAction> getActions() {
			return actions;
		}
		
		
		 
	}
	
	class ScheduledAction {
		private ActionType type;
		private String method;		
		private ScheduleParameters params;
		
		public ScheduledAction(ActionType a, String method, ScheduleParameters params) {
			super();
			this.type = a;
			this.method = method;
			this.params = params;
		}

		public String getMethod() {
			return method;
		}

		public ScheduleParameters getParams() {
			return params;
		}

		public ActionType getType() {
			return type;
		}		
		
	}
	
	enum BehaviorType {
		AgentBehavior, ContextBehavior
	}
	
	enum ActionType {
		AgentAction, ContextAction
	}
	
}
