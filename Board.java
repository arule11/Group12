
/**
* Class representing a Board. sunkShips is an int and the board is a
* list of Sites. Ships and marks can be placed onto the board and the
* board can be shown.
*/

public class Board {
	// Board is a ten by ten array of sites, each with a status of 0
	public Site[][] board = new Site[10][10];
	public int sunkShips = 0;


	public Board() {
	}

	/**
		* Initializes a 10 by 10 game board
		*/
	public void initBoard() {
		 for(int row = 0 ; row < 10 ; row++ ){
			 for(int column = 0 ; column < 10 ; column++ ){
				 Site site = new Site();
	            	board[row][column] = site;
	            	site.status = 0;
			 }
		 }
	}

	/**
		* displays the game board
		*/
	public void showBoard(){
		System.out.println("\n\tA \tB \tC \tD \tE \tF \tG \tH \tI \tJ");
        System.out.println();

        for(int row=0 ; row < 10 ; row++ ){
            System.out.print((row+1)+"");
            for(int column=0 ; column < 10 ; column++ ){
            	System.out.print("\t");
            	board[row][column].siteMark();
            }
            System.out.println();
        }
	}

	/**
	* Checks that the spot the player wishes to place their ship is not
	* already occupied
	* @param ship : the ship the play is placing on the board
	* @return Returns a boolean
	*/
	public boolean freeSpace(Ship ship) {
		if (ship.inBounds() == false) {
			return false;
		} else {
			if (ship.direction == 'V') {
				for (int i = ship.row; i < (ship.row + ship.length); i++) {
					if (board[i][ship.column].status != 0) {
						return false;
					}
				}
		} else if (ship.direction == 'H') {
			for (int i = ship.column; i < (ship.column+ship.length); i++) {
				if (board[ship.row][i].status != 0) {
					return false;
				}
			}
		}
		return true;
		}
	}

	/**
	* Adds the specified ship to the players board
	* @param ship : the ship the player is placing on the board
	*/
	public void addShip(Ship ship) {
		if (ship.direction == 'H'){ // Horizontal
            for (int i = ship.column; i < ship.column+ship.length; i++){
            	board[ship.row][i].status = 1;
            }
        } else { // Vertical
        	for (int i = ship.row; i < ship.row+ship.length; i++){
            	board[i][ship.column].status = 1;
            }
        }
    }


	/**
	* Checks if the spot 'guessed' by the player is occupied by one of their
	* opponents ships
	* @param rowGuess : the row corresponding to the selected spot on the board
	* @param columnGuess : the column corresponding to the selected spot on the board
	* @return Returns an int
	*/
	public int checkGuess(int rowGuess, int columnGuess) {
		if (board[rowGuess][columnGuess].status == 1) {
			markHit(rowGuess, columnGuess);
			sunkShips ++;
			return 1;
		} else if (board[rowGuess][columnGuess].status == 2) {
			return -1;
		} else {
		markMiss(rowGuess, columnGuess);
		}

		return 0;
	}

	/**
	* Marks the spot that was guessed by the player as a hit, if their opponents
	* ship was at the guessed location
	* @param rowGuess : the row corresponding to the selected spot on the board
	* @param columnGuess : the column corresponding to the selected spot on the board
	*/
	public void markHit(int rowGuess, int columnGuess) {
    	board[rowGuess][columnGuess].status = 2;
  }


	/**
	* Marks the spot that was guessed by the player as a miss, if their opponents
	* ship was not at the guessed location
	* @param rowGuess : the row corresponding to the selected spot on the board
	* @param columnGuess : the column corresponding to the selected spot on the board
	*/
  public void markMiss(int rowGuess, int columnGuess) {
    	board[rowGuess][columnGuess].status = 3;
  }

}
