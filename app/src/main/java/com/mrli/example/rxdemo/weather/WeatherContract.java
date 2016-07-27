package com.mrli.example.rxdemo.weather;

import com.mrli.example.rxdemo.base.BasePresenter;
import com.mrli.example.rxdemo.base.BaseView;

/**
 * Created by lizhongquan on 16-7-26.
 */
public interface WeatherContract {

    interface Presenter extends BasePresenter {
        void sendRequest();
    }

    interface View extends BaseView<Presenter> {
        void showWeather(String response);
    }
}
