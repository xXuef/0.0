package net.m56.ckkj.mobile.tourism.activity;

import android.os.Bundle;
import android.webkit.WebView;
import android.widget.TextView;

import com.gyf.barlibrary.ImmersionBar;

import net.m56.ckkj.mobile.tourism.base.activity.BaseActivity;
import net.m56.ckkj.tourism.tourism.R;

public class ViewPagerWebActivity extends BaseActivity {


    @Override
    protected synchronized void onDestroy() {
        super.onDestroy();
        ImmersionBar.with(this).destroy();
    }

    @Override
    public void initVariable() {

        setContentView(R.layout.activity_view_pager_web);
        ImmersionBar.with(this).statusBarColor(R.color.stabar_color).fitsSystemWindows(true).init();

        TextView toolbarname = (TextView) findViewById(R.id.toolbar_title);

        String viewpager0 = getIntent().getStringExtra("Viewpager0");
        String titlename = getIntent().getStringExtra("titlename");
        toolbarname.setText(titlename);
        WebView viewById = (WebView) findViewById(R.id.webview);

        viewById.loadUrl(viewpager0);

    }

    @Override
    public void toLoad(Bundle savedInstanceState) {

    }
}
