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
	
	private int intelligence = 100000; // Total number of simulations to run each iteration
	private boolean verbose = false; // Whether to log confidence scores for each move
	
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
	
	public AIPlayer(String identifier, int intelligence, boolean verbose) {
		super(identifier);
		this.intelligence = intelligence;
		this.verbose = verbose;
	}

	@Override public void makeMove(Move move) throws MultipleMovesException, InvalidMoveException {
		// Allocate space for up to 81 moves, the maximum possible number
		int[] boards = new int[81];
		int[] spaces = new int[81];
		int n_moves = 0; // Number of valid moves actually found
		
		// Compile a list of valid moves
		if(! move.hasRequiredBoard()) {
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
		
		// If only one move is available, don't waste time simulating it
		if(n_moves == 1) {
			move.makeMove(boards[0], spaces[0]);
			return;
		} else if(n_moves == 0) {
			throw new RuntimeException("makeMove called with no valid moves");
		}
		
		// Simulate each move
		int bestScore = intelligence * (-2);
		int bestMove = -1;
		for(int i = 0; i < n_moves; i++) {
			int score = scoreMove(boards[i], spaces[i], move, intelligence/n_moves);
			if(score > bestScore) {
				bestScore = score;
				bestMove = i;
			}
		}
		
		// Logging code
		double confidence = (((double)bestScore)/(((double)intelligence)/((double)n_moves))) * 100.0;
		if(verbose) System.out.println("[" + getIdentifier() + "] Making move with a score of "
				+ bestScore + " / " + (intelligence/n_moves) + " (" + confidence + "%)");
		
		// Make the move with the highest score
		move.makeMove(boards[bestMove], spaces[bestMove]);
	}
	
	// The rules for scoring a move are simple: assume each player makes a random move each turn. 
	// Simulate the whole game, then apply:
	// Opponent wins -> score -= 1. AI wins -> score += 1. Tie -> no effect. 
	// Run a large number of such simulations, then return the total score for the given move. 
	private int scoreMove(int board, int space, Move move, int tries) throws InvalidMoveException, MultipleMovesException {
		Player me = new RandomPlayer(move.getActiveIdentifier());
		Player them = new RandomPlayer(move.getOtherIdentifier());
		int score = 0;
		for(int i = 0; i < tries; i++) {
			// Create simulation game
			Game testGame = new Game(them, me, move.getBoardClone());
			
			// Make the move we're scoring
			Move myMove = new Move(testGame.getBoard(), me, them);
			myMove.makeMove(board, space);
			testGame.setLastMove(myMove);
			
			// Simulate the game and score
			testGame.play();
			if(testGame.getBoard().hasWinner()) {
				if(testGame.getBoard().getWinner().belongsTo(me)) {
					score++;
				} else if(testGame.getBoard().getWinner().belongsTo(them)) {
					score--;
				} else {
					// If this happens something is very wrong
					throw new RuntimeException("Winning piece doesn't belong to either player");
				}
			}
		}
		return score;
	}
}
