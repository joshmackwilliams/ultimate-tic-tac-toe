package com.joshuawilliams.ultimatettt;

/*
 * This class represents a standard, 3x3 tic-tac-toe board. In this project these 
 * serve only to work with the standard Board, but they are stand-alone classes and 
 * are independently tested, so they could be used in an ordinary tic-tac-toe game. 
 */

public class SmallBoard {
	public static int[][] winConditions = {
		{0, 1, 2}, {3, 4, 5}, {6, 7, 8},
		{0, 3, 6}, {1, 4, 7}, {2, 5, 8}, 
		{0, 4, 8}, {2, 4, 6}
	};
	
	private int height = 3;
	private int width = 3;
	private Piece[] pieces = new Piece[height * width];
	private Piece winner = null;
	
	public SmallBoard() {
		
	}
	
	public int getHeight() {
		return height;
	}
	
	public int getWidth() {
		return width;
	}
	
	public Piece getPiece(int space) throws IndexOutOfBoundsException {
		if(! boundsCheck(space)) { 
			throw new IndexOutOfBoundsException("Invalid index " + space + " passed to SmallBoard"); 
		}
		return pieces[space];
	}
	
	public boolean isOccupied(int space) {
		return pieces[space] != null;
	}
	
	public boolean isFull() {
		for(int i = 0; i < height * width; i++) {
			if(pieces[i] == null) {
				return false;
			}
		}
		return true;
	}
	
	public void move(Piece piece, int space) {
		if(! isValidMove(space)) throw new InvalidMoveException();
		pieces[space] = piece;
		if(! hasWinner()) winner = calculateWinner();
	}
	
	public boolean hasWinner() {
		return getWinner() != null;
	}
	
	public Piece getWinner() {
		return winner;
	}
	
	public boolean isValidMove(int space) {
		return boundsCheck(space) && ! isOccupied(space);
	}
	
	// This is protected instead of private because it is used by the Board class
	protected boolean boundsCheck(int space) {
		return space >= 0 && space < getWidth() * getHeight();
	}
	
	private Piece calculateWinner() {
		for(int[] condition : winConditions) {
			Piece checking = pieces[condition[0]];
			if(checking == null) continue;
			boolean winner = true;
			for(int i = 1; i < condition.length; i++) {
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
