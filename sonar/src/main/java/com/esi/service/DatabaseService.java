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
    private static final Logger logger = LoggerFactory.getLogger(DatabaseService.class);

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
            logger.error(ERROR_MESSAGE, se);
        } catch (Exception e) {
            logger.error(ERROR_MESSAGE, e);
        } finally {
            try {
                if (pstmt != null)
                    pstmt.close();
            } catch (SQLException se) {
                logger.error(ERROR_MESSAGE, se);
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
            logger.error(ERROR_MESSAGE, se);
        } catch (Exception e) {
            logger.error(ERROR_MESSAGE, e);
        } finally {
            try {
                if (pstmt != null)
                    pstmt.close();
            } catch (SQLException se) {
                logger.error(ERROR_MESSAGE, se);
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
            logger.error(ERROR_MESSAGE, se);
        } catch (Exception e) {
            logger.error(ERROR_MESSAGE, e);
        } finally {
            try {
                if (stmt != null)
                    stmt.close();
            } catch (SQLException se) {
                logger.error(ERROR_MESSAGE, se);
            }
        }
        return  cities;
    }
// New method

    public static City getCityByName(Connection conn,String name) {
        PreparedStatement pstmt = null;
        City city = new City();

        try {

            String sql = "SELECT * FROM City where name=?";
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1,name);
            ResultSet rs = pstmt.executeQuery(sql);
            while (rs.next()) {

                city.setIdCity(rs.getInt(IDCITY));
                city.setName(rs.getString(NAME));
                city.setTouristNumber(rs.getInt(TOURISTNUMBER));
                city.setDescription(rs.getString(DESCRIPTION));
            }
            rs.close();
            pstmt.close();
        } catch (SQLException se) {
            logger.error(ERROR_MESSAGE, se);
        } catch (Exception e) {
            logger.error(ERROR_MESSAGE, e);
        } finally {
            try {
                if (pstmt != null)
                    pstmt.close();
            } catch (SQLException se) {
                logger.error(ERROR_MESSAGE, se);
            }
        }
        return city;
    }


}
