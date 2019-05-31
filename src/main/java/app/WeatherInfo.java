package app;

import org.openweathermap.api.DataWeatherClient;
import org.openweathermap.api.UrlConnectionDataWeatherClient;
import org.openweathermap.api.model.currentweather.CurrentWeather;
import org.openweathermap.api.model.forecast.ForecastInformation;
import org.openweathermap.api.model.forecast.hourly.HourlyForecast;
import org.openweathermap.api.query.*;
import org.openweathermap.api.query.currentweather.CurrentWeatherOneLocationQuery;
import org.openweathermap.api.query.forecast.hourly.ByCityName;

public class WeatherInfo
{
    private static final String API_KEY = "69eef3f9f3581f6f8c2f6cacbbc1a445";

    public void dailyWeather()
    {
        DataWeatherClient client = new UrlConnectionDataWeatherClient(API_KEY);
        CurrentWeatherOneLocationQuery currentWeatherOneLocationQuery = QueryBuilderPicker.pick()
                .currentWeather()                   // get current weather
                .oneLocation()                      // for one location
                .byCityName("Krakow")
                .countryCode("PL")
                .type(Type.ACCURATE)                // with Accurate search
                .language(Language.ENGLISH)         // in English language
                .responseFormat(ResponseFormat.JSON)// with JSON response format
                .unitFormat(UnitFormat.METRIC)      // in metric units
                .build();
        CurrentWeather currentWeather = client.getCurrentWeather(currentWeatherOneLocationQuery);
        System.out.println(prettyPrint(currentWeather));
    }

    public void hourlyWeather()
    {
        DataWeatherClient client = new UrlConnectionDataWeatherClient(API_KEY);
        ByCityName byCityNameForecast = QueryBuilderPicker.pick()
                .forecast()                                         // get forecast
                .hourly()                                           // it should be hourly forecast
                .byCityName("Krakow")                              // for Kharkiv city
                .countryCode("PL")                                  // in Ukraine
                .unitFormat(UnitFormat.METRIC)                      // in Metric units
                .language(Language.ENGLISH)                         // in English
                .count(7)                                           // limit results to 5 forecasts
                .build();
        ForecastInformation<HourlyForecast> forecastInformation = client.getForecastInformation(byCityNameForecast);
        System.out.println("Forecasts for " + forecastInformation.getCity() + ":");
        for( HourlyForecast forecast : forecastInformation.getForecasts() )
        {
            System.out.println(prettyPrint(forecast));
        }
    }

    private String prettyPrint( CurrentWeather currentWeather )
    {
        return String.format(
                "Current weather in %s(%s):\ntemperature: %.1f ℃\nhumidity: %.1f %%\npressure: %.1f hPa\n",
                currentWeather.getCityName(), currentWeather.getSystemParameters().getCountry(),
                currentWeather.getMainParameters().getTemperature(),
                currentWeather.getMainParameters().getHumidity(),
                currentWeather.getMainParameters().getPressure()
        );
    }

    private String prettyPrint( HourlyForecast hourlyForecast )
    {
        return String.format(
                "Forecast for %s:\ntemperature: %.1f ℃\nhumidity: %.1f %%\npressure: %.1f hPa\n",
                hourlyForecast.getDateTime().toString(),
                hourlyForecast.getMainParameters().getTemperature(),
                hourlyForecast.getMainParameters().getHumidity(),
                hourlyForecast.getMainParameters().getPressure()
        );
    }
}
