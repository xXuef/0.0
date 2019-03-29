package net.m56.ckkj.mobile.tourism.application;

import android.app.Application;
import android.content.Context;

import net.m56.ckkj.mobile.tourism.exception.BaseExceptionHandler;
import net.m56.ckkj.mobile.tourism.exception.LocalFileHandler;

/**
 * @version V1.0 <描述当前版本功能>.
 * @filename: net.m56.ckkj.mobile.tourism.application.BaseApplication.java
 * @Author 兔兔  on 2017/09/25.
 * @Org 山西创客空间科技有限公司.
 * @Description: ${TODO} .
 * @Motto 大梦谁先觉, 贫僧我自知..
 */
public abstract class BaseApplication extends Application {
    public static final String TAG = "Application";
    public static Context applicationContext;


    @Override
    public void onCreate() {
        super.onCreate();
        applicationContext = getApplicationContext();
        if (getDefaultUncaughtExceptionHandler() == null) {
            Thread.setDefaultUncaughtExceptionHandler(new LocalFileHandler(applicationContext));
        } else {
            Thread.setDefaultUncaughtExceptionHandler(getDefaultUncaughtExceptionHandler());
        }
    }

    public abstract BaseExceptionHandler getDefaultUncaughtExceptionHandler();
}
