package com.joshuawilliams.ultimatettt;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class TestMove {
	
	Board board;
	Player player;
	Move move;

	@BeforeEach void setUp() throws Exception {
		board = new Board();
		player = new TestingPlayer("Player");
		move = new Move(board, player);
	}

	@Test void testOneMoveSucceeds() throws InvalidMoveException, MultipleMovesException {
		assertFalse(move.isMade());
		assertTrue(move.isValidMove(0, 0));
		move.makeMove(0, 0);
		assertTrue(move.isMade());
		assertTrue(move.isOccupied(0, 0));
		assertEquals(player.getPiece(), move.getPiece(0, 0));
	}
	
	@Test void testMultipleMovesNotAllowed() throws InvalidMoveException, MultipleMovesException {
		move.makeMove(1, 2);
		assertThrows(MultipleMovesException.class, () -> {
			move.makeMove(5, 6);
		});
		assertEquals(1, move.getBoard());
		assertEquals(2, move.getSpace());
	}
	
	@Test void testRequiredBoardEnforced() throws InvalidMoveException, MultipleMovesException {
		move.makeMove(1, 2);
		move = new Move(board, player, move);
		assertThrows(InvalidMoveException.class, () -> {
			move.makeMove(3, 4);
		});
		assertFalse(move.isMade());
		move.makeMove(2, 3);
		assertTrue(move.isMade());
		assertEquals(player.getPiece(), move.getPiece(2, 3));
	}
}
