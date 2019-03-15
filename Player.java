
/**
* Class representing a Player. NUM_SHIPS and LENGTH are integers, playerBoard
* and oppBoard are of type Board and playerShips is a list of Ships. Each player
* is given a board and a list of ships.
*/

public class Player {
	public static final int NUM_SHIPS = 3;
	public static int LENGTH = 3;

	public Ship[] playerShips;
	public Board playerBoard = new Board();
	public Board oppBoard = new Board();

	/**
	* Default constructor: Initializes a board and a list of three ships for
	* each player, also gives the player a clone of their opponents board
	*/
	public Player() {
		// Each player has a new board and a clone of the opponent
		playerBoard.initBoard();
		oppBoard.initBoard();

		// Each player has a list of three ships
		playerShips = new Ship[NUM_SHIPS];

		// Creates a list of ships for each player, of lengths 3 4 and 5.
		for (int i = 0; i < NUM_SHIPS; i++){
            Ship addPlayerShip = new Ship(LENGTH);
            playerShips[i] = addPlayerShip;
            LENGTH++;
        	}
	}

}
