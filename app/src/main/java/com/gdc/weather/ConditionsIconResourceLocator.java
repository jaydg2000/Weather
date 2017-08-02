package com.gdc.weather;

import com.gdc.weather.model.Conditions;

public final class ConditionsIconResourceLocator {

    private ConditionsIconResourceLocator() {}

    public static int findResourceForConditionsCode(int conditionsCode) {

        switch (conditionsCode) {
            case Conditions.SHOWERS:
            case Conditions.DRIZZLE:
            case Conditions.FREEZING_RAIN:
            case Conditions.FREEZING_DRIZZLE:
                return R.drawable.condition_showers;
            case Conditions.SNOW:
            case Conditions.SNOW_FLURRIES:
            case Conditions.SNOW_SHOWERS:
            case Conditions.BLOWING_SNOW:
            case Conditions.HEAVY_SNOW:
            case Conditions.LIGHT_SNOW_SHOWERS:
                return R.drawable.condition_snow;
            case Conditions.CLOUDY:
            case Conditions.MOSTLY_CLOUDY_DAY:
            case Conditions.MOSTLY_CLOUDY_NIGHT:
            case Conditions.FOGGY:
                return R.drawable.condition_overcast;
            case Conditions.PARTLY_CLOUDY:
            case Conditions.PARTLY_CLOUDY_DAY:
            case Conditions.PARTLY_CLOUDY_NIGHT:
                return R.drawable.condition_clouds;
            case Conditions.HAIL:
            case Conditions.MIXED_RAIN_AND_HAIL:
                return R.drawable.condition_hail;
            case Conditions.THUNDERSTORMS:
            case Conditions.THUNDERSHOWERS:
            case Conditions.ISOLATED_THUNDERSHOWERS:
            case Conditions.ISOLATED_THUNDERSTORMS:
            case Conditions.SCATTERED_THUNDERSTORMS:
            case Conditions.SEVERE_THUNDERSTORMS:
            case Conditions.TROPICAL_STORM:
            case Conditions.HURRICANE:
            case Conditions.SCATTERED_SHOWERS:
                return R.drawable.condition_storms;
            default:
                return R.drawable.condition_clear;
        }
    }
}
