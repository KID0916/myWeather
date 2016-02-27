package me.missfan.syjh.beans.weather;

import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by Gr on 2016/2/20.
 */
public class CityWeatherItem {
    private static final String TAG = "CityWeatherItem";
    //HeWeather data service 3.0 和风天气API


    public CityWeatherItem(JSONObject weatherJson) {

        JSONObject aqiJson = weatherJson.optJSONObject("aqi");
        if(aqiJson != null)
            aqi = new AQI(aqiJson);

        JSONObject basicJson = weatherJson.optJSONObject("basic");
        if(basicJson != null)
            basic = new Basic(basicJson);

        JSONObject nowJson = weatherJson.optJSONObject("now");
        if(nowJson != null)
            now = new Now(nowJson);

        JSONObject suggestionJson = weatherJson.optJSONObject("suggestion");
        if(suggestionJson != null)
            suggestion = new Suggestion(suggestionJson);

        JSONArray daily_forecastJson = weatherJson.optJSONArray("daily_forecast");
        if(daily_forecastJson != null)
            for (int i = 0; i < daily_forecastJson.length(); i++) {
                if(daily_forecastJson.optJSONObject(i) != null)
                dailyForecastList.add(new DailyForecast(daily_forecastJson.optJSONObject(i)));
            }

        JSONArray hourly_forecastJson = weatherJson.optJSONArray("hourly_forecast");
        if(hourly_forecastJson != null)
            for (int i = 0; i < hourly_forecastJson.length(); i++) {
                if(hourly_forecastJson.optJSONObject(i) != null)
                hourlyForecastList.add(new HourlyForecast(hourly_forecastJson.optJSONObject(i)));
            }
    }

    private AQI aqi;
    private Basic basic;
    private ArrayList<DailyForecast> dailyForecastList = new ArrayList<DailyForecast>();
    private ArrayList<HourlyForecast> hourlyForecastList = new ArrayList<HourlyForecast>();
    private Now now;
    private Suggestion suggestion;

    public AQI getAqi() {
        return aqi;
    }

    public Basic getBasic() {
        return basic;
    }

    public ArrayList<DailyForecast> getDailyForecastList() {
        return dailyForecastList;
    }

    public ArrayList<HourlyForecast> getHourlyForecastList() {
        return hourlyForecastList;
    }

    public Now getNow() {
        return now;
    }

    public Suggestion getSuggestion() {
        return suggestion;
    }

    @Override
    public String toString() {
        return "CityWeatherItem{" +
                "aqi=" + aqi +
                ", basic=" + basic +
                ", dailyForecastList=" + dailyForecastList +
                ", hourlyForecastList=" + hourlyForecastList +
                ", now=" + now +
                ", suggestion=" + suggestion +
                '}';
    }
}
