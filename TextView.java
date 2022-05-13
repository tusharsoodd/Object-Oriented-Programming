package com.company;

/**
 * This file is to be completed by you.
 *
 * @author <s2134605>
 */
public final class TextView {

	public TextView() {

	}

	//done
	public final static void displayNewGameMessage() {
		System.out.println("---- NEW GAME STARTED ----, enter 0 when prompted for move to end game");
	}

	//done
	public final static int[] customDimensions() {   //asks the user if hey want custom dimensions and if they do, receives them and returns them as an int[]
		System.out.println("Custom Dimensions? true for yes and false for no: ");
		boolean custom_dimensions = InputUtil.readBoolFromUser();
		int[] arraycustomDimensions = new int[3];
		if (custom_dimensions) {

			System.out.println("Enter height (number of rows): ");
			int custom_rows = InputUtil.readIntFromUser();
			arraycustomDimensions[0] = custom_rows;

			System.out.println("Enter width (number of columns): "); //
			int custom_cols = InputUtil.readIntFromUser();
			arraycustomDimensions[1] = custom_cols;  //

			System.out.println("How many to win? ");
			int custom_neededtowin = InputUtil.readIntFromUser();
			arraycustomDimensions[2] = custom_neededtowin;

			if (custom_neededtowin > custom_cols || custom_neededtowin > custom_rows) {  //making sure input is valid
				customDimensions();
			}
		} else {
			arraycustomDimensions[0] = 7;
			arraycustomDimensions[1] = 6;
			arraycustomDimensions[2] = 4;

		}
		return arraycustomDimensions;  //returning the custom dimensions is array. If custom dimensions not wanted, the default values of 7,6,4 will be returned.
	}

	//done
	public final int askForMove() {
		System.out.print("Select a free column: ");
		return InputUtil.readIntFromUser();
	}

	public final void displayBoard(Model modelPassed) {
		int nrRowsBoard = Model.getNrRows();
		int nrColsBoard = Model.getNrCols();


		// This can be used to print a line between each row.
		// You may need to vary the length to fit your representation.
		String rowDivider = "-".repeat(4 * nrColsBoard - 1);

		// A StringBuilder is used to assemble longer Strings more efficiently.
		StringBuilder sb = new StringBuilder();

		for (int row = 0; (row < nrRowsBoard); ++row) {
			sb.append(rowDivider + "\n"); //row divider and new line
			for (int col = 0; (col < nrColsBoard); ++col) {
				// You can add to the String using its append method.
				sb.append("|" + modelPassed.grid[col][row] + "|" );
				sb.append(" ");
			}
			sb.append("\n");
		}

		// Then print out the assembled String.
		System.out.println(sb);

	}
}
