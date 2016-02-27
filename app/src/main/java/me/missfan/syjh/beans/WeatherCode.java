package me.missfan.syjh.beans;

import android.graphics.Bitmap;

import org.json.JSONException;
import org.json.JSONObject;

import me.missfan.syjh.R;

/**
 * Created by Gr on 2016/2/20.
 */
public class WeatherCode {
    public static int weatherPng(String weatherCode) {
        switch(weatherCode){
            case "100": return R.drawable.w100;    //晴    Sunny/Clear
            case "101": return R.drawable.w101;    //多云    Cloudy
            case "102": return R.drawable.w102;    //少云    Few Clouds
            case "103": return R.drawable.w103;    //晴间多云    Partly Cloudy
            case "104": return R.drawable.w104;    //阴    Overcast
            case "200": return R.drawable.w200;    //有风    Windy
            case "201": return R.drawable.w201;    //平静    Calm
            case "202": return R.drawable.w202;    //微风    Light Breeze
            case "203": return R.drawable.w203;    //和风    Moderate/Gentle Breeze
            case "204": return R.drawable.w204;    //清风    Fresh Breeze
            case "205": return R.drawable.w205;    //强风/劲风    Strong Breeze
            case "206": return R.drawable.w206;    //疾风    High Wind, Near Gale
            case "207": return R.drawable.w207;    //大风    Gale
            case "208": return R.drawable.w208;    //烈风    Strong Gale
            case "209": return R.drawable.w209;    //风暴    Storm
            case "210": return R.drawable.w210;    //狂爆风    Violent Storm
            case "211": return R.drawable.w211;    //飓风    Hurricane
            case "212": return R.drawable.w212;    //龙卷风    Tornado
            case "213": return R.drawable.w213;    //热带风暴    Tropical Storm
            case "300": return R.drawable.w300;    //阵雨    Shower Rain
            case "301": return R.drawable.w301;    //强阵雨    Heavy Shower Rain
            case "302": return R.drawable.w302;    //雷阵雨    Thundershower
            case "303": return R.drawable.w303;    //强雷阵雨    Heavy Thunderstorm
            case "304": return R.drawable.w304;    //雷阵雨伴有冰雹    Hail
            case "305": return R.drawable.w305;    //小雨    Light Rain
            case "306": return R.drawable.w306;    //中雨    Moderate Rain
            case "307": return R.drawable.w307;    //大雨    Heavy Rain
            case "308": return R.drawable.w308;    //极端降雨    Extreme Rain
            case "309": return R.drawable.w309;    //毛毛雨/细雨    Drizzle Rain
            case "310": return R.drawable.w310;    //暴雨    Storm
            case "311": return R.drawable.w311;    //大暴雨    Heavy Storm
            case "312": return R.drawable.w312;    //特大暴雨    Severe Storm
            case "313": return R.drawable.w313;    //冻雨    Freezing Rain
            case "400": return R.drawable.w400;    //小雪    Light Snow
            case "401": return R.drawable.w401;    //中雪    Moderate Snow
            case "402": return R.drawable.w402;    //大雪    Heavy Snow
            case "403": return R.drawable.w403;    //暴雪    Snowstorm
            case "404": return R.drawable.w404;    //雨夹雪    Sleet
            case "405": return R.drawable.w405;    //雨雪天气    Rain And Snow
            case "406": return R.drawable.w406;    //阵雨夹雪    Shower Snow
            case "407": return R.drawable.w407;    //阵雪    Snow Flurry
            case "500": return R.drawable.w500;    //薄雾    Mist
            case "501": return R.drawable.w501;    //雾    Foggy
            case "502": return R.drawable.w502;    //霾    Haze
            case "503": return R.drawable.w503;    //扬沙    Sand
            case "504": return R.drawable.w504;    //浮尘    Dust
            //case "506": return R.drawable.w506;    //火山灰    Volcanic Ash
            case "507": return R.drawable.w507;    //沙尘暴    Duststorm
            case "508": return R.drawable.w508;    //强沙尘暴    Sandstorm
            case "900": return R.drawable.w900;    //热    Hot
            case "901": return R.drawable.w901;    //冷    Cold
            case "999": return R.drawable.w999;    //未知    Unknown
            default:return R.drawable.w999;
        }
    }
    public static String weatherName (String weatherCode){
        switch (weatherCode) {
            default:return null;
        }
    }
}
