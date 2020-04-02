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

	@Test void test() {
		fail("Not yet implemented");
	}
}
