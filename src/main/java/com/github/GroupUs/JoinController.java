package com.github.GroupUs;

import com.github.GroupUs.factory.ServiceFactory;
import com.github.GroupUs.vo.EventInfo;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import static com.github.GroupUs.Main.userId;

public class JoinController {

//    String eventId = getText();
//    boolean bool = ServiceFactory.getIEventServiceInstance().join(userId, eventId);
//    if bool == True: prompt out success, and then direct back to sign in success page; else: prompt out failed

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
}
