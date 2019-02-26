import java.util.Scanner;
import java.util.Random;

public class Player {

  /**
   * Allows player to make a guess to sink their opponents ships. Ensures that
   * the users guess is valid.
   */
  public static void playerTurn(){
    System.out.println("Player 1: its your turn!");
    int numrows = 10;
    int xcoord = -4;
    char ycoord = 'Z';

    do {
    //getting y-coordinate for player turn
    Scanner keyboard = new Scanner(System.in);
    System.out.println("Enter a letter (ycoord): ");
    ycoord = keyboard.next().charAt(0);
    ycoord = Character.toUpperCase(ycoord);

    //getting x-coordinate for player turn
    System.out.println("Enter a number (xcoord): ");
    xcoord = keyboard.nextInt();

    //determine if guess is valid
    if ((ycoord >= 'A' && ycoord <= 'J') &&
      (xcoord >= 0 && xcoord < numrows)){

    } else { //invalid guess
      System.out.println("you cant select that spot (must be with the board)");
     }
   } while((xcoord < 0 || xcoord >= numrows) || (ycoord < 'A' || ycoord > 'J'));
  }

  /**
   * Allows player 2 to make a guess to sink their opponents ships. Ensures that
   * the users guess is valid.
   */
  public static void player2Turn(){
    System.out.println("Player 2: its your turn!");
    int numrows = 10;
    int xcoord = -4;
    char ycoord = 'Z';

    do {
    //getting y-coordinate for player turn
    Scanner keyboard = new Scanner(System.in);
    System.out.println("Enter a letter (ycoord): ");
    ycoord = keyboard.next().charAt(0);
    ycoord = Character.toUpperCase(ycoord);

    //getting x-coordinate for player turn
    System.out.println("Enter a number (xcoord): ");
    xcoord = keyboard.nextInt();

    //determine if guess is valid
    if ((ycoord >= 'A' && ycoord <= 'J') &&
      (xcoord >= 0 && xcoord < numrows)){

    } else { //invalid guess
      System.out.println("you cant select that spot (must be with the board)");
    }

  } while((xcoord < 0 || xcoord >= numrows) || (ycoord < 'A' || ycoord > 'J'));
  }

  /**
	 * generates a valid random guess for computers turn
	 */
    public static void computerTurn(){
      Random r = new Random();
      char ycoord = (char)(r.nextInt(9) + 'a'); // letters a - j
      ycoord = Character.toUpperCase(ycoord);
      int xcoord = (int)(Math.random() * 10 + 1); //max 10, min 1
    }

      public static void main(String[] args) {
        System.out.println("Welcome to BattleShips");
            playerTurn();
            player2Turn();
            computerTurn();
      }

  }
