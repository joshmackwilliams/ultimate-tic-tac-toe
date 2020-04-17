package com.joshuawilliams.ultimatettt;

public class ScoreAIPlayer {
	public static void main(String[] args) {
		Player ai = new AIPlayer("A");
		Player random = new RandomPlayer("R");
		int score = 0;
		for(int i = 0; i < 20; i++) {
			Game game = (i % 2 == 0) ? new Game(ai, random) : new Game(random, ai);
			game.play();
			if(game.getBoard().hasWinner()) {
				if(game.getBoard().getWinner().belongsTo(ai)) {
					score++;
					System.out.print('A');
				} else if(game.getBoard().getWinner().belongsTo(random)) {
					score--;
					System.out.print('R');
				} else {
					throw new RuntimeException("Wat");
				}
			} else {
				System.out.print('T');
			}
		}
		System.out.println("\nFinal score: " + score);
	}
}
