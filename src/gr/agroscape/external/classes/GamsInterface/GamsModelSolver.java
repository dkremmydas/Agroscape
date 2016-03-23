package gr.agroscape.external.classes.GamsInterface;

import java.io.File;
import java.io.IOException;
import java.nio.file.FileVisitResult;
import java.nio.file.Path;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.StringUtils;

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
	
	private File workingDir = null;	
	private Boolean usedWorkingDir = Boolean.FALSE;
	
	private String systemDir = "C:\\Program Files (x86)\\gams_24_0";
	
	private File modelFile = null;
	
	private ArrayList<File> dataFile = new ArrayList<>();
	
	
	public GamsModelSolver() {	
		this.getNewWorkingDir();
	}
	

	public GamsResult solve(String modelFile) throws IOException {
		this.modelFile = new File(modelFile);
		return this.doSolve();
	}

	public GamsResult solve(String modelFile, String dataFile) throws IOException {
		this.modelFile = new File(modelFile);
		this.dataFile.add(new File(dataFile));		
		return this.doSolve();
	}
	
	public GamsResult solve(String modelFile, ArrayList<String> dataFiles) throws IOException {
		this.modelFile = new File(modelFile);
		for (String string : dataFiles) {
			this.dataFile.add(new File(string));		
		}
		return this.doSolve();
	}
	
	public String getSystemDir() {
		return systemDir;
	}


	public void setSystemDir(String systemDir) {
		this.systemDir = systemDir;
	}


	private GamsResult doSolve() throws IOException {
		if (usedWorkingDir) this.getNewWorkingDir();
		usedWorkingDir=Boolean.TRUE;
		
		//copy data file to working dir
		for (File file : dataFile) {
			if(file.exists()) {
				FileUtils.copyFile(file, new File(this.workingDir+File.separator+file.getName()));
			}
			else {
				throw new IOException("Gams Data File does not exist ! ["+file.getAbsolutePath()+"]");
			}
		}
		

		//copy data file to working dir
		if(this.modelFile.exists()) {
			FileUtils.copyFile(this.modelFile, new File(this.workingDir+File.separator+this.modelFile.getName()));
		}
		else {
			throw new IOException("Gams Model File does not exist ! ["+this.modelFile.getAbsolutePath()+"]");
		}
		
		
		
		//create command
		List<String> command = new ArrayList<String>();
	    command.add(this.systemDir + "\\gams.exe");
	    command.add(this.modelFile.getName());
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
    	    command.add("\"" + this.workingDir + File.separator + "output.gdx" + "\"");
    	    command.add("-o");
    	    command.add("\"" + this.workingDir + File.separator+ "results.db" + "\"");
    	    System.out.println(StringUtils.join(command.toArray()," "));
    	    //Arrays.deepToString();
    	    
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
	    	return new GamsResult(new File(this.workingDir+File.separator+"results.db"), 
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
		usedWorkingDir=Boolean.FALSE;
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


	@Override
	public String toString() {
		return "GamsModelSolver [workingDir=" + workingDir + ", systemDir="
				+ systemDir + "]";
	}
	
	
	
	
}
