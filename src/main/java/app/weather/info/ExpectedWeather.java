package app.weather.info;

import app.database.ConnectionClass;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class ExpectedWeather extends Weather {

    private String dateRecord;
    private String dateTime;

    public ExpectedWeather()
    {
        super();
        this.dateRecord = "empty";
        this.dateTime = "empty";

    }
    public ExpectedWeather(String city, String dateRecord, String dateTime,double clouds, double humidity, double pressure, double rain, double snow, double temperature, double wind_speed, double wind_direction) {

        super(city, clouds, humidity, pressure, rain, snow, temperature, wind_speed, wind_direction);

        this.dateRecord = dateRecord;
        this.dateTime = dateTime;

    }

    public void save()
    {
        Connection connection = new ConnectionClass().getConnection();
        try {
            Statement statement = connection.createStatement();
            String sql = "INSERT INTO expectedWeather VALUES('" + super.getCity() + "', '" + this.getDateRecord() + "', '" + this.getDateTime() + "', '" + super.getClouds() + "', '" + super.getHumidity() + "', '" + super.getPressure() + "', '" + super.getRain() + "', '" + super.getSnow() + "', '" + super.getTemperature() + "', '" + super.getWind_speed() + "', '" + super.getWind_direction() +"');";
            statement.executeUpdate(sql);
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    public String getDateRecord() {
        return dateRecord;
    }

    public void setDateRecord(String dateRecord)
    {
        this.dateRecord = dateRecord;
    }

    public String getDateTime() {
        return dateTime;
    }

    public void setDateTime(String dateTime)
    {
        this.dateTime = dateTime;
    }






}
