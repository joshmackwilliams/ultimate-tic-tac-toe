package com.joshuawilliams.ultimatettt;

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
		return true;
	}
	
	public boolean isFull() {
		return true;
	}
	
	public void move(Piece piece, int row, int col) {
		
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
