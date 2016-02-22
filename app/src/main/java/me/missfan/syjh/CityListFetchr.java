package me.missfan.syjh;

import android.content.Context;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONTokener;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.net.URL;
import java.util.ArrayList;

import javax.net.ssl.HttpsURLConnection;

import me.missfan.syjh.beans.CityItem;
import me.missfan.syjh.utils.JSONUtils;
import me.missfan.syjh.utils.NetworkUtils;

/**
 * Created by Gr on 2016/2/18.
 */
public class CityListFetchr {
    private Context mContext;
    private String mFilename;
    private NetworkUtils networkUtils = new NetworkUtils();
    private JSONUtils jsonUtils = new JSONUtils();

    private static final String FILE_NAME = "citylist.json";
    private static final String TAG = "CityListFetchr";
    /*
    获取城市列表
     */

    public CityListFetchr(){
    }

    public CityListFetchr(Context c, String f){
        mContext = c;
        mFilename = f;
    }

    public ArrayList<CityItem> fetCityItems(Context mContext) {
        ArrayList<CityItem> cityItems;
        String cityListJsonStr = networkUtils.getCityJsonStr();
        jsonUtils.writeJsonFiles(mContext, cityListJsonStr, FILE_NAME);
        cityItems = jsonUtils.readCityItemJsonFiles(mContext, FILE_NAME);
        return cityItems;
    }
}
