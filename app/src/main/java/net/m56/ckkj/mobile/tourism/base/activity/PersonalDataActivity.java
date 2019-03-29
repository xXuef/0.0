package net.m56.ckkj.mobile.tourism.base.activity;

import android.Manifest;
import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.google.gson.Gson;
import com.gyf.barlibrary.ImmersionBar;

import net.m56.ckkj.mobile.tourism.Api.Url;
import net.m56.ckkj.mobile.tourism.base.view.BirthDateDialog;
import net.m56.ckkj.mobile.tourism.base.view.XsDialog;
import net.m56.ckkj.mobile.tourism.bean.UserInfo;
import net.m56.ckkj.mobile.tourism.util.ConstantUtil;
import net.m56.ckkj.mobile.tourism.util.DateUtils;
import net.m56.ckkj.mobile.tourism.util.RegexUtils;
import net.m56.ckkj.mobile.tourism.util.StorageUtil;
import net.m56.ckkj.tourism.tourism.R;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;


/**
 * @author yanmin K_pop9799@163.com
 * @ClassName: ${type_name}
 * @Description: ${TODO}(这里用一句话描述这个类的作用)
 * @date 2017/10/24 17:21
 */
public class PersonalDataActivity extends BaseActivity implements View.OnClickListener {
    private RelativeLayout mSexRl, mBirthdayRl, mEditUserImgRl;//性别，生日，头像
    private TextView mUserSexTv, mUserBirthdayTv;//性别
    private ImageView eidtUserImg;//头像
    OkHttpClient client = new OkHttpClient();
    public SharedPreferences logininfo;
    String photoDefaultPath = "android.resource://net.m56.ckkj.tourism.tourism/drawable/" + R.drawable.icon_kefu;

    // 拍照
    private static final int TAKE_PICTURE = 0x000001;
    // 相册
    private static final int SELECT_PIC_BY_PICK_PHOTO = 0x000002;
    // 图片裁剪
    private static final int IMAGE_REQUEST_CODE = 0x000003;
    // 图片路径
    private String picPath;
    //参数类型
    private static final MediaType MEDIA_TYPE_PNG = MediaType.parse("image/png");
    private static final int WRITE_ACCESS_CAMERA_REQUEST_CODE = 1;
    private static final int WRITE_ACCESS_PHOTO_REQUEST_CODE = 2;
    public EditText nikenameEt;
    public EditText signatureEt;
    public EditText ageEt;

    @Override
    public void initVariable() {
        String titlename = getIntent().getStringExtra("titlename");
        tool_title.setText(titlename);
        setContentView(R.layout.activity_persondata_layout);
        //设置沉浸式状态栏
        ImmersionBar.with(this).statusBarColor(R.color.stabar_color).fitsSystemWindows(true).init();
        //6.0权限检查和申请
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA)
                != PackageManager.PERMISSION_GRANTED) {
            //申请WRITE_EXTERNAL_STORAGE权限
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.CAMERA},
                    1);//自定义的code
        }
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE)
                != PackageManager.PERMISSION_GRANTED) {
            //申请WRITE_EXTERNAL_STORAGE权限
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},
                    2);//自定义的code
        }
    }

    @Override
    public void toLoad(Bundle savedInstanceState) {
        mEditUserImgRl = (RelativeLayout) findViewById(R.id.editUserImgRl);
        mSexRl = (RelativeLayout) findViewById(R.id.sexRl);
        mBirthdayRl = (RelativeLayout) findViewById(R.id.birthdayRl);
        nikenameEt = (EditText) findViewById(R.id.nikename);
        signatureEt = (EditText) findViewById(R.id.signature);
        ageEt = (EditText) findViewById(R.id.age);

        eidtUserImg = (ImageView) findViewById(R.id.eidtUserImg);
        mUserSexTv = (TextView) findViewById(R.id.userSexTv);
        mUserBirthdayTv = (TextView) findViewById(R.id.userBirthdayTv);

        mEditUserImgRl.setOnClickListener(this);
        mSexRl.setOnClickListener(this);
        mBirthdayRl.setOnClickListener(this);


        logininfo = getSharedPreferences("logininfo", MODE_PRIVATE);
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
                        final String signature1 = (String) userInfo.message.rows.signature;
                        final String age1 = (String) userInfo.message.rows.age;
                        final String sex1 = (String) userInfo.message.rows.gender;

                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                if (usericonurl.contains("http")) {
                                    Glide.with(PersonalDataActivity.this)
                                            .load((usericonurl == null) || usericonurl.equals("") ? photoDefaultPath : usericonurl)
                                            .into(eidtUserImg);
                                } else {
                                    Glide.with(PersonalDataActivity.this)
                                            .load((usericonurl == null) || usericonurl.equals("") ? photoDefaultPath : "http://" + usericonurl)
                                            .into(eidtUserImg);
                                }
                                nikenameEt.setHint((nickname == null) ? "默认值" : nickname);
                                signatureEt.setHint((signature1 == null) ? "介个人很懒啥都没写" : signature1);
                                ageEt.setHint((age1 == null) ? "25" : age1);
                                mUserSexTv.setHint((sex1 == null) ? "男" : sex1);
                                mUserBirthdayTv.setHint("0000-00-00");
                            }
                        });

                    }

                } catch (JSONException e) {

                }
            }
        });
    }


    /**
     * 选择头像
     */

    private XsDialog selPicDialog;

    private void showTakePhotoOrAlbumDialog() {
        selPicDialog = new XsDialog(mContext, null, null, false, false, false);
        View view = View.inflate(mContext, R.layout.dig_sel_photos_layout, null);
        TextView takePicTv = (TextView) view.findViewById(R.id.takePicTv);
        TextView albumPicTv = (TextView) view.findViewById(R.id.albumPicTv);

        takePicTv.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                if (selPicDialog != null) {
                    selPicDialog.dismiss();
                }
                takePhoto();
            }
        });

        albumPicTv.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                if (selPicDialog != null) {
                    pickPhoto();
                }
                selPicDialog.dismiss();
            }
        });

        Window window = selPicDialog.getWindow();
        WindowManager.LayoutParams lp = window.getAttributes();
        lp.width = WindowManager.LayoutParams.WRAP_CONTENT;
        lp.height = WindowManager.LayoutParams.WRAP_CONTENT;
        lp.gravity = Gravity.CENTER_HORIZONTAL;
        window.setAttributes(lp);
        selPicDialog.setCancelable(true);
        selPicDialog.setContentView(view);
        selPicDialog.show();
    }

    /**
     * @Title: takePhoto
     * @Description: TODO 拍照
     * @param: 设定文件
     * @return: void 返回类型
     * @date: 2017-10-25 下午5:00:06
     */
    public void takePhoto() {
        // 执行拍照前，应该先判断SD卡是否存在
        String SDState = Environment.getExternalStorageState();
        if (SDState.equals(Environment.MEDIA_MOUNTED)) {
            Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
            // 下面这句指定调用相机拍照后的照片存储的路径
            intent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(new File(
                    Environment.getExternalStorageDirectory()
                            + ConstantUtil.CROP_PATH)));
            startActivityForResult(intent, TAKE_PICTURE);
        } else {
            showToast("未找到存储卡，无法存储照片！");
        }
    }

    /***
     * 从相册中取图片
     */
    private void pickPhoto() {
        Intent intent = new Intent(Intent.ACTION_PICK, null);
        intent.setDataAndType(MediaStore.Images.Media.EXTERNAL_CONTENT_URI,
                "image/*");
        startActivityForResult(intent, SELECT_PIC_BY_PICK_PHOTO);
    }

    /**
     * 选择性别
     */
    private void ChoiceSexDialog() {
        String sexStr[] = {"男", "女"};
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("请选择性别");
        builder.setItems(sexStr, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                if (i == 0) {
                    mUserSexTv.setText("男");
                } else {
                    mUserSexTv.setText("女");
                }
            }
        });
        builder.create();
        builder.show();
    }

    /**
     * 选择生日Dialog
     */
    private void ChoiceBirthdayDialog() {
        DisplayMetrics dm = new DisplayMetrics();
        mContext.getWindowManager().getDefaultDisplay().getMetrics(dm);
        int width = dm.widthPixels;
        int height = dm.heightPixels;
        String curDateleft = mUserBirthdayTv.getText().toString();
        int[] date = DateUtils.getYMDArray(curDateleft, "-");
        BirthDateDialog birDiolog = new BirthDateDialog(mContext,
                new BirthDateDialog.PriorityListener() {
                    @Override
                    public void refreshPriorityUI(String year, String month,
                                                  String day) {
                        mUserBirthdayTv.setText(year + "-" + month + "-" + day);
                    }
                }, date[0], date[1], date[2], width, height, "");
        Window window = birDiolog.getWindow();
        window.setGravity(Gravity.BOTTOM); // 此处可以设置dialog显示的位置
        window.setWindowAnimations(R.style.dialogstyle); // 添加动画
        birDiolog.setCancelable(true);
        birDiolog.show();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                break;
            case R.id.action_sub:
                post_data();
                showToast("提交");
                finish();
                break;
        }
        return super.onOptionsItemSelected(item);

    }

    private void post_data() {
        if (picPath != null) {
            Log.e("图片的路径", picPath);
        }
        SharedPreferences sp = getSharedPreferences("logininfo", MODE_PRIVATE);
//        String userid = sp.getString("userid", "");
//        String name = nikenameEt.getText().toString().trim();
//        String age = ageEt.getText().toString().trim();
//        String sex = mUserSexTv.getText().toString().trim();
//        String signature = signatureEt.getText().toString().trim();
        MultipartBody.Builder builder = new MultipartBody.Builder()
                .setType(MultipartBody.FORM);
        if (picPath != null) {
            File file = new File(picPath);
            builder.addFormDataPart("img", file.getName(), RequestBody.create(MEDIA_TYPE_PNG, file));
        }
        builder.addFormDataPart("userid", sp.getString("userid", ""));
        builder.addFormDataPart("nickname", nikenameEt.getText().toString().trim());
        builder.addFormDataPart("age", ageEt.getText().toString().trim());
        builder.addFormDataPart("gender", mUserSexTv.getText().toString().trim());
        builder.addFormDataPart("signature", signatureEt.getText().toString().trim());

        MultipartBody requesBody = builder.build();
        Request request = new Request.Builder().url(Url.EditUserInfo)
                .post(requesBody)
                .build();
        Call call = client.newCall(request);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

                Log.e("上传失败的情况", e.getLocalizedMessage());
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {

                String string = response.body().string();
                try {
                    JSONObject jb = new JSONObject(string);
                    int code = jb.getInt("code");
                    //如果返回的code不等于0返回)
                    if (code != 0) {
                        return;
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                Log.e("修改情况", string);
            }
        });


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menuitem, menu);
        return super.onCreateOptionsMenu(menu);
    }

    /****
     * 裁剪图片方法实现
     *****/
    public void startPhotoZoom(Uri uri) {
        Intent intent = new Intent("com.android.camera.action.CROP");
        intent.setDataAndType(uri, "image/*");
        // 下面这个crop=true是设置在开启的Intent中设置显示的VIEW可裁剪
        intent.putExtra("crop", "true");
        // aspectX aspectY 是宽高的比例
        intent.putExtra("aspectY", 1);
        intent.putExtra("aspectX", 1);
        intent.putExtra("outputX", 250);
        intent.putExtra("outputY", 250);
        intent.putExtra("scale", true);
        intent.putExtra("return-data", false);
        intent.putExtra(MediaStore.EXTRA_OUTPUT, getTempUri());
        intent.putExtra("outputFormat", Bitmap.CompressFormat.JPEG.toString());
        intent.putExtra("noFaceDetection", true);
        startActivityForResult(intent, IMAGE_REQUEST_CODE);
    }

    private Uri getTempUri() {
        Uri tempPhotoUri = Uri.fromFile(getTempFile());
        return tempPhotoUri;
    }

    private File getTempFile() {
        String SDState = Environment.getExternalStorageState();
        if (SDState.equals(Environment.MEDIA_MOUNTED)) {

            File f = new File(Environment.getExternalStorageDirectory(),
                    ConstantUtil.CROP_PATH);
            try {
                f.createNewFile();
            } catch (IOException e) {

            }
            return f;
        }
        return null;
    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == Activity.RESULT_OK) {
            switch (requestCode) {
                case SELECT_PIC_BY_PICK_PHOTO:
                    startPhotoZoom(data.getData());
                    break;
                case TAKE_PICTURE:
                    if (StorageUtil.hasSdcard()) {
                        File temp = new File(
                                Environment.getExternalStorageDirectory()
                                        + ConstantUtil.CROP_PATH);
                        startPhotoZoom(Uri.fromFile(temp));
                    } else {
                        showToast("未找到存储卡，无法存储照片！");
                    }

                    break;
                case IMAGE_REQUEST_CODE:
                    if (data != null) {
                        File picPath = new File(
                                Environment.getExternalStorageDirectory()
                                        + ConstantUtil.CROP_PATH);
                        this.picPath = Uri.fromFile(picPath).getPath();
                        if (RegexUtils.isNotBlankAndEmpty(this.picPath)) {
                            Bitmap bm = BitmapFactory.decodeFile(this.picPath);
                            eidtUserImg.setImageBitmap(bm);
                        }
                    } else {
                        showToast("没有图片数据");
                    }
                    break;

                default:
                    break;
            }
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.editUserImgRl://选择头像
                showTakePhotoOrAlbumDialog();

                break;
            case R.id.sexRl://选择性别
                ChoiceSexDialog();
                break;
            case R.id.birthdayRl://选择生日
                ChoiceBirthdayDialog();
                break;

        }
    }


    //6.0权限，接收回调，用户选择允许或拒绝后，会回调onRequestPermissionsResult方法
    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        //可在此继续其他操作。
        switch (requestCode) {
            case WRITE_ACCESS_CAMERA_REQUEST_CODE: {
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    //用户已授权
                } else {
                    //用户拒绝权限
                }
            }
            case WRITE_ACCESS_PHOTO_REQUEST_CODE: {
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    //用户已授权
                } else {
                    //用户拒绝权限
                }
            }
        }
    }


    @Override
    protected void onResume() {
        super.onResume();

    }
}
