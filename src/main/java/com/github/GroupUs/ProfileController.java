package com.github.GroupUs;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.util.List;
import com.github.GroupUs.factory.ServiceFactory;
import static com.github.GroupUs.Main.userId;
import com.github.GroupUs.vo.EventInfo;

public class ProfileController {

    //    String userId = getText();
    //    list of joined event Obj = ServiceFactory.getIUserServiceInstance().getPostedEvent(userId);
    //    list of posted event Obj = ServiceFactory.getIUserServiceInstance().getJoinedEvent(userId);
    //    user Obj = ServiceFactory.getIUserServiceInstance().get(userId);

    @FXML
    private TableView<EventInfo> postTable ;
    @FXML
    private TableView<EventInfo> joinTable;
    @FXML
    private Label outputarea;
    @FXML
    private Label nameTag;

    //
    // given a user id !
    public void initialize() throws Exception{
        //userId = "trypost123@columbia.edu";  //only for now
        List<EventInfo> postList = ServiceFactory.getIUserServiceInstance().getPostedEvent(userId);
        List<EventInfo> joinList = ServiceFactory.getIUserServiceInstance().getJoinedEvent(userId);
        ObservableList<EventInfo> postitem = FXCollections.observableList(postList);
        ObservableList<EventInfo> joinitem = FXCollections.observableList(joinList);

        TableColumn<EventInfo, String> categoryColumn1 = new TableColumn<>("Category");
        categoryColumn1.setCellValueFactory(new PropertyValueFactory<>("category"));

        TableColumn<EventInfo, String> subjectColumn1 = new TableColumn<>("Subject");
        subjectColumn1.setCellValueFactory(new PropertyValueFactory<>("subject"));

        TableColumn<EventInfo, String> locationColumn1 = new TableColumn<>("Location");
        locationColumn1.setCellValueFactory(new PropertyValueFactory<>("location"));
        postTable.setPlaceholder(new Label("No content"));
        postTable.setItems(postitem);
        postTable.getColumns().addAll(categoryColumn1,subjectColumn1,locationColumn1);

        TableColumn<EventInfo, String> categoryColumn2 = new TableColumn<>("Category");
        categoryColumn2.setCellValueFactory(new PropertyValueFactory<>("category"));

        TableColumn<EventInfo, String> subjectColumn2 = new TableColumn<>("Subject");
        subjectColumn2.setCellValueFactory(new PropertyValueFactory<>("subject"));

        TableColumn<EventInfo, String> locationColumn2 = new TableColumn<>("Location");
        locationColumn2.setCellValueFactory(new PropertyValueFactory<>("location"));
        joinTable.setPlaceholder(new Label("No content"));
        joinTable.setItems(joinitem);
        joinTable.getColumns().addAll(categoryColumn2,subjectColumn2,locationColumn2);
        String userName = ServiceFactory.getIUserServiceInstance().get(userId).getName();
        nameTag.setText("Welcome  "+ userName + " !" + "\n" + "Your user ID is:  " + userId);
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

    @FXML
    private void showFull(MouseEvent mouseEvent) {
        EventInfo itemSelected ;
        itemSelected = postTable.getSelectionModel().getSelectedItem();
        outputarea.setText("Category:  "+itemSelected.getCategory()+"\n"+"Subject:  "+itemSelected.getSubject()+"\n"+
                "Location:  "+itemSelected.getLocation()+"\n"+
                "start:  "+itemSelected.getStart()+"\n"+"end:  "+itemSelected.getEnd()+"\n"+"Memo:  "+itemSelected.getMemo()+"\n"+
                "Description:  "+itemSelected.getDescription());
    }

    @FXML
    private void showFull2(MouseEvent mouseEvent) {
        EventInfo itemSelected ;
        itemSelected = joinTable.getSelectionModel().getSelectedItem();
        outputarea.setText("Category:  "+itemSelected.getCategory()+"\n"+"Subject:  "+itemSelected.getSubject()+"\n"+
                "Location:  "+itemSelected.getLocation()+"\n"+
                "start:  "+itemSelected.getStart()+"\n"+"end:  "+itemSelected.getEnd()+"\n"+"Memo:  "+itemSelected.getMemo()+"\n"+
                "Description:  "+itemSelected.getDescription());
    }
    // finish profile and search/join interface
    // adjust status interface

}
