package net.m56.ckkj.mobile.tourism.activity;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.view.MenuItem;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.TextView;

import com.gyf.barlibrary.ImmersionBar;

import net.m56.ckkj.mobile.tourism.base.activity.BaseActivity;
import net.m56.ckkj.tourism.tourism.R;


/**
 * Created by dragonrong on 2017/6/19.
 */


public class WebViewActivity extends BaseActivity {

    private WebView webView;
    private String url;
    private String name;

    ProgressDialog dialog = null;


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected synchronized void onDestroy() {
        super.onDestroy();
        ImmersionBar.with(this).destroy();
    }

    @Override
    public void initVariable() {

        setContentView(R.layout.activity_web_view);
        ImmersionBar.with(this).statusBarColor(R.color.stabar_color).fitsSystemWindows(true).init();
        String name=getIntent().getStringExtra("name");

        TextView toolbarname = (TextView) findViewById(R.id.toolbar_title);
        toolbarname.setText(name);
        url = getIntent().getStringExtra("url");
        webView = (WebView) findViewById(R.id.webview);
        webView.setWebViewClient(new WebViewClient() {
            //设置在webView点击打开的新网页在当前界面显示,而不跳转到新的浏览器中
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                view.loadUrl(url);
                return true;
            }

            @Override
            public void onPageFinished(WebView view,String url)
            {
                dialog.dismiss();
            }
        });
        webView.getSettings().setJavaScriptEnabled(true);  //设置WebView属性,运行执行js脚本
        webView.loadUrl(url);          //调用loadUrl方法为WebView加入链接
        dialog = ProgressDialog.show(this,null,"页面加载中，请稍后..");
    }

    @Override
    public void toLoad(Bundle savedInstanceState) {

    }



}

