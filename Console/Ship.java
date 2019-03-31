// This version of Battleship was written with reference to Java-Battleship
// by Yuval Marcus (github: ymarcus93)

/**
* Class representing a Ship. shipLength, row and column are integers and direction
* is a char.
* Javadoc by Athena McNeil-Roberts
* Code by Kaylee Novakovski
*/

package Console;

public class Ship {

	private int shipLength;
	private int row;
	private int column;
	private char direction;

	public Ship() {
	}
	
	/**
	* Sets the shipLength to the specified length
	* @param length : the length of the ship
	*/
	public Ship(int length) {
		this.setShipLength(length);
	}
	
	/**
	* Checks that the spot the player wishes to place their ship is not
	* off the board
	* @return Returns a boolean
	*/
	public boolean inBounds(){
		if (getDirection() == 'V'){
			if (getShipLength() <= (10 - getRow())){
				return true;
			}
		} else if (getDirection() == 'H'){
				if (getShipLength() <= (10 - getColumn()) ){
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
	     return Integer.toString(getShipLength()) + " " +  (char)(getRow() + 65) + " " + Integer.toString(getColumn()) + " " + getDirection();
	}

	public int getColumn() {
		return column;
	}

	public void setColumn(int column) {
		this.column = column;
	}

	public int getRow() {
		return row;
	}

	public void setRow(int row) {
		this.row = row;
	}

	public char getDirection() {
		return direction;
	}

	public void setDirection(char direction) {
		this.direction = direction;
	}

	public int getShipLength() {
		return shipLength;
	}

	public void setShipLength(int shipLength) {
		if (shipLength > 5 || shipLength < 1 ) {
			this.shipLength = 5;
		} else {
			this.shipLength = shipLength;
		}
	}
	

}
