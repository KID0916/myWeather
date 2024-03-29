package me.missfan.syjh.activity;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

import me.missfan.syjh.CityListFetchr;
import me.missfan.syjh.beans.CityItem;
import me.missfan.syjh.R;

public class SearchCitiesActivity extends AppCompatActivity {

    private static final String TAG = "SearchCitiesActivity";

    GridView cityList;
    TextView textView;
    ArrayList<CityItem> mItems;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_city_list);

        cityList = (GridView) findViewById(R.id.city_list);
        setGridViewAdapter();
        new FetchCity().execute();
    }

    void setGridViewAdapter(){
        if(mItems != null) {
            cityList.setAdapter(new ArrayAdapter<CityItem>(SearchCitiesActivity.this, android.R.layout.simple_gallery_item, mItems));
        } else {
            cityList.setAdapter(null);
        }
    }

    private class FetchCity extends AsyncTask<Void, Void, ArrayList<CityItem>> {

        @Override
        protected ArrayList<CityItem> doInBackground(Void... params) {
            ArrayList<CityItem> cityItems;
            cityItems = new CityListFetchr().fetCityItems(SearchCitiesActivity.this);
            return cityItems;
        }

        protected void onPostExecute(ArrayList<CityItem> cityItems){
            mItems = cityItems;
            setGridViewAdapter();
            cityList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    CityItem c = (CityItem)(parent.getAdapter()).getItem(position);
                    String cityId = c.getId();
                    String cityName = c.getCity();
                    Intent i = new Intent(SearchCitiesActivity.this , WeatherActivity.class);
                    i.putExtra("cityId",cityId);
                    i.putExtra("cityName", cityName);
                    startActivity(i);
                }
            });
        }
    }

    private class CityItemAdapter extends ArrayAdapter<CityItem>{

        public CityItemAdapter(ArrayList<CityItem> items) {
            super(SearchCitiesActivity.this, 0, items);
        }

        public View getView(int position, View convertView, ViewGroup parent) {
            if(convertView == null) {
                convertView = getLayoutInflater().inflate(R.layout.city_item, parent, false);
            }
            ImageView backImage = (ImageView) convertView.findViewById(R.id.city_item);
            TextView cityName = (TextView) convertView.findViewById(R.id.city_name);
            backImage.setImageResource(R.drawable.cityback);
            return convertView;
        }
    }
}
