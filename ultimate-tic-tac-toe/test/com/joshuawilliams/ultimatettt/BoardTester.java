package com.joshuawilliams.ultimatettt;

/*
 * This class is designed to make testing the Board class easier by allowing lots of moves to 
 * be made by defining a few arrays and calling a single method
 */

public class BoardTester {
	
	public static void test(Board board, Player player, int[] boards, int[] spaces) throws InvalidMoveException, MultipleMovesException {
		for(int i = 0; i < spaces.length; i++) {
			board.move(player.getPiece(), boards[i], spaces[i]);
		}
	}

	public static void test(Board board, Player[] players, int[] boards, int[] spaces) throws InvalidMoveException, MultipleMovesException {
		for(int i = 0; i < spaces.length; i++) {
			board.move(players[i].getPiece(), boards[i], spaces[i]);
		}
	}
	
	public static void test(Board board, Player player, int boardIndex, int[] spaces) throws InvalidMoveException, MultipleMovesException {
		for(int i = 0; i < spaces.length; i++) {
			board.move(player.getPiece(), boardIndex, spaces[i]);
		}
	}

	public static void test(Board board, Player[] players, int boardIndex, int[] spaces) throws InvalidMoveException, MultipleMovesException {
		for(int i = 0; i < spaces.length; i++) {
			board.move(players[i].getPiece(), boardIndex, spaces[i]);
		}
	}
}
