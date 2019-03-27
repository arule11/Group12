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

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.layout.*;
import javafx.scene.text.Text;
import javafx.scene.control.Label;
import javafx.scene.control.Button;
import javafx.event.*;
import javafx.geometry.Insets;

public class GUI extends Application {

		private Button[][] buttonsPlayer = new Button[10][10];
		private Button[][] buttonsOpp = new Button[10][10];
		private Text message = new Text("Place your ships.");
		private Text shipMessage = new Text("Your ship is 2 units long. Set your direction.");
		private Button hori = new Button("Horizontal");
		private Button vert = new Button("Vertical");
		private GridPane allBoards = new GridPane();
		private HBox direction = new HBox(25, hori, vert);
			
		/**
		* Creates the "Battleship" window and all buttons, Labels and text within the window
		* @param primaryStage The stage for "Battleship"
		*/
		public void start(Stage primaryStage) {
			
			GridPane playerBoard = new GridPane();
			for (int row = 0; row < 10; row++) {
				for (int column = 0; column < 10; column++){
					Button b = new Button("  ");
					buttonsPlayer[column][row] = b;
					playerBoard.add(buttonsPlayer[column][row], row, column);
				}
			}
			
			GridPane oppBoard = new GridPane();
			for (int row = 0; row < 10; row++) {
				for (int column = 0; column < 10; column++){
					Button b = new Button("  ");
					buttonsOpp[column][row] = b;
					oppBoard.add(buttonsOpp[column][row], row, column);				
				}
			}
			
			Label player = new Label("Your Board");
			Label enemy = new Label("Your Enemy's Board");	
			VBox messages = new VBox(5, message, shipMessage);

		
			new GameInitialization(this);
			
			allBoards.setHgap(20);
			allBoards.setVgap(20);
			allBoards.setPadding(new Insets(20,20,20,20));
			allBoards.add(player, 0, 1);
			allBoards.add(playerBoard, 1, 1);
			allBoards.add(enemy, 0, 3);
			allBoards.add(oppBoard, 1, 3);	
			allBoards.add(messages, 3, 0);
			allBoards.add(direction, 3, 2);

			Scene scene = new Scene(allBoards, 700, 700);

			primaryStage.setTitle("Battleship");
			primaryStage.setScene(scene);
			primaryStage.show();
		}
	
	public static void main(String[] args) {
		launch(args);
	}
	
	/**
	* Adds a ship to the players board and the specified row and column
	* @param row : the row corresponding to the selected spot on the board
	* @param col : the column corresponding to the selected spot on the board
	*/
	public void placeToken(int row, int col) {
		Button b = buttonsPlayer[row][col];
		b.setText("" + '+');
		b.setDisable(true);
	}
	
	/**
	* Allows the player to make a guess on their opponents board
	* @param char : the token that cooresponds to the guess
	* @param row : the row corresponding to the selected spot on the board
	* @param col : the column corresponding to the selected spot on the board
	*/
	public void guess(char token, int row, int col) {
		Button b = buttonsOpp[row][col];
		b.setText("" + token);
		b.setDisable(true);
	}
	
	/**
	* Allows the AIplayer to make a guess on the players board
	* @param char : the token that cooresponds to the guess
	* @param row : the row corresponding to the selected spot on the board
	* @param col : the column corresponding to the selected spot on the board
	*/
	public void AIguess(char token, int row, int col) {
		Button b = buttonsPlayer[row][col];
		b.setText("" + token);
		b.setDisable(true);
	}
	
	/**
	* Sets the message displayed on the board to the specified message
	* @param newMessage : the specified message to display
	*/
	public void setMessage(String newMessage) {
		message.setText(newMessage);
	}
	
	/**
	* Sets the message about your current ship on the board to the specified message
	* @param newMessage : the specified message to display
	*/
	public void setShipMessage(String newMessage) {
		shipMessage.setText(newMessage);
	}
	
	/**
	* removes the horizontal and vertical buttons
	*/
	public void removeDirections() {
	    allBoards.getChildren().remove(direction);
	}
	
	/**
	* disables the buttons when game has ended
	*/
	public void disable() {
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
	* sets up buttons on player board
	* @param handler : the Event handler
	* @param row : the specified row
	* @param col : the specified column
	*/
	
	public void setPlayerButtonHandler(EventHandler<ActionEvent> handler, int row, int col){
		buttonsPlayer[row][col].setOnAction(handler);		
	}
	
	/**
	* sets up buttons on opponent board
	* @param handler : the Event handler
	* @param row : the specified row
	* @param col : the specified column
	*/
	
	public void setOppButtonHandler(EventHandler<ActionEvent> handler, int row, int col){
		buttonsOpp[row][col].setOnAction(handler);		
	}
	

	/**
	* sets up the horizontal button
	* @param handler : the Event handler
	*/
	
	public void setHoriHandler(EventHandler<ActionEvent> handler){
		hori.setOnAction(handler);		
	}
	
	/**
	* sets up the vertical button
	* @param handler : the Event handler
	*/
	public void setVertHandler(EventHandler<ActionEvent> handler){
		vert.setOnAction(handler);		
	}
	
	
}
