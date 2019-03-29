package net.m56.ckkj.mobile.tourism.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import net.m56.ckkj.mobile.tourism.base.activity.BaseActivity;
import net.m56.ckkj.tourism.tourism.R;

/**
 * @author yanmin K_pop9799@163.com
 * @ClassName: ${type_name}
 * @Description: ${TODO}(这里用一句话描述这个类的作用)
 * @date 2017/8/12 10:38
 */
public class GuideListActivity extends BaseActivity {
    private String name;
    private LinearLayout mWaterLl, mMoutainLl, mTempleLl;



    @Override
    public void initVariable() {
        setContentView(R.layout.activity_guidelist_layout);
        mWaterLl = (LinearLayout) findViewById(R.id.waterLl);
        mMoutainLl = (LinearLayout) findViewById(R.id.moutainLl);
        mTempleLl = (LinearLayout) findViewById(R.id.templeLl);
        name = getIntent().getStringExtra("name");
        TextView toobarname =(TextView)findViewById(R.id.toolbar_title);
        toobarname.setText(name);
        if (this.name.equals("卦山风景区")) {
            mMoutainLl.setVisibility(View.VISIBLE);
            mWaterLl.setVisibility(View.GONE);
            mTempleLl.setVisibility(View.GONE);
        } else if (this.name.equals("庞泉沟风景区")) {
            mMoutainLl.setVisibility(View.GONE);
            mWaterLl.setVisibility(View.VISIBLE);
            mTempleLl.setVisibility(View.GONE);
        } else if (this.name.equals("玄中寺风景区")) {
            mMoutainLl.setVisibility(View.GONE);
            mWaterLl.setVisibility(View.GONE);
            mTempleLl.setVisibility(View.VISIBLE);
        }
    }


    @Override
    public void toLoad(Bundle savedInstanceState) {

    }


}
