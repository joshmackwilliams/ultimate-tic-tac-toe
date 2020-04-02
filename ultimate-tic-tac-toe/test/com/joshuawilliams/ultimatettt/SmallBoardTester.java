package com.joshuawilliams.ultimatettt;

public class SmallBoardTester {
	public static void test(SmallBoard board, Player player, int[] rows, int[] cols) {
		for(int i = 0; i < rows.length; i++) {
			board.move(player.getPiece(), rows[i], cols[i]);
		}
	}
	
	public static void test(SmallBoard board, Player[] players, int[] rows, int[] cols) {
		for(int i = 0; i < players.length; i++) {
			board.move(players[i].getPiece(), rows[i], cols[i]);
		}
	}
}
