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
	* Computer randomly selects spot on the board where their oppenents ships may be
	* @param gui : the GUI
	* @param playerBoard : the opponents board
	*/
	
	public void guess(GUI gui, Board playerBoard) {
		Random random = new Random(); 
    	int rowGuess = random.nextInt(10);
    	int columnGuess = random.nextInt(10);
    	int aiGuess = playerBoard.checkGuess(rowGuess, columnGuess);
    	
    	while (aiGuess == -1) {
    		rowGuess = random.nextInt(10);
        	columnGuess = random.nextInt(10);
        	aiGuess = playerBoard.checkGuess(rowGuess, columnGuess);
    		}
    	if (aiGuess == 1) {
    		gui.setShipMessage("Your battleship was hit!");
    		addPoint();
    		gui.AIguess('X', rowGuess, columnGuess);
    	} else {
    		gui.setShipMessage("Your opponent missed!");	
    		gui.AIguess('0', rowGuess, columnGuess);
    	}		    

	}
	
}
