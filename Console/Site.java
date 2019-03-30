// Thie version of Battleship was written with reference to Java-Battleship
// by Yuval Marcus (github: ymarcus93)

/**
* Class representing a Site. noShip, shipSite, hit, and miss are Strings and
* status is an int. The mark of the site on the board can be changed dependiing
* on its status.
* Javadoc by Athena McNeil-Roberts
* Code by Kaylee Novakovski
*/

package Console;

public class Site {

	private static final String noShip = "-" ;
	private static final String shipSite = "+";
	private static final String hit = "X";
	private static final String miss = "0";
		
	private int status;
	
	/**
	* Default constructor: Initializes the site status to zero
	*/
	public Site() {
		this.setStatus(0);
	}
	
	/**
	* Prints the mark that corresponds to the status of the site
	*/
	public void siteMark() {
		if (getStatus() == 0) {
			System.out.print(noShip);
		} else if (getStatus() == 1) {
			System.out.print(shipSite);
		} else if (getStatus() == 2) {
			System.out.print(hit);
		} else if (getStatus() == 3){
			System.out.print(miss);
		}
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}
	
}
