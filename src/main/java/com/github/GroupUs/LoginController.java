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
    private void pressbutton(ActionEvent actionEvent) throws Exception {
        if(logname.getText().isEmpty()) {
            showAlert(Alert.AlertType.ERROR, loginpane.getScene().getWindow(), "Form Error!", "Please enter your name");
            return;
        } else {

            System.out.println(logname.getText());
        }
        if(logemail.getText().isEmpty()) {
            showAlert(Alert.AlertType.ERROR, loginpane.getScene().getWindow(), "Form Error!", "Please enter your email id");
            return;
        } else {

            vo.setEmail(logemail.getText());
            System.out.println(logemail.getText());
        }
        if(loginpw.getText().isEmpty()) {
            showAlert(Alert.AlertType.ERROR, loginpane.getScene().getWindow(), "Form Error!", "Please enter a password");
            return;
        } else {
            vo.setPassword(loginpw.getText());
            ServiceFactory.getIUserServiceInstance().insert(vo);
            System.out.println(loginpw.getText());
        }

        //showAlert(Alert.AlertType.CONFIRMATION, loginpane.getScene().getWindow(), "Registration Successful!", "Welcome " + logname.getText());

        Parent newroot = FXMLLoader.load(getClass().getResource("/fxml/status.fxml"));
        Stage formerstage = (Stage) ((Node)actionEvent.getSource()).getScene().getWindow();
        formerstage.setScene(new Scene(newroot, 600, 400));

    }



    @FXML
    private void signIn(ActionEvent actionEvent) throws Exception {
        //check empty item
        boolean empty_check = checkEmpty();
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
        showAlert(Alert.AlertType.ERROR, loginpane.getScene().getWindow(), "Welcome!", "Sign in Successfully");
        Parent newroot = FXMLLoader.load(getClass().getResource("/fxml/status.fxml"));
        Stage formerstage = (Stage) ((Node)actionEvent.getSource()).getScene().getWindow();
        formerstage.setScene(new Scene(newroot, 600, 400));

    }

    @FXML
    private void signUp(ActionEvent actionEvent) throws Exception {
        boolean empty_check = checkEmpty();
        if (!empty_check) {
            return;
        }
        if (ServiceFactory.getIUserServiceInstance().get(logemail.getText()) != null){
            showAlert(Alert.AlertType.ERROR, loginpane.getScene().getWindow(), "Form Error!", "Account Exist! Please Sign in.");
            return;
        }
        String pattern1 = ".*@columbia.edu";
        String pattern2 = ".*@gmail.com";
        boolean isMatch1 = Pattern.matches(pattern1, logemail.getText());
        boolean isMatch2 = Pattern.matches(pattern2, logemail.getText());
        if (!isMatch1 && !isMatch2){
            showAlert(Alert.AlertType.ERROR, loginpane.getScene().getWindow(), "Form Error!", "Use columbia/gmail email address");
            return;
        }
        vo.setEmail(logemail.getText());
        vo.setPassword(loginpw.getText());
        ServiceFactory.getIUserServiceInstance().insert(vo);
        showAlert(Alert.AlertType.ERROR, loginpane.getScene().getWindow(), "Welcome!", "Sign up Successfully");

        Parent newroot = FXMLLoader.load(getClass().getResource("/fxml/status.fxml"));
        Stage formerstage = (Stage) ((Node)actionEvent.getSource()).getScene().getWindow();
        formerstage.setScene(new Scene(newroot, 600, 400));

    }

    private boolean checkEmpty() throws Exception{
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

}
