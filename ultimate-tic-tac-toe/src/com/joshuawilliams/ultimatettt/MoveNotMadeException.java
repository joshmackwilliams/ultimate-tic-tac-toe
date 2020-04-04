package com.joshuawilliams.ultimatettt;

public class MoveNotMadeException extends RuntimeException {

	private static final long serialVersionUID = -9214970902145461777L;

	public MoveNotMadeException() {
		super();
	}
	
	public MoveNotMadeException(String message) {
		super(message);
	}
	
	public MoveNotMadeException(String message, Throwable err) {
		super(message, err);
	}
	
}
