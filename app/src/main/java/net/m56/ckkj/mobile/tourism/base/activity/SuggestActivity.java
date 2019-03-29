package net.m56.ckkj.mobile.tourism.base.activity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import net.m56.ckkj.mobile.tourism.Api.Url;
import net.m56.ckkj.mobile.tourism.base.view.XsEditTextWithCount;
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
 * @Description: 投诉建议
 * @date 2017/10/26 16:08
 */
public class SuggestActivity extends BaseActivity implements View.OnClickListener {

    public EditText phone;
    public XsEditTextWithCount content;
    OkHttpClient client = new OkHttpClient();

    @Override
    public void initVariable() {
        tool_title.setText("投诉建议");
        setContentView(R.layout.activity_suggest_layout);
    }

    @Override
    public void toLoad(Bundle savedInstanceState) {
        phone = (EditText) findViewById(R.id.phone);
        content = (XsEditTextWithCount) findViewById(R.id.content);
        Button subt = (Button) findViewById(R.id.submitbtn);
        subt.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.submitbtn:
                if (phone.getText().toString().equals("")){
                    showToast("请输入电话号码");
                    return;
                }
                SharedPreferences sharedPreferences = getSharedPreferences("logininfo", MODE_PRIVATE);
                String userid = sharedPreferences.getString("userid", "");
                MultipartBody.Builder builder = new MultipartBody.Builder().setType(MultipartBody.FORM);
                //type
                builder.addFormDataPart("userid", userid);
                builder.addFormDataPart("cont", content.getInputContent() + "手机号:" + phone.getText().toString());
                MultipartBody requestbody = builder.build();

                //修改url
                Request request = new Request.Builder()
                        .url(Url.Suggest).post(requestbody).build();

                Call call = client.newCall(request);
                call.enqueue(new Callback() {
                    @Override
                    public void onFailure(Call call, IOException e) {

                    }

                    @Override
                    public void onResponse(Call call, Response response) throws IOException {

//                        Log.e("建议提交情况",response.body().string());

                        try {
                            JSONObject jsonObject = new JSONObject(response.body().string());
                            if (jsonObject.getInt("code")==0){
                                String message = jsonObject.getString("message");
                                if (message.equals("发布成功")){
                                    runOnUiThread(new Runnable() {
                                        @Override
                                        public void run() {
                                            showToast("提交成功");
                                        }
                                    });
                                    finish();
                                }

                            }
                        } catch (JSONException e) {
                            e.printStackTrace();

                        }
                    }
                });
                break;
        }
    }
}
