package com.joshuawilliams.ultimatettt;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class TestBoard {

	Board board;
	Player p1;
	Player p2;

	// Create a new Board and Players to make sure tests don't interfere with each other
	@BeforeEach void setUp() {
		board = new Board();
		p1 = new TestingPlayer("P1");
		p2 = new TestingPlayer("P2");
	}

	// Just test that we can successfully initialize a Board
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

	// Check that all bounds are enforced
	@Test void testCheckOutOfBoundsMoves() {
		assertFalse(board.isValidMove(0, -1));
		assertFalse(board.isValidMove(0, 9));
		assertFalse(board.isValidMove(-1, 0));
		assertFalse(board.isValidMove(9, 0));
	}

	// Test that a basic move succeeds and leaves a corresponding piece
	@Test void testFirstMoveSucceeds() throws InvalidMoveException, MultipleMovesException {
		assertFalse(board.isOccupied(0, 0));
		board.move(p1.getPiece(), 0, 0);
		assertTrue(board.isOccupied(0, 0));
		assertEquals(p1.getPiece(), board.getPiece(0, 0));
	}

	// Check that you can't move to an occupied space
	@Test void testCheckOccupiedSpaceMove() throws InvalidMoveException, MultipleMovesException {
		assertTrue(board.isValidMove(0, 0));
		board.move(p1.getPiece(), 0, 0);
		assertFalse(board.isValidMove(0, 0));
		assertThrows(InvalidMoveException.class, () -> {
			board.move(p2.getPiece(), 0, 0);
		});
	}

	// Check that the winner detection won't trigger after a single move
	@Test void testNoWinnerAfterOneMove() throws InvalidMoveException, MultipleMovesException {
		board.move(p1.getPiece(), 0, 0);
		assertNull(board.getWinner());
		assertNull(board.getWinner(0));
		assertFalse(board.hasWinner());
		assertFalse(board.hasWinner(0));
	}
	
	// Test that a player can win a single board without winning the whole game
	@Test void testPlayerWinsBoard0() throws InvalidMoveException, MultipleMovesException {
		BoardTester.test(board, p1, 0, SmallBoard.WIN_CONDITIONS[0]);
		assertFalse(board.hasWinner());
		assertTrue(board.hasWinner(0));
		for(int i = 1; i < 9; i++) {assertFalse(board.hasWinner(i));}
		assertNull(board.getWinner());
		assertEquals(p1.getPiece(), board.getWinner(0));
		for(int i = 1; i < 9; i++) {assertNull(board.getWinner(i));}
	}
	
	// Make sure that the isFull() methods report correctly when one board is full
	@Test void testOneBoardFull() throws InvalidMoveException, MultipleMovesException {
		for(int i = 0; i < 9; i++) {
			board.move(p1.getPiece(), 0, i);
		}
		assertFalse(board.isFull());
		assertTrue(board.isFull(0));
		for(int i = 1; i < 9; i++) {assertFalse(board.isFull(i));}
	}
	
	// Test that isFull() reports when the whole board is full
	@Test void testFull() throws InvalidMoveException, MultipleMovesException {
		assertFalse(board.isFull());
		for(int i = 0; i < 9; i++) {
			for(int j = 0; j < 9; j++) {
				board.move(p1.getPiece(), i, j);
			}
		}
		assertTrue(board.isFull());
	}
	
	// Test that a player can win the game
	@Test void testPlayerWins() throws InvalidMoveException, MultipleMovesException {
		BoardTester.test(board, p1, 0, SmallBoard.WIN_CONDITIONS[0]);
		BoardTester.test(board, p1, 4, SmallBoard.WIN_CONDITIONS[1]);
		BoardTester.test(board, p1, 8, SmallBoard.WIN_CONDITIONS[2]);
		assertTrue(board.hasWinner());
		assertEquals(p1.getPiece(), board.getWinner());
	}
}
