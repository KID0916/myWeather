package me.missfan.syjh.activity;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import me.missfan.syjh.R;
import me.missfan.syjh.beans.weather.CityWeatherItem;
import me.missfan.syjh.utils.JSONUtils;
import me.missfan.syjh.utils.NetworkUtils;

public class CityWeatherActivity extends AppCompatActivity {
    private static final String TAG = "CityWeatherActivity";

    NetworkUtils networkUtils = new NetworkUtils();
    JSONUtils jsonUtils = new JSONUtils();

    CityWeatherItem cityWeatherItem;

    TextView testCityWeather;
    String weatherCityName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_city_weather);

        testCityWeather = (TextView) findViewById(R.id.test_city_weather);
        weatherCityName = getIntent().getStringExtra("cityId");
        Log.i(TAG, weatherCityName);
        new FetchWeatherTask().execute();
    }

    class FetchWeatherTask extends AsyncTask<Void, Void, CityWeatherItem>{

        @Override
        protected CityWeatherItem doInBackground(Void... params) {
            String cityweather = networkUtils.getCityIdWeatherJsonStr(weatherCityName);
            Log.i(TAG, cityweather);
            jsonUtils.writeJsonFiles(CityWeatherActivity.this, cityweather, weatherCityName + ".json");
            CityWeatherItem cityWeatherItem1 = jsonUtils.readCityWeatherItemFiles(CityWeatherActivity.this, weatherCityName+".json");
            return cityWeatherItem1;
        }

        @Override
        protected void onPostExecute(CityWeatherItem c){
            testCityWeather.setText(c.toString());
        }
    }
}
