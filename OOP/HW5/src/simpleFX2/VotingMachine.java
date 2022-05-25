package simpleFX2;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

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
		primaryStage.setTitle("Voting Machine");
		primaryStage.show();
		controller.getLabel().setText("" + 0);
	}
}
