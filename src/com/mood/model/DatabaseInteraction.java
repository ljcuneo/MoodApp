package com.mood.model;

import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;


import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * The DatabaseInteraction class is where the application will connect to a database, query a statement
 * and return results.
 */
public class DatabaseInteraction {
    /**
     * validateLogin method connects to a database, queries the database and verifies login information
     * inputted by user is correct
     *
     * @param usernameTextField - text field where the username has been stored
     * @param passwordTextField - password field where the password was stored
     * @return isLoginValid - returns false if the login does not exist and returns true if it does
     */
    public boolean validateLogin(TextField usernameTextField, PasswordField passwordTextField) {
        boolean isLoginValid = false;

        // Instantiating the databaseConnection class and creating a new connection
        DatabaseConnection connectNow = new DatabaseConnection();
        Connection connectDb = connectNow.getConnection();

        // query to verify whether a login is indeed w/in database or not
        String verifyLogin = "SELECT count(1) FROM bankzyuserinfo WHERE username ='" +
                usernameTextField.getText() + "' AND password ='" + passwordTextField.getText() + "'";

        try {
            // statement based on driver manager which is connected to connection
            Statement statement = connectDb.createStatement();
            // generates a result using result set that will return answer to verifying login
            ResultSet queryResult = statement.executeQuery(verifyLogin);

            while (queryResult.next()) {
                if (queryResult.getInt(1) == 1)
                    isLoginValid = true;
                else
                    isLoginValid = false;
            }

        } catch (Exception e) {
            e.printStackTrace();
            e.getCause();
        }

        return isLoginValid;
    }

    /**
     * isFieldAvailable() method checks to see if a username or email is available for a new user to use
     *
     * @param textField - the text field where the username or email has been stored
     * @param sqlField  - the sql field to look at
     * @return - isFieldAvailable returns true if the sql query returned false and vice versa
     * @throws SQLException - handles a sqlexception that may occur when connecting to DB
     */
    public boolean isFieldAvailable(TextField textField, String sqlField) throws SQLException {
        boolean isFieldAvailable = false;

        DatabaseConnection connectNow = new DatabaseConnection();
        Connection connectDb = connectNow.getConnection();

        // query to verify whether a field is available or not
        String verifyQuery = "SELECT count(1) FROM bankzyuserinfo WHERE " + sqlField + " ='" +
                textField.getText() + "'";

        Statement verifyStatement = connectDb.createStatement();
        ResultSet verifyQueryResult = verifyStatement.executeQuery(verifyQuery);

        while (verifyQueryResult.next()) {
            if (verifyQueryResult.getInt(1) == 1)
                isFieldAvailable = false;
            else
                isFieldAvailable = true;
        }
        return isFieldAvailable;
    }

    /**
     * Method registerNewUser inserts a new user information into the database.
     *
     * @param firstNameTextField - text field where the first name is stored
     * @param lastNameTextField  - text field where the last name is stored
     * @param emailTextField     - text field where the email is stored
     * @param usernameTextField  - text field where the username was stored
     * @param passwordTextField  -  password field where the password is stored
     * @throws SQLException - throws SQLException error if error connecting to DB
     */
    public void registerNewUser(TextField firstNameTextField, TextField lastNameTextField,
                                TextField emailTextField, TextField usernameTextField,
                                PasswordField passwordTextField) throws SQLException {

        DatabaseConnection connectNow = new DatabaseConnection();
        Connection connectDb = connectNow.getConnection();

        String insert = "INSERT INTO bankzyuserinfo(firstname, lastname, email, username, password)" +
                "VALUES('" + firstNameTextField.getText() +
                "', '" + lastNameTextField.getText() +
                "', '" + emailTextField.getText() + "', '" +
                usernameTextField.getText() + "', '" + passwordTextField.getText() + "')";

        Statement insertStatement = connectDb.createStatement();
        insertStatement.executeUpdate(insert);
    }
}

