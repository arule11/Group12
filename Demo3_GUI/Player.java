package BattleshipA;

public class Player {
	public int num_ships = 5;
	public int shipLength = 5;

	public Ship[] playerShips;
	public Board playerBoard = new Board();
	public Board oppBoard = new Board();

	
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
	
	public void setupShip(){
		num_ships--;
	}

}
