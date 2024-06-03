package user;

public class NotFoundException extends Exception{
	
	    public NotFoundException() {
	        super();
	    }

	    public NotFoundException(String message) {
	        super(message);
	    }

	    public NotFoundException(String message, Throwable cause) {
	        super(message, cause);
	    }

	    public NotFoundException(Throwable cause) {
	        super(cause);
	    }
	    
}

