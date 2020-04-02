package com.joshuawilliams.ultimatettt;

/*
 * This class represents an ultimate tic-tac-toe board. It is 
 * 9x9 spaces, made up of 9 3x3 standard tic-tac-toe boards. 
 */

//TODO Add test cases and implement
public class Board {
	
	private int width = 3;
	private int height = 3;
	private SmallBoard[] boards;
	
	public Board() {
		
	}

	public int getWidth() {
		return width;
	}

	public int getHeight() {
		return height;
	}
	
	public Piece getWinner(int board) {
		return null;
	}
	
	public Piece getPiece(int board, int space) {
		return null;
	}
	
	public boolean isOccupied(int board, int space) {
		return true;
	}
	
	public boolean isFull(int board) {
		return true;
	}
	
	public boolean isEmpty() {
		return true;
	}
	
	public boolean boundsCheck(int board, int space) {
		return true;
	}
	
	public void move(Piece piece, int board, int space) {
		
	}
	
	public boolean hasWinner() {
		return getWinner() != null;
	}
	
	public Piece getWinner() {
		return null;
	}
}