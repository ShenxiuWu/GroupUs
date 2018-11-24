package com.github.GroupUs;

import com.github.GroupUs.factory.ServiceFactory;
import com.github.GroupUs.vo.EventInfo;
import com.github.GroupUs.impl.distance;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.util.List;

import javafx.stage.Window;

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
    private TableView<EventInfo> eventTable;
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
        /*if (type.getSelectedToggle() == null){
            System.out.println("Wrong");
            return;
        }*/
        eventTable.getItems().clear();
        eventTable.getColumns().clear();

        RadioButton selectedRadioButton = (RadioButton) type.getSelectedToggle();
        String category = "";
        if (type.getSelectedToggle() != null){
            category = selectedRadioButton.getText();
        }
        System.out.println(category);
        String location = locationText.getText();
        String[] locationCheck = {location};
        boolean bool = distance.distanceCheck(locationCheck);
        if (!bool){
            showAlert(Alert.AlertType.ERROR, eventTable.getScene().getWindow(), "Form Error!", "Invalid Location!");
            return ;
        }
        List<EventInfo> searchedEvent = ServiceFactory.getIEventServiceInstance().searchByCategory(category, location);
        for (int i = 0; i < searchedEvent.size(); i ++) {
            System.out.println(searchedEvent.get(i).getEventId());
        }
        //List<EventInfo> searchedEvent = ServiceFactory.getIUserServiceInstance().getPostedEvent("trypost123@columbia.edu");
        ObservableList<EventInfo> searcheditem = FXCollections.observableList(searchedEvent);

        TableColumn<EventInfo, String> categoryColumn1 = new TableColumn<>("Category");
        categoryColumn1.setCellValueFactory(new PropertyValueFactory<>("category"));

        TableColumn<EventInfo, String> subjectColumn1 = new TableColumn<>("Subject");
        subjectColumn1.setCellValueFactory(new PropertyValueFactory<>("subject"));

        TableColumn<EventInfo, String> locationColumn1 = new TableColumn<>("Location");
        locationColumn1.setCellValueFactory(new PropertyValueFactory<>("location"));

        TableColumn<EventInfo, String> startColumn1 = new TableColumn<>("Start");
        startColumn1.setCellValueFactory(new PropertyValueFactory<>("start"));

        TableColumn<EventInfo, String> endColumn1 = new TableColumn<>("End");
        endColumn1.setCellValueFactory(new PropertyValueFactory<>("end"));

        eventTable.setItems(searcheditem);
        eventTable.getColumns().addAll(categoryColumn1,subjectColumn1,locationColumn1,startColumn1,endColumn1);

    }

    @FXML
    private void pressJoin(ActionEvent actionEvent) throws Exception{
        EventInfo itemSelected ;
        if (eventTable.getSelectionModel().getSelectedItem() == null){
            return ;
        } else {
            itemSelected = eventTable.getSelectionModel().getSelectedItem();
        }
        String idSelected = itemSelected.getEventId();
        boolean bool = ServiceFactory.getIEventServiceInstance().join(userId, idSelected);
        if (!bool){
            showAlert(Alert.AlertType.ERROR, eventTable.getScene().getWindow(), "Form Error!", "You have joined the event already!");
            return ;
        }
        Parent newRoot = FXMLLoader.load(getClass().getResource("/fxml/profile.fxml"));
        Stage formerStage = (Stage) ((Node)actionEvent.getSource()).getScene().getWindow();
        formerStage.setScene(new Scene(newRoot, 600, 400));
    }

    private void showAlert(Alert.AlertType alertType, Window owner, String title, String message) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.initOwner(owner);
        alert.showAndWait();
    }

}
