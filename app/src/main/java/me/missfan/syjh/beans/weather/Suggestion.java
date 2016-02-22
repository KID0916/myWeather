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

    public Suggestion(JSONObject jsonObject) throws JSONException {
        JSONObject comfJson = jsonObject.getJSONObject("comf");
        comf_brf = comfJson.getString("brf");
        comf_txt = comfJson.getString("txt");

        JSONObject cwJson = jsonObject.getJSONObject("cw");
        cw_brf = cwJson.getString("brf");
        cw_txt = cwJson.getString("txt");

        JSONObject drsgJson = jsonObject.getJSONObject("drsg");
        drsg_brf =drsgJson .getString("brf");
        drsg_txt = drsgJson.getString("txt");

        JSONObject fluJson = jsonObject.getJSONObject("flu");
        flu_brf = fluJson.getString("brf");
        flu_txt = fluJson.getString("txt");

        JSONObject sportJson = jsonObject.getJSONObject("sport");
        sport_brf = sportJson.getString("brf");
        sport_txt = sportJson.getString("txt");

        JSONObject travJson = jsonObject.getJSONObject("trav");
        trav_brf = travJson.getString("brf");
        trav_txt = travJson.getString("txt");

        JSONObject uvJson = jsonObject.getJSONObject("uv");
        uv_brf = uvJson.getString("brf");
        uv_txt = uvJson.getString("txt");
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
}
