package com.mood.model;

import java.sql.Connection;
import java.sql.DriverManager;

/**
 * Database connection class connects the application to the database.
 */
public class DatabaseConnection {

    public Connection databaseLink;

    /**
     * getConnection() method establishes the connection to a database
     *
     * @return databaseLink - the database link or connection is stored within the databaseLink
     */
    public Connection getConnection() {
        /* Connection information below */
        String databaseName = "jdbc:mysql://localhost:3306/bankzydb";
        String databaseUser = "root";
        String databasePassword = "sql8967442MnMn!";

        /* Testing if set up and correct imports have been made  and establishing connection */
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            databaseLink = DriverManager.getConnection(databaseName, databaseUser, databasePassword);
        } catch (Exception e) {
            e.printStackTrace();
            e.getCause();
        }

        return databaseLink;
    }
}
