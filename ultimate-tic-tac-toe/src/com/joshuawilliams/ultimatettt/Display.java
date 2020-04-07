package com.joshuawilliams.ultimatettt;

/*
 * The Display class is responsible for displaying the game state in some manner. 
 * Example subclasses might include TerminalDisplay, GraphicalDisplay, 3DDisplay, etc.
 */

public abstract class Display {
	public abstract void getUserMove(Move move); // Get a move from a user 
	public abstract void displayMove(Move move); // Display a completed move
	public abstract void displayBoard(BoardState board); // Display a board state
}
