package com.joshuawilliams.ultimatettt;

/*
 * Empty for now. There will only be a few lines of configuration
 * an initialization in here once the project is finished. 
 */

public class Main {

	public static void main(String[] args) {
		Player player1 = new TerminalPlayer("X");
		Player player2 = new TerminalPlayer("O");
		Game game = new Game(player1, player2, new DisplaySpectator());
		game.play();
	}

}
