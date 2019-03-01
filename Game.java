
import java.util.*;

public class Game {
	
	public static Scanner reader = new Scanner(System.in);
 
    public static void main(String[] args) {
    // Players set up ships.
        System.out.println("Welcome to Battleship");
        Player player1 = new Player();
        player1.playerBoard.showBoard();
        Player compPlayer = new Player();
        setupPlayer(player1);
        setupComp(compPlayer);
        
    // Players guess where ships are.
        while (player1.playerBoard.sunkShips != 12 && compPlayer.playerBoard.sunkShips != 12) {
        	playerGuess(compPlayer);
        	compGuess(player1);	
        }    
        
        if (player1.playerBoard.sunkShips == 12) {
        	System.out.println("You lost!");
        } else {
        	System.out.println("You won!");
        }
    }
  
    public static void setupPlayer(Player player) {
    	
       	// Setup players ships
    	for (Ship ship : player.playerShips) {
            System.out.println("\nShip is " + ship.length + " spaces long.");
            ship.row = chooseRow();
            ship.column = chooseColumn();
            ship.direction = chooseDirection();
            // Error check: Out of Bounds ship
            while (ship.inBounds() == false){
            	System.out.println("\nYour ship is out of bounds.");
            	System.out.println("Ship is " + ship.length + " spaces long.");
            	player.playerBoard.showBoard();
            	ship.row = chooseRow();
                ship.column = chooseColumn();
                ship.direction = chooseDirection();
            }
            // Error check: Ship on another ship
            while (player.playerBoard.freeSpace(ship) == false) {
            	System.out.println("\nYour ship is on another ship.");
            	System.out.println("Ship is " + ship.length + " spaces long.");
            	player.playerBoard.showBoard();
            	ship.row = chooseRow();
                ship.column = chooseColumn();
                ship.direction = chooseDirection();
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
    		compShip.column = random.nextInt(10);
    		compShip.row = random.nextInt(10);
    		boolean randomDirection = random.nextBoolean();
    			if (randomDirection = true) {
    				compShip.direction = 'H';
    			} else {
    				compShip.direction = 'V';
    			}
    		while(compShip.inBounds() == false) {
        		compShip.column = random.nextInt(10);
        		compShip.row = random.nextInt(10);
        		randomDirection = random.nextBoolean();
        			if (randomDirection = true) {
        				compShip.direction = 'H';
        			} else {
        				compShip.direction = 'V';
        			}
    		}	
    		System.out.println(compShip.row + "  " + compShip.column  + "  " + compShip.direction);
    		while (compPlayer.playerBoard.freeSpace(compShip) == false) {
        		compShip.column = random.nextInt(10);
        		compShip.row = random.nextInt(10);
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

    public static int chooseRow() {
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
    	return userInputRow;
    }
    
    public static int chooseColumn() {
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
        return (int)(userInputCol - 65);
    }
    
    public static char chooseDirection() {

    	System.out.print("Choose a direction: Horizontal or Vertical (H or V): ");
        char userInputDir = reader.next().charAt(0);
        userInputDir = Character.toUpperCase(userInputDir);
        while (userInputDir != 'H' && userInputDir != 'V') {
        	System.out.print("Not Valid. Choose a direction: Horizontal or Vertical (H or V): ");
        	userInputDir = reader.next().charAt(0);
        	userInputDir = Character.toUpperCase(userInputDir);
        	}
        return userInputDir;
    	
    }

    public static void playerGuess(Player opponent) {
    	System.out.println("Guess where your opponent's ship is.");
    	int rowGuess = chooseRow();
    	int columnGuess = chooseColumn();
    	int checkGuess = opponent.playerBoard.checkGuess(rowGuess, columnGuess);
    	if (checkGuess == 1) {
    		System.out.println("You hit their battleship!");
    	} else if (checkGuess == 0){
    		System.out.println("You missed!");
    	} else if (checkGuess == -1){
    		System.out.println("You already guessed that spot. Guess again.");
    	}
    }
    
    public static void compGuess(Player opponent) {
    	Random random = new Random(); 
    	int rowGuess = random.nextInt(10);
    	int columnGuess = random.nextInt(10);
    	int checkGuess = opponent.playerBoard.checkGuess(rowGuess, columnGuess);
    	while (checkGuess == -1) {
    		rowGuess = random.nextInt(10);
	    	columnGuess = random.nextInt(10);
	    	checkGuess = opponent.playerBoard.checkGuess(rowGuess, columnGuess);
    		}
    	if (checkGuess == 1) {
    		System.out.println("Your ship was hit!");
    	} else if (checkGuess == 0){
    		System.out.println("Your opponent missed!");	
    	}
   
    }
    
}


