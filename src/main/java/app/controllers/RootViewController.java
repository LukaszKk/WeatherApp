package app.controllers;

import app.weather.info.*;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import org.openweathermap.api.model.forecast.ForecastInformation;
import org.openweathermap.api.model.forecast.hourly.HourlyForecast;

import java.text.SimpleDateFormat;

public class RootViewController
{
    public TextField city;
    public Label dateLabel;
    public Label tempLabel;
    public Label pressLabel;
    public Label humiLable;
    public Label windSpeedLabel;
    public Label windDirLabel;


    public CurrentWeather weather;
    public ExpectedWeather[] tabExpectedWeather = new ExpectedWeather[8];

    @FXML
    private void initialize()
    {

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


        SimpleDateFormat time_formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String current_time_str = time_formatter.format(System.currentTimeMillis());

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
            tabExpectedWeather[i] = new ExpectedWeather(s, current_time_str, forecast.getDateTime().toString(), forecast.getClouds().getAll(), forecast.getMainParameters().getHumidity(), forecast.getMainParameters().getPressure(), rain, snow, forecast.getMainParameters().getTemperature(), forecast.getWind().getSpeed(), forecast.getWind().getDirection().getDegree() );
            tabExpectedWeather[i].save();
            i++;
        }
    }

    public void showWeather()
    {
        dateLabel.setText(weather.getDate());
        tempLabel.setText(weather.getTemperature().toString());
        pressLabel.setText((weather.getPressure().toString()));
        humiLable.setText((weather.getHumidity().toString()));
        windSpeedLabel.setText((weather.getWind_speed().toString()));
        windDirLabel.setText(weather.getWind_direction().toString());
    }
}
