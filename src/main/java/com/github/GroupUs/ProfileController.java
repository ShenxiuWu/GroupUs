package com.github.GroupUs;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ListView;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

public class ProfileController {


    //    String userId = getText();
    //    list of joined event Obj = ServiceFactory.getIUserServiceInstance().getPostedEvent(userId);
    //    list of posted event Obj = ServiceFactory.getIUserServiceInstance().getJoinedEvent(userId);
    //    user Obj = ServiceFactory.getIUserServiceInstance().get(userId);


    @FXML
    private ListView postList;
    @FXML
    private ListView joinList;

// given a user id !
    public void initialize(URL location, ResourceBundle resources){

    }


    public void getPostEvent(){

    }

    public void getJoinEvent(){

    }


    @FXML
    private void backtoStatus(ActionEvent actionEvent) throws Exception{
        Parent newRoot = FXMLLoader.load(getClass().getResource("/fxml/status.fxml"));
        Stage formerStage = (Stage) ((Node)actionEvent.getSource()).getScene().getWindow();
        formerStage.setScene(new Scene(newRoot, 600, 400));
        //user id keep unchanged
    }

    @FXML
    private void logout(ActionEvent actionEvent) throws Exception{
        Parent newRoot = FXMLLoader.load(getClass().getResource("/fxml/login.fxml"));
        Stage formerStage = (Stage) ((Node)actionEvent.getSource()).getScene().getWindow();
        formerStage.setScene(new Scene(newRoot, 600, 400));
        // user id set to initial ?
    }
    // finish profile and search/join interface
    // adjust status interface

}
