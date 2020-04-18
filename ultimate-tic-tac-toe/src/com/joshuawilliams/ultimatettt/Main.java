package com.joshuawilliams.ultimatettt;

import java.util.Scanner;

/*
 * Configuration and initialization for the game
 * We could extract this to another class, but this bit really is application-specific
 */

public class Main {
	
	private static Scanner scanner = new Scanner(System.in);
	private static final int nPlayerTypes = 3;

	public static void main(String[] args) {
		Player player1 = selectPlayer("X");
		Player player2 = selectPlayer("O");
		
		System.out.println("Initializing game...");
		Game game = new Game(player1, player2, new DisplaySpectator());
		System.out.println("Starting game...");
		game.play();
	}
	
	private static Player selectPlayer(String token) {
		System.out.print("Select a type of player to play " + 
				token + ", or press enter for a list of options: ");
		int type = 0;
		boolean error = true;
		while(error) {
			try {
				type = Integer.parseInt(scanner.nextLine());
				error = (type > nPlayerTypes) || (type <= 0);
			} catch (NumberFormatException e) {
				error = true;
			}
			if(error) {
				System.out.print("Please choose from the following options: \n"
						+ "1. Human-Controlled Player\n"
						+ "2. Random-Controlled Player\n"
						+ "3. AI-Controlled Player\n\n"
						+ "Select an option: ");
			}
		}
		switch(type) {
		case 1:
			return new TerminalPlayer(token);
		case 2:
			return new RandomPlayer(token);
		case 3:
			return new AIPlayer(token, true);
		}
		return null; // This should never happen, but it makes the compiler happy
	}
}
