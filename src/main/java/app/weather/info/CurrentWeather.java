package app.weather.info;

import app.database.ConnectionClass;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;

public class CurrentWeather extends  Weather{

    private String date;


    public CurrentWeather()
    {
        super();
        this.date = "empty";
    }

    public CurrentWeather(String city, String date,double clouds, double humidity, double pressure, double rain, double snow, double temperature, double wind_speed, double wind_direction) {

        super(city, clouds, humidity, pressure, rain, snow, temperature, wind_speed, wind_direction);
        this.date = date;
    }

    public CurrentWeather(String city, String code)
    {
        super();
        CityBuilder cityBuilder = new CityBuilder();
        cityBuilder = cityBuilder.cityName(city).countryCode(code);
        WeatherInfo wnfo = new WeatherInfo(cityBuilder);

        double rain;
        double snow;
        if( wnfo.currentRain() == null)
            rain = 0.0;
        else
            rain = wnfo.currentRain().getThreeHours();

        if( wnfo.currentSnow() == null)
            snow = 0.0;
        else
            snow = wnfo.currentSnow().getThreeHours();

        SimpleDateFormat time_formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String current_time_str = time_formatter.format(System.currentTimeMillis());

        super.setCity(city);
        this.date = current_time_str;
        super.setClouds(wnfo.currentCloudsAll());
        super.setHumidity( wnfo.currentHumidity());
        super.setPressure(wnfo.currentPressure());
        super.setRain(rain);
        super.setSnow(snow);
        super.setTemperature(wnfo.currentTemperature());
        super.setWind_speed(wnfo.currentWindSpeed());
        super.setWind_direction(wnfo.currentWindDirectionDegree());
    }


    public void save()
    {
        Connection connection = new ConnectionClass().getConnection();
        try {
            Statement statement = connection.createStatement();
            String sql = "INSERT INTO weather VALUES('" + super.getCity() + "', '" + this.date + "', '" + super.getClouds() + "', '" + super.getHumidity() + "', '" + super.getPressure() + "', '" + super.getRain() + "', '" + super.getSnow() + "', '" + super.getTemperature() + "', '" + super.getWind_speed() + "', '" + super.getWind_direction() +"');";
            statement.executeUpdate(sql);
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public String getDate() {
        return date;
    }

    public void setDate( String date)
    {
        this.date = date;
    }

}
