package com.github.GroupUs;

import com.github.GroupUs.factory.ServiceFactory;
import com.github.GroupUs.vo.EventInfo;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
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
    @FXML
    private TableView eventTable;
//    String eventId = getText();
//    boolean bool = ServiceFactory.getIEventServiceInstance().join(userId, eventId);
//    if bool == True: prompt out success, and then direct back to sign in success page; else: prompt out failed

    public void initialize() {
        eventTable.setPlaceholder(new Label("No content"));
        System.out.println("initialze step");
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
    private void pressSearch(ActionEvent actionEvent) throws Exception {
        if (type.getSelectedToggle() == null){
            System.out.println("Wrong");
            return;
        }
        List<EventInfo> searchedEvent = ServiceFactory.getIUserServiceInstance().getPostedEvent("trypost123@columbia.edu");
        RadioButton selectedRadioButton = (RadioButton) type.getSelectedToggle();
        System.out.println(selectedRadioButton.getText());
    }

    @FXML
    private void pressJoin(ActionEvent actionEvent) {

        // Ideally:
        // select from one of the events listed there after we pressed Search button
        // then press Join, we should getText() for that selected event Id

        // Then we call join method, if return True, we direct back to loginSuccess page, if return False, we have prompt out window

        // String eventId = eventId.getText();
        // boolean bool = ServiceFactory.getIEventServiceInstance().join(userId, eventId);
        // if bool == true: get back to login successfully
    }
}
