package simpleFX;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

// Class that represents a Slot in Mine matrix
public class VotingMachine extends Application {
    private int counter;
    // label that indicate the counter
    private Label label = new Label("" + counter);

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        Scene scene = new Scene(createRoot());
        primaryStage.setScene(scene);
        primaryStage.setTitle("Voting Machine"); // for window title

        primaryStage.show();
		// limiting user to resize the window less than default size
		// (when possible, buttons start to hide their text)
		primaryStage.setMinHeight(primaryStage.getHeight());
		primaryStage.setMinWidth(primaryStage.getWidth());
    }

    // creating root Parent for Scene
    private VBox createRoot() {
        VBox root = new VBox(25); // 25 is space between buttons and label
        root.getChildren().addAll(makeHBtns(), makeLabel());

        root.setPadding(new Insets(10)); // space between border to content
        return root;
    }

    // creating the Horizontal buttons for root
    private Node makeHBtns() {
        HBox navBtns = new HBox(15);
        Button btn1 = new Button("Ofra Haza");
        Button btn2 = new Button("Yardena Arazi");

        // Inner class to perform increment or decrease to label text
        class LabelIncDec implements EventHandler<ActionEvent> {
            private boolean isInc;

            public LabelIncDec(boolean isInc) {
                this.isInc = isInc;
            }

            @Override
            public void handle(ActionEvent event) {
                counter = isInc ? counter + 1 : counter - 1;
                label.setText("" + counter); // changing label text counter
            }
        }

        btn1.setOnAction(new LabelIncDec(true)); // assign EventHandler to btn1
        btn2.setOnAction(new LabelIncDec(false)); // assign EventHandler to btn2
        navBtns.getChildren().addAll(btn1, btn2);
        navBtns.setAlignment(Pos.CENTER); // center both buttons

        return navBtns;
    }

    // creating the counter Label for root
    private Node makeLabel() {
        BackgroundFill bgFill = new BackgroundFill(Color.RED, null, null);
        Background bg = new Background(bgFill);
        label.setBackground(bg);

        // adding padding top+bottom:10, right+left: 20
        label.setPadding(new Insets(10, 20, 10, 20));
        label.setMaxWidth(Double.MAX_VALUE);
        label.setAlignment(Pos.CENTER); // center text in label
        return label;
    }
}
