import java.util.Scanner;
import java.util.Random;

public class Player {


  public static void playerTurn(){
    System.out.println("its your turn!");

    //getting y-coordinate for player turn
    Scanner keyboard = new Scanner(System.in);
    System.out.println("Enter a letter (ycoord): ");
    char ycoord = keyboard.next().charAt(0);
    ycoord = Character.toUpperCase(ycoord);

    //getting x-coordinate for player turn
    System.out.println("Enter a number (xcoord): ");
    int xcoord = keyboard.nextInt();

    //determine if guess is valid
    if (((ycoord >= 65 && ycoord <= 74) && ycoord < numColums) &&
      (xcoord >= 0 && xcoord < numrows)){

      if (board[xcoord][ycoord] == "x"){
        System.out.println("you hit a ship!");
        board[xcoord][ycoord] = "⬤";

      } else if (board[xcoord][ycoord] == " "){
        System.out.println("sorry, you missed");
        board[xcoord][ycoord] = "〇";
      }
    } else { //invalid guess
      System.out.println("you cant select that spot (must be with the board)");
    }
  }

  public static void player2Turn(){
    System.out.println("its your turn!");

    //getting y-coordinate for player turn
    Scanner keyboard = new Scanner(System.in);
    System.out.println("Enter a letter (ycoord): ");
    char ycoord = keyboard.next().charAt(0);
    ycoord = Character.toUpperCase(ycoord);

    //getting x-coordinate for player turn
    System.out.println("Enter a number (xcoord): ");
    int xcoord = keyboard.nextInt();

    //determine if guess is valid
    if (((ycoord >= 65 && ycoord <= 74) && ycoord < numColums) &&
      (xcoord >= 0 && xcoord < numrows)){

      if (board[xcoord][ycoord] == "x"){
        System.out.println("you hit a ship!");
        board[xcoord][ycoord] = "⬤";

      } else if (board[xcoord][ycoord] == " "){
        System.out.println("sorry, you missed");
        board[xcoord][ycoord] = "〇";
      }
    } else { //invalid guess
      System.out.println("you cant select that spot (must be with the board)");
    }
  }


    public static void computerTurn(){
      Random r = new Random();
      char ycoord = (char)(r.nextInt(9) + 'a');
      ycoord = Character.toUpperCase(ycoord);
      int xcoord = (int)(Math.random() * 10 + 1); //max 10, min 1

      // valid guess
      if (((ycoord >= 65 && ycoord <= 74) && ycoord < numColums) &&
        (xcoord >= 0 && xcoord < numrows)){

          if (board[xcoord][ycoord] == "+"){
            System.out.println("you hit a ship!");
            board[xcoord][ycoord] = "⬤";

          } else if (board[xcoord][ycoord] == " "){
            System.out.println("sorry, you missed");
            board[xcoord][ycoord] = "〇";
          }
        }
    }



      public static void main(String[] args) {
        System.out.println("Welcome to BattleShips");

        if (twoPlayer == true){
          while(!gameOver){
            playerTurn();
            player2Turn();
          }
        } else if (singlePlayer == true){
          while(!gameOver){
            playerTurn();
            computerTurn();
          }
        }
      }


}
