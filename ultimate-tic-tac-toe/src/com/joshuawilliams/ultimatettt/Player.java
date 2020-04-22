package com.joshuawilliams.ultimatettt;

/*
 * This abstract class represents a player of any type. It can 
 * be extended by classes like HumanPlayer, AIPlayer, etc. 
 * 
 * The only method that should really be overridden here is the 
 * makeMove() method. 
 */

public abstract class Player {
	protected String identifier; // A String, such as "X" or "O", that identifies this player
	
	public abstract void makeMove(Move move) throws InvalidMoveException, MultipleMovesException;
	
	public Player(String identifier) {
		this.identifier = identifier;
	}

	public final String getIdentifier() {
		return identifier;
	}
	
	// Get a piece belonging to this player
	public final Piece getPiece() {
		return new Piece(this);
	}
	
	// Show the identifier when converting to string to make it easier to differentiate between players in debug
	@Override public final String toString() {
		return getClass().getName() + "(" + getIdentifier() + ")@" + Integer.toHexString(hashCode()); 
	}
}
