package com.joshuawilliams.ultimatettt;

/*
 * As a minmax search would likely be quite expensive and slow, and also require a ranking method 
 * for a game that is difficult to rank, this class uses a Monte Carlo simulation to determine the 
 * best move
 * 
 * Note that an improvement might be to use minmax to look a few steps ahead, then just use Monte Carlo 
 * for state ranking
 */

public class AIPlayer extends Player {
	
	private int intelligence = 100000;
	private boolean verbose = false;
	
	public AIPlayer(String identifier) {
		super(identifier);
	}
	
	public AIPlayer(String identifier, boolean verbose) {
		super(identifier);
		this.verbose = verbose;
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
		
		double confidence = (((double)bestScore)/(((double)intelligence)/((double)n_moves))) * 100.0;
		if(verbose) System.out.println("[" + getIdentifier() + "] Making move with a score of "
				+ bestScore + " / " + (intelligence/n_moves) + " (" + confidence + "%)");
		move.makeMove(boards[bestMove], spaces[bestMove]);
	}
	
	private int scoreMove(int board, int space, Move move, int tries) throws InvalidMoveException, MultipleMovesException {
		Player me = new RandomPlayer(move.getActiveIdentifier());
		Player them = new RandomPlayer(move.getOtherIdentifier());
		int score = 0;
		for(int i = 0; i < tries; i++) {
			Game testGame = new Game(them, me, move.getBoardClone());
			Move myMove = new Move(testGame.getBoard(), me, them);
			myMove.makeMove(board, space);
			testGame.setLastMove(myMove);
			testGame.play();
			if(testGame.getBoard().hasWinner()) {
				if(testGame.getBoard().getWinner().belongsTo(me)) {
					score++;
				} else if(testGame.getBoard().getWinner().belongsTo(them)) {
					score--;
				} else {
					throw new RuntimeException("ERROR");
				}
			}
		}
		return score;
	}
}
