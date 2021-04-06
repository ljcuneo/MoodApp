package com.mood.controller;


import com.mood.MoodManager;
import com.mood.model.DatabaseInteraction;
import com.mood.view.ViewErrorMessages;
import com.mood.view.ViewFactory;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;


/**
 * The SignInSceneController class binds the Sign In Scene components created by FXML with the Java Code needed for
 * functionality.
 */
public class SignInSceneController extends BaseController implements Initializable {
    @FXML
    private TextField usernameTextField;
    @FXML
    private PasswordField passwordTextField;
    @FXML
    private Label signInErrorMessage;
    @FXML
    private ImageView usernameIcon;
    @FXML
    private ImageView passwordIcon;
    @FXML
    private ImageView bankzyLogo;
    @FXML
    private ImageView padLockIcon;

    public SignInSceneController(MoodManager moodManager, ViewFactory viewFactory,
                                 DatabaseInteraction databaseInteraction, ViewErrorMessages viewErrorMessages,
                                 String fxmlName) {
        super(moodManager, viewFactory, databaseInteraction, viewErrorMessages, fxmlName);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        File file2 = new File("src/images/username.png");
        Image image2 = new Image((file2.toURI().toString()));
        usernameIcon.setImage(image2);

        File file3 = new File("src/images/password.png");
        Image image3 = new Image((file3.toURI().toString()));
        passwordIcon.setImage(image3);

    }


        /**
         * singInButtonOnAction method is called upon clicking the sign in button on the sign in page. When the method
         * is called, both text field and password field are checked for text. If there is text, then validateLogin()
         * will be called and if there is not text an error message will appear.
         */
    public void signInButtonOnAction() {
        int errorMessageNumber = 0;

        if ((usernameTextField.getText().isBlank() == false) && (passwordTextField.getText().isBlank() == false))
            validateLogin();
        else {
            errorMessageNumber = 1;
            viewErrorMessages.setSignInErrorMessages(errorMessageNumber, signInErrorMessage);
        }
    }

    public void goBackButtonOnAction() {
        viewFactory.showStartWindow();
        closeStage();
    }

    /**
     * Method validateLogin() tests the username and password typed into the text fields against a username and password
     * in the database. If the login information exists, then the Main Window will appear and the stage will close. If
     * the login does not exist then an error message will populate.
     */
    public void validateLogin() {
        int errorMessageNumber = 0;
        if (databaseInteraction.validateLogin(usernameTextField, passwordTextField)) {
            viewFactory.showMainWindow();
            closeStage();
        } else {
            errorMessageNumber = 2;
            viewErrorMessages.setSignInErrorMessages(errorMessageNumber, signInErrorMessage);
        }
    }

    private void closeStage() {
        Stage stage = (Stage) usernameTextField.getScene().getWindow();
        viewFactory.closeStage(stage);
    }

    /**
     * Resets the sign in error message upon the username text field being typed into.
     */
    @FXML
    void usernameOnKeyTyped() {
        viewErrorMessages.setSignInErrorMessages(0, signInErrorMessage);
    }

    /**
     * Resets the sign in error message upon the username text field being typed into.
     */
    @FXML
    void passwordOnKeyTyped() {
        viewErrorMessages.setSignInErrorMessages(0, signInErrorMessage);
    }
}
