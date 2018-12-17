package com.github.GroupUs;

import com.github.GroupUs.factory.ServiceFactory;
import com.github.GroupUs.impl.distance;
import com.github.GroupUs.vo.EventInfo;
import com.jfoenix.controls.JFXTimePicker;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import javafx.stage.Window;

import java.net.URL;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Calendar;
import java.util.Date;
import java.util.ResourceBundle;
import java.util.regex.Pattern;

import static com.github.GroupUs.Main.userId;

public class PostController implements Initializable {
    EventInfo vo = new EventInfo();
// subject description start end location memo
    @FXML
    private ChoiceBox<String>  choiceBOX ;
    @FXML
    private TextField subjectText;
    @FXML
    private DatePicker startDate;
    @FXML
    private DatePicker endDate;
    @FXML
    private TextField locText;
    @FXML
    private TextArea memoText;
    @FXML
    private TextArea descriptionText;
    @FXML
    private Button postButton;
    @FXML
    private JFXTimePicker startTime;
    @FXML
    private JFXTimePicker endTime;

    public void initialize(URL location, ResourceBundle resources) {
        //choiceBOX.getItems().removeAll(choiceBOX.getItems());
        //choiceBOX.getValue()
        choiceBOX.getItems().addAll("Study","Eat out","Go home");
        choiceBOX.setValue("Study");
    }

    @FXML
    private void logout(ActionEvent actionEvent) throws Exception {
        Parent newRoot = FXMLLoader.load(getClass().getResource("/fxml/login.fxml"));
        Stage formerStage = (Stage) ((Node)actionEvent.getSource()).getScene().getWindow();
        formerStage.setScene(new Scene(newRoot, 600, 400));
        // user id set to initial ?
    }

    @FXML
    private void backtoStatus(ActionEvent actionEvent) throws Exception {
        Parent newRoot = FXMLLoader.load(getClass().getResource("/fxml/status.fxml"));
        Stage formerStage = (Stage) ((Node)actionEvent.getSource()).getScene().getWindow();
        formerStage.setScene(new Scene(newRoot, 600, 400));
        //user id keep unchanged
    }

// choicebox.getValue() subjectText.getText() startDate.getValue() endDate.getValue()
// locText.getText() memoText.getText() descriptionText.getText()
    @FXML
    private void pressPost(ActionEvent actionEvent) throws Exception{
       /* System.out.println(choiceBOX.getValue());
        System.out.println(subjectText.getText());
        System.out.println(startDate.getValue());
        System.out.println(endDate.getValue());
        System.out.println(locText.getText());
        System.out.println(memoText.getText());
        System.out.println(descriptionText.getText()); */
//        System.out.println(startDate.getValue().getClass().getSimpleName());
//        System.out.println(endDate.getValue().getClass().getSimpleName());
//        System.out.println(startTime.getValue().getClass().getSimpleName());
//        System.out.println(endTime.getValue().getClass().getSimpleName());
//
//        System.out.println(start);
//        System.out.println(end);

        try{

            if (startDate.getValue() == null || endDate.getValue() == null || startTime.getValue() == null || endTime.getValue() == null) {
                throw new Exception("The time cannot be empty, please check your input again!");
            }

            LocalDateTime start = LocalDateTime.of(startDate.getValue(), startTime.getValue());
            LocalDateTime end = LocalDateTime.of(endDate.getValue(), endTime.getValue());
            Date startTime = Date.from(start.atZone(ZoneId.systemDefault()).toInstant());
            Date endTime = Date.from(end.atZone(ZoneId.systemDefault()).toInstant());

            System.out.println("startTime" + startTime);

            Calendar calendar = Calendar.getInstance();
            //Date time = calendar.getTime();
            long timeInMillis = calendar.getTimeInMillis();
            if (startTime.getTime() < timeInMillis) {
                throw new Exception("The start time cannot be earlier than current time, please check your input again!");
            }
            if (startTime.getTime() > endTime.getTime()) {
                throw new Exception("The start time cannot be later than end time, please check your input again!");
            }

            // Insert event into database
            // userId = "trypost123@columbia.edu"; // only for now
            vo.setCreator(userId);
            vo.setCategory(choiceBOX.getValue());
            vo.setSubject(subjectText.getText());
            vo.setStart(startTime);
            vo.setEnd(endTime);
            vo.setLocation(locText.getText());
            vo.setMemo(memoText.getText());
            vo.setDescription(descriptionText.getText());
            ServiceFactory.getIEventServiceInstance().insert(vo);
            // showAlert(Alert.AlertType.ERROR, postButton.getScene().getWindow(), "Congrats!", "You post successfully!");
            Parent newRoot = FXMLLoader.load(getClass().getResource("/fxml/status.fxml"));
            Stage formerStage = (Stage) ((Node)actionEvent.getSource()).getScene().getWindow();
            formerStage.setScene(new Scene(newRoot, 600, 400));
        }
        catch (Exception e){
            String error = e.getMessage();
            showAlert(Alert.AlertType.ERROR, postButton.getScene().getWindow(), "Form Error!", error);
            return;
        }
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
