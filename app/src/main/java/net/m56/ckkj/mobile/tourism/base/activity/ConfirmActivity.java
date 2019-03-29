package net.m56.ckkj.mobile.tourism.base.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Looper;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import net.m56.ckkj.mobile.tourism.Api.Url;
import net.m56.ckkj.tourism.tourism.R;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * @author yanmin K_pop9799@163.com
 * @ClassName: ${type_name}
 * @Description: 修改密码手机号验证成功后的设置密码
 * @date 2017/10/26 14:36
 */
public class ConfirmActivity extends BaseActivity implements View.OnClickListener {
    private static final String TAG = "xiugaiqingkuang";
    private Button mConfirmOkBtn;//完成
    private Intent intent;
    public String phonenum;
    private final OkHttpClient client = new OkHttpClient();
    public EditText oldpasword;
    public EditText newpasword;

    @Override
    public void initVariable() {
        tool_title.setText("设置密码");
        setContentView(R.layout.activity_confirm_layout);
        phonenum = getIntent().getStringExtra("phonenum");
    }

    @Override
    public void toLoad(Bundle savedInstanceState) {

        mConfirmOkBtn = (Button) findViewById(R.id.confirmOkBtn);
        mConfirmOkBtn.setOnClickListener(this);
        oldpasword = (EditText) findViewById(R.id.oldconfirmEt);
        newpasword = (EditText) findViewById(R.id.newconfirmEt);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.confirmOkBtn://完成

                Log.e("mum", "电话号码" + phonenum + "");
                //点击下一步是的
                MultipartBody.Builder builder = new MultipartBody.Builder().setType(MultipartBody.FORM);
                //type
                builder.addFormDataPart("type", "修改");
                //电话号码
                builder.addFormDataPart("phone", phonenum);
                //旧密码
                builder.addFormDataPart("old_password", oldpasword.getText().toString().trim());
                //新密码
                builder.addFormDataPart("new_password", newpasword.getText().toString().trim());

                MultipartBody requestbody = builder.build();

                //修改url
                Request request = new Request.Builder()
                        .url(Url.SetPassword).post(requestbody).build();
                Call call = client.newCall(request);
                call.enqueue(new Callback() {
                    @Override
                    public void onFailure(Call call, IOException e) {

                    }

                    @Override
                    public void onResponse(Call call, Response response) throws IOException {
                        String s = response.body().string().toString();
                        Log.e(TAG, s);

                        try {
                            JSONObject jsonObject = new JSONObject(s);
                            String message = jsonObject.getString("message");
                            if (message.equals("密码修改成功")) {
                                new Thread(new Runnable() {
                                    @Override
                                    public void run() {
                                        Looper.prepare();
                                        Toast.makeText(ConfirmActivity.this,
                                                "密码修改成功", Toast.LENGTH_SHORT).show();
                                        Looper.loop();
                                    }
                                }).start();
                            }
                            finish();

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }


                    }
                });

//
//                intent = new Intent(getApplicationContext(), LoginActivity.class);
//                startActivity(intent);
                break;
        }
    }


}
