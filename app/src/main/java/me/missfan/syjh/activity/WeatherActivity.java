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

public class WeatherActivity extends AppCompatActivity {
    private static final String TAG = "WeatherActivity";

    NetworkUtils networkUtils = new NetworkUtils();
    JSONUtils jsonUtils = new JSONUtils();

    CityWeatherItem cityWeatherItem;

    TextView suggestion, pm25, weather, temperature, voice, details, night, tomorrow, seven_day;
    String weatherCityId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_city_weather);
        weatherCityId = getIntent().getStringExtra("cityId");
        setTitle(getIntent().getStringExtra("cityName"));
        suggestion = (TextView) findViewById(R.id.suggestion);
        pm25 = (TextView) findViewById(R.id.pm25);
        weather = (TextView) findViewById(R.id.weather);
        temperature = (TextView) findViewById(R.id.temperature);
        voice = (TextView) findViewById(R.id.voice);
        details = (TextView) findViewById(R.id.details);
        night = (TextView) findViewById(R.id.night);
        tomorrow = (TextView) findViewById(R.id.tomorrow);
        seven_day = (TextView) findViewById(R.id.seven_day);
        Log.i(TAG, weatherCityId);
        new FetchWeatherTask().execute();
    }

    class FetchWeatherTask extends AsyncTask<Void, Void, CityWeatherItem> {

        @Override
        protected CityWeatherItem doInBackground(Void... params) {
            String cityweather = networkUtils.getCityIdWeatherJsonStr(weatherCityId);
            Log.i(TAG, cityweather);
            jsonUtils.writeJsonFiles(WeatherActivity.this, cityweather, weatherCityId + ".json");
            CityWeatherItem cityWeatherItem1 = jsonUtils.readCityWeatherItemFiles(WeatherActivity.this, weatherCityId + ".json");
            return cityWeatherItem1;
        }

        @Override
        protected void onPostExecute(CityWeatherItem c) {
            if (c.getSuggestion() != null)
                suggestion.setText(c.getSuggestion().toString());
            if (c.getAqi() != null)
                pm25.setText("空气质量：" + c.getAqi().toString());
            if (c.getNow() != null) {
                weather.setText("天气：" + c.getNow().getTxt());
                temperature.setText(c.getNow().getTmp());
                details.setText("详细信息:" + c.getNow().toString());
            }
            if (c.getHourlyForecastList() != null)
                night.setText("今天夜间:" + c.getHourlyForecastList().get(0).toString());
            if (c.getDailyForecastList() != null) {
                tomorrow.setText("明天白天" + c.getDailyForecastList().get(1).toString());
                seven_day.setText("未来7天：" + c.getDailyForecastList().toString());
            }
        }
    }
}
