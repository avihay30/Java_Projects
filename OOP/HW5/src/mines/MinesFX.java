package mines;

import java.io.IOException;
import javafx.application.Application;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class MinesFX extends Application {
	// default size of game matrix (10X10) and 10 mines
	private final int DEFAULT_SIZE = 10;
	private MinesController controller;
	private Mines mines;
	private GridPane gridPane;
	private Stage stage;

	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage primaryStage) {
		HBox hBox;
		// save stage variable to resize when reset is pressed
		stage = primaryStage; 
		
		try {
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(getClass().getResource("Mines.fxml"));
			hBox = loader.load();
			controller = loader.getController();
		} catch (IOException e) {
			e.printStackTrace();
			return;
		}
		
		Scene scene = new Scene(hBox);
		primaryStage.setScene(scene);
		primaryStage.setTitle("The Amazing Mines Sweeper");
		initStage();
		primaryStage.show();
		limitSizeWindow(); // limiting resizing to min size possible
	}
	
	private void initStage() {
		addResetEventHandler();
		
		// set default init values for game
		controller.getWidth().setText("" + DEFAULT_SIZE);
		controller.getHeight().setText("" + DEFAULT_SIZE);
		controller.getMinesAmount().setText("" + DEFAULT_SIZE);

		gridPane = new GridPane(); // Creating a Grid Pane  
        gridPane.setPadding(new Insets(10, 10, 10, 10)); // Setting the padding
        controller.getStackPane().getChildren().add(gridPane);
        // creating grid in size DEFAULT_SIZE X DEFAULT_SIZE of a game
		reRanderGridPane(DEFAULT_SIZE, DEFAULT_SIZE, DEFAULT_SIZE);

		// sets the priority of the stackPane, 
		// so that the HBox will allocate to it all empty space available. 
		HBox.setHgrow(controller.getStackPane(), Priority.ALWAYS);
	}
	
	private void limitSizeWindow() {
		stage.setMinHeight(stage.getHeight());
		stage.setMinWidth(stage.getWidth());
	}
	
    private void reRanderGridPane(int width, int height, int minesAmount) {
    	gridPane.getChildren().clear(); // removing old buttons
    	// "replaying" - creating new game instance
    	mines = new Mines(height, width, minesAmount);

        gridPane.setStyle("-fx-border-color: green;");
        controller.getStackPane().setBackground(new Background(new BackgroundFill(Color.BLUEVIOLET, null, null)));
        
        for (int i = 0; i < height; i++) {
        	for (int j = 0; j < width; j++) {
        		GameButton button = new GameButton(i, j);
        		button.setOnMouseClicked(new PlayBtnEventHandler());
        		button.setMinSize(40, 40);

        		// connect size of button to the father grid
        		button.prefWidthProperty().bind(gridPane.widthProperty());
        		button.prefHeightProperty().bind(gridPane.heightProperty());

        		button.setText(mines.get(i, j));
        		button.setFont(new Font("Ariel", 20));
        		gridPane.add(button, j, i); // j = col idx, i = row idx
        	}
		}        
    }
    
    private void reRanderGridContant() {
    	for (Node node : gridPane.getChildren()) {
    		GameButton button = (GameButton) node;
    		reRanderGridBtnContant(button);
		}
    }
    
    private void reRanderGridBtnContant(GameButton button) {
		button.setText(mines.get(button.getX(), button.getY()));
    }
    
    private void addResetEventHandler() {
    	Button resetBtn = controller.getResetBtn();
    	
    	resetBtn.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
		    	int width = Integer.parseInt(controller.getWidth().getText());
		    	int height = Integer.parseInt(controller.getHeight().getText());
		    	int minesAmount = Integer.parseInt(controller.getMinesAmount().getText());
		    	// unable to put more then width*height mines
		    	minesAmount = Math.min(minesAmount, width*height);

		    	reRanderGridPane(width, height, minesAmount);		    	
		    	stage.sizeToScene(); // resizing to appropriate size
		    	limitSizeWindow(); // limiting resizing to min size possible
			}
		});
    }
    
    private class PlayBtnEventHandler implements EventHandler<MouseEvent> {

		@Override
		public void handle(MouseEvent event) {
			GameButton button = (GameButton) event.getSource();
			switch (event.getButton()) {
			case PRIMARY: // when left click
				// if user pressed on a mine
				if (!mines.open(button.getX(), button.getY())) {
					mines.setShowAll(true);
				}
				reRanderGridContant();
				break;
			case SECONDARY: // when right click - toggle a flag
				mines.toggleFlag(button.getX(), button.getY());
				reRanderGridBtnContant(button);
				break;
			default:
				break;
			}

		}    	
    }
}
