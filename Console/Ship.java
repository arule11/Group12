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

	/**
	* Gets the column
	* @return Returns an int
	*/
	public int getColumn() {
		return column;
	}

	/**
	* sets the column to the specified column
	* @param column : the specified column
	*/
	public void setColumn(int column) {
		this.column = column;
	}

	/**
	* Gets the row
	* @return Returns an int
	*/
	public int getRow() {
		return row;
	}

	/**
	* sets the row to the specified row
	* @param row : the specified row
	*/
	public void setRow(int row) {
		this.row = row;
	}

	/**
	* Gets the direction
	* @return Returns a char
	*/
	public char getDirection() {
		return direction;
	}

	/**
	* sets the direction to the specified direction
	* @param direction : the specified direction
	*/
	public void setDirection(char direction) {
		this.direction = direction;
	}

	/**
	* Gets the shipLength
	* @return Returns an int
	*/
	public int getShipLength() {
		return shipLength;
	}

	/**
	* sets the shipLength to the specified length or default length if specifeid
	* length is greater than 5 or less than 1
	* @param shipLength : the specified ship length
	*/
	public void setShipLength(int shipLength) {
		if (shipLength > 5 || shipLength < 1 ) {
			this.shipLength = 5;
		} else {
			this.shipLength = shipLength;
		}
	}


}
