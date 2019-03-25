
/**
* Class representing a Site. noShip, shipSite, hit, and miss are Strings.
* The mark of the site on the board can be changed depending on its status.
*/

public class Site {

	public static final String noShip = "-" ;
	public static final String shipSite = "+";
	public static final String hit = "X";
	public static final String miss = "0";

	public int status;
	public int siteRow;
	public int siteColumn;

	/**
	* Default constructor: Initializes the site status to zero
	*/
	public Site() {
		this.status = 0;
		
	}
	
	public Site(int row, int column) {
		this.siteRow = row;
		this.siteColumn = column;
		getStatus();
	}
	
	public int getSiteRow() {
		return this.siteRow;
	}
	
	public void setSiteRow(int row) {
		this.siteRow = row;
	}
	
	public int getSiteColumn() {
		return this.siteColumn;
	}
	
	public void setSiteColumn(int column) {
		this.siteColumn = column;
	}
	
	public int getStatus() {
		return this.status;
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
