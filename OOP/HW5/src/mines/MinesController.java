package mines;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.StackPane;

// Class that is a Controller for getting data on some of fxml Nodes
public class MinesController {

    @FXML
    private TextField width;

    @FXML
    private TextField height;

    @FXML
    private TextField minesAmount;

    @FXML
    private Button resetBtn;

    @FXML
    private StackPane stackPane;

    public TextField getWidth() {
        return width;
    }

    public TextField getHeight() {
        return height;
    }

    public TextField getMinesAmount() {
        return minesAmount;
    }

    public StackPane getStackPane() {
        return stackPane;
    }

    public Button getResetBtn() {
        return resetBtn;
    }
}