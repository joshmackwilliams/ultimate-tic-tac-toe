package com.joshuawilliams.ultimatettt;

import java.util.Scanner;

/*
 * Configuration and initialization for the game, including console menu. 
 * We could extract this to another class, but this bit really is application-specific. 
 */

public class Main {
	
	private static Scanner scanner = new Scanner(System.in);
	private static final int N_PLAYER_TYPES = 5;

	// Select two player types and start the game
	public static void main(String[] args) {
		Player player1 = selectPlayer("X");
		Player player2 = selectPlayer("O");
		
		System.out.println("Initializing game...");
		Game game = new Game(player1, player2, new DisplaySpectator());
		System.out.println("Starting game...");
		game.play();
	}
	
	// Prompt the user to select a player type and return the selected type
	private static Player selectPlayer(String token) {
		System.out.print("Select a type of player to play " + 
				token + ", or press enter for a list of options: ");
		int type = 0;
		boolean error = true;
		while(error) {
			try {
				type = Integer.parseInt(scanner.nextLine());
				error = (type > N_PLAYER_TYPES) || (type <= 0);
			} catch (NumberFormatException e) {
				error = true;
			}
			if(error) {
				System.out.print("Please choose from the following options: \n"
						+ "1. Human-Controlled Player\n"
						+ "2. AI-Controlled Player (Easy)\n"
						+ "3. AI-Controlled Player (Normal)\n"
						+ "4. AI-Controlled Player (Hard)\n"
						+ "5. Random-Controlled Player\n\n"
						+ "Select an option: ");
			}
		}
		switch(type) {
		case 1:
			return new TerminalPlayer(token);
		case 2:
			return new AIPlayer(token, 10000, true);
		case 3:
			return new AIPlayer(token, 30000, true);
		case 4:
			return new AIPlayer(token, 100000, true);
		case 5:
			return new RandomPlayer(token);
		}
		return null; // This should never happen, but it makes the compiler happy
	}
}
