package com.mood.controller;


import com.mood.MoodManager;
import com.mood.model.DatabaseInteraction;
import com.mood.view.ViewErrorMessages;

import com.mood.view.ViewFactory;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;


import java.sql.SQLException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 */
public class SignUpSceneController extends BaseController{
    @FXML
    private TextField firstNameTextField;

    @FXML
    private TextField lastNameTextField;

    @FXML
    private TextField emailTextField;

    @FXML
    private TextField usernameTextField;

    @FXML
    private PasswordField passwordTextField;

    @FXML
    private PasswordField confirmPasswordTextField;

    @FXML
    private Label registrationErrorLabel;

    @FXML
    private Label registrationErrorLabel2;

    @FXML
    private Label confirmPasswordErrorLabel;

    @FXML
    private Label passwordErrorLabel;

    @FXML
    private Label passwordErrorLabel2;

    @FXML
    private Label passwordErrorLabel3;

    @FXML
    private Label passwordErrorLabel4;

    @FXML
    private Label passwordErrorLabel5;

    @FXML
    private Label usernameErrorLabel;

    @FXML
    private Label usernameErrorLabel2;

    @FXML
    private Label emailErrorLabel;

    @FXML
    private Label emailErrorLabel2;

    @FXML
    private Label lastNameErrorLabel;

    @FXML
    private Label firstNameErrorLabel;

    @FXML
    private ImageView bankzyLogo;

    @FXML
    private ImageView registerIcon;

    private String regexNameFields = "\\w{2,30}$";
    private String regexEmail = "^[\\w-_\\.+]*[\\w-_\\.]\\@([\\w]+\\.)+[\\w]+[\\w]$";
    private String regexUsername = "^[A-Za-z]\\w{5,29}$";

    public SignUpSceneController(MoodManager moodManager, ViewFactory viewFactory,
                                 DatabaseInteraction databaseInteraction, ViewErrorMessages viewErrorMessages,
                                 String fxmlName) {
        super(moodManager, viewFactory, databaseInteraction, viewErrorMessages, fxmlName);
    }


    /**
     * Method doesTextFieldMatch checks whether the text field inputted matches the regex pattern provided.
     *
     * @param textField - the text field being checked
     * @param regex     - the pattern being used to check the text within the text field
     * @return true if the text field matches and returns false if the text does not align with the regex pattern.
     */
    private boolean doesTextFieldMatchPattern(TextField textField, String regex) {
        Pattern p = Pattern.compile(regex);
        Matcher m = p.matcher(textField.getText());
        return m.matches();
    }

    /**
     * Method isTextFieldAvailable checks whether a text field has been used and is stored in the database or not.
     * <p>
     * Helps avoid duplication of usernames and emails.
     *
     * @param textField - the text field being compared
     * @param sqlField  - the field where the text field would be stored
     * @return true if the text field has not been used before and false if it has
     * @throws SQLException - throws sqlexception if there is an issue connecting to the database
     */
    private boolean isTextFieldAvailable(TextField textField, String sqlField) throws SQLException {
        return (databaseInteraction.isFieldAvailable(textField, sqlField));
    }

    /**
     * Method validateNameFields() validates first name and last name entries entered into the registration form.
     *
     * @param textField - text field text to be validated
     * @param regex - regex used to validate text
     * @param label - label where error message will go
     * @return isValidName - returns true if the text matches the regex, false otherwise
     */
    private boolean validateNameFields(TextField textField, String regex, Label label) {
        boolean isValidName = false;
        int errorMessageNumber = 0;
        if (!textField.getText().isBlank()) {
            isValidName = doesTextFieldMatchPattern(textField, regex) ? true : false;
            if (isValidName)
                errorMessageNumber = 2;
            else
                errorMessageNumber = 1;
        }
        viewErrorMessages.setErrorMessageColor(errorMessageNumber, label);
        return isValidName;
    }

    /**
     * Method testUserOrEmailAvailability() checks whether another user has the same username or email attempting to be
     * registered.
     *
     * @param textField - text field where the text entry is located
     * @param sqlField - sql field to be iterated through
     * @param label - label where error messages will be placed
     * @throws SQLException - exception that may arise if an issue connecting to the database occurs
     */
    private void testUsernameOrEmailAvailability(TextField textField, String sqlField, Label label)
            throws SQLException {
        int errorMessageNumber = 0;
        if (!textField.getText().isBlank()) {
            errorMessageNumber = (isTextFieldAvailable(textField, sqlField)) ? 2 : 1;
        }
        viewErrorMessages.setErrorMessageColor(errorMessageNumber, label);
    }

    /**
     * Method testUsernameOrEmailPattern() checks whether the username or email pattern follow the rules of the regex
     * expression provided.
     *
     * @param textField - text within text field will be compared to regex
     * @param regex - regex that text will be compared to
     * @param label - label where the error messages will go
     * @return validUsernameOrEmail - returns true if the username matches the regex and false otherwise
     */
    private boolean testUsernameOrEmailPattern(TextField textField, String regex, Label label) {
        boolean validUsernameOrEmail = false;
        int errorMessageNumber = 0;
        if (!textField.getText().isBlank()) {
            errorMessageNumber = (!doesTextFieldMatchPattern(textField, regex)) ? 1 : 2;
            validUsernameOrEmail = (doesTextFieldMatchPattern(textField, regex)) ? true : false;
        }
        viewErrorMessages.setErrorMessageColor(errorMessageNumber, label);
        return validUsernameOrEmail;
    }

    /**
     * Method validateUsernameOrEmailField() returns true if the email is available and matches the pattern otherwise
     * false.
     *
     * @param textField - text field where text is
     * @param regex - regex to compare against text
     * @param label - label where the error message goes
     * @param label2 - label where the error message goes
     * @param sqlField - field in database to be iterated through
     * @return isValidField - returns true if the text matches the pattern and has not been used by a previous user
     * @throws SQLException - if error occurs connecting to database
     */
    private boolean validateUsernameOrEmailField(TextField textField, String regex, Label label, Label label2,
                                                 String sqlField) throws SQLException {
        testUsernameOrEmailPattern(textField, regex, label);
        testUsernameOrEmailAvailability(textField, sqlField, label2);
        boolean isValidField = (doesTextFieldMatchPattern(textField, regex) &&
                isTextFieldAvailable(textField, sqlField)) ? true : false;
        return isValidField;
    }

    /**
     * Method testPasswordPattern() tests the password field text against the regex provided through argument.
     *
     * @param passwordField - password text provided within password field
     * @param regex         - Regex used to test against the password field pattern.
     * @param label         - label where error messages will appear.
     */
    private void testPasswordPattern(PasswordField passwordField, String regex, Label label) {
        int errorMessageNumber = 0;
        if (!passwordField.getText().isBlank()) {
            errorMessageNumber = (!doesTextFieldMatchPattern(passwordField, regex)) ? 1 : 2;
        }
        viewErrorMessages.setErrorMessageColor(errorMessageNumber, label);
    }

    /**
     * Method testPasswordPatterns() tests multiple regexes to provide the correct error when creating a password.
     */
      private void testPasswordPatterns() {
        String regexLength = "(?=\\S+$).{8,20}$";
        String regexDigit = "(.)*(\\d)(.)*";
        String regexLowercase = "(.)*[a-z](.)*";
        String regexUppercase = "(.)*[A-Z](.)*";
        String regexSpecialCharacter = "(.)*[@#$%^&+=!](.)*";

        testPasswordPattern(passwordTextField, regexLength, passwordErrorLabel);
        testPasswordPattern(passwordTextField, regexDigit, passwordErrorLabel2);
        testPasswordPattern(passwordTextField, regexLowercase, passwordErrorLabel3);
        testPasswordPattern(passwordTextField, regexUppercase, passwordErrorLabel4);
        testPasswordPattern(passwordTextField, regexSpecialCharacter, passwordErrorLabel5);
    }

    /**
     * Resets the error message color back to the default
     */
    private void resetPasswordErrorMessageColor() {
        viewErrorMessages.setErrorMessageColor(0, passwordErrorLabel);
        viewErrorMessages.setErrorMessageColor(0, passwordErrorLabel2);
        viewErrorMessages.setErrorMessageColor(0, passwordErrorLabel3);
        viewErrorMessages.setErrorMessageColor(0, passwordErrorLabel4);
        viewErrorMessages.setErrorMessageColor(0, passwordErrorLabel5);
    }

    /**
     * validatePassword() method tests the password entry against multiple regexes and return true if the password
     * entry meets all the criteria denoted below within the regex.
     *
     * @return true if the password field matches the regex or false otherwise.
     */
    private boolean validatePassword() {
        String regexPassword = "^(?=.*[0-9])" // digit must occur at least once
                + "(?=.*[a-z])(?=.*[A-Z])" // lower and uppercase must occur at least once
                + "(?=.*[@#$%^&+=!])" // special character at least once
                + "(?=\\S+$).{8,20}$"; // no white space and 8 - 20 characters

        if (!passwordTextField.getText().isBlank()) {
            testPasswordPatterns();
        } else {
            resetPasswordErrorMessageColor();
        }

        return (doesTextFieldMatchPattern(passwordTextField, regexPassword));
    }

    /**
     * validateConfirmedPassword() compares the password field entry with the confirmed password entry and returns
     * true if they match and false if they dont.
     *
     * @return - equals returns true if both fields have the same text or false otherwise
     */
    private boolean validateConfirmedPassword() {
        boolean equals = false;
        int errorMessageNumber = 0;
        if (!confirmPasswordTextField.getText().isBlank()) {
            equals = passwordTextField.getText().equals(confirmPasswordTextField.getText());
            if (equals)
                errorMessageNumber = 2;
            else
                errorMessageNumber = 1;
        }
        viewErrorMessages.setErrorMessageColor(errorMessageNumber, confirmPasswordErrorLabel);
        return equals;
    }

    /**
     * Checks the first name and last name fields for any errors after the registration button has been clicked
     * and then highlights the text field in red if there is an error.
     *
     * @param textField - text to be checked
     * @param regex - regex used to compare
     * @param label - label where error message goes
     */
    private void checkForErrorsNameField(TextField textField, String regex, Label label) {
        if(textField.getText().isBlank() ||
                !validateNameFields(textField, regex, label))
            textField.setStyle("-fx-border-color: red ; -fx-border-width: 2px ;");
        else
            textField.setStyle("");
    }

    /**
     * Checks the username and email text fields for errors.
     *
     * @param textField - text field to be checked
     * @param regex - regex to check text against
     * @param label - label to place error messages
     * @param label2 - label to place error messages
     * @param sqlField - sql field to be interated through
     * @throws SQLException - exception thrown if there is an error connecting to database
     */
    private void checkForErrorsEmailOrUsernameField(TextField textField, String regex,
                                                    Label label, Label label2, String sqlField) throws SQLException {
        if(textField.getText().isBlank() ||
                !validateUsernameOrEmailField(textField, regex, label, label2, sqlField))
            textField.setStyle("-fx-border-color: red ; -fx-border-width: 2px ;");
        else
            textField.setStyle("");
    }

    /**
     * Checks the password text fields for errors.
     * @param textField - password text field to be checked
     */
    private void checkForErrorsPassword(TextField textField) {
        if(textField.getText().isBlank() ||
                !validatePassword())
            textField.setStyle("-fx-border-color: red ; -fx-border-width: 2px ;");
        else
            textField.setStyle("");
    }

    /**
     * Checks the confirmed password text fields for errors.
     * @param textField - text field to be checked
     */
    private void checkForErrorsConfirmedPassword(TextField textField) {
        if(textField.getText().isBlank() ||
                !validateConfirmedPassword())
            textField.setStyle("-fx-border-color: red ; -fx-border-width: 2px ;");
        else
            textField.setStyle("");
    }

    /**
     * Checks for all errors that may occur at point of registration .
     * @throws SQLException - if an issue arises connecting to database exception is thrown.
     */
    private void checkForErrors() throws SQLException {
        checkForErrorsNameField(firstNameTextField, regexNameFields, firstNameErrorLabel);
        checkForErrorsNameField(lastNameTextField, regexNameFields, lastNameErrorLabel);

        checkForErrorsEmailOrUsernameField(emailTextField, regexEmail,
                emailErrorLabel, emailErrorLabel2, "email");
        checkForErrorsEmailOrUsernameField(usernameTextField, regexUsername,
                usernameErrorLabel, usernameErrorLabel2, "username");

        checkForErrorsPassword(passwordTextField);
        checkForErrorsConfirmedPassword(confirmPasswordTextField);
    }

    /**
     * Method registerButtonOnAction() validates that all fields have been correctly inputted and if so the
     * new user's information will be inserted into the database.
     * If not the checkForErrors() method is called to provide feedback to the user as to what is missing on the
     * registration form.
     * @throws SQLException - exception thrown if there is an error connecting to database
     */
    public void registerButtonOnAction() throws SQLException {
        if (validateNameFields(firstNameTextField, regexNameFields, firstNameErrorLabel) &&
                validateNameFields(lastNameTextField, regexNameFields, lastNameErrorLabel) &&
                validateUsernameOrEmailField(emailTextField, regexEmail, emailErrorLabel, emailErrorLabel2,
                        "email") &&
                validateUsernameOrEmailField(usernameTextField, regexUsername,
                        usernameErrorLabel, usernameErrorLabel2, "username") &&
                validatePassword() &&
                validateConfirmedPassword()) {
            databaseInteraction.registerNewUser(firstNameTextField, lastNameTextField,
                    emailTextField, usernameTextField, passwordTextField);
            viewFactory.showSignInWindow();
            closeStage();
        } else
            checkForErrors();
    }

    /**
     * Allows the user to go back a stage
     */
    public void goBackButtonOnAction() {
        viewFactory.showStartWindow();
        closeStage();
    }

    /**
     * Validates the entry into the first name text field as it is being typed.
     */
    @FXML
    public void firstNameOnKeyTyped() {
        validateNameFields(firstNameTextField, regexNameFields, firstNameErrorLabel);
        if(validateNameFields(firstNameTextField, regexNameFields, firstNameErrorLabel)) {
            firstNameTextField.setStyle("");
        }
    }

    /**
     * Validates the entry into the last name text field as it is being typed.
     */
    @FXML
    public void lastNameOnKeyTyped() {
        validateNameFields(lastNameTextField, regexNameFields, lastNameErrorLabel);
        if(validateNameFields(lastNameTextField, regexNameFields, lastNameErrorLabel)) {
            lastNameTextField.setStyle("");
        }
    }

    /**
     * Validates the entry into the email text field as it is being typed.
     */
    @FXML
    public void emailOnKeyTyped() throws SQLException {
        validateUsernameOrEmailField(emailTextField, regexEmail, emailErrorLabel, emailErrorLabel2, "email");
        if(validateUsernameOrEmailField(emailTextField, regexEmail, emailErrorLabel, emailErrorLabel2, "email")) {
            emailTextField.setStyle("");
        }
    }

    /**
     * Validates the entry into the username text field as it is being typed.
     */
    @FXML
    public void usernameOnKeyTyped() throws SQLException {
        validateUsernameOrEmailField(usernameTextField, regexUsername,
                usernameErrorLabel, usernameErrorLabel2, "username");
        if(validateUsernameOrEmailField(usernameTextField, regexUsername, usernameErrorLabel, usernameErrorLabel2,
                "username")) {
            usernameTextField.setStyle("");
        }
    }

    /**
     * Validates the entry into the password text field as it is being typed.
     */
    @FXML
    public void passwordOnKeyTyped() {
        validatePassword();
        if(validatePassword()) {
            passwordTextField.setStyle("");
        }
    }

    /**
     * Validates the entry into the confirmed password text field as it is being typed.
     */
    @FXML
    public void confirmPasswordOnKeyTyped() {
        validateConfirmedPassword();
        if(validateConfirmedPassword()) {
            confirmPasswordTextField.setStyle("");
        }
    }
    
    /**
     * closeStage() gets the scene from a component within the start scene and closes the stage.
     */
    private void closeStage() {
        Stage stage = (Stage) firstNameTextField.getScene().getWindow();
        viewFactory.closeStage(stage);
    }
}

