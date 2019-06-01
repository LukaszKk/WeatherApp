package app.weather.info;

import org.openweathermap.api.DataWeatherClient;
import org.openweathermap.api.UrlConnectionDataWeatherClient;
import org.openweathermap.api.model.Clouds;
import org.openweathermap.api.model.Rain;
import org.openweathermap.api.model.Snow;
import org.openweathermap.api.model.Wind;
import org.openweathermap.api.model.currentweather.CurrentWeather;
import org.openweathermap.api.model.forecast.ForecastInformation;
import org.openweathermap.api.model.forecast.hourly.HourlyForecast;
import org.openweathermap.api.query.*;
import org.openweathermap.api.query.currentweather.CurrentWeatherOneLocationQuery;
import org.openweathermap.api.query.forecast.hourly.ByCityName;

public class WeatherInfo
{
    private static final String API_KEY = "69eef3f9f3581f6f8c2f6cacbbc1a445";
    private CityBuilder cityBuilder;
    private DataWeatherClient client;

    public WeatherInfo( CityBuilder cityBuilder )
    {
        this.cityBuilder = cityBuilder;
        this.client = new UrlConnectionDataWeatherClient(API_KEY);
    }

    private CurrentWeatherOneLocationQuery currentWeatherOneLocationQuery()
    {
        return QueryBuilderPicker.pick()
                .currentWeather()                   // get current weather
                .oneLocation()                      // for one location
                .byCityName(cityBuilder.getCityName())
                .countryCode(cityBuilder.getCountryCode())
                .type(Type.ACCURATE)                // with Accurate search
                .language(Language.ENGLISH)         // in English language
                .responseFormat(ResponseFormat.JSON)// with JSON response format
                .unitFormat(UnitFormat.METRIC)      // in metric units
                .build();
    }

    private ByCityName byCityNameForecast()
    {
        return QueryBuilderPicker.pick()
                .forecast()                                         // get forecast
                .hourly()                                           // it should be hourly forecast
                .byCityName(cityBuilder.getCityName())
                .countryCode(cityBuilder.getCountryCode())
                .unitFormat(UnitFormat.METRIC)                      // in Metric units
                .language(Language.ENGLISH)                         // in English
                .count(8)                                           // limit results to X forecasts
                .build();
    }

    public Wind currentWind()
    {
        return client.getCurrentWeather(currentWeatherOneLocationQuery()).getWind();
    }

    public double currentWindSpeed()
    {
        return client.getCurrentWeather(currentWeatherOneLocationQuery()).getWind().getSpeed();
    }

    public double currentWindDirectionDegree()
    {
        return client.getCurrentWeather(currentWeatherOneLocationQuery()).getWind().getDirection().getDegree();
    }

    public Rain currentRain()
    {
        return client.getCurrentWeather(currentWeatherOneLocationQuery()).getRain();
    }

    public Clouds currentClouds()
    {
        return client.getCurrentWeather(currentWeatherOneLocationQuery()).getClouds();
    }

    public double currentCloudsAll()
    {
        return client.getCurrentWeather(currentWeatherOneLocationQuery()).getClouds().getAll();
    }

    public Snow currentSnow()
    {
        return client.getCurrentWeather(currentWeatherOneLocationQuery()).getSnow();
    }

    private CurrentWeather currentWeather()
    {
        return client.getCurrentWeather(currentWeatherOneLocationQuery());
    }

    public double currentTemperature()
    {
        return currentWeather().getMainParameters().getTemperature();
    }

    public double currentHumidity()
    {
        return currentWeather().getMainParameters().getHumidity();
    }

    public double currentPressure()
    {
        return currentWeather().getMainParameters().getPressure();
    }

    public ForecastInformation<HourlyForecast> hourlyWeather()
    {
        return client.getForecastInformation(byCityNameForecast());
    }
}