package app.controllers;

import app.Log;
import app.WeatherFactory;
import app.database.ConnectionClass;
import app.weather.info.*;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import org.openweathermap.api.model.forecast.ForecastInformation;
import org.openweathermap.api.model.forecast.hourly.HourlyForecast;

import java.io.File;
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

    public Label E1Date, E2Date, E3Date, E4Date, E5Date, E6Date, E7Date, E8Date;
    public Label E1Temp, E2Temp, E3Temp, E4Temp, E5Temp, E6Temp, E7Temp, E8Temp;
    public Label E1Pre, E2Pre, E3Pre, E4Pre, E5Pre, E6Pre, E7Pre, E8Pre;
    public Label E1Humi, E2Humi, E3Humi, E4Humi, E5Humi, E6Humi, E7Humi, E8Humi;
    public Label E1WindSpeed, E2WindSpeed, E3WindSpeed, E4WindSpeed, E5WindSpeed, E6WindSpeed, E7WindSpeed, E8WindSpeed;
    public Label E1WindDire, E2WindDire, E3WindDire, E4WindDire, E5WindDire, E6WindDire, E7WindDire, E8WindDire;
    public ImageView weatherImage;

    public CurrentWeather weather;
    public ExpectedWeather[] tabExpectedWeather = new ExpectedWeather[8];
    public WeatherFactory weatherFactory = new WeatherFactory();
    public Log log;

    @FXML
    private void initialize()
    {
        initChoiceBox();
        log = Log.getInstance();
    }

    public void searchAction()
    {
        String s = city.getText();
        weather = new CurrentWeather(s);
        weather.save();
        log.printLog(weather);

        CityBuilder cityBuilder = new CityBuilder();
        cityBuilder = cityBuilder.cityName(s);
        WeatherInfo wnfo = new WeatherInfo(cityBuilder);


        int i=0;
        ForecastInformation<HourlyForecast> forecastInformation = wnfo.hourlyWeather();
        for( HourlyForecast forecast : forecastInformation.getForecasts() ) {
            double rain;
            double snow;
            if (forecast.getRain() == null)
                rain = 0.0;
            else {
                rain = forecast.getRain().getThreeHours();
                weatherImage.setImage(new Image(new File("src/main/java/images/rain.png").toURI().toString()));
            }
            if(forecast.getSnow() == null)
                snow = 0.0;
            else {
                snow = forecast.getSnow().getThreeHours();
                weatherImage.setImage(new Image(new File("src/main/java/images/snow.png").toURI().toString()));
            }
            if (forecast.getClouds() == null) {
                weatherImage.setImage(new Image(new File("src/main/java/images/sunny.png").toURI().toString()));
                weatherImage.setVisible(true);
            } else {
                weatherImage.setImage(new Image(new File("src/main/java/images/cloudy.png").toURI().toString()));
                weatherImage.setVisible(true);
            }
            tabExpectedWeather[i] = new ExpectedWeather(s, weather.getDate(), forecast.getDateTime().toString(), forecast.getClouds().getAll(), forecast.getMainParameters().getHumidity(), forecast.getMainParameters().getPressure(), rain, snow, forecast.getMainParameters().getTemperature(), forecast.getWind().getSpeed(), forecast.getWind().getDirection().getDegree() );
            tabExpectedWeather[i].save();
            i++;
        }

        log.printLog(tabExpectedWeather);

        initChoiceBox();
        showWeather();

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

        E1Date.setText(tabExpectedWeather[0].getDateTime());
        E1Temp.setText(tabExpectedWeather[0].getTemperature().toString());
        E1Pre.setText(tabExpectedWeather[0].getPressure().toString());
        E1Humi.setText(tabExpectedWeather[0].getHumidity().toString());
        E1WindSpeed.setText(tabExpectedWeather[0].getWind_speed().toString());
        E1WindDire.setText(tabExpectedWeather[0].getWind_direction().toString());

        E2Date.setText(tabExpectedWeather[1].getDateTime());
        E2Temp.setText(tabExpectedWeather[1].getTemperature().toString());
        E2Pre.setText(tabExpectedWeather[1].getPressure().toString());
        E2Humi.setText(tabExpectedWeather[1].getHumidity().toString());
        E2WindSpeed.setText(tabExpectedWeather[1].getWind_speed().toString());
        E2WindDire.setText(tabExpectedWeather[1].getWind_direction().toString());

        E3Date.setText(tabExpectedWeather[2].getDateTime());
        E3Temp.setText(tabExpectedWeather[2].getTemperature().toString());
        E3Pre.setText(tabExpectedWeather[2].getPressure().toString());
        E3Humi.setText(tabExpectedWeather[2].getHumidity().toString());
        E3WindSpeed.setText(tabExpectedWeather[2].getWind_speed().toString());
        E3WindDire.setText(tabExpectedWeather[2].getWind_direction().toString());

        E4Date.setText(tabExpectedWeather[3].getDateTime());
        E4Temp.setText(tabExpectedWeather[3].getTemperature().toString());
        E4Pre.setText(tabExpectedWeather[3].getPressure().toString());
        E4Humi.setText(tabExpectedWeather[3].getHumidity().toString());
        E4WindSpeed.setText(tabExpectedWeather[3].getWind_speed().toString());
        E4WindDire.setText(tabExpectedWeather[3].getWind_direction().toString());

        E5Date.setText(tabExpectedWeather[4].getDateTime());
        E5Temp.setText(tabExpectedWeather[4].getTemperature().toString());
        E5Pre.setText(tabExpectedWeather[4].getPressure().toString());
        E5Humi.setText(tabExpectedWeather[4].getHumidity().toString());
        E5WindSpeed.setText(tabExpectedWeather[4].getWind_speed().toString());
        E5WindDire.setText(tabExpectedWeather[4].getWind_direction().toString());

        E6Date.setText(tabExpectedWeather[5].getDateTime());
        E6Temp.setText(tabExpectedWeather[5].getTemperature().toString());
        E6Pre.setText(tabExpectedWeather[5].getPressure().toString());
        E6Humi.setText(tabExpectedWeather[5].getHumidity().toString());
        E6WindSpeed.setText(tabExpectedWeather[5].getWind_speed().toString());
        E6WindDire.setText(tabExpectedWeather[5].getWind_direction().toString());

        E7Date.setText(tabExpectedWeather[6].getDateTime());
        E7Temp.setText(tabExpectedWeather[6].getTemperature().toString());
        E7Pre.setText(tabExpectedWeather[6].getPressure().toString());
        E7Humi.setText(tabExpectedWeather[6].getHumidity().toString());
        E7WindSpeed.setText(tabExpectedWeather[6].getWind_speed().toString());
        E7WindDire.setText(tabExpectedWeather[6].getWind_direction().toString());

        E8Date.setText(tabExpectedWeather[7].getDateTime());
        E8Temp.setText(tabExpectedWeather[7].getTemperature().toString());
        E8Pre.setText(tabExpectedWeather[7].getPressure().toString());
        E8Humi.setText(tabExpectedWeather[7].getHumidity().toString());
        E8WindSpeed.setText(tabExpectedWeather[7].getWind_speed().toString());
        E8WindDire.setText(tabExpectedWeather[7].getWind_direction().toString());

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
        log.printLog(weather);
        tabExpectedWeather = loadExpectedRecord(str);
        log.printLog(tabExpectedWeather);
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
                i++;
            }

            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return tab;
    }
}
