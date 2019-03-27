//Based on: private dropbox CPSC219_Examples> Lecture16_OODesign by Nathaly Verwaal

/*
* Class Initializing the game. gui is of type GUI, player1 is a Player, ai is an
* AIPlayer, playerToken is a char, crrentShip is of type Ship and pointsToWin
* is an int.
* Javadoc by Athena McNeil-Roberts
* Code by Kaylee Novakovski
*/

package GUI;

import Console.Ship;
import Console.Player;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;


public class GameInitialization {
	public GUI gui;
	public Player player1 = new Player();
	public AIPlayer ai = new AIPlayer();
	public char playerToken = '+';
	public Ship currentShip = player1.playerShips[player1.num_ships-1];
	public int pointsToWin = 14;
	
	/*
	* Class representing an event handler. Allows player to changle the direction
	* of their ship, when placing it on the board
	*/
	public class HandleHoriClick implements EventHandler<ActionEvent> {
		/*
		* changes the direction of the ship being placed to horizontal
		* @param event : the Action Event
		*/
		public void handle(ActionEvent event){
			currentShip.direction = 'H';
			gui.setShipMessage("Your ship is " + currentShip.shipLength + " units long and set to horizontal");
			}
	}
	
	/*
	* Class representing an event handler. Allows player to changle the direction
	* of their ship, when placing it on the board
	*/
	public class HandleVertClick implements EventHandler<ActionEvent> {
		/*
		* changes the direction of the ship being placed to vertical
		* @param event : the Action Event
		*/
		public void handle(ActionEvent event){
			currentShip.direction = 'V';
			gui.setShipMessage("Your ship is " + currentShip.shipLength + " units long and set to vertical");	
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
	* Class representing an event handler. row and column are integers. checks
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
	* Adds the specified ship to the players board
	* @param ship: the ship the play is placing on the board
	* @param row: the row corresponding to the selected spot on the board
	* @param column : the column corresponding to the selected spot on the board
	*/
	public void placeShip(Ship ship, int row, int column) {	
		ship.row = row;
		ship.column = column;
		gui.setMessage("Place your ships.");
		
		if (player1.num_ships != 0) {
			if (player1.playerBoard.freeSpace(ship)){	
				player1.playerBoard.addShip(ship);
				
				if (ship.direction == 'V') {
					for (int i = ship.row; i < (ship.row+ship.shipLength); i++) {
						gui.placeToken(i, column);
						
					}
				} else if (ship.direction == 'H') {
					for (int i = ship.column; i < (ship.column+ship.shipLength); i++) {
						gui.placeToken(row, i);
					}
				}	
				
			player1.setupShip();
				if (player1.num_ships != 0) {
					currentShip = player1.playerShips[player1.num_ships-1];	
					gui.setShipMessage("Your ship is " + currentShip.shipLength + " units long. Set your direction.");
				} else {
					gui.setMessage("Ships ready. Prepare for battle.");
					gui.setShipMessage(" ");
					ai.setup(ai.aiBoard);
					playGame();
				}
			}
		}
	}
	
	/**
	* initializes the opponents board for guessing
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
	* Checks if all the players ships have been sunk
	* @return Returns a boolean
	*/
	public boolean allShipsSunk() {
		if (player1.points == pointsToWin) {
			gui.setMessage("You win!");
			return true;
		} else if (ai.points == pointsToWin) {
			gui.setMessage("You lose!");
			return true;
		}
		return false;
	}
		
	/**
	* Checks if the spot 'guessed' by the player is occupied by one of their
	* opponents ships
	* @param rowGuess : the row corresponding to the selected spot on the board
	* @param columnGuess : the column corresponding to the selected spot on the board
	*/
	public void checkGuess(int row, int column) {	
			int check = ai.aiBoard.checkGuess(row,column);
			
				if (check == 0) {
					gui.setMessage("You missed.");
					gui.guess('0', row, column);
				} else if (check == 1){
					gui.setMessage("You hit their battleship!");
					gui.guess('X', row, column);
					player1.points++;
				}
				
				if (allShipsSunk()) {
					gui.disable();
				}
				
				ai.guess(gui, player1.playerBoard);
				
				if (allShipsSunk()) {
					gui.disable();
				}
	}
	 
	/**
	* sets up the game GUI and initializes the game
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