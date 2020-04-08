package com.joshuawilliams.ultimatettt;

public interface BoardState {
	public Piece getPiece(int board, int space);
	public boolean hasWinner(int board);
	public Piece getWinner(int board);
	public boolean hasWinner();
	public Piece getWinner();
	public boolean isOccupied(int board, int space);
}
