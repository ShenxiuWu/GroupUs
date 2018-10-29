package com.github.GroupUs;

import com.github.GroupUs.factory.ServiceFactory;
import com.github.GroupUs.vo.EventInfo;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.stage.Stage;

import java.util.List;

import static com.github.GroupUs.Main.userId;

public class JoinController {
    @FXML
    private RadioButton radioStudy;
    @FXML
    private RadioButton radioHome;
    @FXML
    private RadioButton radioEat;
    @FXML
    private TextField locationText;
    @FXML
    private ToggleGroup type;

//    String eventId = getText();
//    boolean bool = ServiceFactory.getIEventServiceInstance().join(userId, eventId);
//    if bool == True: prompt out success, and then direct back to sign in success page; else: prompt out failed

    public void initialize() {
        System.out.println("yes");
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
    //type.getSelectedToggle().getUserData().toString()
    @FXML
    private void pressSearch(ActionEvent actionEvent) throws Exception{

        System.out.println("yes");
        // Ideally:
        // press search button => we getText() for entered location, and the dropdown box result - two string
        // we call searchByCategory(String location, String category) and return a list of event obj

        // This list of event Obj could be displayed as search results just for now
        List<EventInfo> searchedEvent = ServiceFactory.getIUserServiceInstance().getPostedEvent("trypost123@columbia.edu");
        
    }

    @FXML
    private void pressJoin(ActionEvent actionEvent) {
    }
}
