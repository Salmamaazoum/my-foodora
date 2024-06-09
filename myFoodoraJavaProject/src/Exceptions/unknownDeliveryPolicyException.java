package Exceptions;

public class unknownDeliveryPolicyException extends Exception {
	
    public unknownDeliveryPolicyException() {
        super();
    }

    public unknownDeliveryPolicyException(String message) {
        super(message);
    }

    public unknownDeliveryPolicyException(String message, Throwable cause) {
        super(message, cause);
    }

    public unknownDeliveryPolicyException(Throwable cause) {
        super(cause);
    }
}
