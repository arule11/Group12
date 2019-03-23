// Code adapted from github user AlmasB 


import java.util.Random;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseButton;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class BattleshipMain extends Application {

    private boolean running = false;
    private Board enemyBoard, playerBoard;

    private int shipsToPlace = 5;

    private boolean enemyTurn = false;

    private Random random = new Random();
    
    Label gameplay;
    BorderPane root;

    private Parent createContent() {
        root = new BorderPane();
        root.setPrefSize(500, 500);
        root.setPadding(new Insets(20, 20, 20, 20));
        

        enemyBoard = new Board(true, event -> {

            if (!running)
                return;

            Cell cell = (Cell) event.getSource();
            if (cell.wasShot)
                return;

            enemyTurn = !cell.shoot();

            if (enemyBoard.ships == 0) {
                gameplay.setText("YOU WIN");
                gameOver();
            }

            if (enemyTurn)
                enemyMove();
        });

        playerBoard = new Board(false, event -> {
            if (running)
                return;

            Cell cell = (Cell) event.getSource();
            if (playerBoard.placeShip(new Ship(shipsToPlace, event.getButton() == MouseButton.PRIMARY), cell.x, cell.y)) {
                if (--shipsToPlace == 0) {
                    startGame();
                }
            }
        });
        
        HBox boards = new HBox(25, enemyBoard, playerBoard);
        boards.setAlignment(Pos.TOP_LEFT);
        Label enemy = new Label("Enemy Board");
        Label player = new Label("Player Board");
        HBox toolbar = new HBox(25, enemy, player);
        toolbar.setAlignment(Pos.CENTER);
        VBox game = new VBox(toolbar, boards);
        root.setLeft(game);
        gameplay = new Label("Welcome to Battleship! \nPrepare your ships for battle. \nRight click sets ships horizontal. \nLeft click sets ships vertical.");
        gameplay.setAlignment(Pos.TOP_RIGHT);
        root.setRight(gameplay);
        
        return root;
    }
    
    public void gameOver() {
    	gameplay.setText("Would you like to play again?");
    	Button playAgain = new Button("Play Again");
    	playAgain.setAlignment(Pos.BOTTOM_RIGHT);
    	root.setBottom(playAgain);
    		
    }
    

    private void enemyMove() {
        while (enemyTurn) {
            int x = random.nextInt(10);
            int y = random.nextInt(10);

            Cell cell = playerBoard.getCell(x, y);
            if (cell.wasShot)
                continue;

            enemyTurn = cell.shoot();

            if (playerBoard.ships == 0) {
                gameplay.setText("YOU LOSE");
                enemyTurn = false;
                gameOver();
            }
        }
    }

    private void startGame() {
        // place enemy ships
    	gameplay.setText("Choose a square on your\nenemy's board to fire a missile.");
            
        int type = 5;

        while (type > 0) {
            int x = random.nextInt(10);
            int y = random.nextInt(10);

            if (enemyBoard.placeShip(new Ship(type, Math.random() < 0.5), x, y)) {
                type--;
            }
        }

        running = true;
    }
    

    @Override
    public void start(Stage primaryStage) throws Exception {
        Scene scene = new Scene(createContent());
        primaryStage.setTitle("Battleship");
        primaryStage.setScene(scene);
        primaryStage.setResizable(false);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
