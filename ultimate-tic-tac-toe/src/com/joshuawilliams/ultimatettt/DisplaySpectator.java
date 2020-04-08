package com.joshuawilliams.ultimatettt;

/* 
 * The DisplaySpectator simply displays the game state every move. This 
 * is mostly useful when a human wants to watch a game played between AI
 * players. 
 */

public class DisplaySpectator extends Spectator {

	@Override public void moveMade(Move move) {
		System.out.print(TerminalDisplay.displayBoard(move));
	}

	@Override public void gameOver(Board board) {
		if(board.hasWinner()) {
			System.out.print("Player ");
			System.out.print(board.getWinner().getIdentifier());
			System.out.println(" wins!");
		} else {
			System.out.println("The game has ended in a tie");
		}
	}
}
