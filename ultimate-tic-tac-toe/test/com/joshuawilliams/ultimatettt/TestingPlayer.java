package com.joshuawilliams.ultimatettt;

/*
 * This is a dummy extension of the Player class to be used for testing 
 * only. It has no move-making logic and therefore cannot be used in an 
 * actual game. Basically this is only used when a reference to a Player 
 * is needed but no Player subclass is being tested. See TestSmallBoard.java 
 * for an example use. 
 * 
 * In the future there may be a way to queue moves to be made by this class as 
 * a way to test particular functions. 
 */

public class TestingPlayer extends Player {
	
	public TestingPlayer(String identifier) {
		this.identifier = identifier;
	}

	@Override public void makeMove(Move move) {
		// Nothing here. Since this is just a testing player, we won't be making any moves. 
	}
}
