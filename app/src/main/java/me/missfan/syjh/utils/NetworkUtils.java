package me.missfan.syjh.utils;

import android.util.Log;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.net.URLDecoder;

import javax.net.ssl.HttpsURLConnection;

/**
 * Created by Gr on 2016/2/21.
 */
public class NetworkUtils {

    private static final String TAG = "NetworkUtils";
    private static final String ENDPOINT = "https://api.heweather.com/x3/";
    public static final String CITY_TYPE_CHINA = "allchina"; //国内
    public static final String CITY_TYPE_HOT = "hotworld"; //世界热门
    public static final String CITY_TYPE_WORLD = "allworld"; //全世界
    public static final String WEATHER_TYPE = "allcond"; //全部类型
    private static final String API_KEY = "7879a30bd1f846d9af42a3fad50ef304";

    //// TODO: 2016/2/21 这里可以用Uri.parse(ENDOP).buildUpon()构造请求的地址

    //获取城市列表JSONStr
    public String getCityJsonStr(){
        String cityJsonStr = null;
        cityJsonStr = getRequest("https://api.heweather.com/x3/citylist?search=allchina&key=7879a30bd1f846d9af42a3fad50ef304");
        return cityJsonStr;
    }

    //使用中文城市名查询时会有编码格式不匹配问题 暂不适用 仅用城市ID查询
    //通过城市名或拼音获取城市天气JSONStr
    /*
    public String getCityNameWeatherJsonStr(String cityName){
        String cityWeatherJsonStr = null;
        String requestUrl = "https://api.heweather.com/x3/weather?city="+cityName+"&key="+API_KEY;
        cityWeatherJsonStr = getRequest(requestUrl);
        return cityWeatherJsonStr;
    }
    */

    //通过城市ID获取城市天气JSONStr
    public String getCityIdWeatherJsonStr(String cityId){
        String cityWeatherJsonStr = null;
        String requestUrl = "https://api.heweather.com/x3/weather?cityid="+cityId+"&key="+API_KEY;
        cityWeatherJsonStr = getRequest(requestUrl);
        return cityWeatherJsonStr;
    }

    //通过城市IP获取城市天气JSONStr
    public String getCityIpWeatherJsonStr(String cityIp){
        String cityWeatherJsonStr = null;
        String requestUrl = "https://api.heweather.com/x3/weather?cityip="+cityIp+"&key="+API_KEY;
        cityWeatherJsonStr = getRequest(requestUrl);
        return cityWeatherJsonStr;
    }


    public String getRequest(String httpUrl) {
        BufferedReader reader = null;
        String result = null;
        StringBuffer sbf = new StringBuffer();
        try {
            URL url = new URL(httpUrl);
            HttpsURLConnection connection = (HttpsURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            connection.connect();
            InputStream is = connection.getInputStream();
            reader = new BufferedReader(new InputStreamReader(is, "UTF-8"));
            String strRead = null;
            while ((strRead = reader.readLine()) != null) {
                sbf.append(strRead);
                sbf.append("\r\n");
            }
            reader.close();
            result = sbf.toString();
        } catch (Exception e) {
            Log.e(TAG, "接口错误" + e.getMessage());
        }
        return result;
    }
}
