// This version of Battleship was written with reference to Java-Battleship
// by Yuval Marcus (github: ymarcus93)

/**
* Class representing a Game. points is of type integer. The console game can be played,
* allowing players to place their ships on the board, and guess where their
* opponent's ships are in order to win the game.
* Javadoc by Athena McNeil-Roberts
* Code by Kaylee Novakovski
*/

package Console;

import java.util.*;

public class Game {
	private static int points = 14;
	private static int guessCount = 0;

	private static Scanner reader = new Scanner(System.in);

  public static void main(String[] args) {
    // Welcome message and difficulty selection.
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
    player1.getPlayerBoard().showBoard();
    Player compPlayer = new Player();
    setupPlayer(player1);
    setupComp(compPlayer);
    System.out.println("Here's your opponent's board:");
  	player1.getOppBoard().showBoard();
  	
    // Players guess where ships are.
    
     //  *** Use this code when smartGuess is working ***
    while (player1.getPlayerBoard().getSunkShips() != points && compPlayer.getPlayerBoard().getSunkShips() != points) {
    	int checkGuess = playerGuess(compPlayer, player1);
    	if (checkGuess != -1) {
    		if (difficulty == 1) {
        		compGuess(player1, compPlayer);
        	}
        	if (difficulty == 2) {
        		smartGuess(player1, player1.getPlayerBoard());
        	}
        	if (difficulty == 3) {
        		guessCount++;
        		if (guessCount % 4 == 0) {
        			makeRightGuess(player1, player1.getPlayerBoard());
        		}
        		else {smartGuess(player1, player1.getPlayerBoard());}
        	}
    	}
    }
    
    /**
    while (player1.getPlayerBoard().getSunkShips() != points && compPlayer.getPlayerBoard().getSunkShips() != points) {
    	int checkGuess = playerGuess(compPlayer, player1);
    	if (checkGuess != -1) {
    		if (difficulty == 1) {
        		compGuess(player1, compPlayer);
        	}
        	if (difficulty == 2) {
        		guessCount++;
        		if (guessCount % 4 == 0) {
        			makeRightGuess(player1, player1.getPlayerBoard());
        		}
        		else {compGuess(player1, compPlayer);}
        	}
        	if (difficulty == 3) {
        		guessCount++;
        		if (guessCount % 2 == 0) {
        			makeRightGuess(player1, player1.getPlayerBoard());
        		}
        		else {compGuess(player1, compPlayer);}
        	}
    	}
    }
    */
    

   // Game ends when all of one players ships are sunk.
    if (player1.getPlayerBoard().getSunkShips() == points) {
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
    for (Ship ship : player.getPlayerShips()) {
      System.out.println("\nShip is " + ship.getShipLength() + " spaces long.");
      ship.setRow(chooseRow());
      ship.setColumn(chooseColumn());
      ship.setDirection(chooseDirection());
            // Error check: Out of Bounds ship
      while (ship.inBounds() == false){
        System.out.println("\nYour ship is out of bounds.");
        System.out.println("Ship is " + ship.getShipLength() + " spaces long.");
        player.getPlayerBoard().showBoard();
        ship.setRow(chooseRow());
        ship.setColumn(chooseColumn());
        ship.setDirection(chooseDirection());
      }
            // Error check: Ship on another ship
      while (player.getPlayerBoard().freeSpace(ship) == false) {
        System.out.println("\nYour ship is on another ship.");
        System.out.println("Ship is " + ship.getShipLength() + " spaces long.");
        player.getPlayerBoard().showBoard();
        ship.setRow(chooseRow());
        ship.setColumn(chooseColumn());
        ship.setDirection(chooseDirection());
      }

      player.getPlayerBoard().addShip(ship);
      player.getPlayerBoard().showBoard();
    }
  }

	/**
	* Computer randomly selects spots on their board to place their ships
	* @param compPlayer : the computer Player
	*/
  public static void setupComp(Player compPlayer) {
    // Setup computers ships
  System.out.println("Computer is placing their ships...");
  Random random = new Random();
  for (Ship compShip : compPlayer.getPlayerShips()) {
    compShip.setColumn(random.nextInt(10));
    compShip.setRow(random.nextInt(10));
    int randomDirection = random.nextInt(2);
    if (randomDirection ==0) {
    	compShip.setDirection('H');
    } else {
    	compShip.setDirection('V');
    }
    while(!compShip.inBounds()) {
      compShip.setColumn(random.nextInt(10));
      compShip.setRow(random.nextInt(10));
      randomDirection = random.nextInt(2);
      if (randomDirection == 0) {
        compShip.setDirection('H');
      } else {
        compShip.setDirection('V');
      }
    }
    while (!compPlayer.getPlayerBoard().freeSpace(compShip)) {
      compShip.setColumn(random.nextInt(10));
      compShip.setRow(random.nextInt(10));
      randomDirection = random.nextInt(2);
      if (randomDirection==0) {
        compShip.setDirection('H');
      } else {
        compShip.setDirection('V');
        }
    	}
    	compPlayer.getPlayerBoard().addShip(compShip);
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
	* Allows player to guess a spot on the board where they believe their opponent's
	* ship may be
	* @param opponent : the guessing player's opponent
	* @param guessingPlayer : the Player who is guessing
	*/
  public static int playerGuess(Player opponent, Player guessingPlayer) {
  	System.out.println("Guess where your opponent's ship is.");
  	int rowGuess = chooseRow();
  	int columnGuess = chooseColumn();
  	int checkGuess = opponent.getPlayerBoard().checkGuess(rowGuess, columnGuess);
  	if (checkGuess == 1) {
    	System.out.println("You hit their battleship!");
    	guessingPlayer.getOppBoard().markHit(rowGuess, columnGuess);
    } else if (checkGuess == 0){
    		System.out.println("You missed!");
    		guessingPlayer.getOppBoard().markMiss(rowGuess, columnGuess);
  	} else if (checkGuess == -1){
    		System.out.println("You already guessed that spot. Guess again.");
    }
    System.out.println("Here's your opponent's board:");
    guessingPlayer.getOppBoard().showBoard();
    return checkGuess;
  }

  /**
	* Computer randomly selects spot on the board where their opponent's ships may be
	* @param opponent : the computer's opponent
	* @param compPlayer : the computer Player
	*/
  public static void compGuess(Player opponent, Player compPlayer) {
  	Random random = new Random();
  	int rowGuess = random.nextInt(10);
  	int columnGuess = random.nextInt(10);
  	int checkGuess = opponent.getPlayerBoard().checkGuess(rowGuess, columnGuess);
  	while (checkGuess == -1) {
    	rowGuess = random.nextInt(10);
	    columnGuess = random.nextInt(10);
	   	checkGuess = opponent.getPlayerBoard().checkGuess(rowGuess, columnGuess);
    }
    if (checkGuess == 1) {
    	System.out.println("Your ship was hit!");
    } else if (checkGuess == 0){
    	System.out.println("Your opponent missed!");
    }
  	System.out.println("Here's your board:");
  	opponent.getPlayerBoard().showBoard();
  }
  
  /**
  * Computer makes a smarter than random guess where their opponent's ships may be
  * @param opponent : the computer's opponent
  * @param board: the computer opponent's board
  */
  private static int goodRow = -1;
  private static int goodCol = -1;
  public static void smartGuess(Player opponent, Board board) {
	  Random random = new Random();
		int vertOrHori = random.nextInt(2);
		int upOrDown = random.nextInt(2);
		int leftOrRight = random.nextInt(2);
		int rowGuess = goodRow;
		int colGuess = goodCol;
		
		if (vertOrHori == 1) {
			if (upOrDown == 1 && goodCol + 1 < 9) {
				colGuess++;
			} else if (upOrDown == 1 && goodCol + 1 > 9) {
				colGuess--;
			} else if (upOrDown == 0 && goodCol-1 > 0) {
				colGuess--;
			} else if (upOrDown == 0 && goodCol-1 < 0){
				colGuess++;
			}
		} else {
			if (leftOrRight == 1 && goodRow+1 < 9) {
				rowGuess++;
			} else if (goodRow + 1 > 9){
				rowGuess--;
			} else if (leftOrRight == 0 && goodRow - 1 > 0) {
				rowGuess--;
			} else {
				rowGuess++;
			}
		}
		
		int aiGuess = board.checkGuess(rowGuess, colGuess);
		
		while (aiGuess == -1) {
			vertOrHori = random.nextInt(2);
			upOrDown = random.nextInt(2);
			leftOrRight = random.nextInt(2);

			if (vertOrHori == 1) {
				if (upOrDown == 1 && goodCol + 1 < 9) {
					colGuess++;
				} else if (upOrDown == 1 && goodCol + 1 > 9) {
					colGuess--;
				} else if (upOrDown == 0 && goodCol-1 > 0) {
					colGuess--;
				} else if (upOrDown == 0 && goodCol-1 < 0){
					colGuess++;
				}
			} else {
				if (leftOrRight == 1 && goodRow+1 < 9) {
					rowGuess++;
				} else if (goodRow +1 > 9){
					rowGuess--;
				} else if (leftOrRight == 0 && goodRow - 1 > 0) {
					rowGuess--;
				} else {
					rowGuess++;
				}
			}
			aiGuess = board.checkGuess(rowGuess, colGuess);

			}
				
		if (aiGuess == 1) {
			System.out.println("Your battleship was hit!");
			goodRow = rowGuess;
			goodCol = colGuess;
			System.out.println("Here's your board:");
			opponent.getPlayerBoard().showBoard();
		} 
		else {
			System.out.println("Your opponent missed!");	
			goodRow = -1;
			goodCol = -1;
			}
		System.out.println("Here's your board:");
		opponent.getPlayerBoard().showBoard();
	}
  
  /**
   * Computer selects a spot on their opponent's board where a ship is guaranteed to be
   * @param opponent : the computer's opponent
   * @param board : the computer opponent's board
   */
  public static void makeRightGuess(Player opponent, Board board) {
		int rightRowGuess = 0;
		int rightColumnGuess = 0;
		int aiGuess;
		
		for(int row = 0 ; row < 10 ; row++ ){
			 for(int column = 0 ; column < 10 ; column++ ){
				 if (board.getBoard()[row][column].getStatus() == 1) {
					 rightRowGuess = row;
					 rightColumnGuess = column;
					 column = 10;
					 row = 10;
				 }
			 }
		 }
		
		aiGuess = board.checkGuess(rightRowGuess, rightColumnGuess);
		System.out.println("Your battleship was hit!");
		opponent.getPlayerBoard().showBoard();
	}
    
}

