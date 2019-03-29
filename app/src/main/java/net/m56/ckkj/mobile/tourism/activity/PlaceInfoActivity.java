package net.m56.ckkj.mobile.tourism.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RatingBar;
import android.widget.ScrollView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.gyf.barlibrary.ImmersionBar;

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

import butterknife.ButterKnife;
import de.hdodenhof.circleimageview.CircleImageView;
import okhttp3.Call;
import okhttp3.Request;
import okhttp3.Response;

public class PlaceInfoActivity extends BaseActivity {

    private static final int MSG = 1230;
    ImageView placeimage;
    TextView placename;
    RatingBar ratibar;
    TextView howfar;
    Button gogogo;
    Button post_comment;
    RecyclerView recyclerview;
    public List<String> commentMsgList;
    public List<Object> commentImgUrlList;
    public List<String> commentTimeList;
    public List<String> userNameList;
    public List<Object> userIconList;
    public String id;
    public String comment0;
    public double lat;
    public double lon;

    //页数
    int page = 1;
    //每页的个数
    int limit = 5;

    int pro = 0;
    public PlaceInfoActivity.myAdapter myAdapter;
    String photoDefaultPath = "android.resource://net.m56.ckkj.tourism.tourism/drawable/" + R.drawable.icon_kefu;
    public ScrollView scll;
    public ProgressBar probar;
    public Handler handler;


    @Override
    public void initVariable() {
        setContentView(R.layout.activity_place_info);
        ButterKnife.bind(this);
        ImmersionBar.with(this).statusBarColor(R.color.stabar_color).fitsSystemWindows(true).init();
        placeimage = (ImageView) findViewById(R.id.placeimage);
        placename = (TextView) findViewById(R.id.placename);
        ratibar = (RatingBar) findViewById(R.id.ratibar);
        howfar = (TextView) findViewById(R.id.howfar);
        gogogo = (Button) findViewById(R.id.gogogo);
        post_comment = (Button) findViewById(R.id.post_com);
        recyclerview = (RecyclerView) findViewById(R.id.recyclerview);
        scll = (ScrollView) findViewById(R.id.scrollview);

        scll.setOnTouchListener(new TouchListenerImpl());
        TextView toolemane = (TextView) findViewById(R.id.toolbar_title);
        toolemane.setText("地点详情");
        probar = (ProgressBar) findViewById(R.id.progress);
        String name = getIntent().getStringExtra("placename");
        String placeimageurl = getIntent().getStringExtra("placeimage");
        String placehowfar = getIntent().getStringExtra("placehowfar");
        lat = getIntent().getDoubleExtra("lat", 0);
        lon = getIntent().getDoubleExtra("lon", 0);


        handler = new Handler() {
            @Override
            public void handleMessage(Message msg) {
                super.handleMessage(msg);
                switch (msg.what) {
                    case MSG:
                        //设置滚动条和text的值
                        probar.setProgress(pro);

                        break;
                    default:
                        break;
                }
            }
        };
        Glide.with(getApplicationContext()).load(placeimageurl)
                .into(placeimage);

        placename.setText(name);
        howfar.setText(0.5 + "km");

        gogogo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(PlaceInfoActivity.this, GoToActivity.class);
                intent.putExtra("lont", lon);
                intent.putExtra("lant", lat);
                startActivity(intent);

            }
        });

        //周边景区 id  null  只有2
        id = getIntent().getStringExtra("id");
        final LinearLayoutManager layoutManager = new LinearLayoutManager(this) {
            @Override
            public boolean canScrollVertically() {
                return false;
            }
        };

        recyclerview.setLayoutManager(layoutManager);
        recyclerview.addItemDecoration(new SpacesItemDecoration(10));

        post_comment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent1 = new Intent(PlaceInfoActivity.this, CommentActivity.class);
                intent1.putExtra("spotid", "2");
                intent1.putExtra("committype", "周边");
                startActivity(intent1);
            }
        });
    }

    private class TouchListenerImpl implements View.OnTouchListener {
        @Override
        public boolean onTouch(View view, MotionEvent motionEvent) {
            switch (motionEvent.getAction()) {
                case MotionEvent.ACTION_DOWN:

                    break;
                case MotionEvent.ACTION_MOVE:
                    int scrollY = view.getScrollY();
                    int height = view.getHeight();
                    int scrollViewMeasuredHeight = scll.getChildAt(0).getMeasuredHeight();

                    if ((scrollY + height) == scrollViewMeasuredHeight) {
                        System.out.println("滑动到了底部 scrollY=" + scrollY);
                        System.out.println("滑动到了底部 height=" + height);
                        System.out.println("滑动到了底部 scrollViewMeasuredHeight=" + scrollViewMeasuredHeight);


                        page = page++;
                        limit = limit + 5;
                        onResume();
                        start();
                    }
                    break;

                default:
                    break;
            }
            return false;
        }

    }


    private void start() {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                probar.setVisibility(View.VISIBLE);
            }
        });
        new Thread(new Runnable() {
            @Override
            public void run() {
                int max = probar.getMax();
                try {
                    //子线程循环间隔消息
                    while (pro < 200) {
                        pro += 50;
                        Message msg = new Message();
                        msg.what = MSG;
                        handler.sendMessage(msg);
                        Thread.sleep(1000);
                    }
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            probar.setVisibility(View.INVISIBLE);
                        }
                    });

                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

    ;

    @Override
    protected void onResume() {
        super.onResume();

        Map<String, Object> map = new HashMap<>();
        map.put("type", "周边");
        map.put("id", "2");
        map.put("limit", limit + "");
        map.put("page", page);
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
                Log.e("userIconList", userIconList.toString());
                Log.e("commentImgUrlList", commentImgUrlList.toString());
                Log.e("userNameList", userNameList.toString());
                Log.e("commentMsgList", commentMsgList.toString());

                comment0 = (String) commentImgUrlList.get(0);
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
        public myAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(PlaceInfoActivity.this).inflate(R.layout.item_commit, parent, false);
            myAdapter.ViewHolder viewHolder = new myAdapter.ViewHolder(view);

            return viewHolder;
        }

        @Override
        public void onBindViewHolder(myAdapter.ViewHolder holder, int position) {

            Log.e("userIconList", userIconList.get(position) + "");
            String usericon = (String) userIconList.get(position);
            Glide.with(PlaceInfoActivity.this)
                    .load((usericon == null) ?
                            photoDefaultPath : usericon).into(holder.icon);
            holder.name.setText(userNameList.get(position));
            holder.data.setText(commentTimeList.get(position));
            holder.msg.setText(commentMsgList.get(position));

            if (commentImgUrlList.size() == 0) {
                holder.imgs.setVisibility(View.GONE);

            } else {
                holder.imgs.setVisibility(View.VISIBLE);
                if (commentImgUrlList.size() >= 1) {
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
                            Glide.with(PlaceInfoActivity.this).load("http://" + replace3).into(holder.img1);
                            Glide.with(PlaceInfoActivity.this).load("http://" + replace4).into(holder.img2);
                            Glide.with(PlaceInfoActivity.this).load("http://" + replace5).into(holder.img3);
                        } else if (split.length == 2) {
                            String url1 = split[0];
                            String url2 = split[1];
                            String replace3 = url1.replace("\"", "");
                            String replace4 = url2.replace("\"", "");
                            Glide.with(PlaceInfoActivity.this).load("http://" + replace3).into(holder.img1);
                            Glide.with(PlaceInfoActivity.this).load("http://" + replace4).into(holder.img2);
                            holder.img3.setVisibility(View.GONE);
                        } else if (split.length == 1) {
                            String url1 = split[0];
                            String replace3 = url1.replace("\"", "");
                            Glide.with(PlaceInfoActivity.this).load("http://" + replace3).into(holder.img1);
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
