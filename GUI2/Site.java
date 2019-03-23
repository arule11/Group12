// Thie version of Battleship was written with reference to Java-Battleship
// by Yuval Marcus (github: ymarcus93)

/**
* Class representing a Site. noShip, shipSite, hit, and miss are Strings.
* The mark of the site on the board can be changed dependiing on its status.
*/

public class Site {

	public static final String noShip = "-" ;
	public static final String shipSite = "+";
	public static final String hit = "X";
	public static final String miss = "0";

	public int status;

	/**
	* Default constructor: Initializes the site status to zero
	*/
	public Site() {
		this.status = 0;
	}

	/**
	* Prints the mark that corresponds to the status of the site
	*/
	public void siteMark() {
		if (status == 0) {
			System.out.print(noShip);
		} else if (status == 1) {
			System.out.print(shipSite);
		} else if (status == 2) {
			System.out.print(hit);
		} else {
			System.out.print(miss);
		}
	}

}
