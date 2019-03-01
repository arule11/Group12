

public class Player {
	public static final int NUM_SHIPS = 3;
	public static int LENGTH = 3;

	public Ship[] playerShips;
	public Board playerBoard = new Board();
	public Board oppBoard = new Board();

	
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
