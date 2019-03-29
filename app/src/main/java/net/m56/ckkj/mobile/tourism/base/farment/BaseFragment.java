package net.m56.ckkj.mobile.tourism.base.farment;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;

import android.support.v7.widget.Toolbar;
import android.util.DisplayMetrics;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import net.m56.ckkj.mobile.tourism.project.login.LoginActivity;
import net.m56.ckkj.mobile.tourism.util.ActivityStack;
import net.m56.ckkj.mobile.tourism.util.ConstantUtil;
import net.m56.ckkj.mobile.tourism.util.DialogMaker;
import net.m56.ckkj.mobile.tourism.util.RegexUtils;
import net.m56.ckkj.mobile.tourism.util.SharePreUtil;
import net.m56.ckkj.tourism.tourism.R;


/**
 * basefragment
 * <p>
 * Created by 幻月 on 2015/9/17.
 */
public abstract class BaseFragment extends Fragment {
    private Context context;
    protected Dialog dialog;


    /**
     * SharedPreferences
     */
    protected SharePreUtil sp;

    /**
     * +U userId
     */
    protected String userId;

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        context = activity;
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(getLayoutId(), container, false);
        context = getActivity();
        sp = new SharePreUtil(context);
//        userId = sp.getValue(ConstantUtil.USER.USERID, "");
        initParams(view,savedInstanceState);

        return view;
    }
    /**
     * 初始化布局
     *
     * @author blue
     */
    protected abstract int getLayoutId();

    /**
     * 参数设置
     *
     * @author blue
     */
    protected abstract void initParams(View view, Bundle savedInstanceState);

    /**
     * 弹出对话框
     *
     * @author blue
     */
    public Dialog showAlertDialog(String title, String msg, String[] btns, final DialogMaker.DialogCallBack callBack, boolean isCanCancelabel, final boolean isDismissAfterClickBtn, Object tag) {
        if (null == dialog || !dialog.isShowing()) {
            dialog = DialogMaker.showCommonAlertDialog(context, title, msg, btns, callBack, isCanCancelabel, isDismissAfterClickBtn, tag);
        }
        return dialog;
    }

    /**
     * 等待对话框
     *
     * @author blue
     */
    public Dialog showWaitDialog(String msg, boolean isCanCancelabel, Object tag) {
        if (null == dialog || !dialog.isShowing()) {
            dialog = DialogMaker.showCommenWaitDialog(context, msg, null, isCanCancelabel, tag);
        }
        return dialog;
    }

    /**
     * 关闭对话框
     *
     * @author blue
     */
    public void dismissDialog() {
        if (null != dialog && dialog.isShowing()) {
            dialog.dismiss();
        }
    }
    /**
     * 是否登录
     *
     * @return
     */
//    public boolean checkLogin() {
//        userId = sp.getValue(ConstantUtil.USER.USERID, "");
//        if (!RegexUtils.isNotBlankAndEmpty(userId)) {
//            Intent intent = new Intent(context, LoginActivity.class);
//            startActivity(intent);
//            return false;
//        }
//        return true;
//    }
}
