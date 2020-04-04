package com.joshuawilliams.ultimatettt;

/* 
 * This represents a piece belonging to a single player. The class 
 * exists to decouple the Player objects from the board that stores 
 * them. The Game class will be able to determine who a piece belongs 
 * to by checking for equality
 */

public class Piece {
	
	private Player owner;
	
	public Piece(Player owner) {
		this.owner = owner;
	}
	
	@Override public boolean equals(Object other) {
		if(!(other instanceof Piece)) return false;
		return this.owner.equals(((Piece) other).owner);
	}
	
	@Override public String toString() {
		return getClass().getName() + "(" + owner.getIdentifier() + ")@" + Integer.toHexString(hashCode()); 
	}
	
	public String getIdentifier() {
		return owner.getIdentifier();
	}
	
	public boolean belongsTo(Player player) {
		return owner.equals(player);
	}
}
