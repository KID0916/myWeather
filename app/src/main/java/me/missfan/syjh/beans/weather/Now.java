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
    private String code; //天气代码
    private String txt; //天气描述
    private String fl; //体感温度;
    private String tmp; //当前温度(摄氏度)
    private String pcpn; //降雨量(mm)
    private String hum; //湿度(%)
    private String pres; //气压
    private String vis; //能见度(km)

    //wind 当前时间风速
    private String spd; //风速(Kmph)
    private String sc; //风力等级
    private String deg; //风向(角度)
    private String dir; //风向(方向)

    public Now(JSONObject jsonObject) {
        fl = jsonObject.optString("fl");
        hum = jsonObject.optString("hum");
        pcpn = jsonObject.optString("pcpn");
        pres = jsonObject.optString("pres");
        tmp = jsonObject.optString("tmp");
        vis = jsonObject.optString("vis");

        JSONObject condJson = jsonObject.optJSONObject("cond");
        if(condJson != null){
            code = condJson.optString("code");
            txt = condJson.optString("txt");
        }

        JSONObject windJson = jsonObject.optJSONObject("wind");
        if (windJson != null) {
            deg = windJson.optString("deg");
            dir = windJson.optString("dir");
            sc = windJson.optString("sc");
            spd = windJson.optString("spd");
        }
    }

    public String getCode() {
        return code;
    }

    public String getTxt() {
        return txt;
    }

    public String getFl() {
        return fl;
    }

    public String getTmp() {
        return tmp;
    }

    public String getPcpn() {
        return pcpn;
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
        return "Now{" +
                "code=" + code +
                ", txt='" + txt + '\'' +
                ", fl=" + fl +
                ", tmp=" + tmp +
                ", pcpn=" + pcpn +
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
