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
import net.m56.ckkj.mobile.tourism.fragment.ForDistance;
import net.m56.ckkj.mobile.tourism.fragment.ForHots;
import net.m56.ckkj.tourism.tourism.R;

import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;

public class ShopActivity extends BaseActivity {

    TabLayout tablayout;
    ViewPager viewpager;

    //tablayout的标题
    String[] mTitles = new String[]{"按距离", "按热度"};
    private List<Fragment> fragmentList;
    public ForHots forHots;
    public ForDistance forDistance;

    @Override
    protected synchronized void onDestroy() {
        super.onDestroy();
        ImmersionBar.with(this).destroy();
    }

    @Override
    public void initVariable() {
        setContentView(R.layout.activity_shop);
        ButterKnife.bind(this);
//        TabLayout tablayout;
//        ViewPager viewpager;
        //设置沉浸式状态栏
        ImmersionBar.with(this).statusBarColor(R.color.stabar_color).fitsSystemWindows(true).init();
        tablayout = (TabLayout)findViewById(R.id.tablayout);
        viewpager = (ViewPager)findViewById(R.id.viewpager);

        TextView textView = (TextView) findViewById(R.id.toolbar_title);
        textView.setText("购物");

    }

    @Override
    public void toLoad(Bundle savedInstanceState) {
        fragmentList = new ArrayList<>();
        forHots = new ForHots();
        forDistance = new ForDistance();

        fragmentList.add(forDistance);
        fragmentList.add(forHots);

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


}
