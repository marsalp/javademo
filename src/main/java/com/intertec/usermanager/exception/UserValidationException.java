package com.intertec.usermanager.exception;

public class UserValidationException extends Exception {
	
	private static final long serialVersionUID = -8903508449686363626L;

	public UserValidationException(Throwable cause) {
        super(cause);
    }

    public UserValidationException(String message, Throwable cause) {
        super(message, cause);
    }

    public UserValidationException(String message) {
        super(message);
    }

}
