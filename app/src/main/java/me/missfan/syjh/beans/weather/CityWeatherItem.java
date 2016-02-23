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

    public CityWeatherItem(JSONObject aqiJson, JSONObject basicJson, JSONObject nowJson, JSONObject suggestionJson, JSONArray daily_forecastJson, JSONArray hourly_forecastJson) {
        if (aqiJson != null)
            try {
                aqi = new AQI(aqiJson);

                if (basicJson != null)
                    basic = new Basic(basicJson);
                if (nowJson != null)
                    now = new Now(nowJson);
                if (suggestionJson != null)
                    suggestion = new Suggestion(suggestionJson);

                if (daily_forecastJson != null)
                    for (int i = 0; i < daily_forecastJson.length(); i++) {
                        dailyForecastList.add(new DailyForecast(daily_forecastJson.getJSONObject(i)));
                    }
                if (hourly_forecastJson != null)
                    for (int i = 0; i < hourly_forecastJson.length(); i++) {
                        hourlyForecastList.add(new HourlyForecast(hourly_forecastJson.getJSONObject(i)));
                    }
            } catch (JSONException e) {
                e.printStackTrace();
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
