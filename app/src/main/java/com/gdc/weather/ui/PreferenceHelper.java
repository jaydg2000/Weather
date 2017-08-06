package com.gdc.weather.ui;

/**
 * Created by jaydg on 8/6/2017.
 */

public final class PreferenceHelper {

    private PreferenceHelper() {}

    public static String parseCityFromPreference(String preference) {
        String[] parts = preference.split(",");
        return parts[0].trim();
    }

    public static String parseStateFromPreference(String preference) {
        String[] parts = preference.split(",");
        return parts[1].trim();
    }

    public static String formatCityStatePreference(String city, String state) {
        return String.format("%s, %s", city.trim(), state.trim());
    }

}
