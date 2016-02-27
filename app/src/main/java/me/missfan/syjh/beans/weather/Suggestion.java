package me.missfan.syjh.beans.weather;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by Gr on 2016/2/20.
 */
public class Suggestion {
    /*
   Suggestion 生活指数
    */

    private String drsg_brf; //穿衣指数简介
    private String drsg_txt; //穿衣指数详情
    private String uv_brf; //紫外线指数简介
    private String uv_txt; //紫外线指数详情
    private String cw_brf; //洗车指数简介
    private String cw_txt; //洗车指数详情
    private String trav_brf; //旅游指数简介
    private String trav_txt; //旅游指数详情
    private String flu_brf; //感冒指数简介
    private String flu_txt; //感冒指数详情
    private String sport_brf; //运动指数简介
    private String sport_txt; //运动指数详情
    private String comf_brf; //舒适指数简介
    private String comf_txt; //舒适指数详情


    public Suggestion(JSONObject jsonObject) {
        JSONObject comfJson = jsonObject.optJSONObject("comf");
        if (comfJson != null) {
            comf_brf = comfJson.optString("brf");
            comf_txt = comfJson.optString("txt");
        }

        JSONObject cwJson = jsonObject.optJSONObject("cw");
        if (cwJson != null) {
            cw_brf = cwJson.optString("brf");
            cw_txt = cwJson.optString("txt");
        }

        JSONObject drsgJson = jsonObject.optJSONObject("drsg");
        if (drsgJson != null) {
            drsg_brf = drsgJson.optString("brf");
            drsg_txt = drsgJson.optString("txt");
        }

        JSONObject fluJson = jsonObject.optJSONObject("flu");
        if (fluJson != null) {
            flu_brf = fluJson.optString("brf");
            flu_txt = fluJson.optString("txt");
        }

        JSONObject sportJson = jsonObject.optJSONObject("sport");
        if (sportJson != null) {
            sport_brf = sportJson.optString("brf");
            sport_txt = sportJson.optString("txt");
        }

        JSONObject travJson = jsonObject.optJSONObject("trav");
        if (travJson != null) {
            trav_brf = travJson.optString("brf");
            trav_txt = travJson.optString("txt");
        }


        JSONObject uvJson = jsonObject.optJSONObject("uv");
        if (uvJson != null) {
            uv_brf = uvJson.optString("brf");
            uv_txt = uvJson.optString("txt");
        }
    }

    public String getDrsg_brf() {
        return drsg_brf;
    }

    public String getDrsg_txt() {
        return drsg_txt;
    }

    public String getUv_brf() {
        return uv_brf;
    }

    public String getUv_txt() {
        return uv_txt;
    }

    public String getCw_brf() {
        return cw_brf;
    }

    public String getCw_txt() {
        return cw_txt;
    }

    public String getTrav_brf() {
        return trav_brf;
    }

    public String getTrav_txt() {
        return trav_txt;
    }

    public String getFlu_brf() {
        return flu_brf;
    }

    public String getFlu_txt() {
        return flu_txt;
    }

    public String getSport_brf() {
        return sport_brf;
    }

    public String getSport_txt() {
        return sport_txt;
    }

    public String getComf_brf() {
        return comf_brf;
    }

    public String getComf_txt() {
        return comf_txt;
    }

    @Override
    public String toString() {
        return "Suggestion{" +
                "drsg_brf='" + drsg_brf + '\'' +
                ", drsg_txt='" + drsg_txt + '\'' +
                ", uv_brf='" + uv_brf + '\'' +
                ", uv_txt='" + uv_txt + '\'' +
                ", cw_brf='" + cw_brf + '\'' +
                ", cw_txt='" + cw_txt + '\'' +
                ", trav_brf='" + trav_brf + '\'' +
                ", trav_txt='" + trav_txt + '\'' +
                ", flu_brf='" + flu_brf + '\'' +
                ", flu_txt='" + flu_txt + '\'' +
                ", sport_brf='" + sport_brf + '\'' +
                ", sport_txt='" + sport_txt + '\'' +
                ", comf_brf='" + comf_brf + '\'' +
                ", comf_txt='" + comf_txt + '\'' +
                '}';
    }
}
