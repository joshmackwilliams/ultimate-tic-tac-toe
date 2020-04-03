package com.joshuawilliams.ultimatettt;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class TestBoard {

	Board board;
	Player p1;
	Player p2;

	@BeforeEach void setUp() {
		board = new Board();
		p1 = new TestingPlayer("P1");
		p2 = new TestingPlayer("P2");
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
		assertTrue(board.checkMove(0, 0));
	}

	// Check that all bounds are enforced
	@Test void testCheckOutOfBoundsMoves() {
		assertFalse(board.checkMove(0, -1));
		assertFalse(board.checkMove(0, 9));
		assertFalse(board.checkMove(-1, 0));
		assertFalse(board.checkMove(9, 0));
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
		assertNull(board.getWinner());
		assertNull(board.getWinner(0));
		assertFalse(board.hasWinner());
		assertFalse(board.hasWinner(0));
	}
	
	// Test that a player can win a single board without winning the whole game
	@Test void testPlayerWinsBoard0() {
		BoardTester.test(board, p1, 0, SmallBoard.winConditions[0]);
		assertFalse(board.hasWinner());
		assertTrue(board.hasWinner(0));
		for(int i = 1; i < 9; i++) {assertFalse(board.hasWinner(i));}
		assertNull(board.getWinner());
		assertEquals(p1.getPiece(), board.getWinner(0));
		for(int i = 1; i < 9; i++) {assertNull(board.getWinner(i));}
	}
	
	@Test void testOneBoardFull() {
		for(int i = 0; i < 9; i++) {
			board.move(p1.getPiece(), 0, i);
		}
		assertFalse(board.isFull());
		assertTrue(board.isFull(0));
		for(int i = 1; i < 9; i++) {assertFalse(board.isFull(i));}
	}
	
	@Test void testFull() {
		assertFalse(board.isFull());
		for(int i = 0; i < 9; i++) {
			for(int j = 0; j < 9; j++) {
				board.move(p1.getPiece(), i, j);
			}
		}
		assertTrue(board.isFull());
	}
	
	@Test void testPlayerWins() {
		BoardTester.test(board, p1, 0, SmallBoard.winConditions[0]);
		BoardTester.test(board, p1, 4, SmallBoard.winConditions[1]);
		BoardTester.test(board, p1, 8, SmallBoard.winConditions[2]);
		assertTrue(board.hasWinner());
		assertEquals(p1.getPiece(), board.getWinner());
	}
}
