package com.joshuawilliams.ultimatettt;

public abstract class Player {
	protected String identifier;
	
	public abstract void makeMove(Move move);

	public String getIdentifier() {
		return identifier;
	}
	
	public final Piece getPiece() {
		return new Piece(this);
	}
}
