package BattleshipA;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.layout.*;
import javafx.scene.text.Text;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBase;
import javafx.scene.control.ComboBox;
import javafx.event.*;
import javafx.geometry.Insets;
import javafx.scene.input.*;

public class GUI extends Application {
	//update to 2 boards
		private Button[][] buttons = new Button[20][10];
		private Button[][] button1 = new Button[1][2];
		private Text message = new Text("Welcome to Battleship");
		private Player dummy = new Player();
		private Text shipMessage = new Text("Your ship is " + dummy.shipLength + " units long. Set your direction.");
		private Button hori = new Button("Horizontal");
		private Button vert = new Button("Vertical");
			
		public void start(Stage primaryStage) {
			GridPane playerBoard = new GridPane();
			for (int row = 0; row < 10; row++) {
				for (int column = 0; column < 10; column++){
					Button b = new Button("  ");
					buttons[row][column] = b;
					playerBoard.add(buttons[row][column], row, column);				
				}
			}
			GridPane oppBoard = new GridPane();
			for (int row = 10; row < 20; row++) {
				for (int column = 0; column < 10; column++){
					Button b = new Button("  ");
					buttons[row][column] = b;
					oppBoard.add(buttons[row][column], row, column);				
				}
			}
			
			Label player = new Label("Your Board");
			Label enemy = new Label("Your Enemy's Board");
			HBox direction = new HBox(25, hori, vert);
			VBox messages = new VBox(5, message, shipMessage);

		
			new GameInitialization(this);
			
			GridPane allBoards = new GridPane();
			allBoards.setHgap(20);
			allBoards.setVgap(20);
			allBoards.setPadding(new Insets(20,20,20,20));
			allBoards.add(playerBoard, 1, 1);
			allBoards.add(oppBoard, 1, 3);
			allBoards.add(player, 0, 1);
			allBoards.add(enemy, 0, 3);
			allBoards.add(messages, 2, 0);
			allBoards.add(direction, 2, 2);

			Scene scene = new Scene(allBoards, 700, 700);

			primaryStage.setTitle("Battleship");
			primaryStage.setScene(scene);
			primaryStage.show();
		}
	
	public static void main(String[] args) {
		launch(args);
	}
	
	public void placeToken(int row, int col) {
		Button b = buttons[row][col];
		b.setText("" + '+');
		b.setDisable(true);
	}
	
	public void setMessage(String newMessage) {
		message.setText(newMessage);
	}
	
	public void setShipMessage(String newMessage) {
		shipMessage.setText(newMessage);
	}
	
	public void disable() {
		for (Button[] row : buttons) {
			for (Button b : row) {
				b.setDisable(true);
			}
		}
	}
	public void setButtonHandler(EventHandler<ActionEvent> handler, int row, int col){
		buttons[row][col].setOnAction(handler);		
	}
	
	public void setButtonHandler1(EventHandler<ActionEvent> handler, int row, int col){
		button1[row][col].setOnAction(handler);		
	}
	
	public void setHoriHandler(EventHandler<ActionEvent> handler){
		hori.setOnAction(handler);		
	}
	
	public void setVertHandler(EventHandler<ActionEvent> handler){
		vert.setOnAction(handler);		
	}
	
	
}
