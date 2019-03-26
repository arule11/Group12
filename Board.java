
import java.util.ArrayList;
import java.util.List;

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
				 site.setSiteColumn = column;
				 site.setSiteRow = row;	
				 board[row][column] = site;
				 site.status = 0;
	            	
			 }
		 }
	}

	/**
		* Displays the game board
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
	* @param ship : the ship the play is placing on the board
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
	//Check if area around last shot has any areas that could potentially be hit. If so, adds them to a list of sites 
	//and returns the list.
	public List<Site> checkSurroundingArea(int previousRow, int previousColumn) {
		List<Site> potentialSiteList = new ArrayList<Site>();
		if (board[previousRow + 1][previousColumn].status == 0) {
			potentialSiteList.add(site);
		}
		if (board[previousRow][previousColumn - 1].status == 0) {
			potentialSiteList.add([[previousRow][previousColumn - 1].Site);
		}
		if (board[previousRow][previousColumn + 1].status == 0) {
			potentialSiteList.add([[previousRow][previousColumn + 1].Site);
		}
		if (board[previousRow - 1][previousColumn].status == 0) {
			potentialSiteList.add([previousRow - 1][previousColumn].Site);
		}
		if (potentialSiteList.isEmpty() == false) {
			
		}
		return potentialSiteList;
	}
	
	// Checks board for any spaces containing a ship. Creates and returns a list of sites that can still be hit.
	public List<Site> checkBoardForShip() {
		List<Site> potentialCheatList = new ArrayList<Site>();
		for(int row = 0 ; row < 10 ; row++ ){
			for(int column = 0 ; column < 10 ; column++ ){
				Site site = Site(row, column);
				if (site.getStatus() == 1) {
					potentialCheatList.add(site);
				}
			}
		}
		return potentialCheatList;
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

