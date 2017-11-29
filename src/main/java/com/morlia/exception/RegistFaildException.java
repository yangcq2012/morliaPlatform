package com.morlia.exception;

public class RegistFaildException extends RuntimeException {
	
	private static final long serialVersionUID = -538177507154851562L;

	public RegistFaildException() {
		super();
		
	}

	public RegistFaildException(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	
	}

	public RegistFaildException(String message, Throwable cause) {
		super(message, cause);
	
	}

	public RegistFaildException(String message) {
		super(message);
		
	}

	public RegistFaildException(Throwable cause) {
		super(cause);
	
	}

}
