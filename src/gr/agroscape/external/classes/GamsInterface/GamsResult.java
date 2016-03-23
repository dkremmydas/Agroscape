package gr.agroscape.external.classes.GamsInterface;

import java.io.File;

public class GamsResult {
	
	private File resultFile;
	
	private GamsResultCodes resultCode;

	public GamsResult(File resultFile, GamsResultCodes resultCode) {
		super();
		this.resultFile = resultFile;
		this.resultCode = resultCode;
	}

	public File getResultFile() {
		return resultFile;
	}

	public GamsResultCodes getResultCode() {
		return resultCode;
	}

	@Override
	public String toString() {
		return "GamsResult [resultFile=" + resultFile + ", resultCode="
				+ resultCode + "]";
	}
	
	
	
	
	

}
