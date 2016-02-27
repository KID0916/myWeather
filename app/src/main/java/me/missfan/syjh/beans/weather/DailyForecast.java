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
    private String code_d; //白天天气代码
    private String txt_n; //夜间天气描述
    private String code_n; //夜间天气代码
    //astro 天文数值
    private String sr; //日出时间
    private String ss; //日落时间
    private String date; //当地日期
    //tmp 温度状况
    private String max; //最高温度(摄氏度)
    private String min; //最低温度(摄氏度)
    private String pcpn; //当日降雨量(mm)
    private String pop; //当日降水概率
    private String hum; //当日湿度(%)
    private String pres; //当日气压
    private String vis; //当日能见度(km)
    //wind 当日风力状况
    private String spd; //风速(Kmph)
    private String sc; //风力等级
    private String deg; //风向(角度)
    private String dir; //风向(方向)

    public DailyForecast(JSONObject jsonObject) {
        JSONObject astroJson = jsonObject.optJSONObject("astro");
        if (astroJson != null) {
            date = jsonObject.optString("date");
            hum = jsonObject.optString("hum");
            pcpn = jsonObject.optString("pcpn");
            pop = jsonObject.optString("pop");
            pres = jsonObject.optString("pres");
            vis = jsonObject.optString("vis");
            sr = astroJson.optString("sr");
            ss = astroJson.optString("ss");
        }

        JSONObject condJson = jsonObject.optJSONObject("cond");
        if (condJson != null) {
            code_d = condJson.optString("code_d");
            code_n = condJson.optString("code_n");
            txt_d = condJson.optString("txt_d");
            txt_n = condJson.optString("txt_n");
        }

        JSONObject tmpJson = jsonObject.optJSONObject("tmp");
        if (tmpJson != null) {
            max = tmpJson.optString("max");
            min = tmpJson.optString("min");
        }

        JSONObject windJson = jsonObject.optJSONObject("wind");
        if (windJson != null) {
            deg = windJson.optString("deg");
            dir = windJson.optString("dir");
            sc = windJson.optString("sc");
            spd = windJson.optString("spd");
        }
    }

    public String getTxt_d() {
        return txt_d;
    }

    public String getCode_d() {
        return code_d;
    }

    public String getTxt_n() {
        return txt_n;
    }

    public String getCode_n() {
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

    public String getMax() {
        return max;
    }

    public String getMin() {
        return min;
    }

    public String getPcpn() {
        return pcpn;
    }

    public String getPop() {
        return pop;
    }

    public String getHum() {
        return hum;
    }

    public String getPres() {
        return pres;
    }

    public String getVis() {
        return vis;
    }

    public String getSpd() {
        return spd;
    }

    public String getSc() {
        return sc;
    }

    public String getDeg() {
        return deg;
    }

    public String getDir() {
        return dir;
    }

    @Override
    public String toString() {
        return "DailyForecast{" +
                "txt_d='" + txt_d + '\'' +
                ", code_d=" + code_d +
                ", txt_n='" + txt_n + '\'' +
                ", code_n=" + code_n +
                ", sr='" + sr + '\'' +
                ", ss='" + ss + '\'' +
                ", date='" + date + '\'' +
                ", max=" + max +
                ", min=" + min +
                ", pcpn=" + pcpn +
                ", pop=" + pop +
                ", hum=" + hum +
                ", pres=" + pres +
                ", vis=" + vis +
                ", spd=" + spd +
                ", sc='" + sc + '\'' +
                ", deg=" + deg +
                ", dir='" + dir + '\'' +
                '}';
    }
}
