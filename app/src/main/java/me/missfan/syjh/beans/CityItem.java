package me.missfan.syjh.beans;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by Gr on 2016/2/18.
 */
public class CityItem {
    private String city;
    private String cnty;
    private String id;
    private double lat;
    private double lon;
    private String prov;

    public CityItem(JSONObject json) throws JSONException {
        city = json.getString("city");
        cnty = json.getString("cnty");
        id = json.getString("id");
        lat = json.getDouble("lat");
        lon = json.getDouble("lon");
        prov = json.getString("prov");
    }

    public String getCity() {
        return city;
    }

    public String getCnty() {
        return cnty;
    }

    public String getId() {
        return id;
    }

    public double getLat() {
        return lat;
    }

    public double getLon() {
        return lon;
    }

    public String getProv() {
        return prov;
    }
    //TODO
    public String toString(){
        return city;
    }
}
