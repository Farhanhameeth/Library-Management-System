package com.librarymanage.UIController;

import com.librarymanage.Enum.ServiceType;
import com.librarymanage.dto.UserDto;
import com.librarymanage.service.ServiceFactory;
import com.librarymanage.service.custom.UserService;
import com.librarymanage.util.security.PasswordManager;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Optional;

public class SignUpFormController {
    public AnchorPane context;
    public TextField txtFirstName;
    public TextField txtLastName;
    public TextField txtEmail;
    public TextField txtPassword;

    private final UserService userService = ServiceFactory.getInstance().getService(ServiceType.USER);
    private final ObservableList<UserDto> userList = FXCollections.observableArrayList();

    public void signUpOnAction(ActionEvent actionEvent) throws Exception {
        //validate the email address
        if (!txtEmail.getText().matches("^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$")) {
            new Alert(Alert.AlertType.ERROR, "Invalid Email Address...!").show();
            return;
        }

        if (userService.isUserExist(txtEmail.getText())) {
            new Alert(Alert.AlertType.ERROR, "User already exists...!").show();
            return;
        }

            UserDto user = new UserDto(
                txtEmail.getText().trim(),
                txtFirstName.getText(),
                txtLastName.getText(),
                new PasswordManager().encrypt(txtPassword.getText().trim())
        );

        try {
            String result = userService.save(user);
            if ("Success".equals(result)) {
                userList.add(user);
                clear();
                Optional<ButtonType> buttonType = new Alert(Alert.AlertType.INFORMATION, "User created...!").showAndWait();
                if (buttonType.get().equals(ButtonType.OK)) {
                    setUI("LoginForm");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            new Alert(Alert.AlertType.ERROR, "User Save Failed...").show();
        }
    }

    public void alreadyHaveAnAccountOnAction(ActionEvent actionEvent) throws IOException {
        setUI("LoginForm");
    }

    private void setUI(String location) throws IOException {
        Stage stage = (Stage) context.getScene().getWindow();
        stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("../view/"+location+".fxml"))));
        stage.show();
        stage.centerOnScreen();
    }

    private void clear() {
        txtEmail.clear();
        txtFirstName.clear();
        txtLastName.clear();
        txtPassword.clear();
    }
}
