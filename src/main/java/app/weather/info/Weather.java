package app.weather.info;

public class Weather {
    private String city;
    private String date;
    private double clouds;
    private double humidity;
    private double pressure;
    private double rain;
    private double snow;
    private double temperature;
    private double wind_speed;
    private double wind_direction;


    public Weather(String city, String date,double clouds, double humidity, double pressure, double rain, double snow, double temperature, double wind_speed, double wind_direction) {
        this.city = city;
        this.date = date;
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

    public String getDate() {
        return date;
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
