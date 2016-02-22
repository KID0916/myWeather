package me.missfan.syjh.beans.weather;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by Gr on 2016/2/20.
 */
public class DailyForecast {
    /*
    daily_forecast 天气预报 每次请求包含7天
     */

    //cond 天气状况
    private String txt_d; //白天天气描述
    private int code_d; //白天天气代码
    private String txt_n; //夜间天气描述
    private int code_n; //夜间天气代码
    //astro 天文数值
    private String sr; //日出时间
    private String ss; //日落时间
    private String date; //当地日期
    //tmp 温度状况
    private int max; //最高温度(摄氏度)
    private int min; //最低温度(摄氏度)
    private double pcpn; //当日降雨量(mm)
    private int pop; //当日降水概率
    private int hum; //当日湿度(%)
    private int pres; //当日气压
    private int vis; //当日能见度(km)
    //wind 当日风力状况
    private int spd; //风速(Kmph)
    private String sc; //风力等级
    private int deg; //风向(角度)
    private String dir; //风向(方向)

    public DailyForecast(JSONObject jsonObject) throws JSONException {
        JSONObject astroJson = jsonObject.getJSONObject("astro");
        date = jsonObject.getString("date");
        hum = jsonObject.getInt("hum");
        pcpn = jsonObject.getDouble("pcpn");
        pop = jsonObject.getInt("pop");
        pres = jsonObject.getInt("pres");
        vis = jsonObject.getInt("vis");
        sr = astroJson.getString("sr");
        ss = astroJson.getString("ss");

        JSONObject condJson = jsonObject.getJSONObject("cond");
        code_d = condJson.getInt("code_d");
        code_n = condJson.getInt("code_n");
        txt_d = condJson.getString("txt_d");
        txt_n = condJson.getString("txt_n");

        JSONObject tmpJson = jsonObject.getJSONObject("tmp");
        max = tmpJson.getInt("max");
        min = tmpJson.getInt("min");

        JSONObject windJson = jsonObject.getJSONObject("wind");
        deg = windJson.getInt("deg");
        dir = windJson.getString("dir");
        sc = windJson.getString("sc");
        spd = windJson.getInt("spd");

    }

    public String getTxt_d() {
        return txt_d;
    }

    public int getCode_d() {
        return code_d;
    }

    public String getTxt_n() {
        return txt_n;
    }

    public int getCode_n() {
        return code_n;
    }

    public String getSr() {
        return sr;
    }

    public String getSs() {
        return ss;
    }

    public String getDate() {
        return date;
    }

    public int getMax() {
        return max;
    }

    public int getMin() {
        return min;
    }

    public double getPcpn() {
        return pcpn;
    }

    public int getPop() {
        return pop;
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
