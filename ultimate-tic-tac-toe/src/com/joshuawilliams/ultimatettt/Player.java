package com.joshuawilliams.ultimatettt;

/*
 * This abstract class represents a player of any type. It can 
 * be extended by classes like HumanPlayer, AIPlayer, etc. 
 * 
 * The only method that should really be overridden here is the 
 * makeMove() method. 
 */

public abstract class Player {
	protected String identifier;
	
	public abstract void makeMove(Move move);

	public final String getIdentifier() {
		return identifier;
	}
	
	public final Piece getPiece() {
		return new Piece(this);
	}
	
	@Override public String toString() {
		return getClass().getName() + "(" + getIdentifier() + ")@" + Integer.toHexString(hashCode()); 
	}
}
