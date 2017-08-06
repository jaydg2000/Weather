package com.gdc.weather;

import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.gdc.weather.ui.PreferenceHelper;

public class PreferencesActivity extends AppCompatActivity {

    private EditText editTextSettingsState;
    private EditText editTextSettingsCity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_preferences);

        editTextSettingsState = (EditText) findViewById(R.id.editTextSettingsState);
        editTextSettingsCity = (EditText) findViewById(R.id.editTextSettingsCity);

        loadSettings();
        setupSaveButtonClick();
    }

    private void loadSettings() {
        SharedPreferences sharedPreferences =
                getSharedPreferences(WeatherApp.SHARED_PREF_FILENAME, MODE_PRIVATE);
        String preference = sharedPreferences.getString(WeatherApp.SHARED_PREF_CITY_STATE, "Phoenix, AZ");
        editTextSettingsState.setText(PreferenceHelper.parseStateFromPreference(preference));
        editTextSettingsCity.setText(PreferenceHelper.parseCityFromPreference(preference));
    }

    private void setupSaveButtonClick() {
        findViewById(R.id.buttonSaveSettings)
                .setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        String state = editTextSettingsState.getText().toString();
                        String city = editTextSettingsCity.getText().toString();

                        getSharedPreferences(WeatherApp.SHARED_PREF_FILENAME, MODE_PRIVATE)
                                .edit()
                                .putString(
                                        WeatherApp.SHARED_PREF_CITY_STATE,
                                        PreferenceHelper.formatCityStatePreference(city, state))
                                .commit();
                        finish();
                    }
                });
    }
}
