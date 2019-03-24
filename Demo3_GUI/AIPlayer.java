import java.util.Random;

public class AIPlayer {
	public int num_ships = 5;
	public int shipLength = 5;

	public static Ship[] aiShips;
	public Board aiBoard = new Board();
	
	public AIPlayer() {
		
		// Each player has a list of three ships
		aiShips = new Ship[num_ships];	
		
		// Creates a list of ships for each player, of lengths 3 4 and 5.
		for (int i = 0; i < num_ships; i++){
            Ship addPlayerShip = new Ship(shipLength);
            aiShips[i] = addPlayerShip;
            shipLength--;
        	}		
	}

	public void setup(Board aiBoard) {
		
		Random random = new Random(); 
			for (Ship compShip : aiShips) {
				compShip.column = random.nextInt(10);
				compShip.row = random.nextInt(10);
				int randomDirection = random.nextInt(2);
				if (randomDirection == 0) {
					compShip.direction = 'H';
				} else {
					compShip.direction = 'V';
				}
				while(compShip.inBounds() == false) {
					compShip.column = random.nextInt(10);
					compShip.row = random.nextInt(10);
					randomDirection = random.nextInt(2);
	    			if (randomDirection == 0) {
	    				compShip.direction = 'H';
	    			} else {
	    				compShip.direction = 'V';
	    			}
				}	
				while (aiBoard.freeSpace(compShip) == false) {
					compShip.column = random.nextInt(10);
					compShip.row = random.nextInt(10);
					randomDirection = random.nextInt(2);
						if (randomDirection == 0) {
							compShip.direction = 'H';
						} else {
							compShip.direction = 'V';
						}		
				}
				aiBoard.addShip(compShip);
		}
		aiBoard.showBoard();
}
	
}
