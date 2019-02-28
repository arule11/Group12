
public class Board {
	// Board is a ten by ten array of sites, each with a status of 0
	public Site[][] board = new Site[10][10];

	public Board() {
	}

	public void initBoard() {
		 for(int row = 0 ; row < 10 ; row++ ){
			 for(int column=0 ; column < 10 ; column++ ){
				 Site site = new Site();
	            	board[row][column] = site;
	            	site.status = 0;
			 }
		 }
		 showBoard();

	}

	public void showBoard(){

		System.out.println("\tA \tB \tC \tD \tE \tF \tG \tH \tI \tJ");
        System.out.println();

        for(int row = 0 ; row < 10 ; row++ ){
            System.out.print((row) +"");
            for(int column = 0 ; column < 10 ; column++ ){
            	System.out.print("\t");
            	board[row][column].siteMark();
            }
            System.out.println();
        }
	}

	public void addShip(Ship ship) {
		int row = ship.getRow();
		int col = ship.getColumn();
		char dir = ship.getDirection();
		int size = ship.getLength();


		if (dir == 'H'){
			for (int i = 0; i < size; i++){
				board[row][col + i].status = 1;
			}
		} else {
			for (int j = 0; j < size; j++){
				board[row + j][col].status = 1;
			}
		}
		showBoard();
    }
}
