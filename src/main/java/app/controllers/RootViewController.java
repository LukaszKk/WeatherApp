package app.controllers;

import app.WeatherFactory;
import app.database.ConnectionClass;
import app.weather.info.*;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import org.openweathermap.api.model.forecast.ForecastInformation;
import org.openweathermap.api.model.forecast.hourly.HourlyForecast;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

public class RootViewController
{
    public TextField city;
    public Label dateLabel;
    public Label tempLabel;
    public Label pressLabel;
    public Label humiLabel;
    public Label windSpeedLabel;
    public Label windDirLabel;
    public ChoiceBox choiceRecord;


    public CurrentWeather weather;
    public ExpectedWeather[] tabExpectedWeather = new ExpectedWeather[8];
    WeatherFactory weatherFactory = new WeatherFactory();

    @FXML
    private void initialize()
    {
        initChoiceBox();
    }

    public void searchAction()
    {
        String s = city.getText();
        weather = new CurrentWeather(s);
        weather.save();
        showWeather();

        CityBuilder cityBuilder = new CityBuilder();
        cityBuilder = cityBuilder.cityName(s);
        WeatherInfo wnfo = new WeatherInfo(cityBuilder);


        int i=0;
        ForecastInformation<HourlyForecast> forecastInformation = wnfo.hourlyWeather();
        for( HourlyForecast forecast : forecastInformation.getForecasts() )
        {
            double rain;
            double snow;
            if( forecast.getRain() == null)
                rain = 0.0;
            else
                rain =  forecast.getRain().getThreeHours();
            if(forecast.getSnow() == null)
                snow = 0.0;
            else
                snow = forecast.getSnow().getThreeHours();
            tabExpectedWeather[i] = new ExpectedWeather(s, weather.getDate(), forecast.getDateTime().toString(), forecast.getClouds().getAll(), forecast.getMainParameters().getHumidity(), forecast.getMainParameters().getPressure(), rain, snow, forecast.getMainParameters().getTemperature(), forecast.getWind().getSpeed(), forecast.getWind().getDirection().getDegree() );
            tabExpectedWeather[i].save();
            i++;
        }

        initChoiceBox();

    }

    public void showWeather()
    {
        city.setText(weather.getCity());
        dateLabel.setText(weather.getDate());
        tempLabel.setText(weather.getTemperature().toString());
        pressLabel.setText((weather.getPressure().toString()));
        humiLabel.setText((weather.getHumidity().toString()));
        windSpeedLabel.setText((weather.getWind_speed().toString()));
        windDirLabel.setText(weather.getWind_direction().toString());
    }

    public void initChoiceBox()
    {
        choiceRecord.getItems().clear();
        Connection connection = new ConnectionClass().getConnection();
        try
        {
            String str;
            Statement statement = connection.createStatement();
            String sql = "SELECT * FROM weather;";
            ResultSet resultSet = statement.executeQuery(sql);
            while( resultSet.next() )
            {
                str = resultSet.getString(1) + "; " + resultSet.getString(2);
                choiceRecord.getItems().add(str);
            }
            connection.close();
        } catch( SQLException e )
        {
            e.printStackTrace();
        }

    }

    public void showRecordAction()
    {
        if(choiceRecord.getValue() == null)
            return;
        String str = choiceRecord.getValue().toString();

        str = str.substring(str.indexOf(';')+2);

        weather = loadWeatehrRecord(str);
        tabExpectedWeather = loadExpectedRecord(str);
        showWeather();
    }

    public CurrentWeather loadWeatehrRecord(String str)
    {
        CurrentWeather weather = new CurrentWeather();

        Connection connection = new ConnectionClass().getConnection();
        try {
            Statement statement = connection.createStatement();
            String sql = "SELECT * FROM weather WHERE date = '" + str + "';";
            ResultSet resultSet = statement.executeQuery(sql);
            while( resultSet.next() )
            {
                weather.setCity(resultSet.getString(1));
                weather.setDate(resultSet.getString(2));
                weather.setClouds(resultSet.getDouble(3));
                weather.setHumidity(resultSet.getDouble(4));
                weather.setPressure(resultSet.getDouble(5));
                weather.setRain(resultSet.getDouble(6));
                weather.setSnow(resultSet.getDouble(7));
                weather.setTemperature(resultSet.getDouble(8));
                weather.setWind_speed(resultSet.getDouble(9));
                weather.setWind_direction(resultSet.getDouble(10));
            }

            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return  weather;
    }


    public ExpectedWeather[] loadExpectedRecord(String str)
    {
        ExpectedWeather[] tab = new ExpectedWeather[8];

        Connection connection = new ConnectionClass().getConnection();
        try {
            Statement statement = connection.createStatement();
            String sql = "SELECT * FROM expectedWeather WHERE dateRecord = '" + str + "';";
            ResultSet resultSet = statement.executeQuery(sql);
            int i=0;
            while( resultSet.next() )
            {
                tab[i] = new ExpectedWeather();
                tab[i].setCity(resultSet.getString(1));
                tab[i].setDateRecord(resultSet.getString(2));
                tab[i].setDateTime(resultSet.getString(3));
                tab[i].setClouds(resultSet.getDouble(4));
                tab[i].setHumidity(resultSet.getDouble(5));
                tab[i].setPressure(resultSet.getDouble(6));
                tab[i].setRain(resultSet.getDouble(7));
                tab[i].setSnow(resultSet.getDouble(8));
                tab[i].setTemperature(resultSet.getDouble(9));
                tab[i].setWind_speed(resultSet.getDouble(10));
                tab[i].setWind_direction(resultSet.getDouble(11));
            }

            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return tab;
    }
}
