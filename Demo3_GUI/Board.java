
public class Board {

	// Board is a ten by ten array of sites, each with a status of 0
	public Site[][] board = new Site[10][10];
	public int sunkShips = 0;



	public Board() {
		 for(int row = 0 ; row < 10 ; row++ ){
			 for(int column = 0 ; column < 10 ; column++ ){
				 Site site = new Site();
	            	board[row][column] = site;
	            	site.status = 0;
			 }
		 }
	}
	
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
	
	public boolean freeSpace(Ship ship) {
		if (!ship.inBounds()) {
			return false;
		} else {
			if (ship.direction == 'V') {
				for (int i = ship.row; i < (ship.row + ship.shipLength); i++) {
					if (board[i][ship.column].status != 0) {
						return false;
					}
				}
		} else if (ship.direction == 'H') {
			for (int i = ship.column; i < (ship.column+ship.shipLength); i++) {
				if (board[ship.row][i].status != 0) {
					return false;
				}
			}
		}
		return true;
		}
	}
	
	public void addShip(Ship ship) {
		if (ship.direction == 'H'){ // Horizontal
            for (int i = ship.column; i < ship.column+ship.shipLength; i++){
            	board[ship.row][i].status = 1;
            }
        } else { // Vertical
        	for (int i = ship.row; i < ship.row+ship.shipLength; i++){
            	board[i][ship.column].status = 1;
            }
        }
		
    }
	
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

	public void markHit(int rowGuess, int columnGuess) {
    	board[rowGuess][columnGuess].status = 2;
    }

    public void markMiss(int rowGuess, int columnGuess) {
    	board[rowGuess][columnGuess].status = 3;
    }



}
