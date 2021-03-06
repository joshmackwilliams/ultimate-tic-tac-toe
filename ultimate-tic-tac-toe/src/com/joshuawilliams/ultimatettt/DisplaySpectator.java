package com.joshuawilliams.ultimatettt;

/* 
 * The DisplaySpectator simply displays the game state every move. This 
 * is mostly useful when a human wants to watch a game played between AI
 * players. 
 * 
 * Also displays moves each time they are made
 */

public class DisplaySpectator extends Spectator {
	
	@Override public void gameStarted(BoardState board) {
		System.out.print(TerminalDisplay.displayBoard(board));
	}

	@Override public void moveMade(Move move) {
		System.out.println(String.format("Player %s moves to %d, %d", 
				move.getActiveIdentifier(), move.getBoard(), move.getSpace()));
		System.out.print(TerminalDisplay.displayBoard(move));
	}

	@Override public void gameOver(BoardState board) {
		if(board.hasWinner()) {
			System.out.print("Player ");
			System.out.print(board.getWinner().getIdentifier());
			System.out.println(" wins!");
		} else {
			System.out.println("The game has ended in a tie");
		}
	}
}
