package net.m56.ckkj.mobile.tourism.project.login.presenter;

import android.os.Handler;
import android.os.Looper;

import net.m56.ckkj.mobile.tourism.project.login.moudel.LoginTaskDetailMoudel;

/**
 * @version V1.0 <描述当前版本功能>.
 * @filename: net.m56.ckkj.mobile.tourism.project.login.presenter.LoginTaskDetailPresenter.java
 * @Author 兔兔  on 2017/09/25.
 * @Org 山西创客空间科技有限公司.
 * @Description: ${TODO} .
 * @Motto 大梦谁先觉, 贫僧我自知..
 */
public class  LoginTaskDetailPresenter implements  LoginTaskDetailContract.Presenter {

    private Handler handler;      //获取 main  handler
    private LoginTaskDetailContract.View view;    //获取v层引用
    private LoginTaskDetailContract.Moudel moudel; //获取m层方法

    public LoginTaskDetailPresenter(LoginTaskDetailContract.View view) {
        this.view = view;
        init();                //加载m层方法
        handler = new Handler(Looper.getMainLooper()); //获取 main handler
    }

    private void init() {
        moudel = new LoginTaskDetailMoudel();
    }



}
