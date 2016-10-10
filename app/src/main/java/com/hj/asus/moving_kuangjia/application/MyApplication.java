package com.hj.asus.moving_kuangjia.application;

import android.app.Application;
import android.util.DisplayMetrics;

import org.xutils.BuildConfig;
import org.xutils.x;

/**
 * Created by asus on 2016/9/13.
 */
public class MyApplication extends Application {


    public static int mScreenWidth;
    public static int mScreenHeight;

    @Override
    public void onCreate() {
        super.onCreate();
        x.Ext.init(this);
        x.Ext.setDebug(BuildConfig.DEBUG); // 是否输出debug日志, 开启debug会影响性能.


        DisplayMetrics dm = new DisplayMetrics();
        dm = this.getApplicationContext().getResources().getDisplayMetrics();
        mScreenWidth = dm.widthPixels;
        mScreenHeight = dm.heightPixels;

    }
}
