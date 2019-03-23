package BattleshipA;

import java.util.Random;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.input.KeyEvent;

public class GameInitialization {
	public GUI gui;
	public Player player1 = new Player();
//	public AIPlayer ai = new AIPlayer();
	public char playerToken = '+';
	public Ship currentShip = player1.playerShips[player1.num_ships-1];
	
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
			/*if (player1.num_ships > 0) {
				int numShips = player1.playerShips.length;
				Ship ship = player1.playerShips[numShips-1];*/
				placeShip(currentShip, row, column);
			} 
			
			
			// check if the game is over
/*			if (board.hasWon(playerToken)) {
				gui.setMessage("You won!");
				gui.disable();
			} else {
				// let ai take a turn
				aiTurn();
			}
*/		}		
	
	
	public void placeShip(Ship ship, int row, int column) {	
		ship.row = column;
		ship.column = row;
		gui.setMessage("Place your ships.");
		
		if (ship.row > 10) {
			gui.setMessage("You can't put a ship on \nyour enemy's board.");
		} else if (player1.playerBoard.freeSpace(ship)){	
				player1.playerBoard.addShip(ship);
				
				if (ship.direction == 'H') {
					for (int i = ship.row; i < (ship.row+ship.shipLength); i++) {
						gui.placeToken(i, column);
					}
				} else if (ship.direction == 'V') {
					for (int i = ship.column; i < (ship.column+ship.shipLength); i++) {
						gui.placeToken(row, i);
					}
				}	
				
			player1.setupShip();
			currentShip = player1.playerShips[player1.num_ships-1];	
			gui.setShipMessage("Your ship is " + currentShip.shipLength + " units long. Set your direction.");
			}
		
		if (player1.num_ships < 1) {
			gui.setMessage("Ships ready. Prepare for battle.");
			gui.setShipMessage(" ");
		}
		
		
	//	board.placeToken(token, row, column);		
	}
	
	

/*	private void aiTurn() {
		Move m = ai.getMove(board);
		placeToken(ai.getToken(), m.row, m.column);
		if (board.hasWon(ai.getToken())) {
			gui.setMessage("You lost!");
			gui.disable();
		}		
	}
*/	
	public GameInitialization(GUI gui){
		this.gui = gui;
		for (int row = 0; row < 20; row++){
			for (int col = 0; col < 10; col++) {
				gui.setButtonHandler(new HandleCellClick(row,col),row,col);
			}
		gui.setVertHandler(new HandleVertClick());
		gui.setHoriHandler(new HandleHoriClick());
		}
		
/*		int randomChoice = new Random().nextInt(2);
		if (randomChoice == 0){
			playerToken = 'o';
			ai.setToken('x');
			aiTurn();
			gui.setMessage("Your turn, you're token is 'o'");
		} else {
			playerToken = 'x';
			ai.setToken('o');
			gui.setMessage("Your turn, you're token is 'x'");
		}
*/	}

		
}