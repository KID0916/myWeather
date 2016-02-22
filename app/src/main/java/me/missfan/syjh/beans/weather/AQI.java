package me.missfan.syjh.beans.weather;

import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by Gr on 2016/2/20.
 */
public class AQI {
    /*
    aqi空气质量指数
     */

    public AQI(JSONObject jsonObject) throws JSONException {
            JSONObject cityJson = jsonObject.getJSONObject("city");
            aqi = cityJson.getInt("aqi");
            pm25 = cityJson.getInt("pm25");
            pm10= cityJson.getInt("pm10");
            so2 = cityJson.getInt("so2");
            no2= cityJson.getInt("no2");
            co = cityJson.getInt("co");
            o3 = cityJson.getInt("o3");
            qlty = cityJson.getString("qlty");
    }

    private int aqi; //空气质量指数
    private int pm25; //PM2.5 1小时平均值(ug/m³)
    private int pm10; //PM10 1小时平均值(ug/m³)
    private int so2; //二氧化硫1小时平均值(ug/m³)
    private int no2; //二氧化氮1小时平均值(ug/m³)
    private int co; //一氧化碳1小时平均值(ug/m³)
    private int o3; //臭氧1小时平均值(ug/m³)
    private String qlty; //空气质量类别

    public int getAqi() {
        return aqi;
    }

    public int getPm25() {
        return pm25;
    }

    public int getPm10() {
        return pm10;
    }

    public int getSo2() {
        return so2;
    }

    public int getNo2() {
        return no2;
    }

    public int getCo() {
        return co;
    }

    public int getO3() {
        return o3;
    }

    public String getQlty() {
        return qlty;
    }
}
