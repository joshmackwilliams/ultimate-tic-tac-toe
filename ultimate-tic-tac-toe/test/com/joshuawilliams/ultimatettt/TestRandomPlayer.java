package com.joshuawilliams.ultimatettt;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Random;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class TestRandomPlayer {
	
	Board board;
	Player player;
	Random random = new Random();

	@BeforeEach void setUp() {
		player = new RandomPlayer("RandomPlayer");
		board = new Board();
	}
	
	// This test makes sure that the random player will actually make a move on an empty board
	@Test void testMakesMove() throws InvalidMoveException, MultipleMovesException {
		player.makeMove(new Move(board, player));
		boolean madeMove = false;
		for(int iboard = 0; iboard < 9; iboard++) {
			for(int ispace = 0; ispace < 9; ispace++) {
				if(player.getPiece().equals(board.getPiece(iboard, ispace))) madeMove = true;
			}
		}
		assertTrue(madeMove);
	}
	
	// This test fills all but one space on the board and makes sure that the random player fills that space
	@Test void testWontMakeInvalidMove() throws InvalidMoveException, MultipleMovesException {
		// We have to run this test ten times to make sure that making the 
		// one remaining valid move isn't just a random fluke
		Player player2 = new TestingPlayer("Player2");
		for(int i = 0; i < 10; i++) {
			board = new Board(); // We need clear the board on each loop iteration, so we do it here
			
			int validMoveBoard = random.nextInt(8);
			int validMoveSpace = random.nextInt(8);
			
			for(int moveBoard = 0; moveBoard < 9; moveBoard++) {
				for(int moveSpace = 0; moveSpace < 9; moveSpace++) {
					if(moveBoard == validMoveBoard && moveSpace == validMoveSpace) continue;
					board.move(player2.getPiece(), moveBoard, moveSpace);
				}
			}
			
			player.makeMove(new Move(board, player));
			assertEquals(player.getPiece(), board.getPiece(validMoveBoard, validMoveSpace));
		}
	}

}
