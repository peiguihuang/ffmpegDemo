package com.gui.ffmpegdemo;

import android.app.Application;

import com.tencent.bugly.crashreport.CrashReport;

public class BaseApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        CrashReport.initCrashReport(getApplicationContext(), "41f51d6b13", true);
    }
}
