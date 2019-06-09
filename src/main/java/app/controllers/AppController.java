package app.controllers;

import app.database.ConnectionClass;
import app.weather.info.*;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.openweathermap.api.model.forecast.ForecastInformation;
import org.openweathermap.api.model.forecast.hourly.HourlyForecast;

import java.io.IOException;
import java.net.URL;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class AppController
{
    private CurrentWeather weather;
    private ExpectedWeather[] tabExpectredWeather = new ExpectedWeather[8];
    //private RootViewController view = new RootViewController();
    public AppController()
    {
        createDB();

        //getWeatherInfo();
    }

    /**
     * creates DB and tables
     */
    private static void createDB()
    {
        ConnectionClass.createDB();
        ConnectionClass.createTables();
    }


    public void loadView( Stage stage, String name ) throws IOException
    {
        URL url = Paths.get("src/main/java/app/views/" + name + ".fxml").toUri().toURL();
        Parent root = FXMLLoader.load(url);
        stage.setTitle("Weather Info");
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.setMinWidth(800);
        stage.setMinHeight(600);
        stage.show();
    }

    /* TODO: DELETE OR CHANGE */
    private void getWeatherInfo()
    {
        CityBuilder cityBuilder = new CityBuilder();
        cityBuilder = cityBuilder.cityName("Krakow");
        WeatherInfo wnfo = new WeatherInfo(cityBuilder);


        System.out.println( wnfo.currentCloudsAll() );
        System.out.println( wnfo.currentHumidity() );
        System.out.println( wnfo.currentPressure() );
        System.out.println( wnfo.currentRain() );
        System.out.println( wnfo.currentSnow() );
        System.out.println( wnfo.currentTemperature() );
        System.out.println( wnfo.currentWindSpeed() );
        System.out.println( wnfo.currentWindDirectionDegree() );


        ForecastInformation<HourlyForecast> forecastInformation = wnfo.hourlyWeather();
        for( HourlyForecast forecast : forecastInformation.getForecasts() )
        {
            System.out.println(print(forecast));
        }

    }

    /* TODO: DELETE OR CHANGE */
    private static String print( HourlyForecast hourlyForecast )
    {
        return String.format( "Date: %s \t Temp: %.2f \t Humidity: %.2f \t Pressure: %.2f",
                hourlyForecast.getDateTime().toString(),
                hourlyForecast.getMainParameters().getTemperature(),
                hourlyForecast.getMainParameters().getHumidity(),
                hourlyForecast.getMainParameters().getPressure()
        );
    }


}
