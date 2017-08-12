package com.gdc.weather;

import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.gdc.weather.ui.PreferenceHelper;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class PreferencesActivity extends AppCompatActivity {

    @BindView(R.id.editTextSettingsState)
    EditText editTextSettingsState;
    @BindView(R.id.editTextSettingsCity)
    EditText editTextSettingsCity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_preferences);

        ButterKnife.bind(this);

        loadSettings();
    }

    private void loadSettings() {
        SharedPreferences sharedPreferences =
                getSharedPreferences(WeatherApp.SHARED_PREF_FILENAME, MODE_PRIVATE);
        String preference = sharedPreferences.getString(WeatherApp.SHARED_PREF_CITY_STATE, "Phoenix, AZ");
        editTextSettingsState.setText(PreferenceHelper.parseStateFromPreference(preference));
        editTextSettingsCity.setText(PreferenceHelper.parseCityFromPreference(preference));
    }

    @OnClick(R.id.buttonSaveSettings)
    void onSaveButtonClick() {
        String state = editTextSettingsState.getText().toString();
        String city = editTextSettingsCity.getText().toString();

        getSharedPreferences(WeatherApp.SHARED_PREF_FILENAME, MODE_PRIVATE)
                .edit()
                .putString(
                        WeatherApp.SHARED_PREF_CITY_STATE,
                        PreferenceHelper.formatCityStatePreference(city, state))
                .apply();
        finish();
    }
}
