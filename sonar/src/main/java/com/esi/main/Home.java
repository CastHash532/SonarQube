package com.esi.main;

import com.esi.model.City;
import com.esi.service.DatabaseConnection;
import com.esi.service.DatabaseService;

import java.sql.Connection;

//slf4j
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

//properties
import java.util.Properties;


public class Home {

    //load config.properties file
    private static final Logger logger = LoggerFactory.getLogger(Home.class);

    private static Properties properties = new Properties();
    static {
        try {
            properties.load(Home.class.getClassLoader().getResourceAsStream("config.properties"));
        } catch (Exception e) {
            logger.error("Error while loading properties file", e);
        }
    }
    
    public static final String USER = properties.getProperty("user");
    public static final String PASSWORD = properties.getProperty("password");
    public static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
    public static final String DB_URL = "jdbc:mysql://localhost:3306/citiesdb";

    public static void main(String args[]) {
        DatabaseConnection db = new DatabaseConnection(USER, PASSWORD, JDBC_DRIVER, DB_URL);
        Connection conn = db.connect();
        try {
            if (args.length > 0) {
                int id = Integer.parseInt(args[0]);
                String name = args[1];
                int numTourist = Integer.parseInt(args[2]);
                String desc = args[3];
                City city1 = new City(id, name, numTourist, desc);
                DatabaseService.addCity(conn, city1);
            }
        }
        catch (NumberFormatException e) {
            Logger logger = org.slf4j.LoggerFactory.getLogger(Home.class);
            logger.error("Arguments" + args[0] + args[2] + " must be an integer.");
            System.exit(1);
        }
    }
}
