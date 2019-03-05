package es.iessaldillo.nicol.nicolas_examenfinal_hilos.data.model;

public class CityWeatherInfo {
    boolean error;
    String cityName,description,icon;
    int wind_degrees,clouds,humidity,sunrise,sunset;
    double main_temp,main_temp_max,main_temp_min,wind_speed,rain;

    public CityWeatherInfo(boolean error, String cityName, String description, String icon, int wind_degrees, int clouds, int humidity, double main_temp, double main_temp_max, double main_temp_min, double wind_speed, int sunrise, int sunset, double rain) {
        this.error = error;
        this.cityName = cityName;
        this.description = description;
        this.icon = icon;
        this.wind_degrees = wind_degrees;
        this.clouds = clouds;
        this.humidity = humidity;
        this.main_temp = main_temp;
        this.main_temp_max = main_temp_max;
        this.main_temp_min = main_temp_min;
        this.wind_speed = wind_speed;
        this.sunrise = sunrise;
        this.sunset = sunset;
        this.rain = rain;
    }

    public CityWeatherInfo(boolean error) {
        this.error = error;
    }

    public boolean isError() {
        return error;
    }

    public void setError(boolean error) {
        this.error = error;
    }

    public double getRain() {
        return rain;
    }

    public void setRain(double rain) {
        this.rain = rain;
    }

    public int getSunrise() {
        return sunrise;
    }

    public void setSunrise(int sunrise) {
        this.sunrise = sunrise;
    }

    public int getSunset() {
        return sunset;
    }

    public void setSunset(int sunset) {
        this.sunset = sunset;
    }

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public int getWind_degrees() {
        return wind_degrees;
    }

    public void setWind_degrees(int wind_degrees) {
        this.wind_degrees = wind_degrees;
    }

    public int getClouds() {
        return clouds;
    }

    public void setClouds(int clouds) {
        this.clouds = clouds;
    }

    public int getHumidity() {
        return humidity;
    }

    public void setHumidity(int humidity) {
        this.humidity = humidity;
    }

    public double getMain_temp() {
        return main_temp;
    }

    public void setMain_temp(double main_temp) {
        this.main_temp = main_temp;
    }

    public double getMain_temp_max() {
        return main_temp_max;
    }

    public void setMain_temp_max(double main_temp_max) {
        this.main_temp_max = main_temp_max;
    }

    public double getMain_temp_min() {
        return main_temp_min;
    }

    public void setMain_temp_min(double main_temp_min) {
        this.main_temp_min = main_temp_min;
    }

    public double getWind_speed() {
        return wind_speed;
    }

    public void setWind_speed(double wind_speed) {
        this.wind_speed = wind_speed;
    }
}
