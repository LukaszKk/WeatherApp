package app.weather.info;

public class CityBuilder
{
    private String cityName;
    private String countryCode;

    public CityBuilder()
    {
        cityName = "Krakow";
        countryCode = "PL";
    }

    public CityBuilder cityName(String cityName)
    {
        this.cityName = cityName;
        return this;
    }

    public CityBuilder countryCode(String countryCode)
    {
        this.countryCode = countryCode;
        return this;
    }

    public String getCityName()
    {
        return cityName;
    }

    public String getCountryCode()
    {
        return countryCode;
    }
}
