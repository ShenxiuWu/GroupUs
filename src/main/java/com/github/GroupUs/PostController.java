package com.github.GroupUs;

import com.github.GroupUs.factory.ServiceFactory;
import com.github.GroupUs.vo.EventInfo;
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
import java.time.ZoneId;
import java.util.Calendar;
import java.util.Date;
import java.util.ResourceBundle;

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
        System.out.println(choiceBOX.getValue());
        System.out.println(subjectText.getText());
        System.out.println(startDate.getValue());
        System.out.println(endDate.getValue());
        System.out.println(locText.getText());
        System.out.println(memoText.getText());
        System.out.println(descriptionText.getText());
        if (subjectText.getText().isEmpty()){
            showAlert(Alert.AlertType.ERROR, postButton.getScene().getWindow(), "Form Error", "You should fill in subject!");
            return ;
        }
        if (startDate.getValue() == null || endDate.getValue() == null){
            showAlert(Alert.AlertType.ERROR, postButton.getScene().getWindow(), "Form Error", "You should fill in time!");
            return ;
        }
        String location = locText.getText();
        String[] locationCheck = {location};
        boolean bool = distance.distanceCheck(locationCheck);
        if (!bool) {
            showAlert(Alert.AlertType.ERROR, postButton.getScene().getWindow(), "Form Error", "Invalid Location!");
            return ;
        }

        Date startTime = Date.from(startDate.getValue().atStartOfDay(ZoneId.systemDefault()).toInstant());
        Date endTime = Date.from(endDate.getValue().atStartOfDay(ZoneId.systemDefault()).toInstant());

        Calendar calendar = Calendar.getInstance();
        //Date time = calendar.getTime();
        long timeInMillis = calendar.getTimeInMillis();
        if (startTime.getTime() < timeInMillis) {
            showAlert(Alert.AlertType.ERROR, postButton.getScene().getWindow(), "Form Error", "Start time is earlier than current time!");
            return ;
        }
        if (startTime.getTime() > endTime.getTime()) {
            showAlert(Alert.AlertType.ERROR, postButton.getScene().getWindow(), "Form Error", "Start time is later than end time!");
            return ;
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

    private void showAlert(Alert.AlertType alertType, Window owner, String title, String message) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.initOwner(owner);
        alert.showAndWait();
    }

}
