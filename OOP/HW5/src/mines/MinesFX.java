package mines;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;

// Class that handles all GUI of mine sweeper game
public class MinesFX extends Application {
    // default size of game matrix (10X10) and 10 mines
    private final int DEFAULT_SIZE = 10;
    private final Image WIN_GIF = new Image(getClass().getResourceAsStream("winner.gif"));

    private boolean isLose; // represent if player lost
    // represent if winner window has been shown once in this game
    private boolean winnerDialogIsShown;
    private MinesController controller;
    private Mines mines; // the instance of a game logic
    private GridPane gridPane; // the grid that holds all game buttons
    private Stage stage;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        HBox hBox;
        // save stage variable to resize and limit size when reset is pressed
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

    /**
     * Build & Create all defaults for first show:
     * EventHandler for resetBtn.
     * default text for TextFields, default grid pane.
     * and allow grid pane to grow Horizontally & Vertically
     */
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
        buildGridPane(DEFAULT_SIZE, DEFAULT_SIZE, DEFAULT_SIZE);

        // sets the priority of the stackPane,
        // so that the HBox will allocate to it all empty space available.
        HBox.setHgrow(controller.getStackPane(), Priority.ALWAYS);
    }

    // limiting user to resize the window less than min size
    private void limitSizeWindow() {
        stage.setMinHeight(stage.getHeight());
        stage.setMinWidth(stage.getWidth());
    }

    /**
     * Generate new grid (clear all children) and resting the game to new variables.
     *
     * @param width       width size of matrix (grid x-axis)
     * @param height      height size of matrix (grid y-axis)
     * @param minesAmount number of placed mines in game
     */
    private void buildGridPane(int width, int height, int minesAmount) {
        gridPane.getChildren().clear(); // removing old buttons
        // "replaying" - creating new game instance
        mines = new Mines(height, width, minesAmount);

        // iterating height X width and building grid
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                GameButton button = new GameButton(i, j);
                button.setOnMouseClicked(new GameBtnEventHandler());
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

    /**
     * Refreshing all grid button's text,
     * used after click to open action
     */
    private void refreshGridContent() {
        for (Node node : gridPane.getChildren()) {
            // check if later casting to GameButton is valid
            if (!(node instanceof GameButton)) return;
            GameButton button = (GameButton) node;
            refreshGridBtnContent(button);
        }
    }

    /**
     * Refreshing the specified game button to new text if needed
     */
    private void refreshGridBtnContent(GameButton button) {
        button.setText(mines.get(button.getX(), button.getY()));
        if (button.getText().equals("X")) { // if button is a mine
            button.setStyle("-fx-border-color: red;");
        }
    }

    /**
     * Opening new window for winner player
     */
    private void openWinnerDialog() {
        Stage dialog = new Stage();

        // building stackPane that holds text over a WIN_GIF
        StackPane winnerPane = new StackPane();
        Label label = new Label("OMG, you just won!!!");
        label.setPadding(new Insets(10));
        BackgroundFill bgfill = new BackgroundFill(Color.WHITE, new CornerRadii(5), null);
        label.setBackground(new Background(bgfill));
        winnerPane.getChildren().addAll(new ImageView(WIN_GIF), label);

        // populate dialog with controls.
        Scene scene = new Scene(winnerPane);
        dialog.setScene(scene);
        dialog.setTitle("Winner");

        dialog.initOwner(stage); // specifying the owner of the window
        // defining that the window blocks event on other windows
        dialog.initModality(Modality.APPLICATION_MODAL);
        dialog.setResizable(false); // block user resizing window
        dialog.showAndWait();
    }

    /**
     * on clicking ResetButton - building all grid with the given size
     */
    private void addResetEventHandler() {
        Button resetBtn = controller.getResetBtn();

        resetBtn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                // resting player flags
                isLose = false;
                winnerDialogIsShown = false;

                int width = Integer.parseInt(controller.getWidth().getText());
                int height = Integer.parseInt(controller.getHeight().getText());
                int minesAmount = Integer.parseInt(controller.getMinesAmount().getText());
                // not possible to put more then width*height mines
                minesAmount = Math.min(minesAmount, width * height);

                buildGridPane(width, height, minesAmount);
                stage.sizeToScene(); // resizing to appropriate size
                limitSizeWindow(); // limiting resizing to min size possible
            }
        });
    }

    /**
     * on clicking GameButton - refreshing relevant grid buttons text.
     * and if it's win play - display winning window
     * if it's a lose play - reveling all grid buttons text ("opening" all buttons)
     */
    private class GameBtnEventHandler implements EventHandler<MouseEvent> {
        @Override
        public void handle(MouseEvent event) {
            // check if later casting to GameButton is valid
            if (!(event.getSource() instanceof GameButton)) return;

            GameButton button = (GameButton) event.getSource();
            switch (event.getButton()) {
                case PRIMARY: // when left click
                    // if user pressed on a mine
                    // and didn't win yet: for blocking playing after player won already
                    if (!mines.open(button.getX(), button.getY()) && !winnerDialogIsShown) {
                        isLose = true;
                        mines.setShowAll(true);
                    }
                    refreshGridContent();
                    // if player won (mines.isDone() and didn't press on a mine)
                    // and the window didn't open yet.
                    if (mines.isDone() && !isLose && !winnerDialogIsShown) {
                        winnerDialogIsShown = true;
                        openWinnerDialog();
                    }
                    break;
                case SECONDARY: // when right click - toggle a flag
                    mines.toggleFlag(button.getX(), button.getY());
                    refreshGridBtnContent(button);
                    break;
                default:
                    break;
            }
        }
    }
}
