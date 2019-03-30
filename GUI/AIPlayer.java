//Based on: private dropbox CPSC219_Examples> Lecture16_OODesign by Nathaly Verwaal

/**
* Class representing an AI Player. num_ships, shipLength and points are integers,
* aiBoard is of type Board and aiShips is a list of Ships. The AI is given a board
* and a list of ships.
* 
* Javadoc by Athena McNeil-Roberts
* Code by Kaylee Novakovski
*/

package GUI;

import Console.Ship;
import Console.Board;
import Console.Player;

import java.util.Random;

public class AIPlayer extends Player {
	private int goodRow = -1;
	private int goodCol = -1;
	private int misses = 0;

	/**
	* Computer randomly selects spots on their board to place their ships
	* @param aiBoard : the AI players board
	*/

	public void setup(Board aiBoard) {
		Random random = new Random(); 
		for (Ship compShip : getPlayerShips()) {
			compShip.setColumn(random.nextInt(10));
			compShip.setRow(random.nextInt(10));
			int randomDirection = random.nextInt(2);
			if (randomDirection == 0) {
				compShip.setDirection('H');
			} else {
				compShip.setDirection('V');
			}
			while(compShip.inBounds() == false) {
				compShip.setColumn(random.nextInt(10));
				compShip.setRow(random.nextInt(10));
				randomDirection = random.nextInt(2);
	    		if (randomDirection == 0) {
	    			compShip.setDirection('H');
	    		} else {
	    			compShip.setDirection('V');
	   			}
			}	
			while (aiBoard.freeSpace(compShip) == false) {
				compShip.setColumn(random.nextInt(10));
				compShip.setRow(random.nextInt(10));
				randomDirection = random.nextInt(2);
					if (randomDirection == 0) {
						compShip.setDirection('H');
					} else {
						compShip.setDirection('V');
					}		
			}
			aiBoard.addShip(compShip);
		}
	}
	
	/**
	* Computer randomly selects spot on the board where their opponents ships may be
	* @param gui : the GUI
	* @param board : the opponents board
	*/
	
	public void guess(GUI gui, Board board) {
		if (misses == 4) {
			makeRightGuess(gui, board);
		} else if (goodRow >= 0){
			smartGuess(gui,board);
		} else {
			Random random = new Random(); 
			int rowGuess = random.nextInt(10);
			int columnGuess = random.nextInt(10);
			int aiGuess = board.checkGuess(rowGuess, columnGuess);
	    	
			while (aiGuess == -1) {
				rowGuess = random.nextInt(10);
				columnGuess = random.nextInt(10);
				aiGuess = board.checkGuess(rowGuess, columnGuess);
			}
			if (aiGuess == 1) {
				gui.setShipMessage("Your battleship was hit!");
				addPoint();
				gui.AIguess('X', rowGuess, columnGuess);
				goodRow = rowGuess;
				goodCol = columnGuess;
				misses = 0;

			} else {
				gui.setShipMessage("Your opponent missed!");	
				gui.AIguess('0', rowGuess, columnGuess);
				goodRow = -1;
				goodCol = -1;
				misses++;
			}
		}

	}
	
	public void smartGuess(GUI gui, Board board) {
		Random random = new Random();
		int vertOrHori = random.nextInt(2);
		int upOrDown = random.nextInt(2);
		int leftOrRight = random.nextInt(2);
		int rowGuess = goodRow;
		int colGuess = goodCol;
		
		if (vertOrHori == 1) {
			if (upOrDown == 1 && goodCol + 1 < 9) {
				colGuess++;
			} else if (upOrDown == 1 && goodCol + 1 > 9) {
				colGuess--;
			} else if (upOrDown == 0 && goodCol-1 > 0) {
				colGuess--;
			} else if (upOrDown == 0 && goodCol-1 < 0){
				colGuess++;
			}
		} else {
			if (leftOrRight == 1 && goodRow+1 < 9) {
				rowGuess++;
			} else if (goodRow + 1 > 9){
				rowGuess--;
			} else if (leftOrRight == 0 && goodRow - 1 > 0) {
				rowGuess--;
			} else {
				rowGuess++;
			}
		}
		
		int aiGuess = board.checkGuess(rowGuess, colGuess);
		
		while (aiGuess == -1) {
			vertOrHori = random.nextInt(2);
			upOrDown = random.nextInt(2);
			leftOrRight = random.nextInt(2);

			if (vertOrHori == 1) {
				if (upOrDown == 1 && goodCol + 1 < 9) {
					colGuess++;
				} else if (upOrDown == 1 && goodCol + 1 > 9) {
					colGuess--;
				} else if (upOrDown == 0 && goodCol-1 > 0) {
					colGuess--;
				} else if (upOrDown == 0 && goodCol-1 < 0){
					colGuess++;
				}
			} else {
				if (leftOrRight == 1 && goodRow+1 < 9) {
					rowGuess++;
				} else if (goodRow +1 > 9){
					rowGuess--;
				} else if (leftOrRight == 0 && goodRow - 1 > 0) {
					rowGuess--;
				} else {
					rowGuess++;
				}
			}
			aiGuess = board.checkGuess(rowGuess, colGuess);

			}
				
		if (aiGuess == 1) {
			gui.setShipMessage("Your battleship was hit!");
			addPoint();
			gui.AIguess('X', rowGuess, colGuess);
			goodRow = rowGuess;
			goodCol = colGuess;
		} else {
			gui.setShipMessage("Your opponent missed!");	
			gui.AIguess('0', rowGuess, colGuess);
			goodRow = -1;
			goodCol = -1;
		}
	}

	
	public void makeRightGuess(GUI gui, Board board) {
		int rowGuess = 0;
		int columnGuess = 0;
		int aiGuess;
		
		 for(int row = 0 ; row < 10 ; row++ ){
			 for(int column = 0 ; column < 10 ; column++ ){
				 if (board.getBoard()[row][column].getStatus() == 1) {
					 rowGuess = row;
					 columnGuess = column;
					 column = 10;
					 row = 10;
				 }
			 }
		 }
		
		aiGuess = board.checkGuess(rowGuess, columnGuess);
		gui.setShipMessage("Your battleship was hit!");
		addPoint();
		gui.AIguess('X', rowGuess, columnGuess);
		goodRow = rowGuess;
		goodCol = columnGuess;
		misses = 0;
		
	}
	
}
