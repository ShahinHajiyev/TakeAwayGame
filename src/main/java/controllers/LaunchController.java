package controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;


import java.io.IOException;


public class LaunchController {


    @FXML
    private TextField firstInput;
    @FXML
    private TextField secondInput;
    @FXML
    private Label errorLabel;


    public void whenPressedStart(ActionEvent actionEvent) throws IOException {

        if (firstInput.getText().isEmpty()) {
            errorLabel.setText("* Username is empty!");
        }
        else if (secondInput.getText().isEmpty()) {
            errorLabel.setText("* Username is empty!");
        }

        else {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/fxml/game.fxml"));
            Parent root = fxmlLoader.load();
            fxmlLoader.<GameController>getController().initdata(firstInput.getText(),secondInput.getText());
            Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.show();
            //log.info("Username is set to {}, loading game scene.", firstInput.getText(), secondInput.getText());
        }
    }
}
