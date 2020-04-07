package com.joshuawilliams.ultimatettt;

/* 
 * The Game class represents a single game of Ultimate Tic Tac Toe and is 
 * responsible for controlling most of the other classes in this project. 
 */

// TODO Add test cases and implement
public class Game {
	
	private Board board = new Board();
	private Player player1;
	private Player player2;
	private Spectator spectator;
	private boolean isPlayer1Turn = true;
	
	public Game(Player player1, Player player2, Spectator spectator) {
		this.player1 = player1;
		this.player2 = player2;
	}
	
	public void play() {
		while(!(board.hasWinner() || board.isFull())) {
			playTurn();
		}
		
		spectator.gameOver(board);
	}
	
	public Move playTurn() {
		Player activePlayer = isPlayer1Turn ? player1 : player2;
		Move move = new Move(board, activePlayer);
		int invalidMoves = 0;
		while(true) {
			try {
				activePlayer.makeMove(move);
				break; // If the above line succeeds, no need to try again
			} catch(InvalidMoveException e) { // If the player makes an invalid move
				System.out.println("Warning: Player attempted to make an invalid move");
				System.out.println("While the game is not affected, this may indicate a bug in the player's code");
				invalidMoves++;
				if(invalidMoves > 2) {
					System.out.println("Player turn terminated due to 3 invalid moves");
					break;
				}
			} catch (MultipleMovesException e) {
				// Ignore the exception, since the move wasn't effective anyway, but print a warning
				System.out.println("Warning: Player attempted to make more than one move");
				System.out.println("While the game is not affected, this may indicate a bug in the player's code");
				break; // Since the first move took effect, just ignore the exception
			}
		}
		
		// Alert any spectator that a mvoe was made
		if(spectator != null) {
			spectator.moveMade(move);
		}
		return move;
	}
	
	public Board getBoard() {
		return board;
	}
}
