package com.mood.view;

import javafx.scene.control.Label;
import javafx.scene.paint.Paint;

/**
 * ViewErrorMessages class is used for the sole purpose of setting error messages onto the GUI or changing
 * the text fill of labels already set on GUI
 */
public class ViewErrorMessages {
    public void setErrorMessageColor(int errorMessageNumber, Label label) {
        switch (errorMessageNumber) {
            case 1:
                label.setTextFill(Paint.valueOf("#ff0033"));
                break;
            case 2:
                label.setTextFill(Paint.valueOf("#2A9D8F"));
                break;
            default:
                label.setTextFill(Paint.valueOf("#000000"));
        }
    }

    /**
     * Sets the text in the label indicated sign in error message.
     *
     * @param errorMessageNumber - this integer determines which error message appears on the screen.
     * @param label - this is the sign in error label to be changed.
     */
    public void setSignInErrorMessages(int errorMessageNumber, Label label) {

        switch(errorMessageNumber) {
            case 1:
                label.setTextFill(Paint.valueOf("ff0033"));
                label.setText("Please insert a username and password");
                break;
            case 2:
                label.setTextFill(Paint.valueOf("#ff0033"));
                label.setText("Invalid sign in information");
                break;
            default:
                label.setText("");
        }

    }
}

