package com.joshuawilliams.ultimatettt;

/* 
 * The Spectator class is a mechanism to allow a process to observe a 
 * running game without interfering. The spectator can see the board 
 * and has a hook method that is called each time a move is made. 
 * 
 * Example uses would be to create a spectator that could log a game to 
 * a file, one that would evaluate AI performance, or one that can log a 
 * game between two computer players to a terminal. 
 */

public abstract class Spectator {
	
	// Called by Game each time a move is made. Move will allow 
	// reading state, including what move was made, but not 
	// modifying it
	public abstract void moveMade(Move move);
	public abstract void gameOver(Board board);
	
}
