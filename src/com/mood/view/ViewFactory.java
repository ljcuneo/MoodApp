package com.mood.view;

import com.mood.MoodManager;
import com.mood.controller.*;
import com.mood.model.DatabaseInteraction;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;

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

    private MoodManager moodManager;
    private DatabaseInteraction databaseInteraction;
    private ViewErrorMessages viewErrorMessages;

    public ViewFactory(MoodManager moodManager, DatabaseInteraction databaseInteraction,
                       ViewErrorMessages viewErrorMessages) {
        this.moodManager = moodManager;
        this.databaseInteraction = databaseInteraction;
        this.viewErrorMessages = viewErrorMessages;
    }

    /**
     * Method showStartWindow() instantiates baseController and initializes the stage.
     */
    public void showStartWindow() {
        BaseController controller =
                new StartSceneController(moodManager, this,
                        databaseInteraction, viewErrorMessages, "StartScene.fxml");
        initializeStage(controller);

    }

    /**
     * Method showSignInWindow() instantiates baseController and initializes the stage.
     */
    public void showSignInWindow() {
        BaseController controller =
                new SignInSceneController(moodManager, this, databaseInteraction,
                        viewErrorMessages, "SignInScene.fxml");
        initializeStage(controller);
    }

    /**
     * Method showSignUpWindow() instantiates baseController and initializes the stage.
     */
    public void showSignUpWindow() {
        BaseController controller =
                new SignUpSceneController(moodManager, this, databaseInteraction, viewErrorMessages,
                        "SignUpScene.fxml");
        initializeStage(controller);

    }

    /**
     * Method showMainWindow() instantiates baseController and initializes the stage.
     */
    public void showMainWindow() {
        BaseController controller =
                new MainSceneController(moodManager, this, databaseInteraction, viewErrorMessages,
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
        stage.setTitle("MOOD");
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
