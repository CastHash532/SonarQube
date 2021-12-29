package com.esi.service;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

//slf4j
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DatabaseConnection {

    private String USER;
    private String PASS;
    private String JDBC_DRIVER;
    private String DB_URL;
    Logger logger = LoggerFactory.getLogger(DatabaseConnection.class);


    public DatabaseConnection(String USER, String PASS, String JDBC_DRIVER, String DB_URL) {
        this.USER = USER;
        this.PASS = PASS;
        this.JDBC_DRIVER = JDBC_DRIVER;
        this.DB_URL = DB_URL;
    }

    public  Connection connect() {
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(DB_URL, USER, PASS);
            Class.forName(JDBC_DRIVER);
        } catch (SQLException e) {
            logger.error("Connection failed", e);
        } catch (ClassNotFoundException e) {
            logger.error("Driver not found", e);
        }
        return conn;
    }

    public  void disconnect(Connection conn ) {
        try {
            conn.close();
        } catch (SQLException e) {
            logger.error("Disconnection failed", e);
        }
    }

    public void createDb(Connection conn) {
        Statement stmt = null;
        try {
            stmt = conn.createStatement();
            String sql =  "CREATE TABLE   City " +
                    "(idCity INTEGER not NULL, " +
                    " name VARCHAR(255), " +
                    " touristNumber INTEGER, " +
                    " description VARCHAR(255), " +
                    " PRIMARY KEY ( idCity ))";
            stmt.executeUpdate(sql);
            stmt.close();
        } catch(SQLException se) {
            logger.error("Error while creating table", se);
        } catch(Exception e) {
            logger.error("Error while creating table", e);
        } finally {
            try {
                if (stmt != null) stmt.close();
            } catch (SQLException se2) {
                logger.error("Error while creating table", se2);
            }
        }
    }

}
