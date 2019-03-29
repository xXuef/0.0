package net.m56.ckkj.mobile.tourism.base.activity;

import android.app.Activity;
import android.app.Dialog;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.inputmethod.InputMethodManager;
import android.widget.FrameLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.gyf.barlibrary.ImmersionBar;

import net.m56.ckkj.mobile.tourism.project.login.LoginActivity;
import net.m56.ckkj.mobile.tourism.util.ActivityStack;
import net.m56.ckkj.mobile.tourism.util.ConstantUtil;
import net.m56.ckkj.mobile.tourism.util.DialogMaker;
import net.m56.ckkj.mobile.tourism.util.RegexUtils;
import net.m56.ckkj.mobile.tourism.util.SharePreUtil;
import net.m56.ckkj.tourism.tourism.R;


/**
 * Created by 幻月  on 2017/09/11.
 */

public abstract class BaseActivity extends AppCompatActivity implements DialogMaker.DialogCallBack {
    private static final String TAG = "BaseActivity";
    protected RelativeLayout mContentLayout;
    protected Toolbar toolbar;
    // 当前屏幕的高宽
    public static int screenW = 0;
    public static int screenH = 0;
    protected Dialog dialog;
    protected TextView tool_title;


    /**
     * SharedPreferences
     */
    protected SharePreUtil sp;

    /**
     * +U userId
     */
    protected String userId;
    /**
     * context
     */
    protected Activity mContext = this;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setupViews();
        initVariable();
        toLoad(savedInstanceState);
    }

    /**
     * 配置 base  控件
     */
    private void setupViews() {
        super.setContentView(R.layout.activity_main);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        tool_title = (TextView) toolbar.findViewById(R.id.toolbar_title);
        toolbar.setTitle("");
        tool_title.setVisibility(View.VISIBLE);
        sp = new SharePreUtil(this);

        mContentLayout = (RelativeLayout) findViewById(R.id.contentRl);
        setSupportActionBar(toolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setHomeButtonEnabled(true);
        }
        ActivityStack.getInstance().addActivity(this);
        DisplayMetrics dm = getResources().getDisplayMetrics();
        screenW = dm.widthPixels;//540
        screenH = dm.heightPixels;//888
        //设置沉浸式状态栏
        ImmersionBar.with(this).statusBarColor(R.color.stabar_color).fitsSystemWindows(true).init();
//        userId = sp.getValue(ConstantUtil.USER.USERID, "");
    }

    /**
     * 覆盖 设置title 方法
     *
     * @param title
     */
    @Override
    public void setTitle(CharSequence title) {
        toolbar.setTitle(title);

    }

    @Override
    public void setTitle(int titleId) {
        toolbar.setTitle(titleId);
    }

    /**
     * 覆盖 设置title 字体颜色
     *
     * @param textColor
     */
    @Override
    public void setTitleColor(int textColor) {
        toolbar.setTitleTextColor(textColor);
    }

    /**
     * 弹出toast
     *
     * @param msg
     */
    protected void showToast(CharSequence msg) {

        Toast.makeText(getApplicationContext(), msg, Toast.LENGTH_LONG).show();

    }

    /**
     * 弹出  snackbar
     *
     * @param msg
     * @param view
     */
    protected void showSnackbar(CharSequence msg, View view) {

        Snackbar.make(view, msg, Snackbar.LENGTH_LONG)
//                .setAction("Action", null)
                .show();

    }

    /**
     * 设置log 输出
     *
     * @param msg
     */
    protected void appendLog(String msg) {
        Log.d(TAG, "appendLog: " + msg);
    }

    //影藏软键盘
    public static void hideKeyboard(Context context, View view) {
        InputMethodManager imm = (InputMethodManager) context
                .getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
    }

    //显示软键盘
    public static void showKeyboard(Context context, View view) {
        InputMethodManager imm = (InputMethodManager) context
                .getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.showSoftInput(view, 0);
    }

//    /**
//     * 设置菜单
//     * @param menu
//     * @return
//     */
//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        // Inflate the menu; this adds items to the action bar if it is present.
//        getMenuInflater().inflate(R.menu.menu_main, menu);
//        return true;
//    }
//

    /**
     * 菜单监听
     *
     * @param item
     * @return
     */
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == android.R.id.home) {
//            setToolBarBack();
            finish();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    /**
     * toolbar返回监听
     */
    protected void setToolBarBack() {
        onDestroy();
    }

    /**
     * 覆盖 contentview 方法
     *
     * @param layoutResID
     */
    @Override
    public void setContentView(@LayoutRes int layoutResID) {
//        super.setContentView(layoutResID);
        mContentLayout.removeAllViews();
        View.inflate(this, layoutResID, mContentLayout);
        onContentChanged();
    }

    @Override
    public void setContentView(View view) {
//        super.setContentView(view);
        mContentLayout.removeAllViews();
        mContentLayout.addView(view);
        onContentChanged();
    }

    @Override
    public void setContentView(View view, ViewGroup.LayoutParams params) {
//        super.setContentView(view, params);
        mContentLayout.removeAllViews();
        mContentLayout.addView(view, params);
        onContentChanged();
    }


    /**
     * 给子类初始化的方法
     */
    public abstract void initVariable();

    /**
     * 暴露给子类 加载控件的方法
     *
     * @param savedInstanceState
     */
    public abstract void toLoad(Bundle savedInstanceState);

    /**
     * 等待对话框
     *
     * @param msg
     * @param isCanCancelabel
     * @param tag
     * @return
     */
    public Dialog showWaitDialog(String msg, boolean isCanCancelabel, Object tag) {
        if (null == dialog || !dialog.isShowing()) {
            dialog = DialogMaker.showCommenWaitDialog(this, msg, this, isCanCancelabel, tag);
        }
        return dialog;
    }

    /**
     * 弹出对话框
     *
     * @author blue
     */
    public Dialog showAlertDialog(String title, String msg, String[] btns, boolean isCanCancelabel, final boolean isDismissAfterClickBtn, Object tag) {
        if (null == dialog || !dialog.isShowing()) {
            dialog = DialogMaker.showCommonAlertDialog(this, title, msg, btns, this, isCanCancelabel, isDismissAfterClickBtn, tag);
        }
        return dialog;
    }

    /**
     * 弹dialog 监听
     *
     * @param dialog
     * @param position 点击Button的索引.
     * @param tag
     */
    @Override
    public void onButtonClicked(Dialog dialog, int position, Object tag) {

    }

    /**
     * 弹dialog 取消监听
     *
     * @param dialog
     * @param tag
     */
    @Override
    public void onCancelDialog(Dialog dialog, Object tag) {

    }

    /**
     * 是否登录
     *
     * @return
     */
//    public boolean checkLogin() {
//        userId = sp.getValue(ConstantUtil.USER.USERID, "");
//        if (!RegexUtils.isNotBlankAndEmpty(userId)) {
//            Intent intent = new Intent(mContext, LoginActivity.class);
//            startActivity(intent);
//            return false;
//        }
//        return true;
//    }

    /**
     * 跳转act方法
     *
     * @param cls
     */
    protected void intoActivity(Class<?> cls) {
        Intent into = new Intent();
        into.setClass(this, cls);
        startActivity(into);
    }

    @Override
    protected void onResume() {
        super.onResume();
        userId = sp.getValue(ConstantUtil.USER.USERID, "");
    }

    /**
     * 覆盖Destroy 方法 栈取消任务
     */
    @Override
    protected synchronized void onDestroy() {
//        dismissDialog();
        ActivityStack.getInstance().removeActivity(this);
        super.onDestroy();
    }


}
