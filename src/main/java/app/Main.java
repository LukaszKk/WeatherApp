package app;

import app.database.ConnectionClass;
import app.weather.info.CityBuilder;
import app.weather.info.WeatherInfo;
import org.openweathermap.api.model.forecast.ForecastInformation;
import org.openweathermap.api.model.forecast.hourly.HourlyForecast;

public class Main
{
    private static void createDB()
    {
        ConnectionClass.createDB();
        ConnectionClass.createTables();
    }

    public static void main( String[] args )
    {
        createDB();

        CityBuilder cityBuilder = new CityBuilder();
        cityBuilder = cityBuilder.cityName("Krakow");
        WeatherInfo wnfo = new WeatherInfo(cityBuilder);

        System.out.println( wnfo.currentClouds() );
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
