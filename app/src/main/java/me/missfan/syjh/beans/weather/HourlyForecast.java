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
    private String hum; //湿度(%)
    private String pop; //降水概率
    private String pres; //气压
    private String tmp; //当前温度(摄氏度)
    //wind 小时风力状况
    private String spd; //风速(Kmph)
    private String sc; //风力等级
    private String deg; //风向(角度)
    private String dir; //风向(方向)

    public HourlyForecast(JSONObject jsonObject) {
        date = jsonObject.optString("date");
        hum = jsonObject.optString("hum");
        pop = jsonObject.optString("pop");
        pres = jsonObject.optString("pres");
        tmp = jsonObject.optString("tmp");

        JSONObject windJson = jsonObject.optJSONObject("wind");
        if(windJson != null) {
            deg = windJson.optString("deg");
            dir = windJson.optString("dir");
            sc = windJson.optString("sc");
            spd = windJson.optString("spd");
        }
    }


    @Override
    public String toString() {
        return "HourlyForecast{" +
                "date='" + date + '\'' +
                ", hum=" + hum +
                ", pop=" + pop +
                ", pres=" + pres +
                ", tmp=" + tmp +
                ", spd=" + spd +
                ", sc='" + sc + '\'' +
                ", deg=" + deg +
                ", dir='" + dir + '\'' +
                '}';
    }
}
