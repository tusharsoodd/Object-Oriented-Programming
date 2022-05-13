package com.company;
/**
 * The main class of the Connect Four game.
 * You should not have to touch this code (except maybe for advanced features).
 *
 * @author David Symons & s2134605
 */


public final class Main {
	/**
	 * The code provided for this assignment follows a design pattern called
	 * Model-View-Controller (MVC). The main method instantiates each of these
	 * components and then starts the game loop.
	 *
	 * @param args No arguments expected.
	 */
	public static void main(String[] args)
	{
		// Tell the user that the game has started.
		TextView.displayNewGameMessage();
		int[] customDimensionsArray = TextView.customDimensions(); //asks user if they want custom dimensions and if they do, recieves them
		Model.setNrRows(customDimensionsArray[0]); //using set method to set the value of the static var nrRows based on values recieved in custom_dimensions
		Model.setNrCols(customDimensionsArray[1]);
		Model.setnrNeededToWin(customDimensionsArray[2]);
		// Creates a model representing the state of the game.
		Model model1 = new Model();

		
		// This text-based view is used to communicate with the user.
		// It can print the state of the board and handles user input.
		TextView view = new TextView();
		
		// The controller facilitates communication between model and view.
		// It also contains the main loop that controls the sequence of events.
		Controller controller = new Controller(model1, view);
		
		// Start a new session.
		controller.startSession();
	}


}
