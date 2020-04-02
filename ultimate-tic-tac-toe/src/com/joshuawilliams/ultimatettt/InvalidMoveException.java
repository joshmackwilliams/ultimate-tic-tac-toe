package com.joshuawilliams.ultimatettt;

public class InvalidMoveException extends RuntimeException {

	private static final long serialVersionUID = 8253815940987681482L;

	public InvalidMoveException() {
		super();
	}
	
	public InvalidMoveException(String message) {
		super(message);
	}
	
	public InvalidMoveException(String message, Throwable err) {
		super(message, err);
	}
}
