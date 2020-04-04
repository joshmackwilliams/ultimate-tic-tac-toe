package com.joshuawilliams.ultimatettt;

/* 
 * This class represents a move available to a player and serves to decouple 
 * move-making logic from the inner workings of the board as well as to prevent 
 * players from making multiple/invalid moves. 
 */

// TODO Add test cases
public class Move {
	
	private Player activePlayer;
	private Board board;
	private Move lastMove;
	
	// These will be set when a move is made
	private boolean isMade = false;
	private int moveBoard;
	private int moveSpace;
	
	public Move(Board board, Player activePlayer) {
		this.board = board;
		this.activePlayer = activePlayer;
	}
	
	public Move(Board board, Player activePlayer, Move lastMove) {
		this(board, activePlayer);
		this.lastMove = lastMove;
	}
	
	// Make a move, but not if this object has already been used to make a move
	public void makeMove(int board, int space) throws MultipleMovesException, InvalidMoveException {
		// Don't let the player make multiple moves
		if(isMade()) throw new MultipleMovesException();
		
		// Don't let the player move anywhere other than the required board
		if(hasRequiredBoard() && board != getRequiredBoard()) throw new InvalidMoveException(); 
		
		this.board.move(activePlayer.getPiece(), board, space);
		moveBoard = board;
		moveSpace = space;
		isMade = true;
	}
	
	// Whether or not this move requires a particular board
	public boolean hasRequiredBoard() {
		return (lastMove != null) && (! board.isFull(getRequiredBoard()));
	}
	
	// Get the board required for this move
	public int getRequiredBoard() {
		if(lastMove == null) return -1;
		return lastMove.getSpace();
	}
	
	// Whether this object has already been used to make a move
	public boolean isMade() {
		return isMade;
	}
	
	// Return index of the board that this move was made on
	public int getBoard() {
		if(! isMade()) throw new MoveNotMadeException();
		return moveBoard;
	}
	
	// Return the index of the space that this move was made on
	public int getSpace() {
		if(! isMade()) throw new MoveNotMadeException();
		return moveSpace;
	}
	
	// The rest of the methods in this class are simply passthroughs to the board
	public boolean isValidMove(int board, int space) {
		if(isMade()) return false;
		return this.board.isValidMove(board, space);
	}
	
	public boolean hasWinner(int board) {
		return this.board.hasWinner(board);
	}
	
	public Piece getWinner(int board) {
		return this.board.getWinner(board);
	}
	
	public Piece getPiece(int board, int space) {
		return this.board.getPiece(board, space);
	}
	
	public boolean isFull(int board) {
		return this.board.isFull(board);
	}
	
	public boolean isOccupied(int board, int space) {
		return this.board.isOccupied(board, space);
	}
}
