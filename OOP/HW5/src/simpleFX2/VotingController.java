package simpleFX2;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

// Class that is a Controller for getting data on some of fxml Nodes
// and handle click events on buttons
public class VotingController {
    private int counter;

    @FXML
    private Label counterLabel;

    @FXML
    void clickOfra(ActionEvent event) {
        counterLabel.setText("" + ++counter);
    }

    @FXML
    void clickYardena(ActionEvent event) {
        counterLabel.setText("" + --counter);
    }

    public Label getLabel() {
        return counterLabel;
    }
}
