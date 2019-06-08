package app.weather.info;

public class ExpectedWeather {

    private String city;
    private String dateRecord;
    private String dateTime;
    private double clouds;
    private double humidity;
    private double rain;
    private double snow;
    private double temperature;
    private double pressure;
    private double wind_speed;
    private double wind_direction;

    public ExpectedWeather(String city, String dateRecord, String dateTime,double clouds, double humidity, double pressure, double rain, double snow, double temperature, double wind_speed, double wind_direction) {
        this.city = city;
        this.dateRecord = dateRecord;
        this.dateTime = dateTime;
        this.clouds = clouds;
        this.humidity = humidity;
        this.pressure = pressure;
        this.rain = rain;
        this.snow = snow;
        this.temperature = temperature;
        this.wind_speed = wind_speed;
        this.wind_direction = wind_direction;
    }


    public String getCity() {
        return city;
    }

    public String getDateRecord() {
        return dateRecord;
    }

    public String getDateTime() {
        return dateTime;
    }

    public double getClouds() {
        return clouds;
    }

    public double getHumidity() {
        return humidity;
    }

    public double getPressure() {
        return pressure;
    }

    public double getRain() {
        return rain;
    }

    public double getSnow() {
        return snow;
    }

    public double getTemperature() {
        return temperature;
    }

    public double getWind_speed() {
        return wind_speed;
    }

    public double getWind_direction() {
        return wind_direction;
    }
}
