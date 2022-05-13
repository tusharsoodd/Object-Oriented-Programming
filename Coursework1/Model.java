package com.company;

/**
 * This file is to be completed by you.
 *
 * @author <s2134605>
 */
public final class Model //models the connect 4 board
{
	// ================================ CONSTANTS ================================
	// The most common version of Connect Four has 7 rows and 6 columns.


	public static final String[] list_of_players = {"player 1", "player 2"}; //list of players


	// ================================ FIELDS =====================================
	// The size of the board.

	private static int nrRows;
	private static int nrCols;
	private static int nrNeededToWin;


	public char[][] grid = new char[nrCols][nrRows]; //stores the grid of the model object


	// ================================ CONSTRUCTOR ================================

	public Model() {
		// Initialise the board size to its default values.
		for (int row = 0; (row < nrRows); row++) {
			for (int col = 0; (col < nrCols); ++col) {
				grid[col][row] = 'X'; //placeholder value is 'X'
	}

		}
	}


	
	// ================================ MODEL INTERACTIONS ================================
	//done
	public boolean isMoveValid(int move) {
		//checking if move is valid
		 if (move != 0 && move <= nrCols) { //if the move isn't 0 (meaning user wants to quit) and is less than or equal to the number of columns
			for (int current_row = nrRows-1; (current_row >= 0); --current_row) {
				if (grid[move - 1][current_row] == 'X') { //making user that the spot is empty
					return true;
				}
			}
		}
		return false;
	}


	//done
	public void makeMove(int move, char letter)  //needs to be improved
	{
		for (int curernt_row=nrRows-1; (curernt_row>=0); --curernt_row){
			if (this.grid[move-1][curernt_row] == 'X') { //checking if value is empty here
				this.grid[move-1][curernt_row] = letter;
				break;
			}
		}
	}

	
	
	// ================================ GETTERS & SETTERS ================================

	public static int getNrRows()
	{
		return nrRows;
	}
	
	public static int getNrCols()
	{
		return nrCols;
	}

	//For the auto win detection feature, which didn't get implemented
	public static int getnrNeededToWin()
	{
		return nrNeededToWin;
	}

	public static void setNrRows(int input_rows) {
		nrRows = input_rows;   //this. needed
	}

	public static void setNrCols(int input_cols) {
		nrCols = input_cols;   //this. needed
	}

	//For the auto win detection feature, which didn't get implemented
	public static void setnrNeededToWin(int input_nr_needed_to_win) {
		nrNeededToWin = input_nr_needed_to_win;   //this. needed
	}

}
