

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


	public int getRow() {
    return row;
  }

  public int getColumn() {
    return column;
  }

  public char getDirection(){
    return direction;
  }

	public int getLength(){
		return length;
	}


	public String toString(){
	     return Integer.toString(length) + " " +  (char)(row+65) + " " + Integer.toString(column) + " " + direction;
	}

}
