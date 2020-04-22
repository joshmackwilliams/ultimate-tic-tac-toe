package com.joshuawilliams.ultimatettt;

/*
 * This exception is thrown when a player attempts to make more than one move 
 * using a single Move object. This will usually be caught by the Game and 
 * the extra move will simply be ignored. 
 */

public class MultipleMovesException extends Exception {

	private static final long serialVersionUID = -6635595108031074784L;

	public MultipleMovesException() {
		super();
	}
	
	public MultipleMovesException(String message) {
		super(message);
	}
	
	public MultipleMovesException(String message, Throwable err) {
		super(message, err);
	}
	
}
