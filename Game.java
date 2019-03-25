// This version of Battleship was written with reference to Java-Battleship
// by Yuval Marcus (github: ymarcus93)

/**
* Class representing a Game. points is of type integer. The game can be played,
* allowing players to place their ships on the board, and guess where their
* opponents ships are in order to win the game.
*/

import java.util.*;

public class Game {
	public static int points = 12;
	public static Scanner reader = new Scanner(System.in);
	public static int guessCount = 0;
	public static boolean previouslyHit = false;
	
    public static void main(String[] args) {
    	// Welcome message, difficulty selection and validation.
        System.out.println("Welcome to Battleship. Choose a difficulty level: Type 1 for easy, 2 for medium, 3 for hard.");
        int difficulty = reader.nextInt();
        while (difficulty < 1 || difficulty > 3) {
        	System.out.println("Incorrect input, please try again. Choose a difficulty level: Type 1 for easy, 2 for medium, 3 for hard.");
        	difficulty = reader.nextInt();
        }
        if (difficulty == 1) {
    		System.out.println("Difficulty set to easy.");
    	}
    	if (difficulty == 2) {
    		System.out.println("Difficulty set to medium.");
    	}
    	if (difficulty == 3) {
    		System.out.println("Difficulty set to hard.");
        }
        	
        // Players set up their ships.
        Player player1 = new Player();
        player1.playerBoard.showBoard();
        Player compPlayer = new Player();
        setupPlayer(player1);
        setupComp(compPlayer);

    	// Players guess where ships are.
        while (player1.playerBoard.sunkShips != points && compPlayer.playerBoard.sunkShips != points) {
        	playerGuess(compPlayer, player1);
        	if (difficulty == 1) {
        		compGuess(player1, compPlayer);
        	}
        	if (difficulty == 2) {
        		smartGuess(player1, compPlayer);
        	}
        	if (difficulty == 3) {
        		guessCount++;
        		if (guessCount % 4 == 0) {
        			cheatGuess(player1, compPlayer);
        		}
        		else {smartGuess(player1, compPlayer);}
        	}
        }
   			// Game ends when all of one players ships are sunk.
        if (player1.playerBoard.sunkShips == points) {
        	System.out.println("You lost!");
        } else {
        	System.out.println("You won!");
        }
    }

		/**
		* Player selects spots on the their board where they wish to place their
		* ships
		* @param player : the Player
		*/
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

		/**
		* Computer randomly selects spots on their board to place their ships
		* @param compPlayer : the computer Player
		*/
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
    }

		/**
		* Prompts the player to choose the row where they wish to place their ship
		* @return Returns an int
		*/
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

		/**
		* Prompts the player to choose the column where they wish to place their ship
		* @return Returns an int
		*/
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

		/**
		* Prompts the player to choose the direction of the ship they are placing
		* @return Returns a char
		*/
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

		/**
		* Allows player to guess a spot on the board where they believe their opponents
		* ship may be
		* @param opponent : the guessing players opponent
		* @param guessingPlayer : the Player who is guessing
		*/
    public static void playerGuess(Player opponent, Player guessingPlayer) {
    	System.out.println("Here's your opponent's board:");
    	guessingPlayer.oppBoard.showBoard();
    	System.out.println("Guess where your opponent's ship is.");
    	int rowGuess = chooseRow();
    	int columnGuess = chooseColumn();
    	int checkGuess = opponent.playerBoard.checkGuess(rowGuess, columnGuess);
    	if (checkGuess == 1) {
    		System.out.println("You hit their battleship!");
    		guessingPlayer.oppBoard.markHit(rowGuess, columnGuess);
    	} else if (checkGuess == 0){
    		System.out.println("You missed!");
    		guessingPlayer.oppBoard.markMiss(rowGuess, columnGuess);
    	} else if (checkGuess == -1){
    		System.out.println("You already guessed that spot. Guess again.");
    	}
    	System.out.println("Here's your opponent's board:");
    	guessingPlayer.oppBoard.showBoard();
    }


		/**
		* Computer randomly selects spot on the board where their opponents ships may be
		* @param opponent : the computers opponent
		* @param compPlayer : the computer Player
		*/
    public static void compGuess(Player opponent, Player compPlayer) {
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
    	System.out.println("Here's your board:");
    	opponent.playerBoard.showBoard();
    }
    
    public static void smartGuess(Player opponent, Player compPlayer) {
    	int previousRow = 0;
    	int previousColumn = 0;
    	List<Site> potentialSiteList = new ArrayList<Site>();
    	
    	if (previouslyHit == true) {
    		//Check if area around last shot has any areas that could potentially be hit.
    		//if ([previousRow + 1][previousColumn].status == 0) {
    			//potentialSiteList.add([[previousRow + 1][previousColumn].Site);
    		//}
    		//if ([previousRow][previousColumn - 1].status == 0) {
    			//potentialSiteList.add([[previousRow][previousColumn - 1].Site);
    		//}
    		//if ([previousRow][previousColumn + 1].status == 0) {
    			//potentialSiteList.add([[previousRow][previousColumn + 1].Site);
    		//}
    		//if ([previousRow - 1][previousColumn].status == 0) {
    			//potentialSiteList.add([[previousRow - 1][previousColumn].Site);
    		//}
    		//if (potentialSiteList.isEmpty() == false) {
    			// Choose a random site from this list and shoot at this site. TO BE COMPLETED
    			
    		}
    		
    		else {
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
    		        previouslyHit = true;
    		        previousRow = rowGuess;
    		        previousColumn = columnGuess;
    		        	
    		     } else if (checkGuess == 0){
    		       System.out.println("Your opponent missed!");
    		     }
    		}
       
        System.out.println("Here's your board:");
        opponent.playerBoard.showBoard();
    	 }
    
    public static void cheatGuess(Player opponent, Player compPlayer) {
    	List<Site> potentialCheatList = new ArrayList<Site>();
    	
    	// Check each site on the board for a site that 
    	for(int row = 0 ; row < 10 ; row++ ){
			 for(int column = 0 ; column < 10 ; column++ ){
				 //if (site.status == 2) {
					 //potentialCheatList.add(Site[row][col]);
				 }
				 }
    	//Randomly select a site from this list and shoot at it. TO BE COMPLETED
    	
    }

}

