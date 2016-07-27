package com.mrli.example.rxdemo.weather;

import android.os.Bundle;
import android.widget.Toast;

import com.mrli.example.rxdemo.MyApplication;
import com.mrli.example.rxdemo.data.DataManager;
import com.mrli.example.rxdemo.data.api.Contributor;
import com.mrli.example.rxdemo.data.api.WeatherService;
import com.mrli.example.rxdemo.utils.MyUtils;
import com.mrli.example.rxdemo.utils.Rxbus;

import java.util.List;

import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import rx.Subscription;
import rx.functions.Action1;
import rx.functions.Func1;

/**
 * Created by lizhongquan on 16-7-26.
 */
public class WeatherPresenter implements WeatherContract.Presenter {

    private WeatherContract.View weatherView;
    private DataManager dataManager;

    private Subscription subscription;

    public WeatherPresenter(WeatherContract.View weatherView,
                            DataManager dataManager) {

        this.dataManager = MyUtils.checkNotNull(dataManager, "DataManger in Weather cannot be null");
        this.weatherView = MyUtils.checkNotNull(weatherView, "View in Weather cannot be null");
        this.weatherView.setPresenter(this);
        bindBus();
    }

    @Override
    public void subscribe() {

    }

    private void bindBus() {
        subscription = Rxbus.getInstance().toObservable()
                .map(new Func1<Object, Bundle>() {
                    @Override
                    public Bundle call(Object o) {
                        return (Bundle) o;
                    }
                })
                .subscribe(new Action1<Bundle>() {
                    @Override
                    public void call(Bundle bundle) {
                        handleBus(bundle);
                    }
                }, new Action1<Throwable>() {
                    @Override
                    public void call(Throwable throwable) {
                        Toast.makeText(MyApplication.getGlobalContext(),
                                "Errors happend in Rxbus : " + throwable.getMessage(),
                                Toast.LENGTH_SHORT).show();
                    }
                });

    }

    private void handleBus(Bundle bundle) {

    }

    @Override
    public void unsubscribe() {
        if (subscription.isUnsubscribed()) {
            subscription.unsubscribe();
        }
        weatherView = null;
    }

    @Override
    public void sendRequest() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://api.github.com")
                .addConverterFactory(GsonConverterFactory.create())
                .client(new OkHttpClient())
                .build();
        WeatherService service = retrofit.create(WeatherService.class);

        Call<List<Contributor>> call = service.contributors("square", "retrofit");

        call.enqueue(new Callback<List<Contributor>>() {
            @Override
            public void onResponse(Call<List<Contributor>> call, Response<List<Contributor>> response) {
                weatherView.showWeather("Body : " + response.body().toString());
            }

            @Override
            public void onFailure(Call<List<Contributor>> call, Throwable t) {
                weatherView.showWeather("Error");
            }
        });
    }
}
