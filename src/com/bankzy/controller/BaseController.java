package com.bankzy.controller;

import com.bankzy.BankzyManager;
import com.bankzy.model.DatabaseInteraction;
import com.bankzy.view.ViewErrorMessages;
import com.bankzy.view.ViewFactory;


/**
 * Abstract class BaseController is used as a single instance of creating controllers with the Application.
 * (Dependency Injection)
 */
public abstract class BaseController {

    protected BankzyManager bankzyManager;
    protected ViewFactory viewFactory;
    protected DatabaseInteraction databaseInteraction;
    protected ViewErrorMessages viewErrorMessages;
    private String fxmlName;

    public BaseController(BankzyManager bankzyManager, ViewFactory viewFactory,
                          DatabaseInteraction databaseInteraction, ViewErrorMessages viewErrorMessages,
                          String fxmlName) {
        this.bankzyManager = bankzyManager;
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
