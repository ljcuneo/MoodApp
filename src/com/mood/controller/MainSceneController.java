package com.mood.controller;

import com.mood.MoodManager;
import com.mood.model.DatabaseInteraction;
import com.mood.view.ViewErrorMessages;
import com.mood.view.ViewFactory;




public class MainSceneController extends BaseController {
    public MainSceneController(MoodManager moodManager, ViewFactory viewFactory,
                               DatabaseInteraction databaseInteraction, ViewErrorMessages viewErrorMessages,
                               String fxmlName) {
        super(moodManager, viewFactory, databaseInteraction, viewErrorMessages, fxmlName);
    }
}
