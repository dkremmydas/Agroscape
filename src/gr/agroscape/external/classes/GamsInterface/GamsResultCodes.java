package gr.agroscape.external.classes.GamsInterface;

public enum GamsResultCodes {
	OK ("Everything was ok",0),
	PROBLEM ("There was a problem", 1),
	GAMS_SOLVE_PROBLEM ("Gams exited <> 0", 2),
	GDX2SQLITE_PROBLEM ("Gdx2Sqlite exited <> 0", 3);
	
	
	private final String message;   
    private final int value; 
    private final String extMessage;
    
    GamsResultCodes(String message, int value) {
        this(message,value,"");
    }
    
    GamsResultCodes(String message, int value, String extMessage) {
        this.message = message;
        this.value = value;
        this.extMessage = extMessage;
    }
    
    
    public String message() { return message; }
    public int value() { return value; }
    public String extMessage() { return extMessage; }
    
    
    
}