package gr.agroscape.external.classes.GamsInterface.tests;

import static org.junit.Assert.assertNotNull;
import gr.agroscape.external.classes.GamsInterface.GamsModelSolver;
import gr.agroscape.external.classes.GamsInterface.GamsResult;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

public class TestGamsExe {
	
	private String workingDir;
	
	private String systemDir = "C:\\Program Files (x86)\\gams_22.4\\gams_22.4";
	
	private String modelFile ;
	

	@Before
	public void setUp() throws Exception {
			this.systemDir="C:\\Program Files (x86)\\gams_22.4\\gams_22.4";
			this.workingDir="C:\\Users\\jkr\\Dropbox\\CurrentProjects\\Phd Proposal\\03. Work on progress\\Lombardy Biogas ABM\\data\\testGAMS";
			this.modelFile= this.workingDir + "\\transport.gms";
	}
	
	
	@Test
	public void testGamsLombardyModel() throws IOException {
		GamsModelSolver gms = new GamsModelSolver();
		System.out.println(gms.toString());
		
		String root = "C:\\Users\\jkr\\Dropbox\\CurrentProjects\\Phd Proposal\\03. Work on progress\\Lombardy Biogas ABM\\productionModel";
		String modelF = root + "\\farmerModel.gms";
		ArrayList<String> dataF = new ArrayList<String>(Arrays.asList(root+"\\loadData.gms",root+"\\data.gdx",root+"\\definitions.gms",
				root+"\\price.inc"));
		
		GamsResult gs = gms.solve(modelF,dataF);
		System.out.println(gs.toString());
		
		
		
		assertNotNull(gs);
	}
	
	@Test
	public void testGamsModelSolver() throws IOException {
		GamsModelSolver gms = new GamsModelSolver();
		System.out.println(gms.toString());
		GamsResult gs = gms.solve(this.modelFile);
		System.out.println(gs.toString());
		assertNotNull(gs);
	}

	@Test
	public void testExecute() throws IOException {
		
		List<String> command = new ArrayList<String>();
	    command.add(this.systemDir + "\\gams.exe");
	    command.add(this.modelFile);
	    command.add("Gdx=output");
	    
	    ProcessBuilder builder = new ProcessBuilder(command);
	    builder.directory(new File(this.workingDir));
	    final Process p = builder.start();
	    
	    //Wait to get exit value
        try {
            int exitValue = p.waitFor();
            System.out.println(exitValue);
            //execute "gdx2sqlite -i results.gdx -o results.db";
            command.clear();
            command.add(this.systemDir + "\\gdx2sqlite.exe");
    	    command.add("-i");
    	    command.add("output.gdx");
    	    command.add("-o");
    	    command.add("results.db");
    	    final Process p2 = builder.start();
    	    try {
                int exitValue2 = p2.waitFor();
                System.out.println(exitValue2);
    	    } catch (InterruptedException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
	    
	    System.out.println("Program terminated!");
		
		assertNotNull(p);
	}

}
