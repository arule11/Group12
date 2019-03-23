//Based on: private dropbox CPSC219_Examples> Lecture16_OODesign by Nathaly Verwaal >

/**
 * Class representing a Battleship game application. enemy and player are
 * Labels, messages is of type Text and buttons is a 2D list of buttons.
 * Users can play battle ship through the application. The application
 * displays the Players and their opponents board.
 */

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
import javafx.scene.control.ComboBox;
import javafx.event.*;
import javafx.geometry.Insets;
import javafx.scene.input.*;

public class GUI extends Application {
		private Button[][] buttons = new Button[20][10];
		private Text messages = new Text("Welcome to Battleship");
		private Label player = new Label("Your Board");
		private Label enemy = new Label("Your Enemy's Board");

		/**
     * Creates the "Battleship" window and all buttons, Labels and text within the window
     * @param primaryStage The stage for "Battleship"
     */
		public void start(Stage primaryStage) {
			// build grid of buttons for the board (visual component)
			GridPane playerBoard = new GridPane();
			for (int row = 0; row < 10; row++) {
				for (int column = 0; column < 10; column++){
					Button b = new Button(" ");
					buttons[row][column] = b;
					playerBoard.add(buttons[row][column], row, column);
				}
			}
			GridPane oppBoard = new GridPane();
			for (int row = 10; row < 20; row++) {
				for (int column = 0; column < 10; column++){
					Button b = new Button(" ");
					buttons[row][column] = b;
					oppBoard.add(buttons[row][column], row, column);
				}
			}

			ToggleGroup dir = new ToggleGroup();
			RadioButton hori = new RadioButton("Horizontal");
			hori.setUserData('H');
			RadioButton vert = new RadioButton("Vertical");
			hori.setUserData('V');
			hori.setToggleGroup(dir);
			vert.setToggleGroup(dir);
			hori.setSelected(true);
			HBox direction = new HBox(hori, vert);
		/*
			ObservableList<String> options =
				    FXCollections.observableArrayList(
				        "Horizontal",
				        "Vertical"
				    );
			ComboBox comboBox = new ComboBox(options);
		*/
			new GameInitialization(this);

			GridPane allBoards = new GridPane();
			//allBoards.setGridLinesVisible(true);
			allBoards.setHgap(20);
			allBoards.setVgap(20);
			allBoards.setPadding(new Insets(20,20,20,20));
			allBoards.add(playerBoard, 1, 1);
			allBoards.add(oppBoard, 1, 3);
			allBoards.add(player, 0, 1);
			allBoards.add(enemy, 0, 3);
			allBoards.add(messages, 2, 0);
//			allBoards.add(comboBox, 2, 2);
			allBoards.add(direction, 2, 2);

			Scene scene = new Scene(allBoards, 575, 700);

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
		Button b = buttons[row][col];
		b.setText("" + '+');
		b.setDisable(true);
	}

	/**
	* Sets the message displayed on the board to the specified message
	* @param message : the specified message to display
	*/
	public void setMessage(String message) {
		messages.setText(message);
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
}
