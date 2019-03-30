// Thie version of Battleship was written with reference to Java-Battleship
// by Yuval Marcus (github: ymarcus93)

/**
* Class representing a Board. sunkShips is an int and the board is a
* list of Sites. Ships and marks can be placed onto the board and the
* board can be shown.
* 
* Javadoc by Athena McNeil-Roberts
* Code by Kaylee Novakovski
*/

package Console;

public class Board {

	// Board is a ten by ten array of sites, each with a status of 0
	private Site[][] board = new Site[10][10];
	private int sunkShips = 0;


	/**
	* Default constructor: Initializes a 10 by 10 game board
	*/
	
	public Board() {
		 for(int row = 0 ; row < 10 ; row++ ){
			 for(int column = 0 ; column < 10 ; column++ ){
				 Site site = new Site();
	            	board[row][column] = site;
	            	site.setStatus(0);
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
		if (!ship.inBounds()) {
			return false;
		} else {
			if (ship.getDirection() == 'V') {
				for (int i = ship.getRow(); i < (ship.getRow() + ship.getShipLength()); i++) {
					if (board[i][ship.getColumn()].getStatus() != 0) {
						return false;
					}
				}
		} else if (ship.getDirection() == 'H') {
			for (int i = ship.getColumn(); i < (ship.getColumn()+ship.getShipLength()); i++) {
				if (board[ship.getRow()][i].getStatus() != 0) {
					return false;
				}
			}
		}
		return true;
		}
	}
	
	/**
	* Adds the specified ship to the players board
	* @param ship : the ship the play is placing on the board
	*/ 
	
	public void addShip(Ship ship) {
		if (ship.getDirection() == 'H'){ // Horizontal
            for (int i = ship.getColumn(); i < ship.getColumn()+ship.getShipLength(); i++){
            	board[ship.getRow()][i].setStatus(1);
            }
        } else { // Vertical
        	for (int i = ship.getRow(); i < ship.getRow()+ship.getShipLength(); i++){
            	board[i][ship.getColumn()].setStatus(1);
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
		if (board[rowGuess][columnGuess].getStatus() == 1) {
			markHit(rowGuess, columnGuess);
			sunkShips ++;
			return 1;
		} else if (board[rowGuess][columnGuess].getStatus() == 2 || board[rowGuess][columnGuess].getStatus() == 3) {
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
    	board[rowGuess][columnGuess].setStatus(2);
    }
	
	/**
	* Marks the spot that was guessed by the player as a miss, if their opponents
	* ship was not at the guessed location
	* @param rowGuess : the row corresponding to the selected spot on the board
	* @param columnGuess : the column corresponding to the selected spot on the board
	*/
	
    public void markMiss(int rowGuess, int columnGuess) {
    	board[rowGuess][columnGuess].setStatus(3);
    }
    
    public int getSunkShips() {
    	return sunkShips;
    }

	public Site[][] getBoard() {
		
		return this.board;
	}

}
