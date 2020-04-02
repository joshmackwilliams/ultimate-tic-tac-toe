package com.joshuawilliams.ultimatettt;

/*
 * This class represents a standard, 3x3 tic-tac-toe board. In this project these 
 * serve only to work with the standard Board, but they are stand-alone classes and 
 * are independently tested, so they could be used in an ordinary tic-tac-toe game. 
 */

public class SmallBoard {
	private int height = 3;
	private int width = 3;
	private Piece[][] pieces = new Piece[height][width];
	
	public SmallBoard() {
		
	}
	
	public int getHeight() {
		return height;
	}
	
	public int getWidth() {
		return width;
	}
	
	public Piece getPiece(int row, int col) throws IndexOutOfBoundsException {
		if(! boundsCheck(row, col)) { 
			throw new IndexOutOfBoundsException("Invalid index " + row + ", " + col + " passed to SmallBoard"); 
		}
		return pieces[row][col];
	}
	
	public boolean isOccupied(int row, int col) {
		return pieces[row][col] != null;
	}
	
	public boolean isFull() {
		for(int i = 0; i < height; i++) {
			for(int j = 0; j < width; j++) {
				if(pieces[i][j] == null) {
					return false;
				}
			}
		}
		return true;
	}
	
	public void move(Piece piece, int row, int col) {
		pieces[row][col] = piece;
	}
	
	public boolean hasWinner() {
		return getWinner() != null;
	}
	
	public Piece getWinner() {
		return null;
	}
	
	public boolean isValidMove(int row, int col) {
		return boundsCheck(row, col) && ! isOccupied(row, col);
	}
	
	private boolean boundsCheck(int row, int col) {
		return (row >= 0) && (row < getHeight()) && (col >= 0) && (col < getWidth());
	}
}
