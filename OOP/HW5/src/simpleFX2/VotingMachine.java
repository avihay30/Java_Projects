package simpleFX2;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;

// Class that handles all GUI of the voting machine game
public class VotingMachine extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        VBox vbox;
        VotingController controller;
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("Voting.fxml"));
            vbox = loader.load();
            controller = loader.getController();
        } catch (IOException e) {
            e.printStackTrace();
            return;
        }

        Scene scene = new Scene(vbox);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Voting Machine"); // set window title
        primaryStage.show();
        controller.getLabel().setText("" + 0); // set default value to label
        // limiting user to resize the window less than default size
        // (when possible, buttons start to hide their text)
        primaryStage.setMinHeight(primaryStage.getHeight());
        primaryStage.setMinWidth(primaryStage.getWidth());
    }
}
