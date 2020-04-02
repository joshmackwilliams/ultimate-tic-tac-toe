package com.joshuawilliams.ultimatettt;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

/* 
 * There's not much to this class right now (although the equality function is 
 * probably worth checking), but there may be more in the future. 
 */

class TestPiece {
	
	TestingPlayer player = new TestingPlayer("test");

	@Test void testEquals() {
		assertTrue(new Piece(player).equals(new Piece(player)));
	}
	
	@Test void testBelongsTo() {
		assertTrue(new Piece(player).belongsTo(player));
	}
}
