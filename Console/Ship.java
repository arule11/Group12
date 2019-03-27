// Thie version of Battleship was written with reference to Java-Battleship
// by Yuval Marcus (github: ymarcus93)

/**
* Class representing a Ship. shipLength, row and column are integers and direction
* is a char.
* Javadoc by Athena McNeil-Roberts
* Code by Kaylee Novakovski
*/

package Console;

public class Ship {

	public int shipLength;
	public int row;
	public int column;
	public char direction;

	public Ship() {
	}
	
	/**
	* Sets the shipLength to the specified length
	* @param length : the length of the ship
	*/
	public Ship(int length) {
		this.shipLength = length;
	}
	
	/**
	* Checks that the spot the player wishes to place their ship is not
	* off the board
	* @return Returns a boolean
	*/
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
	
	/**
	* Combines length, row, column and direction in a string
	* @return Returns a string combination of length, row, column and direction
	*/
	public String toString(){
	     return Integer.toString(shipLength) + " " +  (char)(row + 65) + " " + Integer.toString(column) + " " + direction;
	}

}
