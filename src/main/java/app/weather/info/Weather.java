package app.weather.info;

public class Weather {
    private String city;
    private double clouds;
    private double humidity;
    private double pressure;
    private double rain;
    private double snow;
    private double temperature;
    private double wind_speed;
    private double wind_direction;

    public Weather()
    {
        this.city = "empty";
        this.clouds = 0.0;
        this.humidity = 0.0;
        this.pressure = 0.0;
        this.rain = 0.0;
        this.snow = 0.0;
        this.temperature = 0.0;
        this.wind_speed = 0.0;
        this.wind_direction = 0.0;
    }

    public Weather(String city,double clouds, double humidity, double pressure, double rain, double snow, double temperature, double wind_speed, double wind_direction) {
        this.city = city;
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

    public void setCity(String city)
    {
        this.city = city;
    }

    public Double getClouds() {
        return clouds;
    }

    public void setClouds(double clouds)
    {
        this.clouds = clouds;
    }

    public Double getHumidity() {
        return humidity;
    }

    public void setHumidity(double humidity)
    {
        this.humidity = humidity;
    }

    public Double getPressure() {
        return pressure;
    }

    public void setPressure(double pressure)
    {
        this.pressure = pressure;
    }

    public Double getRain() {
        return rain;
    }

    public void setRain(double rain)
    {
        this.rain = rain;
    }

    public Double getSnow() {
        return snow;
    }

    public void setSnow(double snow)
    {
        this.snow = snow;
    }

    public Double getTemperature() {
        return temperature;
    }

    public void setTemperature(double temperature)
    {
        this.temperature = temperature;
    }

    public Double getWind_speed() {
        return wind_speed;
    }

    public void setWind_speed(double wind_speed)
    {
        this.wind_speed = wind_speed;
    }

    public Double getWind_direction() {
        return wind_direction;
    }

    public void setWind_direction(double wind_direction)
    {
        this.wind_direction = wind_direction;
    }


}
