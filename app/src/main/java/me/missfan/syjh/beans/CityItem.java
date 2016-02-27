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
    private String lat;
    private String lon;
    private String prov;
    private String[] address = new String[5];

    public CityItem(String city,String cnty, String id, String lat, String lon, String prov){
        this.city = city;
        this.cnty = cnty;
        this.id = id;
        this.lat = lat;
        this.lon = lon;
        this.prov = prov;
    }

    public CityItem(JSONObject json) throws JSONException {
        city = json.getString("city");
        cnty = json.getString("cnty");
        id = json.getString("id");
        lat = json.getString("lat");
        lon = json.getString("lon");
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

    public String getLat() {
        return lat;
    }

    public String getLon() {
        return lon;
    }

    public String getProv() {
        return prov;
    }

    public void setAddress(String[] address) {
        this.address = address;
    }

    public String toString(){
        return city;
    }
}
