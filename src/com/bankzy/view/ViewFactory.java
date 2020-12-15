package com.bankzy.view;

import com.bankzy.BankzyManager;
import com.bankzy.controller.*;
import com.bankzy.model.DatabaseInteraction;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;

import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * ViewFactory class creates new instances to a single, baseController class and the factory decides
 * by itself what kind of controller to create and what stage is to be seen.
 * <p>
 * Dependency (constructor) Injection of controllers to initalizeStage() allows for loose coupling and
 * better isolation testing. Controller classes always have a banzkyManager, a database, and a
 * viewErrorMessages class to use.
 */
public class ViewFactory {

    private BankzyManager bankzyManager;
    private DatabaseInteraction databaseInteraction;
    private ViewErrorMessages viewErrorMessages;

    public ViewFactory(BankzyManager bankzyManager, DatabaseInteraction databaseInteraction,
                       ViewErrorMessages viewErrorMessages) {
        this.bankzyManager = bankzyManager;
        this.databaseInteraction = databaseInteraction;
        this.viewErrorMessages = viewErrorMessages;
    }

    /**
     * Method showStartWindow() instantiates baseController and initializes the stage.
     */
    public void showStartWindow() {
        BaseController controller =
                new StartSceneController(bankzyManager, this,
                        databaseInteraction, viewErrorMessages, "StartScene.fxml");
        initializeStage(controller);

    }

    /**
     * Method showSignInWindow() instantiates baseController and initializes the stage.
     */
    public void showSignInWindow() {
        BaseController controller =
                new SignInSceneController(bankzyManager, this, databaseInteraction,
                        viewErrorMessages, "SignInScene.fxml");
        initializeStage(controller);
    }

    /**
     * Method showSignUpWindow() instantiates baseController and initializes the stage.
     */
    public void showSignUpWindow() {
        BaseController controller =
                new SignUpSceneController(bankzyManager, this, databaseInteraction, viewErrorMessages,
                        "SignUpScene.fxml");
        initializeStage(controller);

    }

    /**
     * Method showMainWindow() instantiates baseController and initializes the stage.
     */
    public void showMainWindow() {
        BaseController controller =
                new MainSceneController(bankzyManager, this, databaseInteraction, viewErrorMessages,
                        "MainScene.fxml");
        initializeStage(controller);
    }

    /**
     * InitializeStage method loads fxmlFile, sets the controller, creates a scene and then sets the scene to the stage.\
     *
     * @param baseController - controller gets injected into initialize stage class
     */
    private void initializeStage(BaseController baseController) {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(baseController.getFxmlName()));
        fxmlLoader.setController(baseController);
        Parent parent;
        try {
            parent = fxmlLoader.load();
        } catch (IOException e) {
            e.printStackTrace();
            return;
        }
        Scene scene = new Scene(parent);
        Stage stage = new Stage();
        stage.setTitle("BANKZY");
        Image icon = new Image(getClass().getResourceAsStream("/images/piggy-bank.png"));
        stage.getIcons().add(icon);
        stage.resizableProperty().setValue(Boolean.FALSE);
        stage.setScene(scene);
        stage.show();
    }

    /**
     * Method to close a stage
     *
     * @param stageToClose - specifies which stage to close
     */
    public void closeStage(Stage stageToClose) {
        stageToClose.close();
    }
}
