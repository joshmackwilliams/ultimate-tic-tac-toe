package com.joshuawilliams.ultimatettt;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class TestSmallBoard {
	private SmallBoard board;
	private Player p1;
	private Player p2;
	
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
	
	@Test void testCheckOutOfBoundsMoves() {
		assertFalse(board.isValidMove(-1, 0));
		assertFalse(board.isValidMove(0, -1));
		assertFalse(board.isValidMove(3, 0));
		assertFalse(board.isValidMove(0, 3));
	}
	
	@Test void testFirstMoveSucceeds() {
		assertFalse(board.isOccupied(0, 0));
		board.move(p1.getPiece(), 0, 0);
		assertTrue(board.isOccupied(0, 0));
		assertEquals(p1.getPiece(), board.getPiece(0, 0));
	}
	
	@Test void testCheckOccupiedSpaceMove() {
		assertTrue(board.isValidMove(0, 0));
		board.move(p1.getPiece(), 0, 0);
		assertFalse(board.isValidMove(0, 0));
		assertThrows(InvalidMoveException.class, () -> {
			board.move(p2.getPiece(), 0, 0);
		});
	}
	
	@Test void testNoWinnerAfterOneMove() {
		board.move(p1.getPiece(), 0, 0);
		assertFalse(board.hasWinner());
		assertNull(board.getWinner());
	}
	
	@Test void testTiedGame() {
		Player[] players = {p1, p2, p1, p2, p1, p2, p1, p2, p1};
		int[] rows = {0, 0, 0, 1, 1, 1, 2, 2, 2};
		int[] cols = {1, 0, 2, 1, 0, 2, 1, 0, 2};
		SmallBoardTester.test(board, players, rows, cols);
		assertFalse(board.hasWinner());
		assertNull(board.getWinner());
	}
	
	@Test void testPlayerWinsDiagonal() {
		int[] rows = {0, 1, 2};
		int[] cols = {0, 1, 2};
		SmallBoardTester.test(board, p1, rows, cols);
		assertTrue(board.hasWinner());
		assertEquals(p1, board.getWinner());
	}
}
