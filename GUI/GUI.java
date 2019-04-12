//Based on: private dropbox CPSC219_Examples> Lecture16_OODesign by Nathaly Verwaal >

/**
 * Class representing a Battleship game application. buttonsPlayer and buttonsOpp are
 * a list of Buttons. message and shipMessage are Text, hori and vert are Buttons,
 * allBoards is a GridPane and direction is a HBox. Users can play battle ship through
 * the application. The application displays the Players and their opponents board.
 * 
 * Javadoc by Athena McNeil-Roberts
 * Code by Kaylee Novakovski
 */

package GUI;

import java.io.FileNotFoundException;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.layout.*;
import javafx.scene.media.AudioClip;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.control.Button;
import javafx.event.*;
import javafx.geometry.Insets;

public class GUI extends Application {
		
		private Button playAgain = new Button();
		private Button[][] buttonsPlayer = new Button[10][10];
		private Button[][] buttonsOpp = new Button[10][10];
		private Label message = new Label();
		private Label shipMessage = new Label();
		private Button hori = new Button();
		private Button vert = new Button();
		private GridPane allBoards = new GridPane();
		private GridPane playerBoard = new GridPane();
		private GridPane oppBoard = new GridPane();
		private VBox direction = new VBox(25, hori, vert);
		private AudioClip explosionSound = new AudioClip(this.getClass().getResource("/resources/explode.mp3").toExternalForm());
		private Image star = new Image("/resources/star.png");
		
		/**
		* Creates the "Battleship" window and all buttons, Labels and text within the window
		* @param primaryStage The stage for "Battleship"
		*/
		public void start(Stage primaryStage) throws FileNotFoundException{

			
			Image horizontal = new Image("resources/horizontal.png");
			Image vertical = new Image("resources/vertical.png");
			hori.setGraphic(new ImageView(horizontal));
			hori.setStyle("-fx-base: black;"+
					"-fx-focus-color: transparent;" +
					"-fx-padding:3 3 3 3;");
			vert.setGraphic(new ImageView(vertical));
			vert.setStyle("-fx-base: black;"+
					"-fx-focus-color: transparent;" +
					"-fx-padding:3 3 3 3;");
			
			for (int row = 0; row < 10; row++) {
				for (int column = 0; column < 10; column++){
					Button b = new Button("", setButtonImage(star));
					b.setStyle("-fx-base: transparent;"+
							"-fx-focus-color: transparent;");
					buttonsPlayer[column][row] = b;
					playerBoard.add(buttonsPlayer[column][row], row, column);
				}
			}

			for (int row = 0; row < 10; row++) {
				for (int column = 0; column < 10; column++){
					Button b = new Button("", setButtonImage(star));
					b.setStyle("-fx-base: transparent;"+
							"-fx-focus-color: transparent;");
					buttonsOpp[column][row] = b;
					oppBoard.add(buttonsOpp[column][row], row, column);				
				}
			}
			
			Label player = new Label();
			player.setGraphic(new ImageView(new Image("/resources/player.png")));
			Label enemy = new Label();	
			enemy.setGraphic(new ImageView(new Image("/resources/enemy.png")));
			VBox messages = new VBox(5, message, shipMessage);
			
			message.setGraphic(new ImageView(new Image("/resources/2ships.png")));
			shipMessage.setGraphic(new ImageView(new Image("/resources/setDir.png")));

			new GameInitialization(this);
			
			allBoards.setHgap(20);
			allBoards.setVgap(20);
			allBoards.setPadding(new Insets(20,20,20,20));
			allBoards.add(enemy, 0, 0);
			allBoards.add(oppBoard, 0, 2);
			allBoards.add(player, 2,0);
			allBoards.add(playerBoard, 2, 2);	
			allBoards.add(messages, 3, 0);
			allBoards.add(direction, 3, 2);
			allBoards.setStyle(" -fx-background-image: url('/resources/background.jpg');");

			Scene scene = new Scene(allBoards, 1550, 600);

			primaryStage.setTitle("Battleship");
			primaryStage.setScene(scene);
			primaryStage.show();
		}
	
	public static void main(String[] args) {
		launch(args);
	}
	
	/**
	* Adds a ship to the player's board and the specified row and column
	* @param row : the row corresponding to the selected spot on the board
	* @param col : the column corresponding to the selected spot on the board
	*/
	public void placeToken(int row, int col) {
		Button b = buttonsPlayer[row][col];
		Image ship = new Image("/resources/ship.png");
		b.setGraphic(setButtonImage(ship));
		b.setDisable(true);
		b.setStyle("-fx-base: transparent;"+
				"-fx-opacity: 1 ");
		shipMessage.setGraphic(new ImageView(new Image("/resources/setDir.png")));
		
	}
	
	/**
	* Allows the player to make a guess on their opponent's board
	* @param char : the token that corresponds to the guess
	* @param row : the row corresponding to the selected spot on the board
	* @param col : the column corresponding to the selected spot on the board
	*/
	public void guess(char token, int row, int col) {
		Button b = buttonsOpp[row][col];
		if (token == 'X') {
			Image explode = new Image("/resources/explosion.png");
			b.setGraphic(setButtonImage(explode)); 
			explosionSound.play();
			
		} else {
			b.setVisible(false);
		}
		b.setDisable(true);
		b.setStyle("-fx-base: transparent;"+
				"-fx-opacity: 1 ");
		
	}
	
	/**
	* Allows the AIplayer to make a guess on the player's board
	* @param char : the token that corresponds to the guess
	* @param row : the row corresponding to the selected spot on the board
	* @param col : the column corresponding to the selected spot on the board
	*/
	public void AIguess(char token, int row, int col) {
		Button b = buttonsPlayer[row][col];
		
		if (token == 'X') {
			Image explode = new Image("/resources/explosion.png");
			b.setGraphic(setButtonImage(explode));
			explosionSound.play();
		} else {
			b.setVisible(false);
		}
		
		b.setDisable(true);
		b.setStyle("-fx-base: black;"+
				"-fx-opacity: 1 ");

	}
	
	/**
	* Sets the message displayed on the board to the specified message
	* @param newMessage : the specified message to display
	*/
	public void setMessage(String newMessage) {
		message.setGraphic(new ImageView(new Image(newMessage)));
	}
	
	/**
	* Sets the message about your current ship on the board to the specified message
	* @param newMessage : the specified message to display
	*/
	public void setShipMessage(String message) {
		if (message.equals("clear")){
			shipMessage.setVisible(false);
		} else {
		shipMessage.setVisible(true);;
		shipMessage.setGraphic(new ImageView(new Image(message)));
		}
	}
	
	/**
	* Removes the horizontal and vertical buttons
	*/
	public void removeDirections() {
	    allBoards.getChildren().remove(direction);
	}
	
	/**
	* Disables the buttons when game has ended
	*/
	public void disable() {
		playAgain.setGraphic(new ImageView(new Image("/resources/playAgain.png")));
		playAgain.setStyle("-fx-base: black;"+
				"-fx-focus-color: transparent;" +
				"-fx-padding:3 3 3 3;");
		shipMessage.setVisible(false);
		allBoards.add(playAgain, 3, 2);
		
		for (Button[] row : buttonsPlayer) {
			for (Button b : row) {
				b.setDisable(true);
			}
		} 
		for (Button[] row : buttonsOpp){
			for (Button b : row) {
				b.setDisable(true);
			}
		}
	}
	
	/**
	* Sets up buttons on player board
	* @param handler : the Event handler
	* @param row : the specified row
	* @param col : the specified column
	*/
	public void setPlayerButtonHandler(EventHandler<ActionEvent> handler, int row, int col){
		buttonsPlayer[row][col].setOnAction(handler);		
	}
	
	/**
	* Sets up buttons on opponent board
	* @param handler : the Event handler
	* @param row : the specified row
	* @param col : the specified column
	*/
	public void setOppButtonHandler(EventHandler<ActionEvent> handler, int row, int col){
		buttonsOpp[row][col].setOnAction(handler);		
	}
	
	/**
	* Sets up the horizontal button
	* @param handler : the Event handler
	*/
	public void setHoriHandler(EventHandler<ActionEvent> handler){
		hori.setOnAction(handler);		
	}
	
	/**
	* Sets up the vertical button
	* @param handler : the Event handler
	*/
	public void setVertHandler(EventHandler<ActionEvent> handler){
		vert.setOnAction(handler);		
	}
	
	/**
	* Sets up the replay button
	* @param handler : the Event handler
	*/
	public void setNewGameHandler(EventHandler<ActionEvent> handler) {
		playAgain.setOnAction(handler);
	}
	
	/**
	* initializes everything to restart the game
	*/
	public void newGame() {
		playerBoard.getChildren().clear();
		oppBoard.getChildren().clear();
		allBoards.getChildren().remove(playAgain);
		allBoards.add(direction, 3, 2);

		
		for (int row = 0; row < 10; row++) {
			for (int column = 0; column < 10; column++){
				Button b = new Button("", setButtonImage(star));
				b.setStyle("-fx-base: transparent;"+
						"-fx-focus-color: transparent;");
				buttonsPlayer[column][row] = b;
				playerBoard.add(buttonsPlayer[column][row], row, column);
			}
		}
		
		for (int row = 0; row < 10; row++) {
			for (int column = 0; column < 10; column++){
				Button b = new Button("", setButtonImage(star));
				b.setStyle("-fx-base: transparent;"+
						"-fx-focus-color: transparent;");
				buttonsOpp[column][row] = b;
				oppBoard.add(buttonsOpp[column][row], row, column);				
			}
		}
		
		setShipMessage("/resources/setDir.png");
		setMessage("/resources/2ships.png");
		
		new GameInitialization(this);
	}
	
	/**
	* Method for resizing images to fit the board grid buttons.
	* @param image : the specific image
	* @return Returns an ImageView
	*/
	public ImageView setButtonImage(Image image) {
		ImageView img = new ImageView(image);
		img.setFitHeight(20);
		img.setFitWidth(20);
		
		return img;
	}
	
	
}
