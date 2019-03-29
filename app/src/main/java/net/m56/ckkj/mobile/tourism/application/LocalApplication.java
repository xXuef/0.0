package net.m56.ckkj.mobile.tourism.application;

import android.util.DisplayMetrics;

import net.m56.ckkj.mobile.tourism.exception.BaseExceptionHandler;
import net.m56.ckkj.mobile.tourism.exception.LocalFileHandler;
import net.m56.ckkj.mobile.tourism.util.JFileKit;

import java.io.File;

/**
 * @version V1.0 <描述当前版本功能>.
 * @filename: net.m56.ckkj.mobile.tourism.application.LocalApplication.java
 * @Author 兔兔  on 2017/09/25.
 * @Org 山西创客空间科技有限公司.
 * @Description: ${TODO} .
 * @Motto 大梦谁先觉, 贫僧我自知..
 */
public class LocalApplication extends BaseApplication {
    private static LocalApplication instance;
    // 当前屏幕的高宽
    public int screenW = 0;
    public int screenH = 0;

    // 单例模式中获取唯一的MyApplication实例
    public static LocalApplication getInstance() {
        if (instance == null) {
            instance = new LocalApplication();
        }
        return instance;
    }

    @Override
    public void onCreate() {
        super.onCreate();

        // 创建APP崩溃日志目录
        File appFolder = new File(JFileKit.getDiskCacheDir(this) + "/log");
        if (!appFolder.exists()) {
            appFolder.mkdirs();
        }

        instance = this;
        // 得到屏幕的宽度和高度
        DisplayMetrics dm = getResources().getDisplayMetrics();
        screenW = dm.widthPixels;
        screenH = dm.heightPixels;
    }

    @Override
    public BaseExceptionHandler getDefaultUncaughtExceptionHandler() {
        return new LocalFileHandler(applicationContext);
    }

}
