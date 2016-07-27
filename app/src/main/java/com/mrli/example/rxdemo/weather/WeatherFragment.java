package com.mrli.example.rxdemo.weather;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.mrli.example.rxdemo.R;
import com.mrli.example.rxdemo.base.BaseFragment;
import com.mrli.example.rxdemo.utils.MyUtils;

/**
 * Created by lizhongquan on 16-7-26.
 */
public class WeatherFragment extends BaseFragment implements WeatherContract.View {

    private WeatherContract.Presenter presenter;
    private Button button_sendrequest;
    private TextView text_showresponse;

    public static WeatherFragment getInstance() {
        return WeatherFragmentHolder.fragment;
    }

    private static class WeatherFragmentHolder {
        static WeatherFragment fragment = new WeatherFragment();
    }

    @Override
    public void setPresenter(WeatherContract.Presenter presenter) {
        this.presenter = MyUtils.checkNotNull(presenter, "Presenter in Weather cannot be null");
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_weather, container, false);
        button_sendrequest = (Button) root.findViewById(R.id.button_sendrequest);
        text_showresponse = (TextView) root.findViewById(R.id.text_showresponse);

        button_sendrequest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.sendRequest();
            }
        });
        return root;
    }

    @Override
    public void showWeather(String response) {
        text_showresponse.setText(response);
    }

    @Override
    public void onResume() {
        super.onResume();
        presenter.subscribe();
    }

    @Override
    public void onPause() {
        super.onPause();
        presenter.unsubscribe();
    }
}
