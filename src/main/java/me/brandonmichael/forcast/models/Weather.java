package me.brandonmichael.forcast.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Weather {


    private HashMap<String, String> currently = new HashMap<>();

    private Forecast minutely = new Forecast();

    private Forecast hourly = new Forecast();

    private Forecast daily = new Forecast();

    private HashMap<String, String> currentForecastInts = new HashMap<>();

    public Weather() {
        this.currentForecastInts = parseMap(currently);
    }

    public Weather(Weather weather) {
        ArrayList<HashMap<String, String>> daily = new ArrayList<>();
        ArrayList<HashMap<String, String>> hourly = new ArrayList<>();

        this.currently = parseMap(weather.getCurrently());

        this.hourly = new Forecast(weather.getHourly().getSummary(), weather.getHourly().getIcon());

        int i = 0;
        for (HashMap<String, String> forecast : weather.getHourly().getData()) {
            if (i < 12){
                hourly.add(parseMap(forecast));
            } else {
                break;
            }

            i++;
        }

        this.hourly.setData(hourly);

        this.daily = new Forecast(weather.getDaily().getSummary(), weather.getDaily().getIcon());

        for (HashMap<String, String> forecast : weather.getDaily().getData()) {
            daily.add(parseMap(forecast));
        }

        this.daily.setData(daily);
    }

    /* probably should be in a separate class */

    public HashMap<String, String> parseMap(HashMap<String, String> conditions) {
        HashMap<String, String> parsedMap = new HashMap<>();

        for (Map.Entry<String, String> condition : conditions.entrySet()) {
            String forecastKey = condition.getKey();
            String forecastValue = condition.getValue();
            switch(forecastKey) {
                case "temperature": double temp = Double.parseDouble(forecastValue);
                    long temperature = Math.round(temp);
                    String tempString = Long.toString(temperature);
                    parsedMap.put(forecastKey, tempString);
                    break;
                case "temperatureMax": double tempMax = Double.parseDouble(forecastValue);
                    long temperatureMax = Math.round(tempMax);
                    String tempMaxString = Long.toString(temperatureMax);
                    parsedMap.put(forecastKey, tempMaxString);
                    break;
                case "temperatureMin": double tempMin = Double.parseDouble(forecastValue);
                    long temperatureMin = Math.round(tempMin);
                    String tempMinString = Long.toString(temperatureMin);
                    parsedMap.put(forecastKey, tempMinString);
                    break;
                case "apparentTemperature": double aptemp = Double.parseDouble(forecastValue);
                    long aptempLong = Math.round(aptemp);
                    String aptempString = Long.toString(aptempLong);
                    parsedMap.put(forecastKey, aptempString);
                    break;
                case "precipProbability": double rainChance = Double.parseDouble(forecastValue);
                    rainChance = rainChance * 100;
                    long longrainChance = Math.round(rainChance);
                    String rainChanceString = Long.toString(longrainChance);
                    parsedMap.put(forecastKey, rainChanceString);
                    break;
                case "windSpeed": double windSpeed = Double.parseDouble(forecastValue);
                    long windLong = Math.round(windSpeed);
                    String windString = Long.toString(windLong);
                    parsedMap.put(forecastKey, windString);
                    break;
                case "humidity": double humidity = Double.parseDouble(forecastValue);
                    humidity = humidity * 100;
                    long longHumidity = Math.round(humidity);
                    String humidityString = Long.toString(longHumidity);
                    parsedMap.put(forecastKey, humidityString);
                    break;
                case "dewPoint": double dewPoint = Double.parseDouble(forecastValue);
                    long dewPointLong = Math.round(dewPoint);
                    String dewPointString = Long.toString(dewPointLong);
                    parsedMap.put(forecastKey, dewPointString);
                    break;
                case "visibility": double visibility = Double.parseDouble(forecastValue);
                    long visibilityLong = Math.round(visibility);
                    String visibilityString = Long.toString(visibilityLong);
                    parsedMap.put(forecastKey, visibilityString);
                    break;
                case "apparentTemperatureMin": double aptempMin = Double.parseDouble(forecastValue);
                    long aptempMinLong = Math.round(aptempMin);
                    String aptempMinString = Long.toString(aptempMinLong);
                    parsedMap.put(forecastKey, aptempMinString);
                case "apparentTemperatureMax": double aptempMax = Double.parseDouble(forecastValue);
                    long aptempMaxLong = Math.round(aptempMax);
                    String aptempMaxString = Long.toString(aptempMaxLong);
                    parsedMap.put(forecastKey, aptempMaxString);
                    break;
                default: parsedMap.put(forecastKey, forecastValue);

                /* TODO: create statement for time w date object.  */
                    /* create additional parameter to differentiate between current, daily, and hourly forecasts
                    *       this is especially important on what to return in the date object */
            }
        }

        return parsedMap;
    }

    /* create switch case for wind bearing? */



    public HashMap<String, String> getCurrently() {
        return currently;
    }

    public void setCurrently(HashMap<String, String> currently) {
        this.currently = currently;
    }

    public Forecast getMinutely() {
        return minutely;
    }

    public void setMinutely(Forecast minutely) {
        this.minutely = minutely;
    }

    public Forecast getHourly() {
        return hourly;
    }

    public void setHourly(Forecast hourly) {
        this.hourly = hourly;
    }

    public Forecast getDaily() {
        return daily;
    }

    public void setDaily(Forecast daily) {
        this.daily = daily;
    }
}
