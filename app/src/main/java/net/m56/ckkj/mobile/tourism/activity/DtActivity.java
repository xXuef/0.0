package net.m56.ckkj.mobile.tourism.activity;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.gyf.barlibrary.ImmersionBar;

import net.m56.ckkj.mobile.tourism.base.activity.BaseActivity;
import net.m56.ckkj.tourism.tourism.R;

import butterknife.ButterKnife;

public class DtActivity extends BaseActivity {

    private Button button_pqg, button_xzs, button_gs, button_rj, button_ll, button_cz;

    @Override
    protected void onResume() {
        /**
         * 设置为横屏
         */
        if (getRequestedOrientation() != ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE) {
            setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        }
        super.onResume();
    }

    @Override
    public void initVariable() {
        setContentView(R.layout.activity_dt);
        //设置沉浸式状态栏
        ImmersionBar.with(this).statusBarColor(R.color.stabar_color).fitsSystemWindows(true).init();
        ButterKnife.bind(this);
        getSupportActionBar().hide();
        button_pqg = (Button) findViewById(R.id.button_pqg);
        button_xzs = (Button) findViewById(R.id.button_xzs);
        button_gs = (Button) findViewById(R.id.button_gs);
        button_rj = (Button) findViewById(R.id.button_rj);
        button_ll = (Button) findViewById(R.id.button_ll);
        button_cz = (Button) findViewById(R.id.button_cz);

        button_pqg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(DtActivity.this, CopyWebViewActivity.class);
                intent.putExtra("url", "file:///android_asset/glVb2.html");
                intent.putExtra("name", "庞泉沟");
                startActivity(intent);
            }
        });
        button_xzs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(DtActivity.this, CopyWebViewActivity.class);
                intent.putExtra("url", "file:///android_asset/glVb3.html");
                intent.putExtra("name", "玄中寺");
                startActivity(intent);
            }
        });
        button_gs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(DtActivity.this, CopyWebViewActivity.class);
                intent.putExtra("url", "file:///android_asset/bannerVb1.html");
                intent.putExtra("name", "卦山天宁寺出游攻略");
                startActivity(intent);
            }
        });
        button_rj.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(DtActivity.this, CopyWebViewActivity.class);
                intent.putExtra("url", "file:///android_asset/glVb4.html");
                intent.putExtra("name", "如金生态园");
                startActivity(intent);
            }
        });
        button_ll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(DtActivity.this, CopyWebViewActivity.class);
                intent.putExtra("url", "file:///android_asset/glVb1.html");
                intent.putExtra("name", "吕梁英雄广场");
                startActivity(intent);
            }
        });
        button_cz.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(DtActivity.this, CopyWebViewActivity.class);
                intent.putExtra("url", "file:///android_asset/glVb0.html");
                intent.putExtra("name", "翠丰庄园");
                startActivity(intent);
            }
        });
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
