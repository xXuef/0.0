package net.m56.ckkj.mobile.tourism.base.activity;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.text.TextUtils;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import net.m56.ckkj.mobile.tourism.Api.Url;
import net.m56.ckkj.tourism.tourism.R;

import org.json.JSONObject;

import java.io.IOException;

import cn.smssdk.EventHandler;
import cn.smssdk.OnSendMessageHandler;
import cn.smssdk.SMSSDK;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * @author yanmin K_pop9799@163.com
 * @ClassName: ${type_name}
 * @Description: 注册
 * @date 2017/10/12 14:20
 */
public class RegisterActivity extends BaseActivity implements View.OnClickListener {
    private static final String TAG = "CONTENT";

    //完成按钮
    private Button registerNextBtn;
    //发送验证码按钮
    private Button passLookIv;

    private final OkHttpClient client = new OkHttpClient();
    public EditText inputPhoneEt;//电话号码
    public EditText inputPassEt;//验证码
    public EditText registerpassEtone;//输入密码一
    public EditText registerpassEttwo;
    public String phone;//用户输入的电话号码
    public String lookCode;//用户输入的验证码
    private static final int GET_CODE_SUCCES = 100;//获取验证码成功
    private static final int GET_CODE_FAIL = 101;//获取验证码失败

    private static final int SUBMIT_CODE_SUCCES = 104;//校验验证码成功
    private static final int SUBMIT_CODE_FAIL = 105;//校验验证码失败

    private static final int KEEP_TIME_MINS = 102;//保持时间递减的状态码
    private static final int RESET_TIME = 103;//重置时间为60秒
    private int time = 30;
    public boolean isLooking;
    private Handler handler = new Handler() {

        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case GET_CODE_SUCCES:
                    Toast.makeText(RegisterActivity.this, "获取验证码成功", Toast.LENGTH_SHORT).show();
                    break;
                case GET_CODE_FAIL:
                    Toast.makeText(RegisterActivity.this, "获取验证码失败", Toast.LENGTH_SHORT).show();
                    break;
                case SUBMIT_CODE_SUCCES:
                    isLooking = true;
//                    Toast.makeText(RegisterActivity.this, "校验验证码成功", Toast.LENGTH_SHORT).show();
                    //必须获取校验成功,才可以继续下一个发送请求做注册过程
                    post_password();
                    break;
                case SUBMIT_CODE_FAIL:
                    isLooking = false;
                    Toast.makeText(RegisterActivity.this, "校验验证码失败", Toast.LENGTH_SHORT).show();
                    break;
                case KEEP_TIME_MINS:
                    passLookIv.setText("(" + (time--) + "s)后再发");
                    break;
                case RESET_TIME:
                    passLookIv.setText("重新发送");
                    passLookIv.setEnabled(true);
                    time = 30;
                    break;

            }
        }
    };

    //提交账号密码
    private void post_password() {
        String phone2 = inputPhoneEt.getText().toString().trim();
        //验证两次密码是否输入相同
        String passone = registerpassEtone.getText().toString().trim();
        String passtwo = registerpassEttwo.getText().toString().trim();

        if (passone != null) {
            Log.e("passone", passone);
        }
        if (passtwo != null) {
            Log.e("passone", passtwo);
        }
        if (!passone.equals(passtwo)) {
            showToast("两次密码输入不相同");
            System.out.println("两次密码输入不相同");
            return;
        }
        //点击下一步是的
        MultipartBody.Builder builder = new MultipartBody.Builder().setType(MultipartBody.FORM);
        //电话号码
        builder.addFormDataPart("phone", phone2);
        //密码
        builder.addFormDataPart("password", passtwo);

        MultipartBody requestbody = builder.build();

        //修改url
        Request request = new Request.Builder()
                .url(Url.Register).post(requestbody).build();
        Call call = client.newCall(request);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String s = response.body().string().toString();
                Log.e(TAG, s);

                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        Looper.prepare();
                        Toast.makeText(RegisterActivity.this, "注册成功", Toast.LENGTH_SHORT).show();
                        Looper.loop();
                        //这的回调里面直接毁掉
                        finish();
                    }
                }).start();

            }
        });

    }

    // 创建EventHandler对象
    public EventHandler eventHandler = new EventHandler() {
        public void afterEvent(int event, int result, Object data) {
            if (result == SMSSDK.RESULT_COMPLETE) {
                //验证码发送成功
                if (event == SMSSDK.EVENT_GET_VERIFICATION_CODE) {
                    handler.sendEmptyMessage(GET_CODE_SUCCES);
                }
                if (event == SMSSDK.EVENT_SUBMIT_VERIFICATION_CODE) {
                    isLooking = true;
                    //校验验证码成功
                    handler.sendEmptyMessage(SUBMIT_CODE_SUCCES);
                }

            } else {
                // 根据服务器返回的网络错误，给toast提示
                try {
                    Throwable throwable = (Throwable) data;
                    throwable.printStackTrace();
                    JSONObject object = new JSONObject(throwable.getMessage());
                    String des = object.optString("detail");//错误描述
                    int status = object.optInt("status");//错误代码
                    if (status > 0 && !TextUtils.isEmpty(des)) {
                        Toast.makeText(RegisterActivity.this, des, Toast.LENGTH_SHORT).show();
                        Log.e("des", des);
                        return;
                    }
                } catch (Exception e) {
                    //do something
                }
                //失败
                if (event == SMSSDK.EVENT_GET_VERIFICATION_CODE) {
                    //下发验证码短信成功后,才可以做验证码短信+手机号码校验过程
                    handler.sendEmptyMessage(GET_CODE_FAIL);
                    return;
                }
                if (event == SMSSDK.EVENT_SUBMIT_VERIFICATION_CODE) {
                    isLooking = false;
                    //校验验证码失败
                    handler.sendEmptyMessage(SUBMIT_CODE_FAIL);
                    return;
                }
            }
//                做某一个事件结果的监听
            super.afterEvent(event, result, data);
        }
    };

    @Override
    public void initVariable() {
        tool_title.setText("注册");
        setContentView(R.layout.activity_register_layout);

        registerNextBtn = (Button) findViewById(R.id.registerNextBtn);
        passLookIv = (Button) findViewById(R.id.passLookIv);
        inputPhoneEt = (EditText) findViewById(R.id.inputPhoneEt);
        inputPassEt = (EditText) findViewById(R.id.inputPassEt);
        //两次输入的密码
        registerpassEttwo = (EditText) findViewById(R.id.registerpassEttwo);
        registerpassEtone = (EditText) findViewById(R.id.registerpassEtone);

    }

    @Override
    public void toLoad(Bundle savedInstanceState) {

        SMSSDK.registerEventHandler(eventHandler);

        registerNextBtn.setOnClickListener(this);
        passLookIv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getLookingCode();
            }
        });
    }

    /**
     * 获取验证码逻辑
     * 30秒禁用
     */
    private void getLookingCode() {
        time = 30;
        phone = inputPhoneEt.getText().toString().trim();

        //如果输入电话号码无误,则可以对此号码发送验证码
        SMSSDK.getVerificationCode("86", phone, new OnSendMessageHandler() {
            @Override
            public boolean onSendMessage(String s, String s1) {
                return false;
            }
        });

        passLookIv.setEnabled(false);
        //开个子线程倒计时开始
        new Thread(new Runnable() {
            @Override
            public void run() {
                while (time > 0) {
                    handler.sendEmptyMessage(KEEP_TIME_MINS);
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                //完事之后重置时间
                handler.sendEmptyMessage(RESET_TIME);
            }
        }).start();

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

    protected void onDestroy() {
        super.onDestroy();
        SMSSDK.unregisterEventHandler(eventHandler);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            //phone 13335433232 password  123
            case R.id.registerNextBtn://完成//
                String phone1 = inputPhoneEt.getText().toString().trim();
                //输入的验证码
                lookCode = inputPassEt.getText().toString().trim();
                //验证短信和验证码是否相同
                SMSSDK.submitVerificationCode("86", phone1, lookCode);

//                intent = new Intent(getApplicationContext(), LoginActivity.class);
//                startActivity(intent);

                break;
        }
    }

}
