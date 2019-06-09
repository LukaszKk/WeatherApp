package app;

import app.weather.info.CurrentWeather;
import app.weather.info.ExpectedWeather;

import java.sql.SQLOutput;

public class Log {

    private static Log single_instance = null;

    private Log()
    {

    }

    public static Log getInstance()
    {
        if (single_instance == null)
            single_instance = new Log();

        return single_instance;
    }

    public void printLog(CurrentWeather weather)
    {
        String str = String.format("CurrentWeather: City: %s \t Date: %s \t Clouds: %.2f \t Humidity: %.2f" +
                "\t Pressure: %.2f \t Rain: %.2f \n\tSnow: %.2f \t WindSpeed: %.2f \t WindDirection: %.2f\n" ,
                weather.getCity(),
                weather.getDate(),
                weather.getClouds(),
                weather.getHumidity(),
                weather.getPressure(),
                weather.getRain(),
                weather.getSnow(),
                weather.getWind_speed(),
                weather.getWind_direction()
        );
        System.out.println(str);


    }

    public void printLog(ExpectedWeather[] weather)
    {
        String str;
        for(int i =0; i< weather.length; i++)
        {
            str = String.format("ExpectedWeather %d: City: %s \t Date: %s \t Clouds: %.2f \t Humidity: %.2f" +
                            "\t Pressure: %.2f \t Rain: %.2f \n\t Snow: %.2f \t WindSpeed: %.2f \t WindDirection: %.2f" ,
                    i+1,
                    weather[i].getCity(),
                    weather[i].getDateTime(),
                    weather[i].getClouds(),
                    weather[i].getHumidity(),
                    weather[i].getPressure(),
                    weather[i].getRain(),
                    weather[i].getSnow(),
                    weather[i].getWind_speed(),
                    weather[i].getWind_direction()
            );
            System.out.println(str);
        }
    }

}
