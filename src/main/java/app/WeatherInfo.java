package app;

import com.github.fedy2.weather.YahooWeatherService;
import com.github.fedy2.weather.data.Channel;
import com.github.fedy2.weather.data.unit.DegreeUnit;

import java.io.IOException;

public class WeatherInfo
{
    public WeatherInfo() throws IOException
    {
        YahooWeatherService service = new YahooWeatherService();
        Channel channel = service.getForecast("2502265", DegreeUnit.CELSIUS);
        System.out.println(channel.getDescription());
    }
}
