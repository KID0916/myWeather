package me.missfan.syjh.activity;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.AsyncTask;
import android.os.Build;
import android.provider.Settings;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONObject;

import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.Enumeration;

import me.missfan.syjh.CityListFetchr;
import me.missfan.syjh.R;
import me.missfan.syjh.beans.CityItem;
import me.missfan.syjh.database.CityListDbHelper;
import me.missfan.syjh.utils.JSONUtils;
import me.missfan.syjh.utils.LocationUtil;
import me.missfan.syjh.utils.NetworkUtils;

 /*第一次打开应用时
 *检查网络连接->网络未连接->检测到网络未连接，是否打开网络连接->否，退出应用
 *要获取城市列表信息，下载天气图片->保存到数据库中
 * 定位位置信息->
 * 开启定位->通过谷歌地图API查询位置城市名
 * 为开启定位->选项(1)开启定位  (2)手动选择城市->选择城市列表
 *
 * 无法获得城市名->选择城市列表->选择城市，通过城市ID获取天气信息->将JSON解析为对象->将城市ID保存到Sharepreference->将城市天气保存为JSON文件->加载WeatherActivity
 * 可以获取城市名称->查询数据库是否存在城市
 *
 * 存在城市->通过API获取城市天气信息->将JSON解析为对象->将城市ID保存到Sharepreference->将城市天气保存为JSON文件->加载WeatherActivity
 * 未找到在城市->发出弹窗(未能定位所在城市，请手动选择)->城市列表
 *
 * 选择城市，通过城市ID获取天气信息->将JSON解析为对象->将城市ID保存到Sharepreference->将城市天气保存为JSON文件->加载WeatherActivity
 *
  */

/*
*非第一次打开应用
* 检查网络连接->网络未连接->检测到网络未连接是否打开网络连接？-> 否，使用上次缓存的天气信息->从sharepreference中读取城市信息，找到对应JSON文件，加载weatheractivity
* 网络连接->从sharepreference中读取城市信息->获取最新城市天气信息-保存JSON文件->加载WeatherActivity
*是否第一次打开应用可以将一个boolean变量保存到Sharepreference中，第一次打开应用将其更改为false
*
*
 */


public class WelcomeActivity extends AppCompatActivity {
    private static final String TAG = "WelcomeActivity";
    private static final String FIRST_TIME = "firsttime";
    private static final String PREFS_FILE = "citylist";
    private CityListDbHelper cldh;
    private SharedPreferences welcomeSharedPreference;
    NetworkUtils networkUtils = new NetworkUtils();
    private JSONUtils jsonUtils = new JSONUtils();
    TextView dbText;
    ProgressDialog dialog;

    boolean isDbInitOK,isFirstTime, isNetworOK, isLocationOK,isCityOK;
    double lat, lon;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);
        welcomeSharedPreference = getPreferences(Context.MODE_PRIVATE);
        isFirstTime = welcomeSharedPreference.getBoolean(FIRST_TIME, true);
        dbText = (TextView) findViewById(R.id.db_text);
        getLocation();
        new InitWeatherTask().execute();
    }

    //获取城市信息
    public void getLocation() {
        // 注册广播
        IntentFilter filter = new IntentFilter();
        filter.addAction(LocationUtil.LOCATION_ACTION);
        this.registerReceiver(new LocationBroadcastReceiver(), filter);
        // 启动服务
        Intent intent = new Intent();
        intent.setClass(this, LocationUtil.class);
        startService(intent);

        // 等待提示
        dialog = new ProgressDialog(this);
        dialog.setMessage("正在定位...");
        dialog.setCancelable(true);
        dialog.show();
        //return cityName;
    }

    class InitWeatherTask extends AsyncTask<Void, Void, String> {


        @Override
        protected String doInBackground(Void... params) {
            String test_text = "";
            if (isFirstTime) {
                IntiCityListDb();
                SharedPreferences.Editor editor = welcomeSharedPreference.edit();
                editor.putBoolean(FIRST_TIME, false);
                editor.commit();
            }

            while (true) {
                if (isLocationOK) {
                    String locationJsonStr = networkUtils.locationToCityInfoJson(lat, lon);
                    String[] cityName = jsonUtils.locationJsonStrToCityItem(locationJsonStr);
                    for(int i=0; i<cityName.length; i++){
                        if(cityName[i] != null){
                            test_text = test_text + cityName[i];
                        }
                    }
                    break;
                }
            }
            return test_text;
        }


        @Override
        protected void onPostExecute(String s) {
            dbText.setText(s);
            /*Intent i = new Intent(WelcomeActivity.this, WeatherActivity.class);
            i.putExtra("", s);*/

        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        isNetworkOK();
    }

    //检测网络连接状态
    private void isNetworkOK(){
        if (NetworkUtils.getNetworkStatus(this) == 0) {
            new AlertDialog.Builder(this).setCancelable(false)
                    .setTitle("没有网络连接")
                    .setMessage("打开网络设置？")
                    .setPositiveButton("是", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            Intent intent = null;
                            //判断手机系统的版本  即API大于10 就是3.0或以上版本
                            if (Build.VERSION.SDK_INT > 10) {
                                intent = new Intent(Settings.ACTION_SETTINGS);
                            } else {
                                intent = new Intent();
                                //// TODO: 2016/2/26 api <=10 的版本 打开设置页面
                                ComponentName component = new ComponentName("com.android.settings", "com.android.settings.Settings");
                                intent.setComponent(component);
                                intent.setAction("android.intent.action.VIEW");
                            }
                            WelcomeActivity.this.startActivity(intent);
                        }
                    })
                    .setNegativeButton("否", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            WelcomeActivity.this.finish();
                        }
                    })
                    .show();
        }
    }
    //初始化数据库
    private void IntiCityListDb() {
        cldh = new CityListDbHelper(getApplicationContext());
        String cityListJsonStr = getString(R.string.default_city_list_json_str);
        ArrayList<CityItem> cityList = jsonUtils.cityListJsonToCityArrayList(cityListJsonStr);
        cldh.insertCity(cityList);
        Log.i(TAG, "数据库库初始化完成");
        //查找
//        String[] key = {"CN101010100"};
//        ArrayList<CityItem> cityResult = cldh.searchCityDb(key);
//        test_text = cityResult.get(0).getCity();
    }

    //注册位置广播的Receiver
    private class LocationBroadcastReceiver extends BroadcastReceiver {

        @Override
        public void onReceive(Context context, Intent intent) {
            if (!intent.getAction().equals(LocationUtil.LOCATION_ACTION)) return;
            lat = intent.getDoubleExtra(LocationUtil.LOCATION_LAT, 0.0);
            lon = intent.getDoubleExtra(LocationUtil.LOCATION_LON, 0.0);
            dialog.dismiss();
            isLocationOK = true;
            WelcomeActivity.this.unregisterReceiver(this);// 不需要时注销
        }
    }








    /*加载欢迎页时不允许被返回退出
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event){
        if (keyCode == KeyEvent.KEYCODE_BACK
                && event.getRepeatCount() == 0) {
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }
    */
}
