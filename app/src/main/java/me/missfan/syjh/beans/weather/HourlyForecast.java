package me.missfan.syjh.beans.weather;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by Gr on 2016/2/21.
 */
public class HourlyForecast {
    /*
    hourly_forecast 每小时天气预报 包含两个小时天气预报，间隔3小时
     */

    private String date; //当地日期和时间
    private int hum; //湿度(%)
    private int pop; //降水概率
    private int pres; //气压
    private int tmp; //当前温度(摄氏度)
    //wind 小时风力状况
    private int spd; //风速(Kmph)
    private String sc; //风力等级
    private int deg; //风向(角度)
    private String dir; //风向(方向)

    public HourlyForecast(JSONObject jsonObject) throws JSONException {
        date = jsonObject.getString("date");
        hum = jsonObject.getInt("hum");
        pop = jsonObject.getInt("pop");
        pres = jsonObject.getInt("pres");
        tmp = jsonObject.getInt("tmp");

        JSONObject windJson = jsonObject.getJSONObject("wind");
        deg = windJson.getInt("deg");
        dir = windJson.getString("dir");
        sc = windJson.getString("sc");
        spd = windJson.getInt("spd");
    }

    public String getDate() {
        return date;
    }

    public int getHum() {
        return hum;
    }

    public int getPop() {
        return pop;
    }

    public int getPres() {
        return pres;
    }

    public int getTmp() {
        return tmp;
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
