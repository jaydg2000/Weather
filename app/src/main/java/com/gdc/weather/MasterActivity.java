package com.gdc.weather;

import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v4.util.ArraySet;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.gdc.weather.mapper.WeatherReportMapper;
import com.gdc.weather.model.WeatherReport;
import com.gdc.weather.repository.WeatherReportRepository;
import com.gdc.weather.service.YahooWeatherService;
import com.gdc.weather.ui.PreferenceHelper;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;

public class MasterActivity extends AppCompatActivity implements SharedPreferences.OnSharedPreferenceChangeListener {

    private DrawerLayout drawerLayout;
    private ListView listViewFavorites;
    private FragmentCurrentDayConditions fragmentCurrentDayConditions;
    private FragmentForecast fragmentForecast;
    private ActionBarDrawerToggle toggle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_master);

        drawerLayout = (DrawerLayout) findViewById(R.id.drawerLayoutMaster);
        listViewFavorites = (ListView) findViewById(R.id.listViewLeftDrawer);

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
        loadFavoritesIntoDrawer();
        addDrawerListener();
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
    protected void onDestroy() {
        drawerLayout.removeDrawerListener(toggle);
        super.onDestroy();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (toggle.onOptionsItemSelected(item)) {
            return true;
        }
        int itemId = item.getItemId();

        switch (itemId) {
            case R.id.menuItemSettings:
                startActivity(new Intent(this, PreferencesActivity.class));
                return true;
            case R.id.menuItemAddFavorite:
                addToFavorites();
                loadFavoritesIntoDrawer();
                Toast.makeText(this, "Added to favorites.", Toast.LENGTH_SHORT).show();
                break;
            case R.id.menuItemClearFavorites:
                clearFavorites();
                loadFavoritesIntoDrawer();
                Toast.makeText(this, "Favorites cleared.", Toast.LENGTH_SHORT).show();
                break;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        // Sync the toggle state after onRestoreInstanceState has occurred.
        toggle.syncState();
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        toggle.onConfigurationChanged(newConfig);
    }

    private void addToFavorites() {
        SharedPreferences sharedPreferences
                = getSharedPreferences(WeatherApp.SHARED_PREF_FILENAME, MODE_PRIVATE);
        String preference = sharedPreferences.getString(WeatherApp.SHARED_PREF_CITY_STATE, "Phoenix, AZ");

        Set<String> favorites = getFavorites(sharedPreferences);

        if (favorites.contains(preference)) {
            return;
        }

        favorites.add(preference);

        sharedPreferences
                .edit()
                .putStringSet(WeatherApp.SHARED_PREF_FAVORITES, favorites)
                .apply();
    }

    private Set<String> getFavorites(SharedPreferences sharedPreferences) {
        Set<String> favorites =
                sharedPreferences.getStringSet(
                        WeatherApp.SHARED_PREF_FAVORITES,
                        new ArraySet<String>());

        return favorites;
    }

    private void clearFavorites() {
        SharedPreferences sharedPreferences = getAppSharedPreferences();
        sharedPreferences.edit().remove(WeatherApp.SHARED_PREF_FAVORITES).apply();
    }

    private void loadFavoritesIntoDrawer() {
        SharedPreferences sharedPreferences = getAppSharedPreferences();
        Set<String> favorites = getFavorites(sharedPreferences);
        List<String> displayableFavorites;
        if (favorites.isEmpty()) {
            displayableFavorites = new ArrayList<>();
            displayableFavorites.add("No Favorites");
        } else {
            displayableFavorites = new ArrayList<String>(favorites);
        }
        final ListView listViewFavorites = (ListView) findViewById(R.id.listViewLeftDrawer);
        listViewFavorites.setAdapter(
                new ArrayAdapter<String>(
                        this,
                        android.R.layout.simple_list_item_1,
                        displayableFavorites));

        listViewFavorites.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                drawerLayout.closeDrawers();
                String data = listViewFavorites.getItemAtPosition(position).toString();
                String city = PreferenceHelper.parseCityFromPreference(data);
                String state = PreferenceHelper.parseStateFromPreference(data);
                setCurrentCityAndStatePreference(city, state);
                loadWeatherReport();
            }
        });
    }

//    private List<String> transformFavoritesSetIntoList(Set<String> sourceFavorites) {
//        List<String> destinationFavorites = new ArrayList<>();
//        destinationFavorites.addAll(sourceFavorites);
//        String[] arrFavorites = new String[sourceFavorites.size()];
//        sourceFavorites.toArray(arrFavorites);
//        for (int c = 0; c < arrFavorites.length; c += 2) {
//            String city = arrFavorites[c];
//            String state = arrFavorites[c + 1];
//            destinationFavorites.add(formatCityStatePreference(city, state));
//        }
//        return destinationFavorites;
//    }

    private SharedPreferences getAppSharedPreferences() {
        return getSharedPreferences(WeatherApp.SHARED_PREF_FILENAME, MODE_PRIVATE);
    }

    private void setCurrentCityAndStatePreference(String city, String state) {
        SharedPreferences sharedPreferences = getAppSharedPreferences();
        sharedPreferences.edit()
                .putString(
                        WeatherApp.SHARED_PREF_CITY_STATE,
                        PreferenceHelper.formatCityStatePreference(city, state))
                .apply();
    }

    private void loadWeatherReport() {
        SharedPreferences sharedPreferences = getAppSharedPreferences();
        String preference = sharedPreferences.getString(WeatherApp.SHARED_PREF_CITY_STATE, "Phoenix, AZ");
        String state = PreferenceHelper.parseStateFromPreference(preference);
        String city = PreferenceHelper.parseCityFromPreference(preference);

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

    private void addDrawerListener() {
        toggle = new ActionBarDrawerToggle(
                this,
                drawerLayout,
                R.string.drawer_open,
                R.string.drawer_close) {
            @Override
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);
                invalidateOptionsMenu();
            }

            @Override
            public void onDrawerClosed(View drawerView) {
                super.onDrawerClosed(drawerView);
                invalidateOptionsMenu();
            }
        };

        drawerLayout.addDrawerListener(toggle);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);
    }

    @Override
    public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String key) {
        loadWeatherReport();
    }
}
