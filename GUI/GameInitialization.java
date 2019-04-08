//Based on: private dropbox CPSC219_Examples> Lecture16_OODesign by Nathaly Verwaal

/*
* Class Initializing the game. gui is of type GUI, player1 is a Player, ai is an
* AIPlayer, currentShip is of type Ship and POINTS_TO_WIN is an int.
* Javadoc by Athena McNeil-Roberts
* Code by Kaylee Novakovski
*/

package GUI;

import Console.Ship;
import Console.Player;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;


public class GameInitialization {
	private GUI gui;
	private Player player1 = new Player();
	private AIPlayer ai = new AIPlayer();
	private Ship currentShip = player1.getPlayerShips()[player1.getNumShips()-1];
	private static final int POINTS_TO_WIN = 14;


	/*
	* Class representing an event handler. Allows player to change the direction
	* of their ship, when placing it on the board
	*/
	public class HandleHoriClick implements EventHandler<ActionEvent> {
		/*
		* Changes the direction of the ship being placed to horizontal
		* @param event : the Action Event
		*/
		public void handle(ActionEvent event){
			currentShip.setDirection('H');
			gui.setShipMessage("Your ship is " + currentShip.getShipLength() + " units long and set to horizontal");
			}
	}

	/*
	* Class representing an event handler. Allows player to change the direction
	* of their ship, when placing it on the board
	*/
	public class HandleVertClick implements EventHandler<ActionEvent> {
		/*
		* Changes the direction of the ship being placed to vertical
		* @param event : the Action Event
		*/
		public void handle(ActionEvent event){
			currentShip.setDirection('V');
			gui.setShipMessage("Your ship is " + currentShip.getShipLength() + " units long and set to vertical");
		}
	}

	/*
	* Class representing an event handler. row and column are integers. Allows
	* player to place their ship on the board.
	*/
	public class HandleCellClick implements EventHandler<ActionEvent> {
		private int row;
		private int column;

		public HandleCellClick(int aRow, int aColumn) {
			row = aRow;
			column = aColumn;
		}

		/*
		* places ship where player clicks
		* @param event : the Action Event
		*/

		public void handle(ActionEvent event){
				placeShip(currentShip, row, column);
			}
	}

	/*
	* Class representing an event handler. row and column are integers. Checks
	* if the opponent has a ship where the player clicked.
	*/
	public class HandleGuessClick implements EventHandler<ActionEvent> {
		private int row;
		private int column;

		public HandleGuessClick(int aRow, int aColumn) {
			row = aRow;
			column = aColumn;
		}
		/*
		* checks for ship where player clicks
		* @param event : the Action Event
		*/
		public void handle(ActionEvent event){
				checkGuess(row, column);
			}
	}

	/**
	* Adds the specified ship to the player's board
	* @param ship: the ship the player is placing on the board
	* @param row: the row corresponding to the selected spot on the board
	* @param column : the column corresponding to the selected spot on the board
	*/
	public void placeShip(Ship ship, int row, int column) {
		ship.setRow(row);
		ship.setColumn(column);
		gui.setMessage("Place your ships.");

		if (player1.getNumShips() != 0) {
			if (player1.getPlayerBoard().freeSpace(ship)){
				player1.getPlayerBoard().addShip(ship);

				if (ship.getDirection() == 'V') {
					for (int i = ship.getRow(); i < (ship.getRow()+ship.getShipLength()); i++) {
						gui.placeToken(i, column);

					}
				} else if (ship.getDirection() == 'H') {
					for (int i = ship.getColumn(); i < (ship.getColumn()+ship.getShipLength()); i++) {
						gui.placeToken(row, i);
					}
				}

			player1.setupShip();
				if (player1.getNumShips() != 0) {
					currentShip = player1.getPlayerShips()[player1.getNumShips()-1];
					gui.setShipMessage("Your ship is " + currentShip.getShipLength() + " units long. Set your direction.");
				} else {
					gui.setMessage("Ships ready. Prepare for battle.");
					gui.setShipMessage(" ");
					ai.setup(ai.getPlayerBoard());
					playGame();
				}
			}
		}
	}

	/**
	* Initializes the opponent's board for guessing
	*/
	public void playGame() {
		gui.removeDirections();
		for (int row = 0; row < 10; row++){
			for (int col = 0; col < 10; col++) {
				gui.setOppButtonHandler(new HandleGuessClick(row,col),row,col);
			}
		}
	}

	/**
	* Checks if all of the player's ships have been sunk
	* @return Returns a boolean
	*/
	public boolean allShipsSunk() {
		if (player1.getPoints() == POINTS_TO_WIN) {
			gui.setMessage("You win!");
			return true;
		} else if (ai.getPoints() == POINTS_TO_WIN) {
			gui.setMessage("You lose!");
			return true;
		}
		return false;
	}

	/**
	* Checks if the spot 'guessed' by the player is occupied by one of their
	* opponent's ships
	* @param rowGuess : the row corresponding to the selected spot on the board
	* @param columnGuess : the column corresponding to the selected spot on the board
	*/
	public void checkGuess(int row, int column) {
			int check = ai.getPlayerBoard().checkGuess(row,column);

				if (check == 0) {
					gui.setMessage("You missed.");
					gui.guess('0', row, column);
				} else if (check == 1){
					gui.setMessage("You hit their battleship!");
					gui.guess('X', row, column);
					player1.addPoint();
				}

				if (allShipsSunk()) {
					gui.disable();
				}

				ai.guess(gui, player1.getPlayerBoard());

				if (allShipsSunk()) {
					gui.disable();
				}
	}

	/**
	* Sets up the game GUI and initializes the game
	* @param gui : the GUI for the game
	*/
	public GameInitialization(GUI gui){
		this.gui = gui;
		for (int row = 0; row < 10; row++){
			for (int col = 0; col < 10; col++) {
				gui.setPlayerButtonHandler(new HandleCellClick(row,col),row,col);
			}
		}
		gui.setVertHandler(new HandleVertClick());
		gui.setHoriHandler(new HandleHoriClick());
	}

}
