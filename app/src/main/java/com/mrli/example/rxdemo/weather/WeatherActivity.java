package com.mrli.example.rxdemo.weather;

import android.os.Bundle;

import com.mrli.example.rxdemo.R;
import com.mrli.example.rxdemo.base.BaseActivity;
import com.mrli.example.rxdemo.data.DataManager;
import com.mrli.example.rxdemo.utils.MyUtils;

public class WeatherActivity extends BaseActivity {

    private WeatherPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        WeatherFragment weatherFragment = (WeatherFragment) getSupportFragmentManager()
                .findFragmentById(R.id.containerframe);

        if (weatherFragment == null) {
            weatherFragment = WeatherFragment.getInstance();
            MyUtils.addFragmentToActivity(getSupportFragmentManager(),
                    weatherFragment, R.id.containerframe);
        }

        presenter = new WeatherPresenter(weatherFragment, new DataManager());
    }
}
