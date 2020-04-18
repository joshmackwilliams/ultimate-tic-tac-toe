package com.joshuawilliams.ultimatettt;

/* 
 * This represents a piece belonging to a single player. The class 
 * exists to decouple the Player objects from the board that stores 
 * them. The Game class will be able to determine who a piece belongs 
 * to by checking for equality
 */

public final class Piece { // Do not extend, especially to make it non-read-only as this will mess with SmallBoard.clone()
	
	private final Player owner;
	
	public Piece(Player owner) {
		this.owner = owner;
	}
	
	@Override public boolean equals(Object other) {
		if(!(other instanceof Piece)) return false;
		return this.owner.getIdentifier().equals(((Piece) other).owner.getIdentifier());
	}
	
	@Override public String toString() {
		return getClass().getName() + "(" + owner.getIdentifier() + ")@" + Integer.toHexString(hashCode()); 
	}
	
	public String getIdentifier() {
		return owner.getIdentifier();
	}
	
	public boolean belongsTo(Player player) {
		return owner.getIdentifier().equals(player.getIdentifier());
	}
}
