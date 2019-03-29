package net.m56.ckkj.mobile.tourism.project.login;


import android.Manifest;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.Looper;
import android.support.v4.app.ActivityCompat;
import android.telephony.TelephonyManager;
import android.text.method.PasswordTransformationMethod;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;

import net.m56.ckkj.mobile.tourism.Api.Url;
import net.m56.ckkj.mobile.tourism.activity.FristActivity;
import net.m56.ckkj.mobile.tourism.base.activity.BaseActivity;
import net.m56.ckkj.mobile.tourism.base.activity.ForgetPassActivity;
import net.m56.ckkj.mobile.tourism.base.activity.RegisterActivity;
import net.m56.ckkj.mobile.tourism.bean.MessageSend;
import net.m56.ckkj.mobile.tourism.project.login.presenter.LoginTaskDetailContract;
import net.m56.ckkj.mobile.tourism.project.login.presenter.LoginTaskDetailPresenter;
import net.m56.ckkj.tourism.tourism.R;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.HashMap;

import cn.sharesdk.framework.Platform;
import cn.sharesdk.framework.PlatformActionListener;
import cn.sharesdk.framework.ShareSDK;
import cn.sharesdk.tencent.qq.QQ;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * @version V1.0 <描述当前版本功能>.
 * @filename: net.m56.ckkj.mobile.tourism.project.login.LoginActivity.java
 * @Author 兔兔  on 2017/09/25.
 * @Org 山西创客空间科技有限公司.
 * @Description: ${TODO} .
 * @Motto 大梦谁先觉, 贫僧我自知..
 */
public class LoginActivity extends BaseActivity implements LoginTaskDetailContract.View, View.OnClickListener {
    public final OkHttpClient client = new OkHttpClient();
    public LoginTaskDetailContract.Presenter mPresenter;
    public TextView mRegisterTv, mForgetPassTv;//注册、忘记密码
    public Button mLoginBtn;//登录
    public ImageView mLoginBackIv;//返回
    public ImageView passLookIv;//可见密码的按钮
    public EditText inputPhoneEt;//账号
    public EditText inputPassEt;//密码
    public Intent intent;
    public TelephonyManager tm;
    public boolean ishide = true;
    public boolean isLogin = false;
    public SharedPreferences sharedPreferences;
    public ImageView qqIv;
    public ImageView wxIv;
    public ImageView wbIv;

    @Override
    public void initVariable() {
        mPresenter = new LoginTaskDetailPresenter(this);
        setContentView(R.layout.activity_login_layout);
        toolbar.setVisibility(View.GONE);

        tm = (TelephonyManager) this.getSystemService(TELEPHONY_SERVICE);

        sharedPreferences = getSharedPreferences("logininfo", MODE_PRIVATE);
        isLogin = sharedPreferences.getBoolean("islogin", false);


    }


    @Override
    public void toLoad(Bundle savedInstanceState) {
        mRegisterTv = (TextView) findViewById(R.id.registerTv);
        mForgetPassTv = (TextView) findViewById(R.id.forgetPassTv);
        mLoginBtn = (Button) findViewById(R.id.loginBtn);
        mLoginBtn.setText("登录");
        mLoginBackIv = (ImageView) findViewById(R.id.loginBackIv);
        passLookIv = (ImageView) findViewById(R.id.passLookIv);
        inputPhoneEt = (EditText) findViewById(R.id.inputPhoneEt);
        inputPassEt = (EditText) findViewById(R.id.inputPassEt);

        qqIv = (ImageView) findViewById(R.id.loginQQIv);
        wxIv = (ImageView) findViewById(R.id.loginWechatIv);
        wbIv = (ImageView) findViewById(R.id.loginWeiBoIv);

        mRegisterTv.setOnClickListener(this);
        mForgetPassTv.setOnClickListener(this);
        mLoginBtn.setOnClickListener(this);
        mLoginBackIv.setOnClickListener(this);
        passLookIv.setOnClickListener(this);
        inputPhoneEt.setOnClickListener(this);

        qqIv.setOnClickListener(this);
        wxIv.setOnClickListener(this);
        wbIv.setOnClickListener(this);

    }
    //    双卡imei获取方法
//    TelephonyManager telephonyManager = (TelephonyManager)getSystemService(Context.TELEPHONY_SERVICE);
//    for (int slot = 0; slot < telephonyManager.getPhoneCount(); slot++) {
//        String imei = telephonyManager.getDeviceId(slot);
//    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.loginBtn://登录

                if (ActivityCompat.checkSelfPermission(this, Manifest.permission.READ_PHONE_STATE) != PackageManager.PERMISSION_GRANTED) {
                    // TODO: Consider calling
                    //    ActivityCompat#requestPermissions
                    // here to request the missing permissions, and then overriding
                    //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
                    //                                          int[] grantResults)
                    // to handle the case where the user grants the permission. See the documentation
                    // for ActivityCompat#requestPermissions for more details.
                    return;
                }
                final String imei = tm.getDeviceId();
                final String phone = inputPhoneEt.getText().toString();
                final String password = inputPassEt.getText().toString();

                //点击下一步是的
                MultipartBody.Builder builder = new MultipartBody.Builder().setType(MultipartBody.FORM);
                //电话号码
                builder.addFormDataPart("phone", phone);
                //密码
                builder.addFormDataPart("password", password);
                //imei
                builder.addFormDataPart("imei", imei);

                MultipartBody requestbody = builder.build();

                //修改url
                Request request = new Request.Builder()
                        .url(Url.Login).post(requestbody).build();
                Call call = client.newCall(request);
                call.enqueue(new Callback() {
                    @Override
                    public void onFailure(Call call, IOException e) {
                        Log.e("登录请求失败", e.getMessage().toString());
                    }

                    @Override
                    public void onResponse(Call call, Response response) throws IOException {

                        String s = response.body().string().toString();

                        Log.e("登录请求情况", s);
                        try {
                            JSONObject jsonObject = new JSONObject(s);
                            int code = jsonObject.getInt("code");
                            if (code == -1) {
                                System.out.println("您的账号未注册,或者用户名密码为空");
                                String message = jsonObject.getString("message");
                                if (message.equals("用户名或密码不能为空")) {
                                    new Thread(new Runnable() {
                                        @Override
                                        public void run() {
                                            Looper.prepare();
                                            Toast.makeText(LoginActivity.this,
                                                    "用户名或密码不能为空", Toast.LENGTH_SHORT).show();
                                            Looper.loop();
                                        }
                                    }).start();
                                    return;
                                } else if (message.equals("您输入的手机号还未注册，请先注册")) {
                                    new Thread(new Runnable() {
                                        @Override
                                        public void run() {
                                            Looper.prepare();
                                            Toast.makeText(LoginActivity.this,
                                                    "您的账号未注册", Toast.LENGTH_SHORT).show();
                                            Looper.loop();
                                        }
                                    }).start();
                                    return;
                                } else if (message.equals("用户已登陆")) {
                                    intent = new Intent(LoginActivity.this, FristActivity.class);
                                    startActivity(intent);
                                    sharedPreferences = getSharedPreferences("logininfo", MODE_PRIVATE);
                                    SharedPreferences.Editor edit = sharedPreferences.edit();
                                    edit.putBoolean("islogin", true);
                                    edit.putString("phone", phone);
                                    edit.putString("pass", password);
                                    edit.putString("imei", imei);
                                    boolean commit = edit.commit();
                                    System.out.println("edit" + commit);
                                    finish();
                                } else if (message.equals("用户名或密码不正确")) {
                                    new Thread(new Runnable() {
                                        @Override
                                        public void run() {
                                            Looper.prepare();
                                            Toast.makeText(LoginActivity.this,
                                                    "用户名或密码不正确", Toast.LENGTH_SHORT).show();
                                            Looper.loop();
                                        }
                                    }).start();
                                }
                            }
                            if (code == 0) {
                                System.out.println("登录成功 0");
                                Gson gson = new Gson();
                                MessageSend messageSend = gson.fromJson(s, MessageSend.class);
                                String userid121 = messageSend.message.userid;
                                Log.e("userid是", userid121);
                                sharedPreferences = getSharedPreferences("logininfo", MODE_PRIVATE);
                                SharedPreferences.Editor edit = sharedPreferences.edit();
                                edit.putBoolean("islogin", true);
                                edit.putString("phone", phone);
                                edit.putString("pass", password);
                                edit.putString("imei", imei);
                                edit.putString("userid", userid121);
                                boolean commit = edit.commit();
                                System.out.println("edit" + commit);
                                intent = new Intent(LoginActivity.this, FristActivity.class);
                                startActivity(intent);


                                finish();
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                });

                break;
            case R.id.registerTv://注册
                intent = new Intent(LoginActivity.this, RegisterActivity.class);
                startActivity(intent);
                break;
            case R.id.forgetPassTv://忘记密码
                intent = new Intent(LoginActivity.this, ForgetPassActivity.class);
                startActivity(intent);
                break;
            case R.id.loginBackIv://返回
                finish();
                break;
            case R.id.passLookIv:
                System.out.println("眼睛按钮点击了");
                //设置文本可见
                if (ishide) {
                    inputPassEt.setTransformationMethod(new PasswordTransformationMethod());
                    ishide = false;
                } else {
                    inputPassEt.setTransformationMethod(null);
                    ishide = true;
                }
                break;
            //点击x文本消失
            case R.id.inputPhoneEt:
                inputPhoneEt.setText("");
                break;
            case R.id.loginQQIv:
                final Platform qq = ShareSDK.getPlatform(QQ.NAME);
                qq.setPlatformActionListener(new PlatformActionListener() {
                    @Override
                    public void onComplete(final Platform platform, int i, HashMap<String, Object> hashMap) {
                        String s = platform.getDb().exportData();
                        Log.e("登录信息", s);

//                        Iterator iterator = hashMap.entrySet().iterator();
//                        while (iterator.hasNext()) {
//                            Map.Entry entry = (Map.Entry) iterator.next();
//                            Object key = entry.getKey();
//                            Object value = entry.getValue();
//                            System.out.println("set集合"+key + ":" + value);
//                        }
                        MultipartBody.Builder builder = new MultipartBody.Builder().setType(MultipartBody.FORM);
                        String token1 = platform.getDb().getToken();
                        String userId = platform.getDb().getUserId();
                        String userName = platform.getDb().getUserName();
                        String userIcon = platform.getDb().getUserIcon();
                        builder.addFormDataPart("userID", userId);
                        builder.addFormDataPart("icon", userIcon);
                        builder.addFormDataPart("token", token1);
                        builder.addFormDataPart("nickname", userName);

                        MultipartBody req = builder.build();
                        //修改url
                        Request request = new Request.Builder()
                                .url(Url.Three).post(req).build();
                        Call call = client.newCall(request);
                        call.enqueue(new Callback() {
                            @Override
                            public void onFailure(Call call, IOException e) {
                                Log.e("message1", "异常" + e.getMessage().toString());
                            }

                            @Override
                            public void onResponse(Call call, Response response) throws IOException {
                                String string = response.body().string();
                                Log.e("mess","登录结果返回"+string);

                            }
                        });
                        if (qq.isAuthValid()) {
                            sharedPreferences = getSharedPreferences("logininfo", MODE_PRIVATE);
                            SharedPreferences.Editor edit = sharedPreferences.edit();
                            edit.putBoolean("islogin", true);
                            edit.putString("userid",platform.getDb().getUserId());
                            edit.commit();
                        }
                        finish();
                    }

                    @Override
                    public void onError(Platform platform, int i, Throwable throwable) {
                    }

                    @Override
                    public void onCancel(Platform platform, int i) {
                    }
                });
                //authorize与showUser单独调用一个即可
                qq.authorize();
                qq.showUser(null);//授权并获取用户信息

                break;
            case R.id.loginWechatIv:

                break;

            case R.id.loginWeiBoIv:

                break;
        }

    }


}
