package me.missfan.syjh.utils;

import android.content.Context;
import android.location.Location;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.util.Log;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.net.URLDecoder;
import java.text.MessageFormat;

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

    // TODO: 2016/2/21 这里可以用Uri.parse(ENDOP).buildUpon()构造请求的地址


    //检查网络连接状态
    public static int getNetworkStatus(Context c) {
        //返回结果 -1:无法识别网络状态 0:没有网络 1:WIFI网络 2:手机网络
        int networkStatus = -1;
        ConnectivityManager connMgr = (ConnectivityManager)c.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connMgr.getActiveNetworkInfo();
        if (networkInfo == null) {
            networkStatus = 0;
            return networkStatus;
        }
        if (networkInfo.getType() == ConnectivityManager.TYPE_WIFI) {
            networkStatus = 1;
        }
        if (networkInfo.getType() == ConnectivityManager.TYPE_MOBILE) {
            networkStatus = 2;
        }
        return networkStatus;
    }



    //获取城市列表JSONStr
    public String getCityJsonStr(){
        String cityJsonStr = null;
        cityJsonStr = getRequest("https://api.heweather.com/x3/citylist?search=allchina&key=7879a30bd1f846d9af42a3fad50ef304");
        return cityJsonStr;
    }

    //通过location经纬度对象判断所在城市，返回JSONStr
    public String locationToCityInfoJson(double lat, double lon){
        String cityInfoJsonStr = null;
        String mapUriStr = "http://maps.google.cn/maps/api/geocode/json?latlng={0},{1}&sensor=true&language=zh-CN";
        String uriStr = MessageFormat.format(mapUriStr, lat, lon);
        cityInfoJsonStr = downloadUrl(uriStr);
        Log.i(TAG, "获取location的JSON信息："+cityInfoJsonStr);
        return cityInfoJsonStr;
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

    private String downloadUrl(String myurl) {
        InputStream is = null;
        String contentAsString = null;
        // Only display the first 500 characters of the retrieved
        // web page content.

        try {
            URL url = new URL(myurl);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setReadTimeout(10000 /* milliseconds */);
            conn.setConnectTimeout(15000 /* milliseconds */);
            conn.setRequestMethod("GET");
            conn.setDoInput(true);
            // Starts the query
            conn.connect();
            int response = conn.getResponseCode(); // 返回的结果代码 200代表正常
            is = conn.getInputStream();

            // Convert the InputStream into a string
            contentAsString= readIt(is);

            // Makes sure that the InputStream is closed after the app is
            // finished using it.
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (ProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (is != null) {
                try {
                    is.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return contentAsString;
    }

    public String readIt(InputStream stream) throws IOException, UnsupportedEncodingException {
        BufferedReader reader = null;
        StringBuffer sbf = new StringBuffer();
        reader = new BufferedReader(new InputStreamReader(stream,"UTF-8"));
        String strRead = null;
        while ((strRead = reader.readLine()) != null) {
            sbf.append(strRead);
            sbf.append("\r\n");
        }
        reader.close();
        return sbf.toString();

//        char[] buffer = new char[99999];
//        reader.read(buffer);
//        return new String(buffer);
    }

}
