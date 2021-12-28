package com.esi.service;

import com.esi.model.City;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

//logger
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class DatabaseService {

    private static final String IDCITY = "idCity";
    private static final String TOURISTNUMBER = "touristNumber";
    private static final String DESCRIPTION = "description";
    private static final String NAME = "name";
    private static final String ERROR_MESSAGE = "Error while executing query";

    private DatabaseService() {
        throw new IllegalStateException("Utility class");
    }

    public static int addCity(Connection conn, City city) {
        PreparedStatement pstmt = null;
        int i = -1;
        try {
            String sql = "INSERT INTO City " + "VALUES (?,?,?,?)";
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, city.getIdCity());
            pstmt.setString(2, city.getName());
            pstmt.setInt(3, city.getTouristNumber());
            pstmt.setString(4, city.getDescription());
            i= pstmt.executeUpdate();
            pstmt.close();
        } catch (SQLException se) {
            se.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (pstmt != null) pstmt.close();
            } catch (SQLException se2) {
                Logger logger = LoggerFactory.getLogger(DatabaseService.class);
                logger.error(ERROR_MESSAGE, se2);
            }
        }
        return i;
    }


   public static City getCity(Connection conn,int idCity) {
        PreparedStatement pstmt = null;
        City city = new City();
        try {

            String sql = "SELECT * FROM City where idCity=?";
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1,idCity);
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                city.setIdCity(rs.getInt(IDCITY));
                city.setName(rs.getString(NAME));
                city.setTouristNumber(rs.getInt(TOURISTNUMBER));
                city.setDescription(rs.getString(DESCRIPTION));
            }
            rs.close();
            pstmt.close();
        } catch (SQLException se) {
            se.printStackTrace();
        } finally {
            try {
                if (pstmt != null) pstmt.close();
            } catch (SQLException se2) {
                Logger logger = LoggerFactory.getLogger(DatabaseService.class);
                logger.error(ERROR_MESSAGE, se2);
            }
        }
        return  city;
    }


    public static List<City> getCities(Connection conn) {
        Statement stmt = null;
        List<City> cities = new ArrayList<City>();

        try {

            String sql = "SELECT * FROM City";
            stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {
                City city = new City();
                city.setIdCity(rs.getInt(IDCITY));
                city.setName(rs.getString(NAME));
                city.setTouristNumber(rs.getInt(TOURISTNUMBER));
                city.setDescription(rs.getString(DESCRIPTION));
                cities.add(city);
            }
            rs.close();
            stmt.close();
        } catch (SQLException se) {
            se.printStackTrace();
        } finally {
            try {
                if (stmt != null) stmt.close();
            } catch (SQLException se2) {
                Logger logger = LoggerFactory.getLogger(DatabaseService.class);
                logger.error(ERROR_MESSAGE, se2);
            }
        }
        return  cities;
    }
// New method

    public static City getCityByName(Connection conn,String name) {
        Statement stmt = null;
        City city = new City();

        try {

            String sql = "SELECT * FROM City where name='"+name+"'";
            stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {

                city.setIdCity(rs.getInt(IDCITY));
                city.setName(rs.getString(NAME));
                city.setTouristNumber(rs.getInt(TOURISTNUMBER));
                city.setDescription(rs.getString(DESCRIPTION));
            }
            rs.close();
            stmt.close();
        } catch (SQLException se) {
            se.printStackTrace();
        } finally {
            try {
                if (stmt != null) stmt.close();
            } catch (SQLException se2) {
                Logger logger = LoggerFactory.getLogger(DatabaseService.class);
                logger.error(ERROR_MESSAGE, se2);
            }
        }
        return city;
    }


}
