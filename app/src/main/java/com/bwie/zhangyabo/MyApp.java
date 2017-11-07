package com.bwie.zhangyabo;

import android.app.Application;

import com.facebook.drawee.backends.pipeline.Fresco;

/**
 * User: 张亚博
 * Date: 2017-11-06 09:59
 * Description：
 */
public class MyApp extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        Fresco.initialize(this);
    }
}
