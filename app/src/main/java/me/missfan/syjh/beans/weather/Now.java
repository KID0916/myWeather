package me.missfan.syjh.beans.weather;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by Gr on 2016/2/20.
 */
public class Now {
    /*
    Now 实况天气
     */

    //当前时间天气状况
    private int code; //天气代码
    private String txt; //天气描述
    private int fl; //体感温度;
    private int tmp; //当前温度(摄氏度)
    private int pcpn; //降雨量(mm)
    private int hum; //湿度(%)
    private int pres; //气压
    private int vis; //能见度(km)

    //wind 当前时间风速
    private int spd; //风速(Kmph)
    private String sc; //风力等级
    private int deg; //风向(角度)
    private String dir; //风向(方向)

    public Now(JSONObject jsonObject) throws JSONException {
        fl = jsonObject.getInt("fl");
        hum = jsonObject.getInt("hum");
        pcpn = jsonObject.getInt("pcpn");
        pres = jsonObject.getInt("pres");
        tmp = jsonObject.getInt("tmp");
        vis = jsonObject.getInt("vis");

        JSONObject windJson = jsonObject.getJSONObject("wind");
        deg = windJson.getInt("deg");
        dir = windJson.getString("dir");
        sc = windJson.getString("sc");
        spd = windJson.getInt("spd");
    }

    public int getCode() {
        return code;
    }

    public String getTxt() {
        return txt;
    }

    public int getFl() {
        return fl;
    }

    public int getTmp() {
        return tmp;
    }

    public int getPcpn() {
        return pcpn;
    }

    public int getHum() {
        return hum;
    }

    public int getPres() {
        return pres;
    }

    public int getVis() {
        return vis;
    }

    public int getSpd() {
        return spd;
    }

    public String getSc() {
        return sc;
    }

    public int getDeg() {
        return deg;
    }

    public String getDir() {
        return dir;
    }
}
