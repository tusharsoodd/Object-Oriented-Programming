package com.company;


/**
 * This file is to be completed by you.
 *
 * @author <s2134605>
 */



public final class Controller
{
	public boolean hasQuit;
	public final Model model;
	public final TextView view;

	public Controller(Model model, TextView view)
	{
		this.model = model;  //sets the Model object passed in as the value for the Controller object's Model field
		this.view = view;   //same with the view object
	}
	public void startSession() {

		for (int empty_cells = Model.getNrCols()*Model.getNrRows(), i = 0; (empty_cells!=0 && !hasQuit); --empty_cells, ++i) {
				if (i % 2 == 0) {
					System.out.println(Model.list_of_players[0]);
					view.displayBoard(this.model); //display the grid field of the model field of this controller object

					boolean valid;
					do {
						int col = view.askForMove();
						if (col == 0) {
							hasQuit = true;
							break;
						} //checking if user input is 0, which means they want to end current game
						else {
							valid = model.isMoveValid(col); //check if move valid, return boolean value
							if (valid) this.model.makeMove(col, 'R');
						}
					}
						while (!valid) ; //while the input is not valid, keep doing this
				}

				else {
					System.out.println(Model.list_of_players[1]);
					view.displayBoard(this.model);

					boolean valid;
					do {
						int col = view.askForMove();
						if (col == 0) {hasQuit = true; break;} //checking if user wants to quit
						else{
						valid = model.isMoveValid(col); //checking if move is valid
						if (valid)  this.model.makeMove(col, 'Y'); } //making move with custom makeMove method
					}
					while (!valid);

					}
				}


		System.out.println("Game ended!");
		System.out.println("New Game? true for yes and false for no:");
		if (InputUtil.readBoolFromUser()) {  //asking user if they want a new game
			Main.main(null);  //recursive call to the main method if user wants new game
		} else {
			System.out.println("Program Connect4 ending...");
			System.exit(0);
		}

		}
	}