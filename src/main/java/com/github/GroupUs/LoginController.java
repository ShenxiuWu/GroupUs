package com.github.GroupUs;

import com.github.GroupUs.factory.ServiceFactory;
import com.github.GroupUs.vo.UserInfo;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.*;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Window;
import javafx.stage.Stage;
import java.util.regex.*;


import static com.github.GroupUs.Main.userId;

public class LoginController {
    UserInfo vo = new UserInfo();

    @FXML
    private GridPane loginpane;
    @FXML
    private TextField logname;
    @FXML
    private TextField logemail;
    @FXML
    private PasswordField loginpw;

    @FXML
    private void signIn(ActionEvent actionEvent) throws Exception {
        //check empty item
        boolean empty_check = checkEmpty1();
        if (!empty_check) {
            return ;
        }
        // check account and password
        if (ServiceFactory.getIUserServiceInstance().get(logemail.getText())== null ) {
            System.out.println("Account not found ");
            showAlert(Alert.AlertType.ERROR, loginpane.getScene().getWindow(), "Form Error!", "Account Info/Password Wrong");
            return ;
        }else if (! ServiceFactory.getIUserServiceInstance().get(logemail.getText()).getPassword().equals(loginpw.getText())){
            System.out.println("Password Wrong");
            showAlert(Alert.AlertType.ERROR, loginpane.getScene().getWindow(), "Form Error!", "Account Info/Password Wrong");
            return ;
        }
        // showAlert(Alert.AlertType.ERROR, loginpane.getScene().getWindow(), "Welcome!", "Sign in Successfully");
        userId = logemail.getText();
        // user id get value
        Parent newRoot = FXMLLoader.load(getClass().getResource("/fxml/status.fxml"));
        Stage formerStage = (Stage) ((Node)actionEvent.getSource()).getScene().getWindow();
        formerStage.setScene(new Scene(newRoot, 600, 400));

    }

    @FXML
    private void signUp(ActionEvent actionEvent) {
        try {
            boolean empty_check = checkEmpty2();
            if (!empty_check) {
                return;
            }
            if (ServiceFactory.getIUserServiceInstance().get(logemail.getText()) != null){
                showAlert(Alert.AlertType.ERROR, loginpane.getScene().getWindow(), "Form Error!", "Account Exist! Please Sign in.");
                return;
            }
            String pattern1 = ".*@columbia.edu";
            // String pattern2 = ".*@gmail.com";
            boolean isMatch1 = Pattern.matches(pattern1, logemail.getText());
            // boolean isMatch2 = Pattern.matches(pattern2, logemail.getText());
            if (!isMatch1){
                showAlert(Alert.AlertType.ERROR, loginpane.getScene().getWindow(), "Form Error!", "Please use columbia email address");
                return;
            }
            vo.setName(logname.getText());
            vo.setEmail(logemail.getText());
            vo.setPassword(loginpw.getText());

            // try to insert the new input message to database
            ServiceFactory.getIUserServiceInstance().insert(vo);
            showAlert(Alert.AlertType.INFORMATION, loginpane.getScene().getWindow(), "Welcome!", "Sign up Successfully, click OK button to jump to the profile page");
            // user id get value
            userId = logemail.getText();
            Parent newRoot = FXMLLoader.load(getClass().getResource("/fxml/status.fxml"));
            Stage formerStage = (Stage) ((Node)actionEvent.getSource()).getScene().getWindow();
            formerStage.setScene(new Scene(newRoot, 600, 400));
        } catch (Exception e){
            showAlert(Alert.AlertType.ERROR, loginpane.getScene().getWindow(), "Form Error!", e.getMessage());  // get and return the error message from backend to frontend
        }
    }
    private boolean checkEmpty1() throws Exception{
        if(logemail.getText().isEmpty()) {
            showAlert(Alert.AlertType.ERROR, loginpane.getScene().getWindow(), "Form Error!", "Please enter your email id");
            return false;
        }
        if(loginpw.getText().isEmpty()) {
            showAlert(Alert.AlertType.ERROR, loginpane.getScene().getWindow(), "Form Error!", "Please enter a password");
            return false;
        }
        return true;
    }

    private boolean checkEmpty2() throws Exception{
        if(logname.getText().isEmpty()) {
            showAlert(Alert.AlertType.ERROR, loginpane.getScene().getWindow(), "Form Error!", "Please enter your name");
            return false;
        }
        if(logemail.getText().isEmpty()) {
            showAlert(Alert.AlertType.ERROR, loginpane.getScene().getWindow(), "Form Error!", "Please enter your email id");
            return false;
        }
        if(loginpw.getText().isEmpty()) {
            showAlert(Alert.AlertType.ERROR, loginpane.getScene().getWindow(), "Form Error!", "Please enter a password");
            return false;
        }
        return true;
    }

    private void showAlert(Alert.AlertType alertType, Window owner, String title, String message) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.initOwner(owner);
        alert.showAndWait();
    }
   // use to quick swtich to test
    @FXML
    private void testJoin(ActionEvent actionEvent) throws Exception{
        Parent newRoot = FXMLLoader.load(getClass().getResource("/fxml/status.fxml"));
        Stage formerStage = (Stage) ((Node)actionEvent.getSource()).getScene().getWindow();
        formerStage.setScene(new Scene(newRoot, 600, 400));
    }



}
