package com.ew.electronicwardrobe;

import android.app.Application;
import android.content.Context;

import interfaces.heweather.com.interfacesmodule.view.HeConfig;

public class AppClient extends Application {

    private static Context context;

    public static Context getmContext() {
        return context;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        HeConfig.init("HE2005280230411519", "48bdbb48918e41bb8d6cd6869d6a869f");
        HeConfig.switchToFreeServerNode();
        context = this.getApplicationContext();
    }
}
