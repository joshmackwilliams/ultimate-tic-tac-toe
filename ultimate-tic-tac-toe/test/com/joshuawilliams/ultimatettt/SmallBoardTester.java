package com.joshuawilliams.ultimatettt;

/*
 * This class is used for testing purposes only. It is designed to efficiently run a test 
 * scenario by allowing the test cases to use arrays of move data rather than repeated calls 
 * to Board.move(). 
 */

public class SmallBoardTester {
	public static void test(SmallBoard board, Player player, int[] spaces) throws InvalidMoveException, MultipleMovesException {
		for(int i = 0; i < spaces.length; i++) {
			board.move(player.getPiece(), spaces[i]);
		}
	}
	
	public static void test(SmallBoard board, Player[] players, int[] spaces) throws InvalidMoveException, MultipleMovesException {
		for(int i = 0; i < spaces.length; i++) {
			board.move(players[i].getPiece(), spaces[i]);
		}
	}
}
