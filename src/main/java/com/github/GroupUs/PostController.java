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
import java.net.URL;
import java.time.ZoneId;
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

        Date startTime = Date.from(startDate.getValue().atStartOfDay(ZoneId.systemDefault()).toInstant());
        Date endTime = Date.from(endDate.getValue().atStartOfDay(ZoneId.systemDefault()).toInstant());

        // Insert event into database
        userId = "rz2390@columbia.edu"; // only for now
        vo.setCreator(userId);
        vo.setCategory(choiceBOX.getValue());
        vo.setSubject(subjectText.getText());
        vo.setStart(startTime);
        vo.setEnd(endTime);
        vo.setLocation(locText.getText());
        vo.setMemo(memoText.getText());
        vo.setDescription(descriptionText.getText());
        ServiceFactory.getIEventServiceInstance().insert(vo);
    }
}
