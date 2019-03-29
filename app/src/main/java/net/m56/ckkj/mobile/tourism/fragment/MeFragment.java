package net.m56.ckkj.mobile.tourism.fragment;

import android.Manifest;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.google.gson.Gson;

import net.m56.ckkj.mobile.tourism.Api.Url;
import net.m56.ckkj.mobile.tourism.activity.CustomGuideActivity;
import net.m56.ckkj.mobile.tourism.base.activity.CaptureActivity;
import net.m56.ckkj.mobile.tourism.base.activity.PersonalDataActivity;
import net.m56.ckkj.mobile.tourism.base.activity.SetActivity;
import net.m56.ckkj.mobile.tourism.base.activity.SuggestActivity;
import net.m56.ckkj.mobile.tourism.base.farment.BaseFragment;
import net.m56.ckkj.mobile.tourism.bean.UserInfo;
import net.m56.ckkj.mobile.tourism.project.login.LoginActivity;
import net.m56.ckkj.tourism.tourism.R;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import butterknife.ButterKnife;
import de.hdodenhof.circleimageview.CircleImageView;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import static android.content.Context.MODE_PRIVATE;

/**
 * 2017/10/11 9:26
 */

public class MeFragment extends BaseFragment implements View.OnClickListener {

    ImageView erweima;
    TextView titleName;
    ImageView xiaoxi;
    RelativeLayout mImgRl;
    TextView mUerNameTv;
    TextView mUserSignTv;
    TextView mSuggestTv;
    TextView mServiceTv;
    TextView mGuideTv;
    CircleImageView mUserImg;

    private Intent intent;
    public SharedPreferences logininfo;
    OkHttpClient client = new OkHttpClient();
    String photoDefaultPath = "android.resource://net.m56.ckkj.tourism.tourism/drawable/" + R.drawable.icon_kefu;

    @Override
    protected int getLayoutId() {
        return R.layout.mefragment;
    }

    @Override
    protected void initParams(View view, Bundle savedInstanceState) {

        erweima = (ImageView) view.findViewById(R.id.erweima);
        xiaoxi = (ImageView) view.findViewById(R.id.xiaoxi);
        //用户头像
        mUserImg = (CircleImageView) view.findViewById(R.id.userImg);
        mImgRl = (RelativeLayout) view.findViewById(R.id.imgRl);
        titleName = (TextView) view.findViewById(R.id.title_name);

        //用户名 个性签名
        mUerNameTv = (TextView) view.findViewById(R.id.userNameTv);
        mUserSignTv = (TextView) view.findViewById(R.id.userSignTv);

        mSuggestTv = (TextView) view.findViewById(R.id.suggestTv);
        mServiceTv = (TextView) view.findViewById(R.id.serviceTv);
        mGuideTv = (TextView) view.findViewById(R.id.guideTv);

        titleName.setText("个人中心");
        xiaoxi.setImageResource(R.mipmap.icon_shezhi);
        erweima.setOnClickListener(this);
        xiaoxi.setOnClickListener(this);
        mServiceTv.setOnClickListener(this);
        mSuggestTv.setOnClickListener(this);
        mGuideTv.setOnClickListener(this);
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }

    @Override
    public void onResume() {
        super.onResume();
        //6.0权限检查和申请
        if (ContextCompat.checkSelfPermission(getContext(), Manifest.permission.CAMERA)
                != PackageManager.PERMISSION_GRANTED) {
            //申请WRITE_EXTERNAL_STORAGE权限
            ActivityCompat.requestPermissions(getActivity(), new String[]{Manifest.permission.CAMERA},
                    1);//自定义的code
        }
        if (ContextCompat.checkSelfPermission(getContext(), Manifest.permission.WRITE_EXTERNAL_STORAGE)
                != PackageManager.PERMISSION_GRANTED) {
            //申请WRITE_EXTERNAL_STORAGE权限
            ActivityCompat.requestPermissions(getActivity(), new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},
                    2);//自定义的code
        }
        mUserImg.setImageResource(R.mipmap.icon_kefu);
        logininfo = getActivity().getSharedPreferences("logininfo", MODE_PRIVATE);
        boolean islogin = logininfo.getBoolean("islogin", false);
        if (islogin == false) {
            mUerNameTv.setText("请先登录");
            mUserSignTv.setVisibility(View.GONE);
            mImgRl.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    intent = new Intent(getActivity(), LoginActivity.class);
                    startActivity(intent);
                }
            });
            //没有登录设置按钮不出来
            xiaoxi.setVisibility(View.INVISIBLE);
        } else {
            //登陆之后 设置按钮显示
            xiaoxi.setVisibility(View.VISIBLE);
            Glide.with(getContext())
                    .load(photoDefaultPath)
                    .into(mUserImg);
            mUerNameTv.setText("默认值");
            mUserSignTv.setText("介个人很懒啥都没写");
            //登录成功之后的逻辑
            mImgRl.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    intent = new Intent(getActivity(), PersonalDataActivity.class);
                    intent.putExtra("titlename", "个人资料");
                    startActivity(intent);
                }
            });
            logininfo = getActivity().getSharedPreferences("logininfo", MODE_PRIVATE);

            final String userid = logininfo.getString("userid", "");
            MultipartBody.Builder builder = new MultipartBody.Builder().setType(MultipartBody.FORM);
            //用户id
            builder.addFormDataPart("userid", userid);

            MultipartBody requestbody = builder.build();

            final Request reques = new Request.Builder().url(Url.userInfo).post(requestbody).build();
            client.newCall(reques).enqueue(new Callback() {
                @Override
                public void onFailure(Call call, IOException e) {
                }

                @Override
                public void onResponse(Call call, Response response) throws IOException {

                    String string = response.body().string();
                    Log.e("请求的数据", string);
                    try {
                        JSONObject jb = new JSONObject(string);
                        int code = jb.getInt("code");
                        if (code == 0) {
                            Gson gson = new Gson();
                            UserInfo userInfo = gson.fromJson(string, UserInfo.class);
                            final String nickname = (String) userInfo.message.rows.nickname;
                            final String usericonurl = (String) userInfo.message.rows.usericon;
                            final String signature = (String) userInfo.message.rows.signature;
                            getActivity().runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    if (usericonurl.contains("http")) {
                                        Glide.with(getContext())
                                                .load((usericonurl == null) || usericonurl.equals("") ? photoDefaultPath : usericonurl)
                                                .into(mUserImg);
                                    } else {
                                        Glide.with(getContext())
                                                .load((usericonurl == null) || usericonurl.equals("") ? photoDefaultPath : "http://" + usericonurl)
                                                .into(mUserImg);
                                    }
                                    mUerNameTv.setText((nickname == null) ? "默认值" : nickname);
                                    mUserSignTv.setText((signature == null) ? "介个人很懒啥都没写" : signature);
                                }
                            });

                        }

                    } catch (JSONException e) {

                    }

                }
            });

        }
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.erweima://二维码
                intent = new Intent(getActivity(), CaptureActivity.class);
                startActivity(intent);
                break;
            case R.id.xiaoxi:
                intent = new Intent(getActivity(), SetActivity.class);
                startActivity(intent);
                break;
            case R.id.suggestTv:
                intent = new Intent(getActivity(), SuggestActivity.class);
                startActivity(intent);
                break;
            case R.id.serviceTv:
                Call("0358-8231251");
                break;
            case R.id.guideTv:
                intent = new Intent(getActivity(), CustomGuideActivity.class);
                startActivity(intent);
        }
    }

    /**
     * 拨打电话(拨号键)
     *
     * @param phoneStr
     */
    private void Call(String phoneStr) {
        intent = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:" + phoneStr));
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }

}
