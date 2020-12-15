package com.bankzy.controller;

import com.bankzy.BankzyManager;
import com.bankzy.model.DatabaseInteraction;
import com.bankzy.view.ViewErrorMessages;
import com.bankzy.view.ViewFactory;




public class MainSceneController extends BaseController {
    public MainSceneController(BankzyManager bankzyManager, ViewFactory viewFactory,
                               DatabaseInteraction databaseInteraction, ViewErrorMessages viewErrorMessages,
                               String fxmlName) {
        super(bankzyManager, viewFactory, databaseInteraction, viewErrorMessages, fxmlName);
    }
}
