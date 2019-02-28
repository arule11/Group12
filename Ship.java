

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
	
	public boolean inBounds() {
		if (direction == 'V') {
			int check = length + row;
			if (check < 10) {
				return true;
			}
		} else if (direction == 'H') {
			int check = length + column;
			if (check < 10) {
				return true;
			}
		}
		return false;
	}
	
	
	public String toString(){
	     return Integer.toString(length) + " " +  (char)(row+65) + " " + Integer.toString(column) + " " + direction;
	}
	
}
