package com.esi.main;

import com.esi.model.City;
import com.esi.service.DatabaseConnection;
import com.esi.service.DatabaseService;

import java.sql.Connection;
import java.util.List;

public class Home {

    public static final String USER = "root";
    public static final String PASSWORD = "root";
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
            System.err.println("Arguments" + args[0] + args[2] + " must be an integer.");
            System.exit(1);
        }
    }
}
