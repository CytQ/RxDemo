package com.mrli.example.rxdemo.utils;


import android.os.Bundle;

import rx.Observable;
import rx.subjects.PublishSubject;
import rx.subjects.SerializedSubject;
import rx.subjects.Subject;

/**
 * Eventbus by Rxjava
 * <p/>
 * Created by lizhongquan on 16-5-20.
 */
public class Rxbus {

    private final Subject<Object, Object>
            rxBus = new SerializedSubject<>(PublishSubject.create());

    private Rxbus() {

    }

    public static final Rxbus getInstance() {
        return RxbusHolder.instance;
    }

    private static class RxbusHolder {
        private static final Rxbus instance = new Rxbus();
    }

    public void send(Bundle o) {
        rxBus.onNext(o);
    }

    public Observable<Object> toObservable() {
        return rxBus;
    }
}
