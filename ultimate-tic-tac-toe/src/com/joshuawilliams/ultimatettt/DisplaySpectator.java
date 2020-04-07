package com.joshuawilliams.ultimatettt;

/* 
 * The DisplaySpectator simply displays the game state every move. This 
 * is mostly useful when a human wants to watch a game played between AI
 * players. 
 */

//TODO Add test cases and implement
public class DisplaySpectator extends Spectator {
	
	private Display display;
	
	public DisplaySpectator(Display display) {
		this.display = display;
	}

	@Override public void moveMade(Move move) {
		display.displayMove(move);
	}

	@Override public void gameOver(Board board) {
		
	}
}
