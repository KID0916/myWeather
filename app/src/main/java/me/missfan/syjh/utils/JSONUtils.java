package me.missfan.syjh.utils;

import android.content.Context;
import android.nfc.NfcEvent;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONTokener;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.ArrayList;

import me.missfan.syjh.beans.CityItem;
import me.missfan.syjh.beans.Status;
import me.missfan.syjh.beans.WeatherCode;
import me.missfan.syjh.beans.weather.AQI;
import me.missfan.syjh.beans.weather.Basic;
import me.missfan.syjh.beans.weather.CityWeatherItem;
import me.missfan.syjh.beans.weather.DailyForecast;
import me.missfan.syjh.beans.weather.HourlyForecast;
import me.missfan.syjh.beans.weather.Now;
import me.missfan.syjh.beans.weather.Suggestion;

/**
 * Created by Gr on 2016/2/20.
 */
public class JSONUtils {
    private static final String TAG = "JSONUtils";

    //TODO: 2016/2/21 status
    private Status status;
    private NetworkUtils networkUtils;

    //JSON字符串 --> JSON文件
    public void writeJsonFiles(Context mContext, String jsonStr, String jsonFileName) {
        Writer writer = null;
        try {
            OutputStream out = mContext.openFileOutput(jsonFileName, Context.MODE_PRIVATE);
            writer = new OutputStreamWriter(out);
            writer.write(jsonStr);
        } catch (FileNotFoundException e) {
            Log.i(TAG, e.getMessage());
        } catch (IOException e) {
            Log.i(TAG, e.getMessage());
        } finally {
            if (writer != null)
                try {
                    writer.close();
                } catch (IOException e) {
                    Log.i(TAG, e.getMessage());
                }
        }

    }

    //JSON文件 --> JSON字符串  返回JSON字符串
    public String readJsonFiles(Context mContext, String jsonFileName) {
        String jsonStr = null;
        BufferedReader reader = null;
        try {
            InputStream in = mContext.openFileInput(jsonFileName);
            reader = new BufferedReader(new InputStreamReader(in));
            StringBuilder jsonString = new StringBuilder();
            String line = null;
            while ((line = reader.readLine()) != null) {
                jsonString.append(line);
            }
            jsonStr = jsonString.toString();
        } catch (FileNotFoundException e) {
            Log.i(TAG, e.getMessage());
        } catch (IOException e) {
            Log.i(TAG, e.getMessage());
        }
        return jsonStr;
    }


        //从JSON文件中读取城市信息
        public ArrayList<CityItem> readCityItemJsonFiles (Context mContext, String jsonFileName) {
            ArrayList<CityItem> cityItems = new ArrayList<CityItem>();
            String cityJsonStr = new String(readJsonFiles(mContext, jsonFileName));
            Log.i(TAG, cityJsonStr);
            try {
                JSONTokener jsonTokener = new JSONTokener(cityJsonStr);
                JSONObject testCity = (JSONObject) jsonTokener.nextValue();
                JSONArray cityList = testCity.getJSONArray("city_info");
                for (int i = 0; i < cityList.length(); i++) {
                    JSONObject citytemp = cityList.getJSONObject(i);
                    cityItems.add(new CityItem(citytemp));
                }
            } catch (JSONException e) {
                Log.i(TAG, e.getMessage());
            }
            return cityItems;
        }

        //从JSON文件中读取城市天气信息 JSON文件 --> CityWeatherItem
        public CityWeatherItem readCityWeatherItemFiles (Context mContext, String jsonFileName) {
            CityWeatherItem cwi = null;
            String cityWeatherJsonStr = new String(readJsonFiles(mContext, jsonFileName));
            try {
                JSONTokener jsonTokener = new JSONTokener(cityWeatherJsonStr);
                JSONObject cityWeatherJsonObject = (JSONObject) jsonTokener.nextValue();

                JSONArray weatherListJson = cityWeatherJsonObject.getJSONArray("HeWeather data service 3.0");
                JSONObject weatherJson = weatherListJson.getJSONObject(0);
                JSONObject aqiJson = weatherJson.getJSONObject("aqi");
                JSONObject basicJson = weatherJson.getJSONObject("basic");
                JSONObject nowJson = weatherJson.getJSONObject("now");
                JSONObject suggestionJson = weatherJson.getJSONObject("suggestion");

                JSONArray daily_forecastJson = weatherJson.getJSONArray("daily_forecast");
                JSONArray hourly_forecastJson = weatherJson.getJSONArray("hourly_forecast");

                cwi = new CityWeatherItem(aqiJson, basicJson, nowJson, suggestionJson, daily_forecastJson, hourly_forecastJson);
            } catch (JSONException e) {
                Log.i(TAG, e.getMessage());
            }
            return cwi;
        }

        //从JSON文件中读取天气代码信息
        public ArrayList<WeatherCode> readWeatherCodeFiles (Context mContext, String jsonFileName) {
            ArrayList<WeatherCode> weatherItems = new ArrayList<WeatherCode>();
            String weatherJsonStr = new String(readJsonFiles(mContext, jsonFileName));
            Log.i(TAG, weatherJsonStr);
            try {
                JSONTokener jsonTokener = new JSONTokener(weatherJsonStr);
                JSONObject weatherJsonObject = (JSONObject) jsonTokener.nextValue();
                JSONArray weatherList = weatherJsonObject.getJSONArray("cond_info");
                for (int i = 0; i < weatherList.length(); i++) {
                    JSONObject weathertemp = weatherList.getJSONObject(i);
                    weatherItems.add(new WeatherCode(weathertemp));
                }
            } catch (JSONException e) {
                Log.i(TAG, e.getMessage());
            }
            return weatherItems;
        }
    }