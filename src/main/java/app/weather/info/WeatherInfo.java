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

    /**
     * sets city information
     * @param cityBuilder
     */
    public WeatherInfo( CityBuilder cityBuilder )
    {
        this.cityBuilder = cityBuilder;
        this.client = new UrlConnectionDataWeatherClient(API_KEY);
    }

    private CurrentWeatherOneLocationQuery currentWeatherOneLocationQuery() {
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

    private CurrentWeather currentWeather()
    {
        return client.getCurrentWeather(currentWeatherOneLocationQuery());
    }

    private Wind currentWind()
    {
        return currentWeather().getWind();
    }

    public double currentWindSpeed() {
        return currentWind().getSpeed();
    }

    public double currentWindDirectionDegree() {
        if( currentWind().getDirection() == null )
            return 0;
        return currentWind().getDirection().getDegree();
    }

    public Rain currentRain()
    {
        return currentWeather().getRain();
    }

    private Clouds currentClouds()
    {
        return currentWeather().getClouds();
    }

    public double currentCloudsAll() {
        return currentClouds().getAll();
    }

    public Snow currentSnow()
    {
        return currentWeather().getSnow();
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

    private ByCityName byCityNameForecast() {
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

    /**
     * returns weather info every 3 hour
     * @return
     */
    public ForecastInformation<HourlyForecast> hourlyWeather() {
        return client.getForecastInformation(byCityNameForecast());
    }

}