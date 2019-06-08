package app.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class ConnectionClass
{
    private Connection connection;

    /**
     * @return connection with mysql driver
     */
    public Connection getConnection()
    {
        String dbName = "weather_db";
        String userName = "root";
        String password = "";

        try
        {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection("jdbc:mysql://localhost/" + dbName, userName, password);
        } catch( ClassNotFoundException | SQLException e )
        {
            e.printStackTrace();
        }

        try
        {
            if( !connection.isValid(2) )
            {
                connection = null;
                throw new SQLException("Invalid Connection");
            }
        } catch( SQLException e )
        {
            e.printStackTrace();
        }
        return connection;
    }

    /**
     * creates DB
     */
    public static void createDB() {
        try {
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost/?user=root&password=");
            Statement statement = connection.createStatement();
            statement.execute("CREATE DATABASE IF NOT EXISTS weather_db");
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * creates tables in DB
     */
    public static void createTables() {

        Connection connection = new ConnectionClass().getConnection();
        try {
            Statement statement = connection.createStatement();
            String sql = "CREATE TABLE IF NOT EXISTS weather(city VARCHAR(100), date VARCHAR(100), clouds DOUBLE, humidity DOUBLE, pressure DOUBLE, rain DOUBLE, snow DOUBLE, temperature DOUBLE, wind_speed DOUBLE, wind_direction DOUBLE);";
            statement.execute(sql);
            sql = "CREATE TABLE IF NOT EXISTS expectedWeather(city VARCHAR(100),dateRecord VARCHAR(100), dateTime VARCHAR(100), clouds DOUBLE, humidity DOUBLE, pressure DOUBLE, rain DOUBLE, snow DOUBLE, temperature DOUBLE, wind_speed DOUBLE, wind_direction DOUBLE);";
            statement.execute(sql);
            statement.close();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
