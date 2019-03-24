

public class Ship {

	public int shipLength;
	public int row;
	public int column;
	public char direction;

	public Ship() {

	}
	public Ship(int length) {
		this.shipLength = length;
	}

	public boolean inBounds(){

	if (direction == 'V'){
		if (shipLength <= (10 - row)){
			return true;
		}
	} else if (direction == 'H'){
			if (shipLength <= (10 - column) ){
				return true;
			}
		}
		return false;
	}

	public String toString(){
	     return Integer.toString(shipLength) + " " +  (char)(row + 65) + " " + Integer.toString(column) + " " + direction;
	}

}
