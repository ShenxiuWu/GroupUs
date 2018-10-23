package com.github.GroupUs;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.*;
import javafx.stage.Stage;

public class StatusController {

    @FXML
    private void backtofirst(ActionEvent actionEvent) throws Exception {
        Parent newroot = FXMLLoader.load(getClass().getResource("/fxml/login.fxml"));
        Stage formerstage = (Stage) ((Node)actionEvent.getSource()).getScene().getWindow();
        formerstage.setScene(new Scene(newroot, 600, 400));
    }

    @FXML
    private void showact1(ActionEvent actionEvent) {
        AlertBox.display("Activity list", "This is all the posted activity");

    }
}
