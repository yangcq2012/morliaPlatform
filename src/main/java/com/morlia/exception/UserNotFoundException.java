package com.morlia.exception;

public class UserNotFoundException extends RuntimeException {

	private static final long serialVersionUID = -8202589796877599002L;

	public UserNotFoundException() {
		super();
	}

	public UserNotFoundException(String arg0, Throwable arg1) {
		super(arg0, arg1);
	}

	public UserNotFoundException(String arg0) {
		super(arg0);
	}

	public UserNotFoundException(Throwable arg0) {
		super(arg0);
	}

	
}
