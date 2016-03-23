package gr.agroscape.external.classes.GamsInterface;

import gr.agroscape.skeleton.agents.human.Farmer;

import java.util.ArrayList;

import com.gams.api.GAMSGlobals;
import com.gams.api.GAMSGlobals.DebugLevel;
import com.gams.api.GAMSWorkspace;

public class GamsModelSolver {
	
	private String workingDir;
	
	private String systemDir = "C:\\Program Files (x86)\\gams_22.4\\gams_22.4";
	
	private GAMSWorkspace ws;
	
	private GAMSGlobals.DebugLevel debugLevel;
	
	
	public GamsModelSolver(String workingDir, DebugLevel debugLevel) {
		this.workingDir=workingDir;
		this.debugLevel = debugLevel;
		this.ws = new GAMSWorkspace(this.workingDir, this.systemDir, this.debugLevel);
	}
	
	public GamsModelSolver(String workingDir) {
		this(workingDir, GAMSGlobals.DebugLevel.OFF);
	}



	public void execute() {

	}
	
	
	public ArrayList<Farmer> getResults() {
		ArrayList<Farmer> r = new ArrayList<>();
		
		
		return r;
	}

}
