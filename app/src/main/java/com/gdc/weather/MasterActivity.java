package com.gdc.weather;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class MasterActivity extends AppCompatActivity {

    private FragmentCurrentDayConditions fragmentCurrentDayConditions;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_master);
    }
}
