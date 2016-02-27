package me.missfan.syjh.beans.weather;

import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by Gr on 2016/2/20.
 */
public class Basic {
    private static final String TAG = "Basic";
    /*
    basic城市基本信息
     */

    private String city; //城市名称
    private String id; //城市ID
    private String cnty; //国家名称
    private String lat; //纬度
    private String lon; //经度
    private String loc; //数据更新的当地时间
    private String utc; //数据更新的UTC时间

    public String getCity() {
        return city;
    }

    public String getId() {
        return id;
    }

    public String getCnty() {
        return cnty;
    }

    public String getLat() {
        return lat;
    }

    public String getLon() {
        return lon;
    }

    public String getLoc() {
        return loc;
    }

    public String getUtc() {
        return utc;
    }

    public Basic(JSONObject jsonObject) {
        city = jsonObject.optString("city");
        id = jsonObject.optString("id");
        cnty = jsonObject.optString("cnty");
        lat = jsonObject.optString("lat");
        lon = jsonObject.optString("lon");
        JSONObject updateJson = jsonObject.optJSONObject("update");
        if (updateJson != null) {
            loc = updateJson.optString("loc");
            utc = updateJson.optString("utc");
        }
    }

    @Override
    public String toString() {
        return "Basic{" +
                "city='" + city + '\'' +
                ", id='" + id + '\'' +
                ", cnty='" + cnty + '\'' +
                ", lat=" + lat +
                ", lon=" + lon +
                ", loc='" + loc + '\'' +
                ", utc='" + utc + '\'' +
                '}';
    }
}
