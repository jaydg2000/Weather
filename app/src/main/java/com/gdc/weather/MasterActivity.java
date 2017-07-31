package com.gdc.weather;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import com.gdc.weather.mapper.WeatherReportMapper;
import com.gdc.weather.model.WeatherReport;
import com.gdc.weather.repository.WeatherReportRepository;
import com.gdc.weather.service.YahooWeatherService;

public class MasterActivity extends AppCompatActivity implements SharedPreferences.OnSharedPreferenceChangeListener {

    private FragmentCurrentDayConditions fragmentCurrentDayConditions;
    private FragmentForecast fragmentForecast;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_master);

        fragmentCurrentDayConditions
                = (FragmentCurrentDayConditions) getFragmentManager()
                .findFragmentById(R.id.fragmentCurrentDayConditions);

        fragmentForecast
                = (FragmentForecast) getFragmentManager().findFragmentById(R.id.fragmentForecast);
    }

    @Override
    protected void onStart() {
        super.onStart();
        SharedPreferences sharedPreferences
                = getSharedPreferences(WeatherApp.SHARED_PREF_FILENAME, MODE_PRIVATE);
        sharedPreferences.registerOnSharedPreferenceChangeListener(this);
        // TODO: Don't want to call service more than once per hour.
        loadWeatherReport();
    }

    @Override
    protected void onStop() {
        SharedPreferences sharedPreferences
                = getSharedPreferences(WeatherApp.SHARED_PREF_FILENAME, MODE_PRIVATE);
        sharedPreferences.unregisterOnSharedPreferenceChangeListener(this);

        super.onStop();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int itemId = item.getItemId();

        if (itemId == R.id.menuItemSettings) {
            startActivity(new Intent(this, PreferencesActivity.class));
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private void loadWeatherReport() {
        SharedPreferences sharedPreferences
                = getSharedPreferences(WeatherApp.SHARED_PREF_FILENAME, MODE_PRIVATE);
        String state = sharedPreferences.getString(WeatherApp.SHARED_PREF_STATE, "AZ");
        String city = sharedPreferences.getString(WeatherApp.SHARED_PREF_CITY, "Phoenix");

        new WeatherReportRepository(new YahooWeatherService(), new WeatherReportMapper()) {
            @Override
            public void onWeatherReportRetrieved(WeatherReport weatherReport) {
                bindWeatherReport(weatherReport);
            }
        }.retrieveWeatherReport(city, state);
    }

    private void bindWeatherReport(WeatherReport weatherReport) {
        fragmentCurrentDayConditions
                .setCurrentDayConditions(weatherReport.getCurrentDayConditions());
        fragmentForecast
                .setForecasts(weatherReport.getForecasts());
    }

    @Override
    public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String key) {
        loadWeatherReport();
    }
}
