package com.mood.controller;

import com.mood.MoodManager;
import com.mood.model.DatabaseInteraction;
import com.mood.view.ViewErrorMessages;
import com.mood.view.ViewFactory;


/**
 * Abstract class BaseController is used as a single instance of creating controllers with the Application.
 * (Dependency Injection)
 */
public abstract class BaseController {

    protected MoodManager moodManager;
    protected ViewFactory viewFactory;
    protected DatabaseInteraction databaseInteraction;
    protected ViewErrorMessages viewErrorMessages;
    private String fxmlName;

    public BaseController(MoodManager moodManager, ViewFactory viewFactory,
                          DatabaseInteraction databaseInteraction, ViewErrorMessages viewErrorMessages,
                          String fxmlName) {
        this.moodManager = moodManager;
        this.viewFactory = viewFactory;
        this.databaseInteraction = databaseInteraction;
        this.viewErrorMessages = viewErrorMessages;
        this.fxmlName = fxmlName;
    }

    /**
     * getFxmlName returns the fxml name
     *
     * @return fxml file name
     */
    public String getFxmlName() {
        return fxmlName;
    }
}
