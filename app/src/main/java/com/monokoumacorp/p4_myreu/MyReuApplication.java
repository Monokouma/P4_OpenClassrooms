package com.monokoumacorp.p4_myreu;

import android.app.Application;

public class MyReuApplication extends Application {

    private static Application sApplication;

    @Override
    public void onCreate() {
        super.onCreate();
        sApplication = this;
    }

    public static Application getInstance() {
        return sApplication;
    }
}