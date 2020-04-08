package com.joshuawilliams.ultimatettt;

/* 
 * The TerminalDisplay is a type of Display that converts the board to ASCII and 
 * displays game state through the terminal. 
 */

// TODO Add test cases
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
					builder.append(' ');
					builder.append(state.getPiece(board, space).getIdentifier());
					builder.append(' ');
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
