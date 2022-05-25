package simpleFX2;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

public class VotingController {
	private int counter;

    @FXML
    private Label counterLabel;

    @FXML
    private Button ofraBtn;

    @FXML
    private Button yardenaBtn;

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
