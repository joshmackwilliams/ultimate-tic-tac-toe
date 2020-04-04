package com.joshuawilliams.ultimatettt;

/*
 * This class represents a standard, 3x3 tic-tac-toe board. In this project these 
 * serve only to work with the standard Board, but they are stand-alone classes and 
 * are independently tested, so they could be used in an ordinary tic-tac-toe game. 
 * 
 * The board uses indices 0-8, where the top-left box is 0, the bottom-right is 8, 
 * and everything in between is numbered left to right and top to bottom, where row 
 * is more significant than column. 
 *  
 * To illustrate: 
 * +-+-+-+
 * |0|1|2|
 * +-+-+-+
 * |3|4|5|
 * +-+-+-+
 * |6|7|8|
 * +-+-+-+
 * 
 * It may not be truly representative of what happens in a real Tic-Tac-Toe game to 
 * have the SmallBoard class be responsible for determining what a valid move is and 
 * who wins the board, but it makes a lot more sense programmatically to handle the 
 * logic this way. 
 * 
 * The SmallBoard class is extremely similar to the Board class, but I couldn't see 
 * any meaningful way to inherit behavior without a lot of ugliness (and even then, 
 * maybe not)
 */

public class SmallBoard {
	// All combinations of spaces that can result in a win
	public static int[][] winConditions = {
		{0, 1, 2}, {3, 4, 5}, {6, 7, 8},
		{0, 3, 6}, {1, 4, 7}, {2, 5, 8}, 
		{0, 4, 8}, {2, 4, 6}
	};
	
	// These are read-only, since there's no meaningful way to change them later on
	private int height = 3;
	private int width = 3;
	
	// Space to store pieces as we receive them. See above for the coordinate system. 
	private Piece[] pieces = new Piece[height * width];
	
	// Tracks any piece of the player who won first (all pieces of the same player 
	// are logically equivalent. 
	private Piece winner = null;
	
	// Empty default constructor is the only constructor here
	public SmallBoard() {
		
	}
	
	public int getHeight() {
		return height;
	}
	
	public int getWidth() {
		return width;
	}
	
	public Piece getPiece(int space) {
		return pieces[space];
	}
	
	// True if the given space index is occupied, false otherwise
	public boolean isOccupied(int space) {
		return pieces[space] != null;
	}
	
	// Returns true if every square on this board is occupied, false otherwise
	public boolean isFull() {
		for(int i = 0; i < height * width; i++) {
			if(pieces[i] == null) {
				return false;
			}
		}
		return true;
	}
	
	// Called by Move when a player makes a move
	public void move(Piece piece, int space) {
		if(! isValidMove(space)) throw new InvalidMoveException();
		pieces[space] = piece;
		
		// Note that winner is recalculated each move, only if there is no current 
		// winner. This is because once a player wins a board, they have won it forever. 
		if(! hasWinner()) winner = calculateWinner();
	}
	
	public boolean hasWinner() {
		return getWinner() != null;
	}
	
	public Piece getWinner() {
		return winner;
	}
	
	// Returns true if the space given is available to be moved to
	public boolean isValidMove(int space) {
		// You cannot move to a space that isn't on the board, and you can't move to an 
		// occupied space. 
		return boundsCheck(space) && ! isOccupied(space);
	}
	
	// Check if the given space is actually on the board
	private boolean boundsCheck(int space) {
		return space >= 0 && space < getWidth() * getHeight();
	}
	
	// Calculate the winner and return one of their pieces. This method has not respect for 
	// who won first, and if more than one player has a winning condition, it will simply 
	// return whoever matched the earlier condition in the winConditions array. 
	private Piece calculateWinner() {
		// Check all conditions
		for(int[] condition : winConditions) {
			// If there's no piece at the first index given by the condition, move on
			Piece checking = pieces[condition[0]];
			if(checking == null) continue;
			boolean winner = true;
			for(int i = 1; i < condition.length; i++) {
				// If the piece at any of the indices specified doesn't match the first
				if((pieces[condition[i]] == null) || (! pieces[condition[i]].equals(checking))) {
					winner = false;
					break;
				}
			}
			if(winner) return checking;
		}
		return null;
	}
}
