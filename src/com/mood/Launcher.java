package com.mood;

import com.mood.model.DatabaseInteraction;
import com.mood.view.ViewErrorMessages;
import com.mood.view.ViewFactory;
import javafx.application.Application;
import javafx.stage.Stage;



/**
 * Launches Application's primary stage by extending Application class
 * <p>
 * Application class requires overridden start method after init() has finished being called.
 */
public class Launcher extends Application {
    /**
     * Start method is main entry for JavaFX application and and begins to run the application.
     * <p>
     * Start method is called on the JavaFX Application Thread and sets the primary stage for the application.
     * Start method creates an instance of ViewFactory class which sets the start window as the primary stage.
     *
     * @param primaryStage -  primary stage for application, onto which the application scene can be set.
     */
    @Override
    public void start(Stage primaryStage) {
        ViewFactory viewFactory = new ViewFactory(new MoodManager(), new DatabaseInteraction(),
                new ViewErrorMessages());
        viewFactory.showStartWindow();
    }

    /**
     * Main() method is ignored in correctly deployed JavaFX application.
     * Main() serves only as a fallback in case the application can not be launched through deployment
     *
     * @param args - the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
}
