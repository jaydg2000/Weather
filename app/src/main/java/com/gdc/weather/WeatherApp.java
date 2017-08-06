package com.gdc.weather;

import android.app.Application;

/**
 * Created by jaydg on 7/16/2017.
 */

public class WeatherApp extends Application {

    public static final String TAG = "GDC-WEATHER";
    public static final String SHARED_PREF_FILENAME = "WeatherSharedPref";
    public static final String SHARED_PREF_CITY_STATE = "key_city_state";
    public static final String SHARED_PREF_FAVORITES = "key_favorites";

    @Override
    public void onCreate() {
        super.onCreate();
    }
}
