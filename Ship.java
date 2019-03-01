

public class Ship {

	public int length;
	public int row;
	public int column;
	public char direction;

	public Ship() {

	}
	public Ship(int length) {
		this.length = length;
	}

	public boolean inBounds(){

	if (direction == 'V'){
		if (length <= (10 - row)){
			return true;
		}
	} else if (direction == 'H'){
			if (length <= (10 - column) ){
				return true;
			}
		}
		return false;
	}

	public String toString(){
	     return Integer.toString(length) + " " +  (char)(row + 65) + " " + Integer.toString(column) + " " + direction;
	}

}
