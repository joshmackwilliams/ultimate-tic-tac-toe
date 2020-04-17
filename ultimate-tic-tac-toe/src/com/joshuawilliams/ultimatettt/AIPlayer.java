package com.joshuawilliams.ultimatettt;

/*
 * As a minmax search would likely be quite expensive and slow, and also require a ranking method 
 * for a game that is difficult to rank, this class uses a Monte Carlo simulation to determine the 
 * best move
 */

public class AIPlayer extends Player {
	
	private int intelligence = 500000;
	
	public AIPlayer(String identifier) {
		super(identifier);
	}
	
	public AIPlayer(String identifier, int intelligence) {
		super(identifier);
		this.intelligence = intelligence;
	}

	@Override public void makeMove(Move move) throws MultipleMovesException, InvalidMoveException {
		int[] boards = new int[81];
		int[] spaces = new int[81];
		int n_moves = 0;
		
		if(! move.hasRequiredBoard()) {
			// Compile a list of valid moves
			for(int board = 0; board < 9; board++) {
				for(int space = 0; space < 9; space++) {
					if(move.isValidMove(board, space)) {
						boards[n_moves] = board;
						spaces[n_moves] = space;
						n_moves++;
					}
				}
			}
		} else {
			int board = move.getRequiredBoard();
			for(int space = 0; space < 9; space++) {
				if(move.isValidMove(board, space)) {
					boards[n_moves] = board;
					spaces[n_moves] = space;
					n_moves++;
				}
			}
		}
		
		if(n_moves == 1) {
			move.makeMove(boards[0], spaces[0]);
			return;
		} else if(n_moves == 0) {
			throw new RuntimeException("makeMove called with no valid moves");
		}
		
		int bestScore = intelligence * (-2);
		int bestMove = -1;
		for(int i = 0; i < n_moves; i++) {
			int score = scoreMove(boards[i], spaces[i], move, intelligence/n_moves);
			if(score > bestScore) {
				bestScore = score;
				bestMove = i;
			}
		}
		
		move.makeMove(boards[bestMove], spaces[bestMove]);
	}
	
	private int scoreMove(int board, int space, Move move, int tries) {
		Player me = new RandomPlayer(move.getActiveIdentifier());
		Player them = new RandomPlayer(move.getOtherIdentifier());
		int score = 0;
		for(int i = 0; i < tries; i++) {
			Game testGame = new Game(them, me, move.getBoardClone());
			testGame.setLastMove(move.getLastMove());
			testGame.play();
			if(testGame.getBoard().hasWinner()) {
				if(testGame.getBoard().getWinner().belongsTo(me)) {
					score++;
				} else {
					score--;
				}
			}
		}
		return score;
	}
}
