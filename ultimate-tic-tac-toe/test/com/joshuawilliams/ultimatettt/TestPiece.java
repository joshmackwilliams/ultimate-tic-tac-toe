package com.joshuawilliams.ultimatettt;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

/* 
 * There's not much to this class right now (although the equality function is 
 * probably worth checking), but there may be more in the future. 
 */

class TestPiece {
	
	TestingPlayer player = new TestingPlayer("test");
	TestingPlayer player2 = new TestingPlayer("test2");

	// This test is here because I actually had a bug in this method involving 
	// not properly overriding it
	@Test void testEquals() {
		assertTrue(new Piece(player).equals(new Piece(player)));
		assertFalse(new Piece(player).equals(new Piece(player2)));
	}
	
	// This test is mostly here because why not
	@Test void testBelongsTo() {
		assertTrue(new Piece(player).belongsTo(player));
		assertFalse(new Piece(player).belongsTo(player2));
	}
}
