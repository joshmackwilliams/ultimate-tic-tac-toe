package com.joshuawilliams.ultimatettt;

public class BoardTester {
	
	public static void test(Board board, Player player, int[] boards, int[] spaces) {
		for(int i = 0; i < spaces.length; i++) {
			board.move(player.getPiece(), boards[i], spaces[i]);
		}
	}

	public static void test(Board board, Player[] players, int[] boards, int[] spaces) {
		for(int i = 0; i < spaces.length; i++) {
			board.move(players[i].getPiece(), boards[i], spaces[i]);
		}
	}
	
	public static void test(Board board, Player player, int boardIndex, int[] spaces) {
		for(int i = 0; i < spaces.length; i++) {
			board.move(player.getPiece(), boardIndex, spaces[i]);
		}
	}

	public static void test(Board board, Player[] players, int boardIndex, int[] spaces) {
		for(int i = 0; i < spaces.length; i++) {
			board.move(players[i].getPiece(), boardIndex, spaces[i]);
		}
	}
}
