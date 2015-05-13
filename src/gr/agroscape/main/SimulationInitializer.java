package gr.agroscape.main;

import repast.simphony.engine.environment.RunEnvironmentBuilder;
import repast.simphony.engine.schedule.IAction;
import repast.simphony.engine.schedule.NonModelAction;
import repast.simphony.scenario.ModelInitializer;
import repast.simphony.scenario.Scenario;
import repast.simphony.visualization.IDisplay;



/**
 * This class makes certain displays
 * @author Dimitris Kremmydas
 *
 */
public class SimulationInitializer implements ModelInitializer {

	  private IDisplay display;

	  @NonModelAction
	  static class DisplayUpdater implements IAction {

	    private IDisplay display;

	    public DisplayUpdater(IDisplay display) {
	      this.display = display;
	    }

	    public void execute() {
	      display.update();
	    }
	  }

	  /**
	   * This is ran after the model has been loaded. This is only ran once, but the settings set
	   * through the {@link repast.simphony..scenario.Scenario} will apply to every run of the simulation.
	   *
	   * @param scen the {@link repast.simphony..scenario.Scenario} object that hold settings for the run
	   */
	  public void initialize(Scenario scen, RunEnvironmentBuilder builder) {
		  
		  /*
	  }

	    scen.addMasterControllerAction(new NullAbstractControllerAction() {
	      @Override
	      public void runInitialize(RunState runState, Context context, Parameters runParams) {
	        display = new DisplayValueLayer(context);
	        runState.getGUIRegistry().addDisplay("ValueLayers", GUIRegistryType.OTHER, display);
	        runState.getScheduleRegistry().getModelSchedule().schedule(ScheduleParameters.createRepeating(1, 1,
	                ScheduleParameters.END), new DisplayUpdater(display));

	      }
	      

	      @Override
	      public void runCleanup(RunState runState, Context context) {
	        display.destroy();
	        display = null;
	      }

	      public String toString() {
	        return "Create a custom display";
	      }
	    });
	         
	*/
	  }

	}

