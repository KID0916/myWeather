package me.missfan.syjh.beans;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by Gr on 2016/2/20.
 */
public class WeatherCode {
    private int code;
    private String txt;
    private String txt_en;
    private String icon;

    public WeatherCode(JSONObject json) {
        try {
            code = json.getInt("code");
            txt = json.getString("txt");
            txt_en = json.getString("txt_en");
            icon = json.getString("icon");
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getTxt() {
        return txt;
    }

    public void setTxt(String txt) {
        this.txt = txt;
    }

    public String getTxt_en() {
        return txt_en;
    }

    public void setTxt_en(String txt_en) {
        this.txt_en = txt_en;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }
}
