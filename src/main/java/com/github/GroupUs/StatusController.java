package com.github.GroupUs;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.*;
import javafx.stage.Stage;

public class StatusController {


    @FXML
    private void gotoProfile(ActionEvent actionEvent) throws Exception{
        //user id
        Parent newRoot = FXMLLoader.load(getClass().getResource("/fxml/profile.fxml"));
        Stage formerStage = (Stage) ((Node)actionEvent.getSource()).getScene().getWindow();
        formerStage.setScene(new Scene(newRoot, 600, 400));

    }

    @FXML
    private void gotoPost(ActionEvent actionEvent) throws Exception{
        // user id
        Parent newRoot = FXMLLoader.load(getClass().getResource("/fxml/post.fxml"));
        Stage formerStage = (Stage) ((Node)actionEvent.getSource()).getScene().getWindow();
        formerStage.setScene(new Scene(newRoot, 600, 400));
    }


    @FXML
    private void gotoJoin(ActionEvent actionEvent) throws Exception {
        Parent newRoot = FXMLLoader.load(getClass().getResource("/fxml/join.fxml"));
        Stage formerStage = (Stage) ((Node)actionEvent.getSource()).getScene().getWindow();
        formerStage.setScene(new Scene(newRoot, 600, 400));
    }

    @FXML
    private void logout(ActionEvent actionEvent) throws Exception {
        Parent newRoot = FXMLLoader.load(getClass().getResource("/fxml/login.fxml"));
        Stage formerStage = (Stage) ((Node)actionEvent.getSource()).getScene().getWindow();
        formerStage.setScene(new Scene(newRoot, 600, 400));
        // user id set to initial ?
    }
}
