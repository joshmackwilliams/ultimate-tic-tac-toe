package com.joshuawilliams.ultimatettt;

/*
 * This class represents an ultimate tic-tac-toe board. It is 
 * 9x9 spaces, made up of 9 3x3 standard tic-tac-toe boards. 
 */

public class Board {
	// winConditions is here as well as SmallBoard to reduce dependency between the classes
	public static int[][] winConditions = {
			{0, 1, 2}, {3, 4, 5}, {6, 7, 8},
			{0, 3, 6}, {1, 4, 7}, {2, 5, 8}, 
			{0, 4, 8}, {2, 4, 6}
	};

	private int width = 3;
	private int height = 3;
	private SmallBoard[] boards;
	private Piece winner;

	public Board() {
		boards = new SmallBoard[getWidth() * getHeight()];
		for(int i = 0; i < boards.length; i++) {
			boards[i] = new SmallBoard();
		}
	}

	public int getWidth() {
		return width;
	}

	public int getHeight() {
		return height;
	}

	public boolean hasWinner() {
		return getWinner() != null;
	}

	public boolean hasWinner(int board) {
		return boards[board].hasWinner();
	}

	public Piece getWinner() {
		return winner;
	}

	public Piece getWinner(int board) {
		return boards[board].getWinner();
	}

	public Piece getPiece(int board, int space) {
		return boards[board].getPiece(space);
	}

	public boolean isOccupied(int board, int space) {
		return boards[board].isOccupied(space);
	}

	public boolean isFull(int board) {
		return boards[board].isFull();
	}

	public void move(Piece piece, int board, int space) {
		if(! isValidMove(board, space)) throw new InvalidMoveException();
		boards[board].move(piece, space);
		if(! hasWinner()) winner = calculateWinner();
	}

	public boolean isFull() {
		for(SmallBoard board : boards) {
			if(! board.isFull()) return false;
		}
		return true;
	}

	public boolean isValidMove(int board, int space) {
		if(board < 0 || board >= (getWidth() * getHeight())) return false;
		return boards[board].isValidMove(space);
	}

	private Piece calculateWinner() {
		for(int[] condition : winConditions) {
			if(! boards[condition[0]].hasWinner()) continue;
			Piece checking = boards[condition[0]].getWinner();
			boolean winner = true;
			for(int i = 1; i < condition.length; i++) {
				if((! boards[condition[i]].hasWinner()) || (! boards[condition[i]].getWinner().equals(checking))) {
					winner = false;
					break;
				}
			}
			if(winner) return checking;
		}
		return null;
	}
}