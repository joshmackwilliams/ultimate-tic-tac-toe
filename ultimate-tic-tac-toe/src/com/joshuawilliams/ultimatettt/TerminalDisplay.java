package com.joshuawilliams.ultimatettt;

// Class to convert BoardStates to ASCII

public class TerminalDisplay {
	
	private static final String topDivider = "╔═══╤═══╤═══╦═══╤═══╤═══╦═══╤═══╤═══╗\n";
	private static final String bottomDivider = "╚═══╧═══╧═══╩═══╧═══╧═══╩═══╧═══╧═══╝\n";
	private static final String minorDivider = "╟───┼───┼───╫───┼───┼───╫───┼───┼───╢\n";
	private static final String majorDivider = "╠═══╪═══╪═══╬═══╪═══╪═══╬═══╪═══╪═══╣\n";

	public static String displayBoard(BoardState state) {
		StringBuilder builder = new StringBuilder();
		builder.append(topDivider);
		for(int row = 0; row <= 8; row++) {
			for(int col = 0; col <= 8; col++) {
				builder.append((col % 3 == 0) ? '║' : '│');
				int board = ((row / 3) * 3) + (col / 3);
				int space = ((row % 3) * 3) + (col % 3);
				if(state.isOccupied(board, space)) {
					boolean winningSpace = false;
					if(state.hasWinner(board)) {
						for(int i = 0; i < state.getWinningCondition(board).length; i++) {
							if(state.getWinningCondition(board)[i] == space) {
								winningSpace = true;
								break;
							}
						}
					}
					if(winningSpace) {
						builder.append('[');
						builder.append(state.getPiece(board, space).getIdentifier());
						builder.append(']');
					} else {
						builder.append(' ');
						builder.append(state.getPiece(board, space).getIdentifier());
						builder.append(' ');
					}
				} else {
					builder.append("   ");
				}
			}
			builder.append("║\n");
			if(row < 8) {
				builder.append((row % 3 == 2) ? majorDivider : minorDivider);
			}
		}
		builder.append(bottomDivider);
		return builder.toString();
	}
}
