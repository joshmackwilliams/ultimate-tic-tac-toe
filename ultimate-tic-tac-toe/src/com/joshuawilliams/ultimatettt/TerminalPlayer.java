package com.joshuawilliams.ultimatettt;

import java.util.Scanner;

/* 
 * A subclass of Player that represents a player being controlled
 * by a user. 
 */

//TODO Add test cases
public class TerminalPlayer extends Player {
	
	Scanner scanner = new Scanner(System.in);
	
	public TerminalPlayer(String identifier) {
		super(identifier);
	}

	@Override public void makeMove(Move move) throws MultipleMovesException {
		System.out.print("Player ");
		System.out.print(identifier);
		System.out.print(": Enter your move ([board] [space]): ");
		int board = scanner.nextInt();
		int space = scanner.nextInt();
		try {
			move.makeMove(board, space);
		} catch(InvalidMoveException e) {
			System.out.println("Invalid move! Please try again. ");
			makeMove(move);
		}
	}
}
