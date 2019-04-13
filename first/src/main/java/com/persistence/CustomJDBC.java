package com.persistence;

import com.util.PropertiesUtil;
import com.util.enums.DataBaseProperties;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;

import java.sql.*;
import java.util.*;

@Component
public class CustomJDBC {

    private static final Logger logger = Logger.getLogger(CustomJDBC.class);
    private static final Properties PROPERTIES = PropertiesUtil.loadPropertiesFile("application.properties");

    private static final String SPACE = " ";
    private static final String COMMA = ",";

    private Connection connection;

    public boolean openConnection() {
        final String databaseName = PROPERTIES.getProperty("jdbc.database.name");
        final String url = PROPERTIES.getProperty("jdbc.url");
        final String user = PROPERTIES.getProperty("jdbc.user");
        final String password = PROPERTIES.getProperty("jdbc.password");
        final String driver = PROPERTIES.getProperty("jdbc.driver");

        try {
            Class.forName(driver);
            connection = DriverManager.getConnection(url, user, password);
            logger.debug(
                    String.format("Opened connection for:\n\tDRIVER - %s\n\tURL - %s\n\tUSER - %s\n\tPASSWORD - %s",
                            driver, url, user, password));
            return true;
        } catch (SQLException | ClassNotFoundException e) {
            logger.error(e.getMessage());
        }
        return false;
    }

    public void closeConnection() {
        try {
            connection.close();
            logger.debug("Connection is closed");
        } catch (SQLException e) {
            logger.error(e.getMessage());
        }
    }

    public boolean createDataBase(String databaseName) {
        return executeCustomQuery("CREATE DATABASE " + databaseName);
    }

    public boolean dropDataBase(String databaseName) {
        return executeCustomQuery("DROP DATABASE " + databaseName);
    }

    public boolean createTable(String tableName, Map<String, List<DataBaseProperties>> columns) {
        StringBuilder query = new StringBuilder("CREATE TABLE " + tableName + "(");
        for (Map.Entry<String, List<DataBaseProperties>> entry : columns.entrySet()) {
            query.append(entry.getKey());
            for (DataBaseProperties property : entry.getValue()) {
                query.append(SPACE).append(property);
            }
            query.append(COMMA);
        }
        if (isLastIndexInString(query.toString(), COMMA)) {
            query.deleteCharAt(query.length() - 1);
        }
        query.append(")");

        return executeCustomQuery(query.toString());
    }

    public boolean dropTable(String tableName) {
        return executeCustomQuery("DROP TABLE " + tableName);
    }

    public boolean isDataBaseExist(String databaseName) {
        try {
            String query = "SHOW DATABASES";
            logger.debug("Executing query '" + query + "'");

            ResultSet databases = connection.createStatement().executeQuery(query);
            List<String> dataBases = resultSetToList(databases);
            return dataBases.contains(databaseName);
        } catch (SQLException e) {
            logger.error(e.getMessage());
        }
        return false;
    }

    private boolean executeCustomQuery(String query) {
        try {
            logger.debug("Executing query -> '" + query + "'");
            return connection.createStatement().executeUpdate(query) != 0;
        } catch (SQLException e) {
            logger.error(e.getMessage());
        }
        return false;
    }

    private boolean isLastIndexInString(String string, String index) {
        return string.lastIndexOf(index) == string.length() - 1;
    }

    private List<String> resultSetToList(ResultSet resultSet) throws SQLException {
        ResultSetMetaData resultSetMetaData = resultSet.getMetaData();
        int columns = resultSetMetaData.getColumnCount();
        List<String> result = new ArrayList<>();
        while (resultSet.next()) {
            for (int index = 1; index <= columns; ++index) {
                result.add(String.valueOf(resultSet.getObject(index)));
            }
        }
        return result;
    }
}