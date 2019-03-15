

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

	
	public class HandleCellClick implements EventHandler<ActionEvent> {
		private int row;
		private int column;
		public HandleCellClick(int aRow, int aColumn) {
			row = aRow;
			column = aColumn;
		}
		public void handle(ActionEvent event){
			// place token
			if (player1.num_ships > 0) {
				int numShips = player1.playerShips.length;
				Ship ship = player1.playerShips[numShips-1];
				placeShip(ship, row, column);
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
	}
	
//	public class 
		
	
	public void placeShip(Ship ship, int row, int column) {		
		gui.setMessage("Place your ships.");
		if (row > 10) {
			gui.setMessage("You can't put a ship on \nyour enemy's board.");
		}
		ship.row = row;
		ship.column = column;
		if (player1.playerBoard.freeSpace(ship)){			
				player1.playerBoard.addShip(ship);
				player1.setupShip();
				for (int i = column; i < column+ship.shipLength; i++) {
					gui.placeToken(row, i);
			}	
		} else {
			gui.setMessage("You can't put a ship there");
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
