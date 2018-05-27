package com.platforma.concedii.util;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class DbUtil {

    private static Connection dbConnection = null;
    private static final String DB_PROPERTIES_FILE = "db.properties";
    private static final String DB_DRIVER_PROPERTY_NAME = "dbDriver";
    private static final String DB_CONNECTION_URL_PROPERTY_NAME = "connectionUrl";
    private static final String DB_USER_NAME_PROPERTY_NAME = "userName";
    private static final String DB_PASSWORD_PROPERTY_NAME = "password";

    public static Connection getConnection () {
        if(dbConnection != null) {
            return dbConnection;
        } else {
            try(InputStream is = DbUtil.class.getClassLoader().getResourceAsStream(DB_PROPERTIES_FILE);) {
                Properties properties = new Properties();
                if(properties != null) {
                    properties.load(is);
                    String dbDriver  = properties.getProperty(DB_DRIVER_PROPERTY_NAME);
                    String connectionUrl = properties.getProperty(DB_CONNECTION_URL_PROPERTY_NAME);
                    String userName = properties.getProperty(DB_USER_NAME_PROPERTY_NAME);
                    String password = properties.getProperty(DB_PASSWORD_PROPERTY_NAME);
                    Class.forName(dbDriver).newInstance();
                    dbConnection = DriverManager.getConnection(connectionUrl, userName, password);
                }
            } catch (IOException | IllegalAccessException | InstantiationException | SQLException | ClassNotFoundException e) {
                e.printStackTrace();
            }
            return dbConnection;
        }
    }

}
