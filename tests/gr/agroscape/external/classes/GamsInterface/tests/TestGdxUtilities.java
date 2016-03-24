package gr.agroscape.external.classes.GamsInterface.tests;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.fail;
import gr.agroscape.external.classes.GamsInterface.GdxUtilities;

import java.io.File;
import java.io.IOException;

import org.junit.Before;
import org.junit.Test;

public class TestGdxUtilities {

	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void testCsv2gdx() throws IOException, Exception {
		//File csvFile, String id, String Index, String Values
		String csvFile="C:\\Users\\jkr\\Dropbox\\CurrentProjects\\Phd Proposal\\03. Work on progress\\Lombardy Biogas ABM\\productionModel\\testCsvConverter.txt";
		File result = GdxUtilities.csv2gdx(new File(csvFile),"rend","(1,2)","(3..23)");
		System.out.println(result.getAbsolutePath());
		assertNotNull(result);
	}

}
