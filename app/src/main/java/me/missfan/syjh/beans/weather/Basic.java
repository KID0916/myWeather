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

    public Basic(JSONObject jsonObject) throws JSONException {
            city = jsonObject.getString("city");
            id = jsonObject.getString("id");
            cnty = jsonObject.getString("cnty");
            lat = jsonObject.getDouble("lat");
            lon = jsonObject.getDouble("lon");
            JSONObject updateJson = jsonObject.getJSONObject("update");
            loc = updateJson.getString("loc");
            utc = updateJson.getString("utc");
    }

    private String city; //城市名称
    private String id; //城市ID
    private String cnty; //国家名称
    private double lat; //纬度
    private double lon; //经度
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

    public double getLat() {
        return lat;
    }

    public double getLon() {
        return lon;
    }

    public String getLoc() {
        return loc;
    }

    public String getUtc() {
        return utc;
    }
}
