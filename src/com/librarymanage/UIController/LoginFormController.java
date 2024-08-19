package com.librarymanage.UIController;

import com.librarymanage.Enum.ServiceType;
import com.librarymanage.dto.UserDto;
import com.librarymanage.service.ServiceFactory;
import com.librarymanage.service.custom.UserService;
import com.librarymanage.util.security.PasswordManager;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;

public class LoginFormController {
    public AnchorPane context;
    public TextField txtEmail;
    public PasswordField txtPassword;

    private final UserService userService = ServiceFactory.getInstance().getService(ServiceType.USER);

    public void loginOnAction(ActionEvent actionEvent) throws IOException {

        String email = txtEmail.getText().trim();
        String password = txtPassword.getText().trim();

        try{
            UserDto user = userService.getUser(email);
            if (user != null) {
                if (new PasswordManager().checkPassword(password, user.getPassword())) {
                    setUI("Dashboard");
                } else {
                    new Alert(Alert.AlertType.ERROR, "Email or Password Incorrect...!").show();
                }
            } else {
                new Alert(Alert.AlertType.ERROR, "User Not Found...!").show();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void createAnAccountOnAction(ActionEvent actionEvent) throws IOException {
        setUI("SignUpForm");
    }

    private void setUI(String location) throws IOException {
        Stage stage = (Stage) context.getScene().getWindow();
        stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("../view/"+location+".fxml"))));
        stage.show();
        stage.centerOnScreen();
    }
}
