package net.m56.ckkj.mobile.tourism.activity;

import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.widget.TextView;

import com.gyf.barlibrary.ImmersionBar;

import net.m56.ckkj.mobile.tourism.base.activity.BaseActivity;
import net.m56.ckkj.mobile.tourism.fragment.ActivityInfoFragement;
import net.m56.ckkj.mobile.tourism.fragment.SystemInfoFragment;
import net.m56.ckkj.tourism.tourism.R;

import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;

public class NewsInfoActivity extends BaseActivity {

    TabLayout tablayout;
    ViewPager viewpager;
    //tablayout的标题
    private String[] mTitles = new String[]{"活动消息", "系统消息"};
    public ActivityInfoFragement activityInfoFragement;
    public SystemInfoFragment systemInfoFragment;
    private List<Fragment> fragmentList;


    @Override
    public void initVariable() {
        setContentView(R.layout.activity_news_info);
        ButterKnife.bind(this);
        //设置沉浸式状态栏
        tablayout = (TabLayout) findViewById(R.id.tablayout);
        viewpager =  (ViewPager) findViewById(R.id.viewpager);

        ImmersionBar.with(this).statusBarColor(R.color.stabar_color).fitsSystemWindows(true).init();
        TextView textView = (TextView) findViewById(R.id.toolbar_title);
        textView.setText("消息");
        activityInfoFragement = new ActivityInfoFragement();
        systemInfoFragment = new SystemInfoFragment();
        fragmentList = new ArrayList<>();
        fragmentList.add(activityInfoFragement);
        fragmentList.add(systemInfoFragment);

        viewpager.setAdapter(new FragmentPagerAdapter(getSupportFragmentManager()) {
            @Override
            public Fragment getItem(int position) {
                return fragmentList.get(position);
            }

            @Override
            public int getCount() {
                return mTitles.length;
            }

            @Override
            public CharSequence getPageTitle(int position) {
                return mTitles[position];
            }
        });
        //tablayout与viewpager关联
        tablayout.setupWithViewPager(viewpager);
        tablayout.setSelectedTabIndicatorColor(Color.GRAY);
    }

    @Override
    public void toLoad(Bundle savedInstanceState) {

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ImmersionBar.with(this).destroy();
    }
}
