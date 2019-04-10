
// Thanks to JavaCravings lessons 2-6 : https://www.youtube.com/channel/UC60jAor0sZzfaguXvCBi7ng/videos

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import javafx.scene.image.*;
import javafx.scene.control.*;
import java.util.ArrayList;
import java.util.List;
import javafx.scene.input.*;
import javafx.event.EventHandler;
import javafx.scene.effect.*;
import javafx.scene.text.*;
import javafx.scene.paint.*;
import javafx.event.ActionEvent;
import javafx.application.Platform;
import javafx.geometry.Pos;
import javafx.scene.SubScene;

import javafx.scene.SubScene;
import javafx.animation.TranslateTransition;
import javafx.util.Duration;

/**
* Class representing a Menu. height, width, menuX, and menuY are integers. menuButtons is
* a list of buttons, mainScene is a Scene, mainPane is an AnchorPane, mainStage is a Stage,
* creditsSub, helpSub, and sceneToHide are of type MenuSubScene. The menu can be displayed
* and the buttons "PLAY", "HELP", "CREDITS", and "EXIT" can be clicked on.
*/
public class Menu extends Application {

  private static final int height = 748;
  private static final int width = 1024;
  private AnchorPane mainPane;
  private Scene mainScene;
  private Stage mainStage;

  private final static int menuX = 100;
  private final static int menuY = 220;

  List<Button> menuButtons;

  private MenuSubScene creditsSub;
  private MenuSubScene helpSub;
  private MenuSubScene sceneToHide;

  @Override
  public void start(Stage mainStage) throws Exception {

    menuButtons = new ArrayList<>();
    mainPane = new AnchorPane();
    mainScene = new Scene(mainPane,width,height);
    mainStage = new Stage();
    //mainStage.setResizabl1e(false);
    mainStage.setScene(mainScene);
    mainStage.show();
    createSubs();
    createButtons();
    createBackground();
    createTitle();
  }

  /**
  * Creates the credits subScene with required text, that cooresponds to the credit button
  */
  private void createCreditsSub(){
    creditsSub = new MenuSubScene();
    mainPane.getChildren().add(creditsSub);

    Label credits = new Label("CREDITS");
    credits.setLayoutX(168);
    credits.setLayoutY(10);
    //credits.setAlignment(Pos.CENTER);
    credits.setFont(new Font("Karmatic Arcade", 30));

    Label c1 = new Label("CREATED BY:");
    c1.setFont(new Font("Karmatic Arcade", 21));
    c1.setLayoutX(166);
    c1.setLayoutY(100);

    Label c2 = new Label("UofC CPSC219 GROUP12");
    c2.setFont(new Font("Karmatic Arcade", 21));
    c2.setLayoutX(78);
    c2.setLayoutY(130);

    Label c3 = new Label("MEMBERS");
    c3.setFont(new Font("Karmatic Arcade", 21));
    c3.setLayoutX(186);
    c3.setLayoutY(210);

    Label c4 = new Label("KAYLEE N, ATHENA MR, LIAM L, MATT B");
    c4.setFont(new Font("Karmatic Arcade", 19));
    c4.setLayoutX(24);
    c4.setLayoutY(240);

    creditsSub.getPane().getChildren().addAll(credits, c1, c2, c3, c4);
  }

  /**
  * Creates the help subScene with required text, that cooresponds to the help button
  */
  private void createHelpSub(){
    helpSub = new MenuSubScene();
    mainPane.getChildren().add(helpSub);

    Label howTo = new Label("HOW TO PLAY");
    howTo.setLayoutX(126);
    howTo.setLayoutY(10);
    howTo.setFont(new Font("Karmatic Arcade", 30));

    Text h1 = new Text("Players:");
    h1.setFont(new Font("Karmatic Arcade", 22));
    h1.setLayoutX(10);
    h1.setLayoutY(80);

    Text h2 = new Text("    2 player game");
    h2.setFont(new Font("Karmatic Arcade", 16));
    h2.setLayoutX(10);
    h2.setLayoutY(100);

    Text h3 = new Text("End goal:");
    h3.setFont(new Font("Karmatic Arcade", 22));
    h3.setLayoutX(10);
    h3.setLayoutY(140);
    Text h4 = new Text("    destroy all of your opponent’s ships");
    h4.setFont(new Font("Karmatic Arcade", 16));
    h4.setLayoutX(10);
    h4.setLayoutY(160);

    Text h5 = new Text("Rules:");
    h5.setFont(new Font("Karmatic Arcade", 22));
    h5.setLayoutX(10);
    h5.setLayoutY(210);
    Text h6 = new Text("   each player will place their ships on \n their boards" );
    h6.setFont(new Font("Karmatic Arcade", 16));
    h6.setLayoutX(10);
    h6.setLayoutY(230);

    Text h7 = new Text("   Players will take turns guessing \n coordinates on the other players board \n where they think a ship has been placed");
    h7.setFont(new Font("Karmatic Arcade", 16));
    h7.setLayoutX(10);
    h7.setLayoutY(270);

    Text h8 = new Text("   Continue taking turns until one player \n has destroyed all their opponents’ ships");
    h8.setFont(new Font("Karmatic Arcade", 16));
    h8.setLayoutX(10);
    h8.setLayoutY(330);

    helpSub.getPane().getChildren().addAll(howTo, h1, h2, h3, h4, h5, h6, h7, h8);
  }


  /**
  * Creates the SubScenes for the help and credit buttons
  */
  private void createSubs(){
    createCreditsSub();
    createHelpSub();
  }

  /**
  * Shows the subScene specified
  * @param m : the subscene for showing
  */
  private void showSub(MenuSubScene m){
    if(sceneToHide != null){
      sceneToHide.moveSub();
    }
    m.moveSub();
    sceneToHide = m;
  }

  /**
  * gets the mainStage
  * @return Returns a Stage
  */
  public Stage getMainStage(){
    return mainStage;
  }

  /**
  * adds the specified button to the main menu
  * @param button : the button being added
  */
  private void addMenuButton(Button button){
    button.setLayoutX(menuX);
    button.setLayoutY(menuY + menuButtons.size()*100);
    menuButtons.add(button);
    mainPane.getChildren().add(button);
  }

  /**
  * creates all the buttons on the menu
  */
  private void createButtons(){
    createPlayButton();
		createHelpButton();
		createCreditsButton();
		createExitButton();
  }

  /**
  * creates the play button
  */
  private void createPlayButton(){
    Button playButton = new Button("PLAY");
    //addMenuButton(startButton);
    playButton.setFont(Font.font("Karmatic Arcade", 25));
    //startButton.setFill(Color.ORANGE);
    playButton.setStyle("-fx-base: rgb(255, 255, 255); -fx-text-fill: black;");
    //startButton.setStyle("-fx-base: rgb(119,232,48);");
    // startButton.setPrefWidth(190);
    // startButton.setPrefHeight(49);
    addMenuButton(playButton);

  }

  /**
  * creates the help button
  */
  private void createHelpButton(){
    Button helpButton = new Button("HELP");
    helpButton.setFont(Font.font("Karmatic Arcade", 25));
    helpButton.setStyle("-fx-base: rgb(255, 255, 255); -fx-text-fill: black;");
    addMenuButton(helpButton);

    helpButton.setOnAction(new EventHandler<ActionEvent>(){
      @Override
  		public void handle(ActionEvent event) {
  			//helpSub.moveSub();
        showSub(helpSub);
  		}
    });
  }

  /**
  * creates the credits button
  */
  private void createCreditsButton(){
    Button creditsButton = new Button("CREDITS");
    creditsButton.setFont(Font.font("Karmatic Arcade", 25));
    creditsButton.setStyle("-fx-base: rgb(255, 255, 255); -fx-text-fill: black;");
    addMenuButton(creditsButton);

    creditsButton.setOnAction(new EventHandler<ActionEvent>(){
      @Override
  		public void handle(ActionEvent event) {
  			//creditsSub.moveSub();
        showSub(creditsSub);
  		}
    });
  }

  /**
  * creates the exit button
  */
  private void createExitButton(){
    Button exitButton = new Button("EXIT");
    exitButton.setFont(Font.font("Karmatic Arcade", 25));
    exitButton.setStyle("-fx-base: rgb(255, 255, 255); -fx-text-fill: black;");
    addMenuButton(exitButton);

    exitButton.setOnAction(new EventHandler<ActionEvent>(){
      @Override
  		public void handle(ActionEvent event) {
        Platform.exit();
  		}
    });
  }

  /**
  * creates the background of the menu
  */
  private void createBackground(){
    Image backgroundimg = new Image("stars.jpg", 1024, 768, false, true);
    BackgroundImage background = new BackgroundImage(backgroundimg, BackgroundRepeat.NO_REPEAT,
              BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT, null);
    mainPane.setBackground(new Background(background));

  }

  /**
  * creates the title for the menu
  */
  private void createTitle(){
    ImageView title = new ImageView("titleLogo.png");
    title.setLayoutX(208);
    title.setLayoutY(60);

    title.setOnMouseEntered(new EventHandler<MouseEvent>(){
  		@Override
  		public void handle(MouseEvent event) {
  			title.setEffect(new DropShadow(0.5, Color.WHITESMOKE));
  		}
  	});
  	title.setOnMouseExited(new EventHandler<MouseEvent>(){
  		@Override
			public void handle(MouseEvent event) {
				title.setEffect(null);
			}
		});
  		mainPane.getChildren().add(title);
  }


  public static void main(String[] args) {
      launch(args);
  }

  /**
  * Class representing a MenuSubScene. isHidden is a boolean. SubScenes for the
  * menu can be created and moved.
  */
  class MenuSubScene extends SubScene {
    private boolean isHidden;

    public MenuSubScene(){
      super(new AnchorPane(), 600, 400);
      prefWidth(600);
      prefHeight(400);

      BackgroundImage img = new BackgroundImage(new Image("white.png", 500, 400, false, true),
          BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT, null);

      AnchorPane root2 = (AnchorPane)this.getRoot();
      root2.setBackground(new Background(img));
      isHidden = true;
      setLayoutX(1024);
      setLayoutY(180);
    }

    /**
    * moves the subscene in and out of scene
    */
    public void moveSub(){
      TranslateTransition trans = new TranslateTransition();
      trans.setDuration(Duration.seconds(0.4));
      trans.setNode(this);

      if(isHidden){
        trans.setToX(-676);
        isHidden = false;
      } else {
        trans.setToX(0);
        isHidden = true;
      }
      trans.play();
    }

    /**
    * gets the root converted to an AnchorPane
    * @return Returns an AnchorPane
    */
    public AnchorPane getPane(){
      return (AnchorPane) this.getRoot();
    }
  }



}
