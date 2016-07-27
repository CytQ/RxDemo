package com.mrli.example.rxdemo.utils;

import android.support.v4.app.FragmentManager;

import com.mrli.example.rxdemo.base.BaseFragment;


/**
 * Created by lizhongquan on 16-7-26.
 */
public class MyUtils<T> {

    public static <T> T checkNotNull(T object, String msg) {

        if (object == null) {
            throw new NullPointerException(msg);
        }
        return object;
    }

    public static <T> T checkNotNull(T object) {

        if (object == null) {
            throw new NullPointerException();
        }
        return object;
    }

    public static void addFragmentToActivity(FragmentManager fragmentManager,
                                             BaseFragment fragment,
                                             int frameId) {
        checkNotNull(fragmentManager);
        checkNotNull(fragment);

        fragmentManager.beginTransaction()
                .add(frameId, fragment)
                .commit();
    }

}
