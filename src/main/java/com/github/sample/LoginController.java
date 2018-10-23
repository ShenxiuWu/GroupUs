package com.github.sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.*;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Modality;
import javafx.stage.Window;
import javafx.stage.Stage;

public class LoginController {

    @FXML
    private GridPane loginpane;
    @FXML
    private TextField logname;
    @FXML
    private TextField logemail;
    @FXML
    private PasswordField loginpw;
    @FXML
    private Button signbutton;

    @FXML
    private void pressbutton(ActionEvent actionEvent) throws Exception {
        if(logname.getText().isEmpty()) {
            showAlert(Alert.AlertType.ERROR, loginpane.getScene().getWindow(), "Form Error!", "Please enter your name");
            return;
        } else {
            System.out.println(logname.getText());
        }
        if(logemail.getText().isEmpty()) {
            showAlert(Alert.AlertType.ERROR, loginpane.getScene().getWindow(), "Form Error!", "Please enter your email id");
            return;
        } else {
            System.out.println(logemail.getText());
        }
        if(loginpw.getText().isEmpty()) {
            showAlert(Alert.AlertType.ERROR, loginpane.getScene().getWindow(), "Form Error!", "Please enter a password");
            return;
        } else {
            System.out.println(loginpw.getText());
        }

        //showAlert(Alert.AlertType.CONFIRMATION, loginpane.getScene().getWindow(), "Registration Successful!", "Welcome " + logname.getText());

        Parent newroot = FXMLLoader.load(getClass().getResource("status.fxml"));
        Stage formerstage = (Stage) ((Node)actionEvent.getSource()).getScene().getWindow();
        formerstage.setScene(new Scene(newroot, 600, 400));

    }

    private void showAlert(Alert.AlertType alertType, Window owner, String title, String message) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.initOwner(owner);
        alert.show();

    }


}
