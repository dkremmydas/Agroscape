package gr.agroscape.external.classes.GamsInterface.tests;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import com.gams.api.GAMSGlobals;
import com.gams.api.GAMSJob;
import com.gams.api.GAMSOptions;
import com.gams.api.GAMSWorkspace;

public class TestGamsExe {
	
	GAMSWorkspace ws;

	@Before
	public void setUp() throws Exception {
		this.ws = new GAMSWorkspace("C:\\Users\\jkr\\Dropbox\\CurrentProjects\\Phd Proposal\\03. Work on progress\\Lombardy Biogas ABM\\data\\testGAMS", 
				"C:\\Program Files (x86)\\gams_22.4\\gams_22.4", 
				GAMSGlobals.DebugLevel.SHOW_LOG
			);
	}

	@Test
	public void test() {
		
		 GAMSJob t2 = ws.addJobFromFile("transport.gms");
         // create GAMSOption "opt" and define "incname" as "tdata"
         GAMSOptions opt = ws.addOptions();
         t2.run(opt);
		
		assertNotNull(t2);
	}

}
