// Thie version of Battleship was written with reference to Java-Battleship
// by Yuval Marcus (github: ymarcus93)

/**
* Class representing a Player. num_ships and shipLength are integers, playerBoard
* and oppBoard are of type Board and playerShips is a list of Ships. Each player
* is given a board and a list of ships.
*/

public class Player {
	public int num_ships = 3;
	public int shipLength = 5;

	public Ship[] playerShips;
	public Board playerBoard = new Board();
	public Board oppBoard = new Board();

	/**
	* Default constructor:  gives a list of three ships for each player
	*/
	public Player() {

		// Each player has a list of three ships
		playerShips = new Ship[num_ships];

		// Creates a list of ships for each player, of lengths 3 4 and 5.
		for (int i = 0; i < num_ships; i++){
            Ship addPlayerShip = new Ship(shipLength);
            playerShips[i] = addPlayerShip;
            shipLength--;
        	}
	}

	/**
	* decreases the number of ships as the player places them on their board
	*/
	public void setupShip(){
		num_ships--;
	}

}
