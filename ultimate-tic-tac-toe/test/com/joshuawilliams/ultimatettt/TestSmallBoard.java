package com.joshuawilliams.ultimatettt;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class TestSmallBoard {
	private SmallBoard board;
	private Player p1;
	private Player p2;
	
	// Create a new board and new players before each test
	@BeforeEach void init() {
		board = new SmallBoard();
		p1 = new TestingPlayer();
		p2 = new TestingPlayer();
	}
	
	@Test void testInitializes() {
		assertEquals(3, board.getWidth());
		assertEquals(3, board.getHeight());
	}
	
	@Test void testNotFullWhenEmpty() {
		assertFalse(board.isFull());
	}
	
	@Test void testNoWinnerOnEmpty() {
		assertFalse(board.hasWinner());
		assertNull(board.getWinner());
	}
	
	@Test void testCheckValidMove() {
		assertTrue(board.isValidMove(0, 0));
	}
	
	// Check all bounds are enforced
	@Test void testCheckOutOfBoundsMoves() {
		assertFalse(board.isValidMove(-1, 0));
		assertFalse(board.isValidMove(0, -1));
		assertFalse(board.isValidMove(3, 0));
		assertFalse(board.isValidMove(0, 3));
	}
	
	// Test that a basic move succeeds and leaves a corresponding piece
	@Test void testFirstMoveSucceeds() {
		assertFalse(board.isOccupied(0, 0));
		board.move(p1.getPiece(), 0, 0);
		assertTrue(board.isOccupied(0, 0));
		assertEquals(p1.getPiece(), board.getPiece(0, 0));
	}
	
	// Check that you can't move to an occupied space
	@Test void testCheckOccupiedSpaceMove() {
		assertTrue(board.isValidMove(0, 0));
		board.move(p1.getPiece(), 0, 0);
		assertFalse(board.isValidMove(0, 0));
		assertThrows(InvalidMoveException.class, () -> {
			board.move(p2.getPiece(), 0, 0);
		});
	}
	
	// Check that the winner detection won't trigger after a single move
	@Test void testNoWinnerAfterOneMove() {
		board.move(p1.getPiece(), 0, 0);
		assertFalse(board.hasWinner());
		assertNull(board.getWinner());
	}
	
	// Test an example tied game scenario
	@Test void testTiedGame() {
		Player[] players = {p1, p2, p1, p2, p1, p2, p1, p2, p1};
		int[] rows = {0, 0, 0, 1, 1, 1, 2, 2, 2};
		int[] cols = {1, 0, 2, 1, 0, 2, 1, 0, 2};
		SmallBoardTester.test(board, players, rows, cols);
		assertFalse(board.hasWinner());
		assertNull(board.getWinner());
	}
	
	// Test the top-left-to-bottom-right diagonal
	@Test void testPlayerWinsDiagonal1() {
		int[] rows = {0, 1, 2};
		int[] cols = {0, 1, 2};
		SmallBoardTester.test(board, p1, rows, cols);
		assertTrue(board.hasWinner());
		assertEquals(p1, board.getWinner());
	}
	
	// Test the other diagonal
	@Test void testPlayerWinsDiagonal2() {
		int[] rows = {2, 1, 0};
		int[] cols = {0, 1, 2};
		SmallBoardTester.test(board, p1, rows, cols);
		assertTrue(board.hasWinner());
		assertEquals(p1, board.getWinner());
	}
	
	// Test that a player can win with a row
	@Test void testPlayerWinsRow() {
		int[] rows = {1, 1, 1};
		int[] cols = {0, 1, 2};
		SmallBoardTester.test(board, p1, rows, cols);
		assertTrue(board.hasWinner());
		assertEquals(p1, board.getWinner());
	}
	
	// Test that a player can win with a column
	@Test void testPlayerWinsColumn() {
		int[] rows = {0, 1, 2};
		int[] cols = {1, 1, 1};
		SmallBoardTester.test(board, p1, rows, cols);
		assertTrue(board.hasWinner());
		assertEquals(p1, board.getWinner());
	}
	
	// Test that when both players win, only the first player to hit the win condition 
	// is considered the winner. 
	@Test void testFirstPlayerWins() {
		Player[] players = {p1, p2, p1, p2, p1, p2};
		int[] rows = {0, 0, 1, 1, 2, 2};
		int[] cols = {0, 1, 0, 1, 0, 1};
		SmallBoardTester.test(board, players, rows, cols);
		assertTrue(board.hasWinner());
		assertEquals(p1, board.getWinner());
	}
}
