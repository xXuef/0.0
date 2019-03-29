package net.m56.ckkj.mobile.tourism.base.activity;

import android.os.Bundle;
import android.os.Looper;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
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
 * @Description: 忘记密码的设置新密码
 * @date 2017/10/26 15:31
 */
public class SetPassActivity extends BaseActivity implements View.OnClickListener{

    private static final String TAG = "setpassword";
    private RelativeLayout mOldconfirmRl;
    public String phonenum;
    public String newpassword;
    private final OkHttpClient client = new OkHttpClient();
    public String oldpassword;
    public EditText oldconfirmEt;
    public EditText newconfirmEt;

    @Override
    public void initVariable() {
        tool_title.setText("设置密码");
        setContentView(R.layout.activity_confirm_layout);
    }

    @Override
    public void toLoad(Bundle savedInstanceState) {
        mOldconfirmRl= (RelativeLayout) findViewById(R.id.oldconfirmRl);
        //确认按钮
        Button ok = (Button) findViewById(R.id.confirmOkBtn);
        ok.setOnClickListener(this);
        phonenum = getIntent().getStringExtra("phonenum");
        //两次输入的密码的edittext
        oldconfirmEt = (EditText) findViewById(R.id.oldconfirmEt);
        newconfirmEt = (EditText) findViewById(R.id.newconfirmEt);
        oldconfirmEt.setHint("请输入密码");
        newconfirmEt.setHint("请再次输入密码");


    }

    @Override
    public void onClick(View v) {

        switch (v.getId()){
            case R.id.confirmOkBtn:
                oldpassword = oldconfirmEt.getText().toString().trim();
                newpassword = newconfirmEt.getText().toString().trim();
                if (!oldpassword.equals(newpassword)){
                    Toast.makeText(this,"两次密码输入不一致",Toast.LENGTH_SHORT).show();
                    return;
                }
                Log.e("mum", "电话号码" + phonenum + "");
                //点击下一步是的
                MultipartBody.Builder builder = new MultipartBody.Builder().setType(MultipartBody.FORM);
                //type
                builder.addFormDataPart("type", "忘记");
                //电话号码
                builder.addFormDataPart("phone", phonenum);
                //新密码
                builder.addFormDataPart("new_password", newpassword);

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

                        try {
                            JSONObject jsonObject = new JSONObject(s);
                            String message = jsonObject.getString("message");
                            Log.e(TAG,message);
                            if (message.equals("设置成功")) {
                                new Thread(new Runnable() {
                                    @Override
                                    public void run() {
                                        Looper.prepare();
                                        Toast.makeText(SetPassActivity.this,
                                                "密码重置成功", Toast.LENGTH_SHORT).show();
                                        Looper.loop();
                                    }
                                }).start();

                            }else{
                                return;
                            }
                            finish();
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }


                    }
                });
                break;
        }
    }
}
