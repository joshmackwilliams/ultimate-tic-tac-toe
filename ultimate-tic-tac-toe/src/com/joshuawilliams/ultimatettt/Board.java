package com.joshuawilliams.ultimatettt;

/*
 * This class represents an ultimate tic-tac-toe board. It is 
 * 9x9 spaces, made up of 9 3x3 standard tic-tac-toe boards. 
 */

//TODO Add test cases and implement
public class Board {
	
	private int width = 3;
	private int height = 3;
	private SmallBoard[][] boards;
	
	public Board() {
		
	}

	public int getWidth() {
		return width;
	}

	public int getHeight() {
		return height;
	}
	
	public Piece getWinner(int boardRow, int boardCol) {
		return null;
	}
	
	public Piece getPiece(int boardRow, int boardCol, int row, int col) {
		return null;
	}
	
	public boolean isOccupied(int boardRow, int boardCol, int row, int col) {
		return true;
	}
	
	public boolean isFull(int boardRow, int boardCol) {
		return true;
	}
	
	public boolean isEmpty() {
		return true;
	}
	
	public boolean boundsCheck(int boardRow, int boardCol, int row, int col) {
		return true;
	}
	
	public void move(Piece piece, int boardRow, int boardCol, int row, int col) {
		
	}
	
	public boolean hasWinner() {
		return getWinner() != null;
	}
	
	public Piece getWinner() {
		return null;
	}
}