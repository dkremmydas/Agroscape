package gr.agroscape.external.classes.GamsInterface;

import java.io.File;
import java.io.IOException;
import java.nio.file.FileVisitResult;
import java.nio.file.Path;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.ArrayList;
import java.util.List;

import org.apache.ivy.util.StringUtils;

import com.google.common.io.Files;

/*
 * Responsible for solving GAMS model that contain a bunch of Farmers
 * A gams model file and an input.gdx have to be provided. 
 * The results are provided by a sqlite database containing the output of the model
 * 
 * GAMS Error Codes (from: https://www.gams.com/help/index.jsp?topic=%2Fgams.doc%2Fuserguides%2Fmccarl%2Fgams_command_line_parameters_1.htm)
 * cmexRC 	cmexRC modulo 256 	Description
 *	0 	0 	normal return
 *	1 	1 	solver is to be called the system should never return this number
 *	2 	2 	there was a compilation error
 *	3 	3 	there was an execution error
 *	4 	4 	system limits were reached
 *	5 	5 	there was a file error
 *	6 	6 	there was a parameter error
 *	7 	7 	there was a licensing error
 *	8 	8 	there was a GAMS system error
 *	9 	9 	GAMS could not be started
 *	10 	10 	out of memory
 *	11 	11 	out of disk 
 * 
 * 
 */
public class GamsModelSolver {
	
	private File workingDir;
	
	private String systemDir = "C:\\Program Files (x86)\\gams_22.4\\gams_22.4";
	
	private String modelFile;
	
	private File dataFile;
	
	
	public GamsModelSolver() {		
		this.getNewWorkingDir();
	}
	

	public GamsResult solve(String modelFile) throws IOException {
		return this.solve(modelFile,"");
	}

	public GamsResult solve(String modelFile, String dataFile) throws IOException {
		this.modelFile=modelFile;
		this.dataFile=new File(dataFile);		
		this.getNewWorkingDir();
		return this.doSolve();
	}
	
	private void getNewWorkingDir() {
		try {
			if(! (this.workingDir==null)) {
				this.deleteExistingWorkingDir();
			}
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		this.workingDir = Files.createTempDir();
	}
	
	private void deleteExistingWorkingDir() throws IOException {
		if(! this.workingDir.exists()) return;
		java.nio.file.Files.walkFileTree(this.workingDir.toPath(), new SimpleFileVisitor<Path>() {
			   @Override
			   public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
				   java.nio.file.Files.delete(file);
			       return FileVisitResult.CONTINUE;
			   }

			   @Override
			   public FileVisitResult postVisitDirectory(Path dir, IOException exc) throws IOException {
				   java.nio.file.Files.delete(dir);
			       return FileVisitResult.CONTINUE;
			   }
			});
	}
	
	
	private GamsResult doSolve() throws IOException {
		//copy data file to working dir
		if(this.dataFile.exists()) {
			Files.copy(this.dataFile, new File(this.workingDir+File.pathSeparator+this.dataFile.getName()));
		}
		
		//create command
		List<String> command = new ArrayList<String>();
	    command.add(this.systemDir + "\\gams.exe");
	    command.add(this.modelFile);
	    command.add("Gdx=output");
	    
	    //create processbuilder
	    ProcessBuilder builder = new ProcessBuilder(command);
	    builder.directory(this.workingDir);
	    final Process p = builder.start();
	    
	    int exitValueGams=666, exitValueGdx=666;
		
	    try {
	    	exitValueGams = p.waitFor();
	    	
	    	if(exitValueGams!=0) {
	    		String exeCmd = org.apache.commons.lang3.StringUtils.join(command," ");
	    		System.out.println(exeCmd);
		    	return new GamsResult(new File(""),GamsResultCodes.GAMS_SOLVE_PROBLEM);
	    	}
            //execute "gdx2sqlite -i results.gdx -o results.db";
            command.clear();
            command.add(this.systemDir + "\\gdx2sqlite.exe");
    	    command.add("-i");
    	    command.add("output.gdx");
    	    command.add("-o");
    	    command.add("results.db");
    	    final Process p2 = builder.start();
    	    try {
                exitValueGdx = p2.waitFor();
    	    } catch (InterruptedException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
	    
	    if(exitValueGams==0 && exitValueGdx==0) {
	    	return new GamsResult(new File(this.workingDir+File.pathSeparator+"results.db"), 
	    			GamsResultCodes.OK);	
	    }
	    else if(exitValueGdx!=0) {
	    	return new GamsResult(new File(""),GamsResultCodes.GDX2SQLITE_PROBLEM);
	    }
	    else {
	    	return new GamsResult(null, 
	    			GamsResultCodes.PROBLEM);
	    }
		
	}


	@Override
	public String toString() {
		return "GamsModelSolver [workingDir=" + workingDir + ", systemDir="
				+ systemDir + "]";
	}
	
	
	
	
}
