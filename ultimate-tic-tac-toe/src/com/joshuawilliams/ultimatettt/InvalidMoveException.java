package com.joshuawilliams.ultimatettt;

/* 
 * This exception is thrown when a player makes an invalid move. This could 
 * be handled differently by various player classes. An AIPlayer might have 
 * a fallback algorithm to use if its original move fails, while a HumanPlayer 
 * could simply require the user to make another choice. 
 * 
 * Alternatively, this can be caught by the Game class and used to notify the 
 * user that something is wrong with the Player code
 */

public class InvalidMoveException extends Exception {

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
