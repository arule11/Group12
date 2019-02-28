
import java.util.*;

public class Game {
	
	public static Scanner reader = new Scanner(System.in);
 
    public static void main(String[] args) {
    // Players set up ships.
        System.out.println("Welcome to Battleship");
        Player newPlayer = new Player();
        newPlayer.playerBoard.showBoard();
        Player compPlayer = new Player();
        Player testPlayer = new Player();
        setupPlayer(newPlayer);
        //setupComp(compPlayer);
        setupTest(testPlayer);
        
    // Players guess where ships are.
        
    }
    
    public static void setupPlayer(Player player) {
    	
       	// Setup players ships
    	for (Ship ship : player.playerShips) {
            System.out.println("\nShip is " + ship.length + " spaces long.");
            chooseRow(ship);
            chooseColumn(ship);
            chooseDirection(ship);
            // Error check: Out of Bounds ship
            while (ship.inBounds() == false){
            	System.out.println("\nYour ship is out of bounds.");
            	System.out.println("Ship is " + ship.length + " spaces long.");
            	player.playerBoard.showBoard();
            	chooseRow(ship);
                chooseColumn(ship);
                chooseDirection(ship);
            }
            // Error check: Ship on another ship
            while (player.playerBoard.freeSpace(ship) == false) {
            	System.out.println("\nYour ship is on another ship.");
            	System.out.println("Ship is " + ship.length + " spaces long.");
            	player.playerBoard.showBoard();
            	chooseRow(ship);
                chooseColumn(ship);
                chooseDirection(ship);
            }
            
            player.playerBoard.addShip(ship);
            player.playerBoard.showBoard();
    	}
    }
    
    // Test computer with hard set ships
    public static void setupTest(Player testPlayer) {
    	System.out.println("Computer is placing their ships...");
    	int increase = 0;
    	for (Ship compShip : testPlayer.playerShips) {
    		compShip.row = increase;
    		compShip.column = increase;  
    		compShip.direction = 'H';
    		increase += 2;
    		testPlayer.playerBoard.addShip(compShip);
       	}
    	testPlayer.playerBoard.showBoard();
    }
    
    // Random computer
    public static void setupComp(Player compPlayer) {
    	// Setup computers ships
    	System.out.println("Computer is placing their ships...");
    	Random random = new Random(); 
    	for (Ship compShip : compPlayer.playerShips) {
    		compShip.column = random.nextInt(10) + 1;
    		compShip.row = random.nextInt(10) + 1;
    		boolean randomDirection = random.nextBoolean();
    			if (randomDirection = true) {
    				compShip.direction = 'H';
    			} else {
    				compShip.direction = 'V';
    			}
    		System.out.println(compShip.row + "  " + compShip.column  + "  " + compShip.direction);
    		while(compShip.inBounds() == false) {
        		compShip.column = random.nextInt(10) + 1;
        		compShip.row = random.nextInt(10) + 1;
        		randomDirection = random.nextBoolean();
        			if (randomDirection = true) {
        				compShip.direction = 'H';
        			} else {
        				compShip.direction = 'V';
        			}
    		}	
    		System.out.println(compShip.row + "  " + compShip.column  + "  " + compShip.direction);
    		while (compPlayer.playerBoard.freeSpace(compShip) == false) {
        		compShip.column = random.nextInt(10) + 1;
        		compShip.row = random.nextInt(10) + 1;
        		randomDirection = random.nextBoolean();
        			if (randomDirection = true) {
        				compShip.direction = 'H';
        			} else {
        				compShip.direction = 'V';
        			}		
    		}
    		compPlayer.playerBoard.addShip(compShip);
    	}
    	compPlayer.playerBoard.showBoard();
    }

    public static void chooseRow(Ship ship) {
    	System.out.print("Choose a row (1-10): ");
    	boolean check = true;
    	int userInputRow = 0;
        while(check) {
        	try {
                userInputRow = reader.nextInt();
                userInputRow -= 1;
                while (userInputRow < 0 ^ userInputRow > 9) {
                	System.out.print("Number must be between 1 and 10. Choose a row: (1-10): ");
                	userInputRow = reader.nextInt();
                }
                check = false;
        	  } catch (InputMismatchException e) {
                  System.out.println("Not a number. Choose a row (1-10): ");
                  reader.nextLine();
              }
        }
    	ship.row = userInputRow;
    }
    
    public static void chooseColumn(Ship ship) {
        System.out.print("Choose a column (A-J): ");
        boolean check = true;
        char userInputCol = 'A';
        while(check) {
        	try {
        		userInputCol = reader.next().charAt(0);
        		
        		while ((int)userInputCol > 106 ^ (int)userInputCol < 97) {
        	        System.out.print("Not a valid letter. Choose a column: (A-J): ");
        	        userInputCol = reader.next().charAt(0);
        		}
        		check = false;
        	} catch (InputMismatchException e) {
                System.out.println("Not a letter. Choose a column: (A-J): ");
                reader.nextLine();
        	}
        }
        userInputCol = Character.toUpperCase(userInputCol);
        ship.column = (int)(userInputCol - 65);
    }
    
    public static void chooseDirection(Ship ship) {

    	System.out.print("Choose a direction: Horizontal or Vertical (H or V): ");
        char userInputDir = reader.next().charAt(0);
        userInputDir = Character.toUpperCase(userInputDir);
        while (userInputDir != 'H' && userInputDir != 'V') {
        	System.out.print("Not Valid. Choose a direction: Horizontal or Vertical (H or V): ");
        	userInputDir = reader.next().charAt(0);
        	userInputDir = Character.toUpperCase(userInputDir);
        	}
        ship.direction = userInputDir;
    	
    }

   
	
    



}


