
public class Site {

	public static final String shipSite = "+";
	public static final String noShip = "-" ;
	public static final String hit = "1";
	public static final String miss = "0";
		
	public int status;
	
	public Site() {
		this.status = 0;
	}
	
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
	
	
	//setHit
	
//	setMiss

}
