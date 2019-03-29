package net.m56.ckkj.mobile.tourism.base.activity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.text.TextUtils;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import net.m56.ckkj.mobile.tourism.Api.Url;
import net.m56.ckkj.mobile.tourism.project.login.LoginActivity;
import net.m56.ckkj.mobile.tourism.util.APPUtil;
import net.m56.ckkj.mobile.tourism.util.StorageUtil;
import net.m56.ckkj.tourism.tourism.R;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;

/**
 * @author yanmin K_pop9799@163.com
 * @ClassName: ${type_name}
 * @Description: 设置
 * @date 2017/10/12 15:17
 */
public class SetActivity extends BaseActivity implements View.OnClickListener {
    private RelativeLayout modifypersondataRl, mClearRl;
    private TextView mModifypersonalTv, mCleanTv, mModifypassTv;
    public final OkHttpClient client = new OkHttpClient();
    private Button loginoutBtn;
    SharedPreferences sharedPreferences;
    private Intent intent;

    private static final int MSG_CLEAR_CACHE_DATA_SUCC = 3201;
    private static final int MSG_CLEAR_CACHE_DATA_FAIL = 3202;

    @Override
    public void initVariable() {
        tool_title.setText("设置");
        setContentView(R.layout.activity_setting_layout);
//        //设置沉浸式状态栏
//        ImmersionBar.with(this).statusBarColor(R.color.stabar_color).fitsSystemWindows(true).init();
    }


    @Override
    public void toLoad(Bundle savedInstanceState) {

        modifypersondataRl = (RelativeLayout) findViewById(R.id.modifypersondataRl);
        mClearRl = (RelativeLayout) findViewById(R.id.clearRl);
        mModifypersonalTv = (TextView) findViewById(R.id.modifypersonalTv);
        mModifypassTv = (TextView) findViewById(R.id.modifypassTv);
        mCleanTv = (TextView) findViewById(R.id.clearTv);
        loginoutBtn = (Button) findViewById(R.id.loginoutBtn);


        mModifypersonalTv.setOnClickListener(this);
        mModifypassTv.setOnClickListener(this);
        mClearRl.setOnClickListener(this);
        loginoutBtn.setOnClickListener(this);
    }

    private final Handler mHandler = new Handler() {
        public void handleMessage(android.os.Message msg) {

            switch (msg.what) {
                case MSG_CLEAR_CACHE_DATA_SUCC:
                    showToast("清除数据完成");
                    break;
                case MSG_CLEAR_CACHE_DATA_FAIL:
                    showToast("清除数据失败，稍后重试");
                    break;
            }
            refreshCacheSize();
        }
    };

    /**
     * 清除缓存
     */
    private void showCleanCacheDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("提示");
        builder.setMessage("是否清除所有缓存数据？");
        builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                new Thread(new Runnable() {
                    public void run() {
                        Glide.get(mContext).clearDiskCache();
                    }
                }).start();
                Glide.get(mContext).clearMemory();
                mHandler.sendEmptyMessageDelayed(MSG_CLEAR_CACHE_DATA_SUCC,
                        1000);

            }
        });
        builder.setNegativeButton("取消", null);
        builder.show();
    }

    private void refreshCacheSize() {
        mCleanTv.setText(getString(R.string.total, getCacheTotalSize()));
    }

    private String getCacheTotalSize() {
        return APPUtil.convertFileSize(getDiskCacheSize());
    }

    public long getDiskCacheSize() {
        if (getExternalCacheDir() != null) {
            if (getExternalCacheDir().length() > 0) {
                String diskpath = getExternalCacheDir().getAbsolutePath() + "/glide_cache";
                if (!TextUtils.isEmpty(diskpath)) {
                    long cacheSize = StorageUtil.getDirSize(new File(diskpath));
                    cacheSize = cacheSize - 240 * 1024;
                    if (cacheSize < 0)
                        cacheSize = 0;
                    return cacheSize;
                } else {
                    return 0;
                }
            } else {
                return 0;
            }
        }
        return 0;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                break;
        }
        return super.onOptionsItemSelected(item);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.modifypersonalTv://修改个人资料
                intent = new Intent(getApplicationContext(), PersonalDataActivity.class);
                intent.putExtra("titlename", "修改个人资料");
                startActivity(intent);
                break;
            case R.id.modifypassTv://修改密码
                intent = new Intent(getApplicationContext(), ChangePassWordActivity.class);
                startActivity(intent);
                break;
            case R.id.loginoutBtn:
                sharedPreferences = getSharedPreferences("logininfo", MODE_PRIVATE);
                String userid = sharedPreferences.getString("userid", "");
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putBoolean("islogin",false);
                editor.commit();
                //点击下一步是的
                MultipartBody.Builder builder = new MultipartBody.Builder().setType(MultipartBody.FORM);
                //用户id
                builder.addFormDataPart("userid", userid);

                MultipartBody requestbody = builder.build();
                //修改url
                final Request request = new Request.Builder()
                        .url(Url.OutLogin).post(requestbody).build();
                Call call = client.newCall(request);
                call.enqueue(new Callback() {
                    @Override
                    public void onFailure(Call call, IOException e) {

                    }

                    @Override
                    public void onResponse(Call call, Response response) throws IOException {
                        if (response!=null) {
                            ResponseBody body = response.body();
                            String ss = body.string();
                            try {
                                JSONObject jsonObject = new JSONObject(ss);
                                String message = jsonObject.getString("message");
                                if (message.equals("退出成功")){
                                    runOnUiThread(new Runnable() {
                                        @Override
                                        public void run() {
                                            sharedPreferences = getSharedPreferences("logininfo", MODE_PRIVATE);
                                            SharedPreferences.Editor edit = sharedPreferences.edit();
                                            edit.putBoolean("islogin", false);
                                            edit.putString("userid","");
                                            boolean commit = edit.commit();
                                            System.out.println("edit" + commit);
                                        }
                                    });
                                }
                            } catch (JSONException e) {

                            }

                        }
                    }
                });

                intent = new Intent(getApplicationContext(), LoginActivity.class);
                startActivity(intent);
                finish();
                break;
            case R.id.clearTv://清除缓存
                showCleanCacheDialog();
                break;
        }
    }


}
