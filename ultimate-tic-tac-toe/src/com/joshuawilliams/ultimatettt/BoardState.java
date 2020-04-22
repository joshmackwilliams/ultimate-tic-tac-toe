package com.joshuawilliams.ultimatettt;

// Interface satisfied by both Board and Move that allows read-only access to the Board's state
public interface BoardState {
	public Piece getPiece(int board, int space);
	public boolean hasWinner(int board);
	public Piece getWinner(int board);
	public boolean hasWinner();
	public Piece getWinner();
	public boolean isOccupied(int board, int space);
	public int[] getWinningCondition(int board);
}
