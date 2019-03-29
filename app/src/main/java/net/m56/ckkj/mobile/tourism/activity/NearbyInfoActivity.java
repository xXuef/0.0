package net.m56.ckkj.mobile.tourism.activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RatingBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.gyf.barlibrary.ImmersionBar;
import com.ms.square.android.expandabletextview.ExpandableTextView;

import net.m56.ckkj.mobile.tourism.Api.Url;
import net.m56.ckkj.mobile.tourism.base.activity.BaseActivity;
import net.m56.ckkj.mobile.tourism.bean.CommentBean;
import net.m56.ckkj.mobile.tourism.okhttputil.BaseCallback;
import net.m56.ckkj.mobile.tourism.okhttputil.OkHttpHelper;
import net.m56.ckkj.mobile.tourism.util.SpacesItemDecoration;
import net.m56.ckkj.tourism.tourism.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import de.hdodenhof.circleimageview.CircleImageView;
import okhttp3.Call;
import okhttp3.Request;
import okhttp3.Response;

public class NearbyInfoActivity extends BaseActivity  {

    private static final String TAG = "message";
    ImageView placeimage;
    TextView placename;
    RatingBar ratingbar;
    TextView location;
    RelativeLayout toca;
    TextView qi;
    TextView price;
    Button booking;
    RecyclerView recyclerview;
    public List<String> commentMsgList;
    public List<Object> commentImgUrlList;
    public List<String> commentTimeList;
    public List<String> userNameList;
    public List<Object> userIconList;

    public String id;
    Button postComment;
    String photoDefaultPath = "android.resource://net.m56.ckkj.tourism.tourism/drawable/" + R.drawable.icon_kefu;
    public String lons;
    public String lats;
    public NearbyInfoActivity.myAdapter myAdapter;

    @Override
    public void initVariable() {
        setContentView(R.layout.activity_nearby_info);
        //设置沉浸式状态栏
        ImmersionBar.with(this).statusBarColor(R.color.stabar_color).fitsSystemWindows(true).init();
        placeimage = (ImageView) findViewById(R.id.placeimage);
        placename = (TextView)findViewById(R.id.placename);
        location = (TextView)findViewById(R.id.location);
        qi = (TextView)findViewById(R.id.qi);
        price = (TextView)findViewById(R.id.price);
        price = (TextView)findViewById(R.id.price);
        ratingbar = (RatingBar)findViewById(R.id.ratingbar);
        toca = (RelativeLayout)findViewById(R.id.toca);
        booking = (Button)findViewById(R.id.booking);
        recyclerview = (RecyclerView)findViewById(R.id.recyclerview);
        postComment = (Button)findViewById(R.id.post_comment);

        placeimage.setFocusable(true);
        placeimage.setFocusableInTouchMode(true);
        placeimage.requestFocus();

        TextView titlename = (TextView) findViewById(R.id.toolbar_title);
        titlename.setText("景区详情");
        String name = getIntent().getStringExtra("name");
        String imageurl = getIntent().getStringExtra("imageurl");
        String desc = getIntent().getStringExtra("desc");
        String adress = getIntent().getStringExtra("adress");
        lons = getIntent().getStringExtra("lon");
        lats = getIntent().getStringExtra("lat");
        Log.e("imageurl", imageurl + "123");
        //net.m56.ckkj.tourism.tourism   com.frank.glide

        Glide.with(this).load(imageurl).into(placeimage);
        placename.setText(name);

        ExpandableTextView expTv1 = (ExpandableTextView) findViewById(R.id.expand_text_view);
        expTv1.setText("    " + desc);
        location.setText(adress);

        //景区id
        id = getIntent().getStringExtra("id");
        LinearLayoutManager layoutManager = new LinearLayoutManager(this) {
            @Override
            public boolean canScrollVertically() {
                return false;
            }
        };
        recyclerview.setLayoutManager(layoutManager);
        recyclerview.addItemDecoration(new SpacesItemDecoration(10));

    }

    @Override
    protected void onResume() {
        super.onResume();


        Map<String, Object> map = new HashMap<>();
        map.put("type", "景区");
        map.put("id", id + "");
        map.put("limit", "20");
        map.put("page", "1");
        OkHttpHelper.getInstance().post(Url.CommitUrl, map, new BaseCallback<CommentBean>() {
            @Override
            public void onBeforeRequest(Request request) {
            }

            @Override
            public void onFailure(Call call, Exception e) {
            }

            @Override
            public void onResponse(Response response) {
            }

            @Override
            public void onSuccess(Response response, CommentBean commentBean) {
                userIconList = new ArrayList<>();
                userNameList = new ArrayList<>();
                commentTimeList = new ArrayList<>();
                commentImgUrlList = new ArrayList<>();
                commentMsgList = new ArrayList<>();

                for (int i = 0; i < commentBean.message.rows.size(); i++) {
                    Object usericon = commentBean.message.rows.get(i).usericon;
                    String nickname = commentBean.message.rows.get(i).nickname;

                    String commenttime = commentBean.message.rows.get(i).commenttime;
                    Object imgUrl = commentBean.message.rows.get(i).imgUrl;
                    String commentmsg = commentBean.message.rows.get(i).commentmsg;

                    userIconList.add(usericon);
                    userNameList.add(nickname);
                    commentTimeList.add(commenttime);
                    commentImgUrlList.add(imgUrl);
                    commentMsgList.add(commentmsg);
                }

                Log.e(TAG, commentImgUrlList.toString());
                myAdapter = new myAdapter();
                recyclerview.setAdapter(myAdapter);
            }

            @Override
            public void onError(Response response, int code, Exception e) {
            }

            @Override
            public void onTokenError(Response response, int code) {
            }
        });
    }

    @Override
    public void toLoad(Bundle savedInstanceState) {
        toca.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(NearbyInfoActivity.this, MapActivity.class);
                intent.putExtra("lonss", lons);
                intent.putExtra("latss", lats);
                startActivity(intent);
            }
        });
        postComment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences logininfo = getSharedPreferences("logininfo", MODE_PRIVATE);
                boolean islogin = logininfo.getBoolean("islogin", false);
                if(islogin) {
                    Intent intent1 = new Intent(NearbyInfoActivity.this, CommentActivity.class);
                    intent1.putExtra("spotid", id);
                    intent1.putExtra("committype", "景区");
                    startActivity(intent1);
                }else{
                    showToast("请先登录");
                }
            }
        });


    }

    @Override
    protected synchronized void onDestroy() {
        super.onDestroy();
        ImmersionBar.with(this).destroy();
    }



    class myAdapter extends RecyclerView.Adapter<myAdapter.ViewHolder> {

        class ViewHolder extends RecyclerView.ViewHolder {
            CircleImageView icon;
            TextView name;
            TextView data;
            TextView msg;
            LinearLayout imgs;
            ImageView img1;
            ImageView img2;
            ImageView img3;

            public ViewHolder(View itemView) {
                super(itemView);
                icon = (CircleImageView) itemView.findViewById(R.id.icon);
                name = (TextView) itemView.findViewById(R.id.name);
                data = (TextView) itemView.findViewById(R.id.data);
                msg = (TextView) itemView.findViewById(R.id.msg);
                imgs = (LinearLayout) itemView.findViewById(R.id.imgs);
                img1 = (ImageView) itemView.findViewById(R.id.img1);
                img2 = (ImageView) itemView.findViewById(R.id.img2);
                img3 = (ImageView) itemView.findViewById(R.id.img3);
            }
        }

        @Override
        public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(NearbyInfoActivity.this).inflate(R.layout.item_commit, parent, false);
            ViewHolder viewHolder = new ViewHolder(view);

            return viewHolder;
        }

        @Override
        public void onBindViewHolder(ViewHolder holder, int position) {

            Log.e("userIconList", userIconList.get(position) + "");
            String usericon = (String) userIconList.get(position);
            Glide.with(NearbyInfoActivity.this)
                    .load((usericon==null) ? photoDefaultPath : usericon)
                    .into(holder.icon);
            holder.name.setText((userNameList.get(position)==null)?"史蒂夫":userNameList.get(position));
            holder.data.setText(commentTimeList.get(position));
            holder.msg.setText(commentMsgList.get(position));
            if (commentImgUrlList != null) {
                Log.e(TAG, commentMsgList.toString());
            }
            if (commentImgUrlList.size() == 0) {
                holder.imgs.setVisibility(View.GONE);
            } else {
                holder.imgs.setVisibility(View.VISIBLE);
                if (commentImgUrlList.size() == 1) {
                    String ss = (String) commentImgUrlList.get(position);
                    if (ss == null) {
                        holder.imgs.setVisibility(View.GONE);
                        return;
                    } else {

                        String imgs = (String) commentImgUrlList.get(position);
                        Log.e("commentImgUrlList1", imgs);
                        String replace = imgs.replace("[", "");//去掉[ ]
                        String replace1 = replace.replace("]", "");
//                        "123.57.247.239\/smart_tourism\/public\/uploads\/comment\/20171025\/ddccd20e116e944bfc1addb01dba84de.jpg"
                        String replace2 = replace1.replace("\\", "");//去掉里面的\
                        String[] split = replace2.split(",");
                        Log.e("splitlenth", split.length + "");
                        if (split.length == 3) {
//                        "123.57.247.239/smart_tourism/public/uploads/comment/20171025/ddccd20e116e944bfc1addb01dba84de.jpg"
                            String url1 = split[0];
                            Log.e("url1", url1);
                            String url2 = split[1];
                            String url3 = split[2];
                            String replace3 = url1.replace("\"", "");
                            String replace4 = url2.replace("\"", "");
                            String replace5 = url3.replace("\"", "");//去掉符号"
                            Glide.with(NearbyInfoActivity.this).load("http://" + replace3).into(holder.img1);
                            Glide.with(NearbyInfoActivity.this).load("http://" + replace4).into(holder.img2);
                            Glide.with(NearbyInfoActivity.this).load("http://" + replace5).into(holder.img3);
                        } else if (split.length == 2) {
                            String url1 = split[0];
                            String url2 = split[1];
                            String replace3 = url1.replace("\"", "");
                            String replace4 = url2.replace("\"", "");
                            Glide.with(NearbyInfoActivity.this).load("http://" + replace3).into(holder.img1);
                            Glide.with(NearbyInfoActivity.this).load("http://" + replace4).into(holder.img2);
                            holder.img3.setVisibility(View.GONE);
                        } else if (split.length == 1) {
                            String url1 = split[0];
                            String replace3 = url1.replace("\"", "");
                            Glide.with(NearbyInfoActivity.this).load("http://" + replace3).into(holder.img1);
                            holder.img2.setVisibility(View.GONE);
                            holder.img3.setVisibility(View.GONE);
                        }
                    }
                } else if (commentImgUrlList.size() > 1) {
                    String s = (String) commentImgUrlList.get(position);
                    if (s == null) {
                        holder.imgs.setVisibility(View.GONE);
                        return;
                    } else {
                        String imgs = (String) commentImgUrlList.get(position);
                        Log.e("commentImgUrlList1", imgs);
                        String replace = imgs.replace("[", "");//去掉[ ]
                        String replace1 = replace.replace("]", "");
//                        "123.57.247.239\/smart_tourism\/public\/uploads\/comment\/20171025\/ddccd20e116e944bfc1addb01dba84de.jpg"
                        String replace2 = replace1.replace("\\", "");//去掉里面的\
                        String[] split = replace2.split(",");
                        Log.e("splitlenth", split.length + "");
                        if (split.length == 3) {
//                        "123.57.247.239/smart_tourism/public/uploads/comment/20171025/ddccd20e116e944bfc1addb01dba84de.jpg"
                            String url1 = split[0];
                            Log.e("url1", url1);
                            String url2 = split[1];
                            String url3 = split[2];
                            String replace3 = url1.replace("\"", "");
                            String replace4 = url2.replace("\"", "");
                            String replace5 = url3.replace("\"", "");//去掉符号"
                            Glide.with(NearbyInfoActivity.this).load("http://" + replace3).into(holder.img1);
                            Glide.with(NearbyInfoActivity.this).load("http://" + replace4).into(holder.img2);
                            Glide.with(NearbyInfoActivity.this).load("http://" + replace5).into(holder.img3);
                        } else if (split.length == 2) {
                            String url1 = split[0];
                            String url2 = split[1];
                            String replace3 = url1.replace("\"", "");
                            String replace4 = url2.replace("\"", "");
                            Glide.with(NearbyInfoActivity.this).load("http://" + replace3).into(holder.img1);
                            Glide.with(NearbyInfoActivity.this).load("http://" + replace4).into(holder.img2);
                            holder.img3.setVisibility(View.GONE);
                        } else if (split.length == 1) {
                            String url1 = split[0];
                            String replace3 = url1.replace("\"", "");
                            Glide.with(NearbyInfoActivity.this).load("http://" + replace3).into(holder.img1);
                            holder.img2.setVisibility(View.GONE);
                            holder.img3.setVisibility(View.GONE);
                        }
                    }

                }
            }
        }

        @Override
        public int getItemCount() {
            return userNameList.size();
        }
    }
}
