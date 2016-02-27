package me.missfan.syjh.database;

import android.provider.BaseColumns;

/**
 * Created by Gr on 2016/2/26.
 */
public final class CityListContract {
    public CityListContract() {}

    public abstract class CityList implements BaseColumns {
        public static final String TABLE_NAME = "cities";
        public static final String COLUMN_NAME_CITY_ID = "id";
        public static final String COLUMN_NAME_CITY_NAME = "city";
        public static final String COLUMN_NAME_CNTY_NAME= "cnty";
        public static final String COLUMN_NAME_LAT= "lat";
        public static final String COLUMN_NAME_LON= "lon";
        public static final String COLUMN_NAME_PROV= "prov";
    }
}
