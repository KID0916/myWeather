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

    private String aqi; //空气质量指数
    private String pm25; //PM2.5 1小时平均值(ug/m³)
    private String pm10; //PM10 1小时平均值(ug/m³)
    private String so2; //二氧化硫1小时平均值(ug/m³)
    private String no2; //二氧化氮1小时平均值(ug/m³)
    private String co; //一氧化碳1小时平均值(ug/m³)
    private String o3; //臭氧1小时平均值(ug/m³)
    private String qlty; //空气质量类别

    public AQI(JSONObject jsonObject) {
        JSONObject cityJson = jsonObject.optJSONObject("city");
        if (cityJson != null) {
            aqi = cityJson.optString("aqi");
            pm25 = cityJson.optString("pm25");
            pm10 = cityJson.optString("pm10");
            so2 = cityJson.optString("so2");
            no2 = cityJson.optString("no2");
            co = cityJson.optString("co");
            o3 = cityJson.optString("o3");
            qlty = cityJson.optString("qlty");
        }
    }

    public String getAqi() {
        return aqi;
    }

    public String getPm25() {
        return pm25;
    }

    public String getPm10() {
        return pm10;
    }

    public String getSo2() {
        return so2;
    }

    public String getNo2() {
        return no2;
    }

    public String getCo() {
        return co;
    }

    public String getO3() {
        return o3;
    }

    public String getQlty() {
        return qlty;
    }

    @Override
    public String toString() {
        return "AQI{" +
                "aqi=" + aqi +
                ", pm25=" + pm25 +
                ", pm10=" + pm10 +
                ", so2=" + so2 +
                ", no2=" + no2 +
                ", co=" + co +
                ", o3=" + o3 +
                ", qlty='" + qlty + '\'' +
                '}';
    }
}
