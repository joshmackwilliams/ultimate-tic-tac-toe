package com.joshuawilliams.ultimatettt;

import java.util.Scanner;

/* 
 * A subclass of Player that represents a player being controlled
 * by a user. 
 */

public class TerminalPlayer extends Player {
	
	Scanner scanner = new Scanner(System.in);
	
	public TerminalPlayer(String identifier) {
		super(identifier);
	}

	@Override public void makeMove(Move move) throws MultipleMovesException {
		System.out.print("Player ");
		System.out.print(identifier);
		
		if(move.hasRequiredBoard()) {
			System.out.print(" must move to a space on board ");
			System.out.println(move.getRequiredBoard());
			makeRestrictedMove(move);
		} else {
			System.out.println(" is free to move anywhere on the board");
			makeFreeMove(move);
		}
	}
	
	private void makeRestrictedMove(Move move) throws MultipleMovesException {
		System.out.print("Enter the number of the space you want to move to: ");
		try {
			move.makeMove(move.getRequiredBoard(), scanner.nextInt());
		} catch(InvalidMoveException e) {
			System.out.println("Invalid move! Please try again. ");
			makeRestrictedMove(move);
		}
	}
	
	private void makeFreeMove(Move move) throws MultipleMovesException {
		System.out.print("Enter your move ([board] [space]): ");
		int board = scanner.nextInt();
		int space = scanner.nextInt();
		try {
			move.makeMove(board, space);
		} catch(InvalidMoveException e) {
			System.out.println("Invalid move! Please try again. ");
			makeFreeMove(move);
		}
	}
}
