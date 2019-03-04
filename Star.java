
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.*;
import javafx.scene.image.*;

public class Star extends Application {

  public void start(Stage primaryStage) {

    Button btn = new Button("Say 'place ship'");
    btn.setOnAction((ActionEvent event) -> {
        System.out.println("ship placed");
    });

    Region region = new Region();
    region.setStyle("-fx-background-image: url('boardBackground.jpg');" +
      "-fx-background-size: 100 100;" + "-fx-background-position: center center;");
    region.setMouseTransparent(true);

    StackPane root = new StackPane(btn, region);

    Scene scene = new Scene(root, 100, 100);
    primaryStage.setScene(scene);
    primaryStage.show();
  }

}
