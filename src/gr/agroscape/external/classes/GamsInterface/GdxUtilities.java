package gr.agroscape.external.classes.GamsInterface;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;

import com.google.common.io.Files;

public class GdxUtilities {
	
	private static String csv2gdxExe = "C:\\Program Files (x86)\\gams_24_0\\csv2gdx.exe";
	private static File workingDir = Files.createTempDir();
	
	/**
	 * The CSV file should have a header and be TAB-delimited
	 * csv2gdx utility doc: https://www.gams.com/help/index.jsp?topic=%2Fgams.doc%2Ftools%2Fcsv2gdx%2Findex.html
	 * @param csvFile
	 * @param id
	 * @param Index
	 * @param Values
	 * @return
	 * @throws IOException
	 * @throws Exception
	 * @return
	 * @throws IOException 
	 * @throws InterruptedException 
	 */
	public static File csv2gdx(File csvFile, String id, String Index, String Values) throws IOException, Exception {
		File csvF = new File(workingDir+File.separator+csvFile.getName());
		FileUtils.copyFile(csvFile, csvF);
		
		List<String> command = new ArrayList<String>();
	    command.add(csv2gdxExe);
	    command.add(csvF.getAbsolutePath());
	    command.add("id="+id);
	    command.add("Index="+Index);
	    command.add("Values="+Values);
	    command.add("UseHeader=Y");
	    command.add("FieldSep=Tab");

	    //create processbuilder
	    ProcessBuilder builder = new ProcessBuilder(command);
	    builder.directory(workingDir);
	    try {
	    	final Process p = builder.start();
			int	exitValue = p.waitFor();
			File retF = new File(workingDir+File.separator+FilenameUtils.getBaseName(csvFile.getName())+".gdx");
			System.out.println(retF.getAbsolutePath());
			if(retF.exists()) return retF;
			else throw new Exception("Could not make the conversion");
		} catch (InterruptedException e) {
			e.printStackTrace();
		} finally {
			 FileUtils.deleteQuietly(csvF);		
		}
	    
	    throw new Exception("Could not make the conversion");
		
	}
	

}
