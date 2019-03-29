package net.m56.ckkj.mobile.tourism.activity;

import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;

import com.gyf.barlibrary.ImmersionBar;
import com.heima.tabview.library.TabView;
import com.heima.tabview.library.TabViewChild;

import net.m56.ckkj.mobile.tourism.base.activity.BaseActivity;
import net.m56.ckkj.mobile.tourism.fragment.GlFragment;
import net.m56.ckkj.mobile.tourism.fragment.HomeFragment;
import net.m56.ckkj.mobile.tourism.fragment.MeFragment;
import net.m56.ckkj.mobile.tourism.fragment.ZbFragment;
import net.m56.ckkj.tourism.tourism.R;

import java.util.ArrayList;
import java.util.List;

public class FristActivity extends BaseActivity {

    public FragmentManager fragmentManager;
    public HomeFragment fragment;

    @Override
    public void initVariable() {
        setContentView(R.layout.activity_splash);
//        ButterKnife.bind(this);
    }

    @Override
    public void toLoad(Bundle savedInstanceState) {
        getSupportActionBar().hide();
        ImmersionBar.with(this).statusBarColor(R.color.stabar_color).init();

//        if (Build.VERSION.SDK_INT > 23) {         /**Android 7.0以上的方式**/
//            Uri contentUri = getUriForFile(this, getString(R.string.install_apk_path), file);
//            grantUriPermission(getPackageName(), contentUri, Intent.FLAG_GRANT_WRITE_URI_PERMISSION);
//            intent.putExtra(MediaStore.EXTRA_OUTPUT, contentUri);
//        }

        List<TabViewChild> tabViewChildList = new ArrayList<>();
        TabViewChild tabViewChild01 = new TabViewChild(R.drawable.icon_shouye, R.drawable.icon_shouyehui, "首页", new HomeFragment());
        TabViewChild tabViewChild02 = new TabViewChild(R.drawable.icon_gonglue, R.drawable.icon_gongluehui, "攻略", new GlFragment());
        TabViewChild tabViewChild03 = new TabViewChild(R.drawable.icon_zhoubian, R.drawable.icon_zhoubianhui, "周边", new ZbFragment());
        TabViewChild tabViewChild04 = new TabViewChild(R.drawable.icon_wode, R.drawable.icon_wodehui, "我的", new MeFragment());

        tabViewChildList.add(tabViewChild01);
        tabViewChildList.add(tabViewChild02);
        tabViewChildList.add(tabViewChild03);
        tabViewChildList.add(tabViewChild04);


//        if (savedInstanceState == null) {
//            // 添加显示第一个fragment
//            fragment = new HomeFragment();
//            getSupportFragmentManager().beginTransaction().add(R.id.framlayout, fragment,
//                    PictureConfig.FC_TAG).show(fragment)
//                    .commit();
//        } else {
//            fragment = (HomeFragment) getSupportFragmentManager()
//                    .findFragmentByTag(PictureConfig.FC_TAG);
//        }

        //默认首页
        fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction()
                .replace(R.id.framlayout, new HomeFragment(), null).commit();

        TabView tabView = (TabView) findViewById(R.id.tabView);
        tabView.setTabViewChild(tabViewChildList, fragmentManager);


    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ImmersionBar.with(this).destroy();
    }


    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
    }
}
