/**
 * Class for the Ship object. Ships have a name, length, position on the board, and orientation. They can take hits
 * and be sunk. 
 * @author Liam Langill
 * Code used as a reference: https://codereview.stackexchange.com/questions/161680/oop-battleship-console-game-in-java/161695
 *  
 *
 */
public class Ship {
	
	private String name;
	private int length;
	private int shipXcoord;
	private int shipYcoord;
	private boolean isSunk;
	private int hitsTaken;
	private boolean orientation;
	
	public Ship(String name, int length) {
		this.name = name;
		this.length = length;
		this.isSunk = false;
		this.hitsTaken = 0;

	}
	
	public String getName() {
		return name;
	}
	
	public int getLength() {
		return length;
	}
	
	public int getHitsTaken() {
		return hitsTaken;
	}
	
	public int getShipXcoord() {
		return shipXcoord;
	}
	
	public int getShipYcoord() {
		return shipYcoord;
	}
	
	public boolean getOrientation() {
		return orientation;
	}
	
	public boolean getIsSunk() {
		return isSunk;
	}
	
	
	/**
	 * 
	 * @param shipXcoord The X coordinate of the bottom or left edge of the ship being placed
	 * @param shipYcoord The Y coordinate of the bottom or left edge of the ship being placed
	 */
	public void setLocation(int inXcoord, int inYcoord) {
		this.shipXcoord = inXcoord;
		this.shipYcoord = inYcoord;
	} 
	
	/**
	 * When a ship is hit, this method checks if the ship can take any more hits.
	 * If it cannot take any more hits, the ship is sunk.
	 */
	public void shipWasHit() {
		hitsTaken = hitsTaken + 1;
		System.out.println("You hit the " + name);
		if(hitsTaken == length) {
			isSunk = true;
			System.out.println("You sunk the " + name);
		}
	}
}
