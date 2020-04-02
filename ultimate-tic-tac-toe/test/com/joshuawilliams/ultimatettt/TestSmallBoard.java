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
		assertTrue(board.isValidMove(0));
	}
	
	// Check all bounds are enforced
	@Test void testCheckOutOfBoundsMoves() {
		assertFalse(board.isValidMove(-1));
		assertFalse(board.isValidMove(9));
	}
	
	// Test that a basic move succeeds and leaves a corresponding piece
	@Test void testFirstMoveSucceeds() {
		assertFalse(board.isOccupied(0));
		board.move(p1.getPiece(), 0);
		assertTrue(board.isOccupied(0));
		assertEquals(p1.getPiece(), board.getPiece(0));
	}
	
	// Check that you can't move to an occupied space
	@Test void testCheckOccupiedSpaceMove() {
		assertTrue(board.isValidMove(0));
		board.move(p1.getPiece(), 0);
		assertFalse(board.isValidMove(0));
		assertThrows(InvalidMoveException.class, () -> {
			board.move(p2.getPiece(), 0);
		});
	}
	
	// Check that the winner detection won't trigger after a single move
	@Test void testNoWinnerAfterOneMove() {
		board.move(p1.getPiece(), 0);
		assertNull(board.getWinner());
		assertFalse(board.hasWinner());
	}
	
	// Test an example tied game scenario
	@Test void testTiedGame() {
		Player[] players = {p1, p2, p1, p2, p1, p2, p1, p2, p1};
		int[] spaces = {0, 2, 1, 4, 5, 3, 6, 8, 7};
		SmallBoardTester.test(board, players, spaces);
		assertFalse(board.hasWinner());
		assertNull(board.getWinner());
	}
	
	// Test that the board correctly reports being full
	@Test void testFull() {
		Player[] players = {p1, p2, p1, p2, p1, p2, p1, p2, p1};
		int[] spaces = {0, 2, 1, 4, 5, 3, 6, 8, 7};
		SmallBoardTester.test(board, players, spaces);
		assertTrue(board.isFull());
	}
	
	// Test that a player can win
	@Test void testPlayerWins() {
		SmallBoardTester.test(board, p1, SmallBoard.winConditions[0]);
		assertEquals(p1.getPiece(), board.getWinner());
		assertTrue(board.hasWinner());
	}
	
	// Test that when both players win, only the first player to hit the win condition 
	// is considered the winner. 
	@Test void testFirstPlayerWins() {
		Player[] players = {p1, p2, p1, p2, p1, p2};
		int[] spaces = {0, 3, 1, 4, 2, 5};
		SmallBoardTester.test(board, players, spaces);
		assertTrue(board.hasWinner());
		assertEquals(p1.getPiece(), board.getWinner());
	}
	
	// Switch the players in the above test and make sure it still works
	@Test void testFirstPlayerWins2() {
		Player[] players = {p1, p2, p1, p2, p1, p2};
		int[] spaces = {3, 0, 4, 1, 5, 2};
		SmallBoardTester.test(board, players, spaces);
		assertTrue(board.hasWinner());
		assertEquals(p1.getPiece(), board.getWinner());
	}
}
