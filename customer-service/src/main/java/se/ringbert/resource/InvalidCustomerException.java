package se.ringbert.resource;

public class InvalidCustomerException extends RuntimeException {

	private static final long serialVersionUID = -3343967643276007816L;

	public InvalidCustomerException(String message) {
		super(message);
	}

	
}
