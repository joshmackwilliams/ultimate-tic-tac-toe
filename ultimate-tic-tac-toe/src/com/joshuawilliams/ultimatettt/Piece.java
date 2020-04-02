package com.joshuawilliams.ultimatettt;

public class Piece {
	private Player owner;
	
	public Piece(Player owner) {
		this.owner = owner;
	}
	
	public boolean equals(Piece other) {
		return this.owner.equals(other.owner);
	}
	
	public String getIdentifier() {
		return owner.getIdentifier();
	}
}
