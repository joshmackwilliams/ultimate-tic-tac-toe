package com.joshuawilliams.ultimatettt;

public class Move {
	public Move(Game game, Player activePlayer) {
		
	}
	
	public void makeMove(int boardRow, int boardCol, int row, int col) {
		
	}
	
	public boolean hasRequiredBoard() {
		return true;
	}
	
	public int getRequiredBoardRow() {
		return 0;
	}
	
	public int getRequiredBoardCol() {
		return 0;
	}
	
	public Piece getWinner(int boardRow, int boardCol) {
		return null;
	}
	
	public Piece getPiece(int boardRow, int boardCol, int row, int col) {
		return null;
	}
	
	public boolean isFull(int boardRow, int boardCol) {
		return true;
	}
	
	public boolean isOccupied(int boardRow, int boardCol, int row, int col) {
		return true;
	}
}
