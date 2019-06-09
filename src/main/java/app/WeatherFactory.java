package app;

import app.weather.info.CurrentWeather;
import app.weather.info.ExpectedWeather;
import app.weather.info.Weather;

public class WeatherFactory {

    public Weather getWeather(String weatherType){
        if(weatherType == null){
            return null;
        }
        if(weatherType.equalsIgnoreCase("CURRENT")){
            return new CurrentWeather();

        } else if(weatherType.equalsIgnoreCase("EXPECTED")){
            return new ExpectedWeather();

        }

        return null;
    }
}
