package com.bankzy.controller;

import com.bankzy.BankzyManager;
import com.bankzy.model.DatabaseInteraction;
import com.bankzy.view.ViewErrorMessages;
import com.bankzy.view.ViewFactory;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;


import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;

/**
 * The StartSceneController binds the start scene components created by FXML with the Java Code needed for
 * functionality.
 */
public class StartSceneController extends BaseController implements Initializable {
    @FXML
    private ImageView bankzyLogo;
    @FXML
    private ImageView padLock;
    @FXML
    private ImageView padLock2;

    public StartSceneController(BankzyManager bankzyManager, ViewFactory viewFactory,
                                DatabaseInteraction databaseInteraction, ViewErrorMessages viewErrorMessages,
                                String fxmlName) {
        super(bankzyManager, viewFactory, databaseInteraction, viewErrorMessages, fxmlName);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        File file = new File("src/images/piggy-bank.png");
        Image image = new Image((file.toURI().toString()));
        bankzyLogo.setImage(image);

        File file2 = new File("src/images/padlock.png");
        Image image2 = new Image((file2.toURI().toString()));
        padLock.setImage(image2);

        File file3 = new File("src/images/padlock.png");
        Image image3 = new Image((file3.toURI().toString()));
        padLock2.setImage(image3);
    }

    /**
     * Upon clicking the sign in button on the start scene, the sign in window will appear and the start scene will
     * close.
     */
    public void signInButtonOnAction() {
        viewFactory.showSignInWindow();
        closeStage();
    }

    /**
     * Upon clicking the sign up button on the start scene, the sign up window will appear and the start scene will
     * close.
     */
    public void signUpButtonOnAction() {
        viewFactory.showSignUpWindow();
        closeStage();
    }

    /**
     * The entire application closes upon clicking the exit button
     */
    public void exitButtonOnAction() {
        closeStage();
    }

    /**
     * closeStage() gets the scene from a component within the start scene and closes the stage.
     */
    public void closeStage() {
        Stage stage = (Stage) bankzyLogo.getScene().getWindow();
        viewFactory.closeStage(stage);
    }
}
