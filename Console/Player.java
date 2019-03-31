// Thie version of Battleship was written with reference to Java-Battleship
// by Yuval Marcus (github: ymarcus93)

/**
* Class representing a Player. num_ships, shipLength and points are integers, playerBoard
* and oppBoard are of type Board and playerShips is a list of Ships. Each player
* is given a board and a list of ships.
* Javadoc by Athena McNeil-Roberts
* Code by Kaylee Novakovski
*/

package Console;


public class Player {
	private int numShips = 4;
	private int shipLength = 5;
	
	private int points = 0;
	
	private Ship[] playerShips;
	private Board playerBoard = new Board();
	private Board oppBoard = new Board();

	/**
	* Default constructor:  gives a list of three ships for each player
	*/

	public Player() {
		playerShips = (new Ship[numShips]);	
		
		for (int i = 0; i < getNumShips(); i++){
            Ship addPlayerShip = new Ship(shipLength);
            getPlayerShips()[i] = addPlayerShip;
            shipLength--;
        	}		
	}
	
	/**
	* decreases the number of ships as the player places them on their board
	*/
	public void setupShip(){
		if (numShips != 0) 
			numShips--;
	}

	public int getPoints() {
		return this.points;
	}

	public void addPoint() {
		points++;
	}

	public int getNumShips() {
		return numShips;
	}

	public Board getPlayerBoard() {
		return playerBoard;
	}

	public Ship[] getPlayerShips() {
		return playerShips;
	}

	public Board getOppBoard() {
		return oppBoard;
	}

	public double getShipLength() {
		return shipLength;
	}


}
