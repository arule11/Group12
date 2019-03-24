
import java.util.Random;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.input.KeyEvent;

public class GameInitialization {
	public GUI gui;
	public Player player1 = new Player();
	public AIPlayer ai = new AIPlayer();
	public char playerToken = '+';
	public Ship currentShip = player1.playerShips[player1.num_ships-1];
	public int pointsToWin = 14;
	
	public class HandleHoriClick implements EventHandler<ActionEvent> {
		public void handle(ActionEvent event){
			currentShip.direction = 'H';
			gui.setShipMessage("Your ship is " + currentShip.shipLength + " units long and set to horizontal");
			}
	}
	
	public class HandleVertClick implements EventHandler<ActionEvent> {
		public void handle(ActionEvent event){
			currentShip.direction = 'V';
			gui.setShipMessage("Your ship is " + currentShip.shipLength + " units long and set to vertical");	
		}
	}
	
	public class HandleCellClick implements EventHandler<ActionEvent> {
		private int row;
		private int column;
		
		public HandleCellClick(int aRow, int aColumn) {
			row = aRow;
			column = aColumn;
		}
		
		public void handle(ActionEvent event){
				placeShip(currentShip, row, column);
			} 
	}		
	
	public class HandleGuessClick implements EventHandler<ActionEvent> {
		private int row;
		private int column;
		
		public HandleGuessClick(int aRow, int aColumn) {
			row = aRow;
			column = aColumn;
		}
		
		public void handle(ActionEvent event){
				checkGuess(row, column);
			} 
	}	
	
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
	
	public void playGame() {
		gui.removeDirections();
		for (int row = 0; row < 10; row++){
			for (int col = 0; col < 10; col++) {
				gui.setOppButtonHandler(new HandleGuessClick(row,col),row,col);
			}
		}	
	}
	
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
			//	ai.aiBoard.showBoard();
				
				if (allShipsSunk()) {
					gui.disable();
				}
	}
	 

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