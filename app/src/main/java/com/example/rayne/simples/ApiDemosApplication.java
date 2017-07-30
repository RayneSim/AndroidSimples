package com.example.rayne.simples;

import android.app.Application;

import com.orhanobut.logger.AndroidLogAdapter;
import com.orhanobut.logger.Logger;


/**
 * Created by RayneSim on 17/07/28.
 */

public class ApiDemosApplication extends Application{
    @Override
    public void onCreate() {
        super.onCreate();
        //初始化日志打印工具
        Logger.addLogAdapter(new AndroidLogAdapter());
    }
}
