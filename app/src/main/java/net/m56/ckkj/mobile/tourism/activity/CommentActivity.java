package net.m56.ckkj.mobile.tourism.activity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.os.Looper;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.luck.picture.lib.PictureSelector;
import com.luck.picture.lib.config.PictureConfig;
import com.luck.picture.lib.config.PictureMimeType;
import com.luck.picture.lib.entity.LocalMedia;

import net.m56.ckkj.mobile.tourism.Api.Url;
import net.m56.ckkj.mobile.tourism.base.activity.BaseActivity;
import net.m56.ckkj.mobile.tourism.bean.CommentResout;
import net.m56.ckkj.tourism.tourism.R;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import okhttp3.ResponseBody;

public class CommentActivity extends BaseActivity {

    private static final String TAG = "Message";
    EditText txt;
    ImageView add;
    Button post;
    //参数类型
    private static final MediaType MEDIA_TYPE_PNG = MediaType.parse("image/png");
    public String spotid;
    public Uri uri;
    public LinearLayout imgs;
    public List<LocalMedia> selectList;
    public List<String> mHashMap;
    public String path;
    public ImageView imgs1;
    public ImageView imgs2;
    public ImageView imgs3;
    public String committype;

    @Override
    public void initVariable() {
        setContentView(R.layout.activity_comment);
        ButterKnife.bind(this);
        txt =(EditText) findViewById(R.id.txt);
        add =(ImageView) findViewById(R.id.add);
        post =(Button) findViewById(R.id.post);
        imgs = (LinearLayout) findViewById(R.id.imgs);
        TextView toolBarName = (TextView) findViewById(R.id.toolbar_title);
        toolBarName.setText("评论");
        spotid = getIntent().getStringExtra("spotid");
        //接口的type
        committype = getIntent().getStringExtra("committype");
        imgs1 = (ImageView) findViewById(R.id.img1);
        imgs2 = (ImageView) findViewById(R.id.img2);
        imgs3 = (ImageView) findViewById(R.id.img3);

    }

    @Override
    public void toLoad(Bundle savedInstanceState) {

        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                add.setVisibility(View.GONE);

                imgs1.setImageDrawable(null);
                imgs2.setImageDrawable(null);
                imgs3.setImageDrawable(null);
                //图片选择器的
                PictureSelector.create(CommentActivity.this)
                        .openGallery(PictureMimeType.ofAll())
                        .maxSelectNum(3)
                        .minSelectNum(0)
                        .imageSpanCount(3)
                        .selectionMode(PictureConfig.MULTIPLE)// 多选 or 单选 PictureConfig.MULTIPLE or PictureConfig.SINGLE
                        .previewImage(true)
                        .isCamera(true)
                        .enableCrop(true)
                        .compress(true)
                        .forResult(PictureConfig.CHOOSE_REQUEST);

            }
        });
        post.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mHashMap = new ArrayList<>();

                //
                if (selectList!=null) {
                    for (int i = 0; i < selectList.size(); i++) {
                        String path = selectList.get(i).getCompressPath();
                        mHashMap.add(path);
                    }
                    Log.e(TAG,"selectList.size:"+selectList.size());
                    Log.e(TAG,"mHashMap:"+mHashMap.toString());
                }
                OkHttpClient okHttpClient = new OkHttpClient();
                MultipartBody.Builder builder = new MultipartBody.Builder().setType(MultipartBody.FORM);
                //mHashMap是图片path的集合
                if (mHashMap != null&&selectList!=null) {
                    for (int i = 0; i < selectList.size(); i++) {
                        File f = new File(mHashMap.get(i));
                        if (f == null) break;
                        try {
                            BitmapFactory.Options options = new BitmapFactory.Options();
                            options.inPreferredConfig = Bitmap.Config.RGB_565;
                            Bitmap bm = BitmapFactory.decodeFile(f.getAbsolutePath(), options);

                            bm.compress(Bitmap.CompressFormat.JPEG, 90, new FileOutputStream(f));
                            bm.recycle();
                        } catch (FileNotFoundException e) {
                            e.printStackTrace();
                        }
                        builder.addFormDataPart("img[]",f.getName(),RequestBody.create(MEDIA_TYPE_PNG,f));
                    }
                }
                if (txt.getText().toString().equals("")){
                    showToast("评论内容不能为空");
                    return;
                }
                builder.addFormDataPart("type", committype);
                builder.addFormDataPart("id", spotid);
                builder.addFormDataPart("cont", txt.getText().toString());
                builder.addFormDataPart("userid", "2");
                MultipartBody requesBody = builder.build();

                Request request = new Request.Builder().url(Url.PostCommitUrl)
                        .post(requesBody).build();
                Call call = okHttpClient.newCall(request);
                call.enqueue(new Callback() {
                    @Override
                    public void onFailure(Call call, IOException e) {

                    }

                    @Override
                    public void onResponse(Call call, Response response) throws IOException {
                        ResponseBody body = response.body();
                        String s = body.string().toString();
                        Log.e("response", s);
                        Gson gson = new Gson();
                        CommentResout commentResout = gson.fromJson(s, CommentResout.class);
                        if (commentResout.code==0){
                            new Thread(new Runnable() {
                                @Override
                                public void run() {
                                    Looper.prepare();
                                    Toast.makeText(CommentActivity.this, "发表评论成功", Toast.LENGTH_SHORT).show();
                                    Looper.loop();
                                }
                            }).start();
                            finish();
                        }
                    }
                });
            }
        });

    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            switch (requestCode) {
                case PictureConfig.CHOOSE_REQUEST:
                    // 图片选择结果回调
                    selectList = PictureSelector.obtainMultipleResult(data);
                    ArrayList<String> strings = new ArrayList<>();
                    for (int i = 0; i < selectList.size(); i++) {
                        LocalMedia localMedia = selectList.get(i);
                        path = localMedia.getPath();
                        strings.add(path);
                    }
                    if (strings.size()==1) {
                        ImageView imageView = (ImageView) findViewById(R.id.img1);
                        imageView.setImageDrawable(Drawable.createFromPath(strings.get(0)));
                        imageView.setVisibility(View.VISIBLE);
                    }else if(strings.size()==2){
                        ImageView imageView1 = (ImageView) findViewById(R.id.img1);
                        imageView1.setImageDrawable(Drawable.createFromPath(strings.get(0)));
                        imageView1.setVisibility(View.VISIBLE);
                        ImageView imageView2 = (ImageView) findViewById(R.id.img2);
                        imageView2.setImageDrawable(Drawable.createFromPath(strings.get(1)));
                        imageView2.setVisibility(View.VISIBLE);
                    }else if(strings.size()==3){
                        ImageView imageView1 = (ImageView) findViewById(R.id.img1);
                        imageView1.setImageDrawable(Drawable.createFromPath(strings.get(0)));
                        imageView1.setVisibility(View.VISIBLE);
                        ImageView imageView2 = (ImageView) findViewById(R.id.img2);
                        imageView2.setImageDrawable(Drawable.createFromPath(strings.get(1)));
                        imageView2.setVisibility(View.VISIBLE);
                        ImageView imageView3 = (ImageView) findViewById(R.id.img3);
                        imageView3.setImageDrawable(Drawable.createFromPath(strings.get(2)));
                        imageView3.setVisibility(View.VISIBLE);
                    }
                    // 例如 LocalMedia 里面返回三种path
                    // 1.media.getPath(); 为原图path
                    // 2.media.getCutPath();为裁剪后path，需判断media.isCut();是否为true
                    // 3.media.getCompressPath();为压缩后path，需判断media.isCompressed();是否为true
                    break;
            }
        }
    }


}
