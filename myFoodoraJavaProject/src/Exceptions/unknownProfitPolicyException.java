package Exceptions;

public class unknownProfitPolicyException extends Exception {
    public unknownProfitPolicyException() {
        super();
    }

    public unknownProfitPolicyException(String message) {
        super(message);
    }

    public unknownProfitPolicyException(String message, Throwable cause) {
        super(message, cause);
    }

    public unknownProfitPolicyException(Throwable cause) {
        super(cause);
    }
}
