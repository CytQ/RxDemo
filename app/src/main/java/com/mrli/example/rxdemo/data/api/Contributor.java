package com.mrli.example.rxdemo.data.api;

/**
 * Created by lizhongquan on 16-7-27.
 */
public class Contributor {

    public final String login;
    public final int contributions;

    public Contributor(String login, int contributions) {
        this.login = login;
        this.contributions = contributions;
    }
}
