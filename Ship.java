
/**
* Class representing a Ship. length, row and column are integers and direction
* is a char.
*/

public class Ship {

	public int length;
	public int row;
	public int column;
	public char direction;

	public Ship() {
	}

	/**
	* Sets the length to the specified length
	* @param length : the length of the ship
	*/
	public Ship(int length) {
		this.length = length;
	}

	/**
	* Checks that the spot the player wishes to place their ship is not
	* off the board
	* @return Returns a boolean
	*/
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

	/**
		* Combines length, row, column and direction in a string
		* @return Returns a string combination of length, row, column and direction
		*/
	public String toString(){
	     return Integer.toString(length) + " " +  (char)(row + 65) + " " + Integer.toString(column) + " " + direction;
	}

}
