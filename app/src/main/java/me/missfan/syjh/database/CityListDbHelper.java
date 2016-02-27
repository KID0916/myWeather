package me.missfan.syjh.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;

import me.missfan.syjh.beans.CityItem;

/**
 * Created by Gr on 2016/2/26.
 */
public class CityListDbHelper extends SQLiteOpenHelper {
    private static final String TAG = "CityListDbHelper";
    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "citiesinfo.sqlite";

    private static final String TABLE_CITIES = "cities";
    private static final String COLUMN_CITY_ID = "id";
    private static final String COLUMN_CITY_NAME = "city";
    private static final String COLUMN_CNTY_NAME = "cnty";
    private static final String COLUMN_LAT = "lat";
    private static final String COLUMN_LON = "lon";
    private static final String COLUMN_PROV = "prov";

    private static final String TEXT_TYPE = " TEXT";
    private static final String COMMA_SEP = ",";

    private static final String SQL_DELETE_ENTRIES =
            "DROP TABLE IF EXISTS " + TABLE_CITIES;


    public CityListDbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        //创建表cities
        db.execSQL("create table " + TABLE_CITIES + " (" + COLUMN_CITY_ID + TEXT_TYPE + " primary key, " + COLUMN_CITY_NAME + TEXT_TYPE + ", " + COLUMN_CNTY_NAME + TEXT_TYPE + ", " + COLUMN_LAT + TEXT_TYPE + ", " + COLUMN_LON + TEXT_TYPE + ", " + COLUMN_PROV + TEXT_TYPE + ")");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public void insertCity(ArrayList<CityItem> cityList) {
        ContentValues cv = new ContentValues();
        SQLiteDatabase db = getWritableDatabase();
        for(int i = 0; i<cityList.size(); i++) {
            cv.put(COLUMN_CITY_ID, cityList.get(i).getId());
            cv.put(COLUMN_CITY_NAME, cityList.get(i).getCity());
            cv.put(COLUMN_CNTY_NAME, cityList.get(i).getCnty());
            cv.put(COLUMN_LAT, cityList.get(i).getLat());
            cv.put(COLUMN_LON, cityList.get(i).getLon());
            cv.put(COLUMN_PROV, cityList.get(i).getProv());
            db.insert(TABLE_CITIES, null, cv);
        }
    }

    public ArrayList<CityItem> searchCityDb(String[] key) {
        ArrayList<CityItem> searchCityList = new ArrayList<CityItem>();

        String[] projection = {
                COLUMN_CITY_ID,
                COLUMN_CITY_NAME,
                COLUMN_CNTY_NAME,
                COLUMN_LAT,
                COLUMN_LON,
                COLUMN_PROV};
        String sortOrder = COLUMN_CITY_NAME + " asc";
        Cursor c = getWritableDatabase().query(
                TABLE_CITIES,  // The table to query
                projection,                               // The columns to return
                COLUMN_CITY_ID + "=?",                                // The columns for the WHERE clause
                key,                            // The values for the WHERE clause
                null,                                     // don't group the rows
                null,                                     // don't filter by row groups
                sortOrder                                 // The sort order
        );
        if(c.getCount() != 0){
            c.moveToFirst();
            for(int i =0;i<c.getCount();i++){
                String cityID = c.getString(c.getColumnIndexOrThrow(COLUMN_CITY_ID));
                String cityName = c.getString(c.getColumnIndexOrThrow(COLUMN_CITY_NAME));
                String cityCnty = c.getString(c.getColumnIndexOrThrow(COLUMN_CNTY_NAME));
                String cityLat = c.getString(c.getColumnIndexOrThrow(COLUMN_LAT));
                String cityLon = c.getString(c.getColumnIndexOrThrow(COLUMN_LON));
                String cityProv = c.getString(c.getColumnIndexOrThrow(COLUMN_PROV));
                searchCityList.add(new CityItem(cityName,cityCnty,cityID,cityLat,cityLon,cityProv));
            }
        }
        c.close();
        return searchCityList;
    }


}
