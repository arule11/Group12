
import java.util.Scanner;
import java.util.Random;

public class Game {

	public static Scanner reader = new Scanner(System.in);

    public static void main(String[] args) {

        System.out.println("Welcome to Battleship");
        Player newPlayer = new Player();
        setupPlayer(newPlayer);
    }

    public static void setupPlayer(Player player) {
    	// Setup players ships

				for (Ship ship : player.playerShips) {
						System.out.println("\nShip is " + ship.length + " spaces long.");
						System.out.print("Choose a column (A-J): ");
						char userInputCol = reader.next().charAt(0);
						userInputCol = Character.toUpperCase(userInputCol);
						while ((int)userInputCol > 74 ^ (int)userInputCol < 65) {
							System.out.print("Not valid. Choose a column: (A-J): ");
							userInputCol = reader.next().charAt(0);
							userInputCol = Character.toUpperCase(userInputCol);
						}
            ship.column = (int)(userInputCol - 65);

						System.out.print("Choose a row (0-9): ");
						int userInputRow = reader.nextInt();
						while (userInputRow < 0 ^ userInputRow > 10) {
							System.out.print("Not valid. Choose a column: (0-9): ");
							userInputRow = reader.nextInt();
						}
            ship.row = userInputRow;

            System.out.print("Choose a direction: Horizontal or Vertical (H or V): ");
            char userInputDir = reader.next().charAt(0);
            userInputDir = Character.toUpperCase(userInputDir);
            while (userInputDir != 'H' && userInputDir != 'V') {
            	System.out.print("Not Valid. Choose a direction: Horizontal or Vertical (H or V): ");
            	userInputDir = reader.next().charAt(0);
            	userInputDir = Character.toUpperCase(userInputDir);
            	}
          	ship.direction = userInputDir;
            player.playerBoard.addShip(ship);

    	}
    	// Setup computers ships
    /*	System.out.println("Computer is placing their ships...");
    	Random random = new Random();
    	for (Ship compShip : player.computerShips) {
    		int randomRow = random.nextInt(10) + 1;
    		compShip.row = (char)(randomRow + 65);
    		compShip.column = random.nextInt(10) + 1;
    		boolean randomDirection = random.nextBoolean();
    		if (randomDirection = true)
    			compShip.direction = 'H';
    		else
    			compShip.direction = 'V';
    	//	System.out.println(compShip.row + compShip.column + compShip.direction + compShip.length);
    	//  player.computerBoard.placeShip(compShip);

    	}
    	*/

    }



}
