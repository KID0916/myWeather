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

    public CityWeatherItem(JSONObject aqiJson, JSONObject basicJson, JSONObject nowJson, JSONObject suggestionJson, JSONArray daily_forecastJson, JSONArray hourly_forecastJson) throws JSONException {
        aqi = new AQI(aqiJson);
        basic = new Basic(basicJson);
        now = new Now(nowJson);
        suggestion = new Suggestion(suggestionJson);

        for (int i = 0; i < daily_forecastJson.length(); i++) {
            dailyForecastList.add(new DailyForecast(daily_forecastJson.getJSONObject(i)));
        }
        for (int i = 0; i < hourly_forecastJson.length(); i++) {
            hourlyForecastList.add(new HourlyForecast(hourly_forecastJson.getJSONObject(i)));
        }
    }

    private AQI aqi;
    private Basic basic;
    private ArrayList<DailyForecast> dailyForecastList = new ArrayList<DailyForecast>();
    private ArrayList<HourlyForecast> hourlyForecastList = new ArrayList<HourlyForecast>();
    private Now now;
    private Suggestion suggestion;

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
