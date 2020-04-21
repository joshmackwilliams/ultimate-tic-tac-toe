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
		String line = scanner.nextLine();
		if(line.equals("")) {
			displayRestrictedMoves(move);
			makeRestrictedMove(move);
			return;
		}
		try {
			move.makeMove(move.getRequiredBoard(), Integer.parseInt(line));
		} catch(InvalidMoveException e) {
			System.out.println("Invalid move! Please try again, or press enter for a list of valid moves. ");
			makeRestrictedMove(move);
		} catch(NumberFormatException e) {
			System.out.println("Please enter a valid move. You can press enter for a list. ");
			makeRestrictedMove(move);
		}
	}
	
	private void makeFreeMove(Move move) throws MultipleMovesException {
		System.out.print("Enter your move (<board> <space>): ");
		String line = scanner.nextLine();
		if(line.equals("")) {
			displayFreeMoves(move);
			makeFreeMove(move);
			return;
		}
		try {
			int board = Integer.parseInt(line.substring(0, 1));
			int space = Integer.parseInt(line.substring(2, 3));
			move.makeMove(board, space);
		} catch(InvalidMoveException e) {
			System.out.println("Invalid move! Please try again, or press enter for a list of valid moves. ");
			makeFreeMove(move);
		} catch(NumberFormatException e) {
			System.out.println("Please enter a valid move. You can press enter for a list. ");
			makeFreeMove(move);
		} catch(IndexOutOfBoundsException e) {
			System.out.println("Please enter a valid move. You can press enter for a list. ");
			makeFreeMove(move);
		}
	}
	
	private void displayRestrictedMoves(Move move) {
		System.out.print("Valid moves are: ");
		for(int i = 0; i < 9; i++) {
			if(move.isValidMove(move.getRequiredBoard(), i)) {
				System.out.print(i);
				System.out.print("; ");
			}
		}
		System.out.println();
	}
	
	private void displayFreeMoves(Move move) {
		System.out.print("Valid moves are: ");
		for(int i = 0; i < 9; i++) {
			for(int j = 0; j < 9; j++) {
				if(move.isValidMove(i, j)) {
					System.out.print(i);
					System.out.print(' ');
					System.out.print(j);
					System.out.print("; ");
				}
			}
		}
		System.out.println();
	}
}
