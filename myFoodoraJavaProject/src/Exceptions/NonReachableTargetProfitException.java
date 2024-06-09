package Exceptions;

public class NonReachableTargetProfitException extends Exception {
	
    public NonReachableTargetProfitException() {
        super();
    }

    public NonReachableTargetProfitException(String message) {
        super(message);
    }

    public NonReachableTargetProfitException(String message, Throwable cause) {
        super(message, cause);
    }

    public NonReachableTargetProfitException(Throwable cause) {
        super(cause);
    }
}
