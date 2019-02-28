

public class Player {

	public Ship[] playerShips;
	public Board playerBoard = new Board();
	
	public Player() {
		// Each player has a new board
		playerBoard.initBoard();
		
		// Each player has a list of three ships
		playerShips = new Ship[3];	
		int shipLength = 3;
		
		// Creates a list of ships for each player, of lengths 3 4 and 5.
		for (int i = 0; i < 3; i++){
            Ship addPlayerShip = new Ship(shipLength);
            playerShips[i] = addPlayerShip;
            shipLength++;
        	}

		
		
	}
	
	
	
}
