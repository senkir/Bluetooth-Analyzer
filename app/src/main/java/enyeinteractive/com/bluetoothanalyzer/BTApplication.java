package enyeinteractive.com.bluetoothanalyzer;

import android.app.Application;

import com.raizlabs.android.dbflow.config.FlowManager;


public class BTApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        FlowManager.init(this);
    }
}
