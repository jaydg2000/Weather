package com.gdc.weather.ui;

/**
 * Created by jaydg on 7/30/2017.
 */

public final class UIFormatHelper {
    private UIFormatHelper() {}

    public static String formatTemperature(float temperature) {
        return Float.toString(temperature);
    }

    public static String formatPercentage(float percentage) {
        return Float.toString(percentage);
    }
}
