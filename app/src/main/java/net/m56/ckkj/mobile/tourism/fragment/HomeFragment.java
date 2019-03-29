package net.m56.ckkj.mobile.tourism.fragment;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.paradoxie.autoscrolltextview.VerticalTextview;

import net.m56.ckkj.mobile.tourism.Api.Url;
import net.m56.ckkj.mobile.tourism.activity.DtActivity;
import net.m56.ckkj.mobile.tourism.activity.FyActivity;
import net.m56.ckkj.mobile.tourism.activity.JcActivity;
import net.m56.ckkj.mobile.tourism.activity.JqActivity;
import net.m56.ckkj.mobile.tourism.activity.NearbyInfoActivity;
import net.m56.ckkj.mobile.tourism.activity.NewsInfoActivity;
import net.m56.ckkj.mobile.tourism.activity.ViewPagerWebActivity;
import net.m56.ckkj.mobile.tourism.activity.WebViewActivity;
import net.m56.ckkj.mobile.tourism.base.activity.CaptureActivity;
import net.m56.ckkj.mobile.tourism.base.farment.BaseFragment;
import net.m56.ckkj.mobile.tourism.bean.DataBanner;
import net.m56.ckkj.mobile.tourism.bean.ListBean;
import net.m56.ckkj.mobile.tourism.okhttputil.BaseCallback;
import net.m56.ckkj.mobile.tourism.okhttputil.OkHttpHelper;
import net.m56.ckkj.mobile.tourism.util.SpacesItemDecoration;
import net.m56.ckkj.tourism.tourism.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import okhttp3.Call;
import okhttp3.Request;
import okhttp3.Response;

/**
 * 2017/10/10 10:39
 */

public class HomeFragment extends BaseFragment implements View.OnClickListener {


    ImageView erweima;
    ImageView xiaoxi;
    ViewPager viewpager;
    LinearLayout point;
    LinearLayout jiaocheng;
    LinearLayout ditu;
    LinearLayout jingqu;
    LinearLayout feiyi;
    LinearLayout laayout;
    RecyclerView recyclerview;
    public ArrayList<ImageView> imgs;
    int[] imgids = new int[]{R.mipmap.banner0, R.mipmap.banner1,
            R.mipmap.banner2};

    public LinearLayout points;
    private boolean isRun = true;
    public VerticalTextview verticalTextview;
    ArrayList<ImageView> imageViewArrayList;
    ArrayList<String> UrlArrayList;
    ArrayList<String> webArrayList;
    ArrayList<String> lonArrayList;
    ArrayList<String> latArrayList;

    ArrayList<String> soptImgUrlArrayList;
    ArrayList<String> soptNameArrayList;
    ArrayList<String> soptPriceArrayList;
    ArrayList<String> recommindexArrayList;
    ArrayList<String> describitionArrayList;
    ArrayList<String> adressArrayList;
    ArrayList<String> idArrayList;
    //变化的textview的文字集合
    ArrayList<String> textArrayList;

    private Handler handler = new Handler() {

        public void handleMessage(Message msg) {
            if (!isRun) {
                return;
            }
            // 把ViewPager切换到下一页
            //Log.d(TAG, "handleMessage: " + viewpager.getCurrentItem());
            if (viewpager.getCurrentItem() == UrlArrayList.size() - 1)
                viewpager.setCurrentItem(0);
            else
                viewpager.setCurrentItem(viewpager.getCurrentItem() + 1);
            // 再次发送消息
            handler.sendMessageDelayed(Message.obtain(), 3000);
        }
    };
    public Context context;

    @Override
    protected int getLayoutId() {
        return R.layout.homefragment;
    }


    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);

    }

    @Override
    protected void initParams(View view, Bundle savedInstanceState) {

        context = getActivity();
        xiaoxi = (ImageView) view.findViewById(R.id.xiaoxi);
        erweima = (ImageView) view.findViewById(R.id.erweima);
        point = (LinearLayout) view.findViewById(R.id.point);
        jiaocheng = (LinearLayout) view.findViewById(R.id.jiaocheng);
        ditu = (LinearLayout) view.findViewById(R.id.ditu);
        jingqu = (LinearLayout) view.findViewById(R.id.jingqu);
        feiyi = (LinearLayout) view.findViewById(R.id.feiyi);
        laayout = (LinearLayout) view.findViewById(R.id.laayout);
        recyclerview = (RecyclerView) view.findViewById(R.id.recyclerview);



        TextView textView = (TextView) view.findViewById(R.id.title_name);
        textView.setText("山水交城");
        viewpager = (ViewPager) view.findViewById(R.id.viewpager);
//        titleName.setText("山水交城");
        points = (LinearLayout) view.findViewById(R.id.point);


        //监听viewpager滑动事件
        viewpager.setOnPageChangeListener(new MyOnPageChangeListion());
        textArrayList = new ArrayList<>();
        textArrayList.add("多水多情，魅力交城");
        textArrayList.add("多水多情");
        textArrayList.add("魅力交城");
        textArrayList.add("魅力交城,多水多情");

        verticalTextview = (VerticalTextview) view.findViewById(R.id.vertext);
        verticalTextview.setTextList(textArrayList);
        verticalTextview.setText(14, 0, Color.BLACK);
        verticalTextview.setTextStillTime(3000);
        verticalTextview.startAutoScroll();
        verticalTextview.setAnimTime(200);
        verticalTextview.setOnItemClickListener(new VerticalTextview.OnItemClickListener() {
            @Override
            public void onItemClick(int i) {
                Intent intent = new Intent(getContext(), WebViewActivity.class);

                intent.putExtra("name", "公告");
                intent.putExtra("url", "http://123.57.247.239/android/smart_tourism/banner/web_view/view1.html");
                startActivity(intent);
            }
        });

        xiaoxi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(context, NewsInfoActivity.class));
            }
        });
        erweima.setOnClickListener(this);
        ditu.setOnClickListener(this);
        jiaocheng.setOnClickListener(this);
        jingqu.setOnClickListener(this);
        feiyi.setOnClickListener(this);

        //解决进去默认不在顶部的方法
        viewpager.setFocusable(true);
        viewpager.setFocusableInTouchMode(true);
        viewpager.requestFocus();

        //禁止recyclerview滑动
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false) {
            @Override
            public boolean canScrollVertically() {
                return false;
            }
        };

        //设置布局管理器
        recyclerview.setLayoutManager(layoutManager);
        //设置条目的间隔
        recyclerview.addItemDecoration(new SpacesItemDecoration(15));


        Map<String, Object> map = new HashMap<>();
//        Log.d("okhttp", "onCreateView: " + Url.BananUrl);
        OkHttpHelper.getInstance().post(Url.BananUrl, map, new BaseCallback<DataBanner>() {
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
            public void onSuccess(Response response, DataBanner dataBanner) {
//                Log.d("okhttp", "onSuccess: ");
                ImageView imageView = null;
                int size = dataBanner.message.rows.size();
                imageViewArrayList = new ArrayList<>();
                UrlArrayList = new ArrayList<>();
                webArrayList = new ArrayList<>();
                for (int i = 0; i < dataBanner.message.count; i++) {
                    String imgUrl = dataBanner.message.rows.get(i).imgUrl;
                    String webUrl = dataBanner.message.rows.get(i).getWebUrl();
                    UrlArrayList.add(imgUrl);
                    webArrayList.add(webUrl);
                }


                //使用handler发送延时消息
                handler.sendMessageDelayed(Message.obtain(), 3000);

                imgs = new ArrayList<>();
                for (int i = 0; i < imgids.length; i++) {
                    ImageView imageiew = new ImageView(context);
                    imageiew.setBackgroundResource(imgids[i]);
                    imgs.add(imageiew);
                    //在这里创建点
                    ImageView point = new ImageView(context);
                    point.setBackgroundResource(R.mipmap.black);

                    // 把点添加到线性布局
                    points.addView(point);
                    //设置点之前的差距
                    LinearLayout.LayoutParams params = (LinearLayout.LayoutParams) point.getLayoutParams();// 需要导报为父容器对应的LayoutParams
                    params.leftMargin = 5;
                    point.setLayoutParams(params);

                }
                //初始化第0个白点
                points.getChildAt(0).setBackgroundResource(R.mipmap.white);
                viewpager.setAdapter(new Myadapter());


            }

            @Override
            public void onError(Response response, int code, Exception e) {
            }

            @Override
            public void onTokenError(Response response, int code) {
            }
        });

        Map<String, Object> map1 = new HashMap<>();
        OkHttpHelper.getInstance().post(Url.ListUrl, map1, new BaseCallback<ListBean>() {
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
            public void onSuccess(Response response, ListBean listBean) {
                soptImgUrlArrayList = new ArrayList<>();
                soptNameArrayList = new ArrayList<>();
                soptPriceArrayList = new ArrayList<>();
                recommindexArrayList = new ArrayList<>();
                describitionArrayList = new ArrayList<>();
                adressArrayList = new ArrayList<>();
                idArrayList = new ArrayList<>();
                lonArrayList = new ArrayList<>();
                latArrayList = new ArrayList<>();

                for (int i = 0; i < listBean.message.count; i++) {
                    String soptimg = listBean.message.rows.get(i).spotimg;
                    String spotname = listBean.message.rows.get(i).spotname;
                    String spotprice = listBean.message.rows.get(i).spotprice;
                    String recommindex = listBean.message.rows.get(i).recommindex;
                    //地点描述
                    String describtion = listBean.message.rows.get(i).spotdescribtion;
                    String adress = listBean.message.rows.get(i).address;
                    String id = listBean.message.rows.get(i).id;
                    String longitude = listBean.message.rows.get(i).longitude;
                    String latitude = listBean.message.rows.get(i).latitude;

                    lonArrayList.add(longitude);
                    latArrayList.add(latitude);
                    soptImgUrlArrayList.add(soptimg);
                    soptNameArrayList.add(spotname);
                    soptPriceArrayList.add(spotprice);
                    recommindexArrayList.add(recommindex);
                    describitionArrayList.add(describtion);
                    adressArrayList.add(adress);
                    idArrayList.add(id);
                    lonArrayList.add(longitude);
                    latArrayList.add(latitude);
                }
                recyclerview.setAdapter(new MyRecyclerviewAdapter());
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
    public void onDestroyView() {
        super.onDestroyView();
        isRun = false;
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
    }

    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.erweima:
                startActivity(new Intent(getActivity(), CaptureActivity.class));
                break;
            case R.id.jiaocheng:
                startActivity(new Intent(getContext(), JcActivity.class));
                break;
            case R.id.ditu:
                startActivity(new Intent(getContext(), DtActivity.class));
                break;
            case R.id.jingqu:
                startActivity(new Intent(getContext(), JqActivity.class));
                break;
            case R.id.feiyi:
                startActivity(new Intent(getContext(), FyActivity.class));
                break;
        }
    }


    class MyRecyclerviewAdapter extends RecyclerView.Adapter<MyRecyclerviewAdapter.MyViewHolder> {
        @Override
        public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

            MyViewHolder holder = new MyViewHolder(LayoutInflater.from(
                    parent.getContext()).inflate(R.layout.item_home, parent,
                    false));
            return holder;
        }

        @Override
        public void onBindViewHolder(MyViewHolder holder, final int position) {

            if (position == 0) {
                holder.zuire.setVisibility(View.VISIBLE);
            }
            holder.diming.setText(soptNameArrayList.get(position));
            //设置地名图片
            Glide.with(getContext()).load(soptImgUrlArrayList.get(position)).into(holder.dimingtupian);
            //设置价格
            holder.jiage.setText(soptPriceArrayList.get(position));
            //设置人流量
            holder.renliuliang.setText(recommindexArrayList.get(position) + "人去过");
            holder.itemView.setTag(position);

            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(getContext(), NearbyInfoActivity.class);
                    intent.putExtra("name", soptNameArrayList.get(position));
                    intent.putExtra("imageurl", soptImgUrlArrayList.get(position));
                    intent.putExtra("desc", describitionArrayList.get(position));
                    intent.putExtra("adress", adressArrayList.get(position));
                    intent.putExtra("id", idArrayList.get(position));
                    intent.putExtra("lon", lonArrayList.get(position));
                    intent.putExtra("lat", latArrayList.get(position));
                    startActivity(intent);
                }
            });

        }

        @Override
        public int getItemCount() {
            return soptNameArrayList.size();
        }

        class MyViewHolder extends RecyclerView.ViewHolder {
            ImageView zuire;
            TextView diming;
            TextView jiage;
            TextView renliuliang;
            ImageView dimingtupian;

            public MyViewHolder(View itemView) {
                super(itemView);

                zuire = (ImageView) itemView.findViewById(R.id.zuire);
                diming = (TextView) itemView.findViewById(R.id.diming);
                jiage = (TextView) itemView.findViewById(R.id.jiage);
                renliuliang = (TextView) itemView.findViewById(R.id.reliuliang);
                dimingtupian = (ImageView) itemView.findViewById(R.id.dimingtupan);
            }
        }
    }


    class Myadapter extends PagerAdapter {


        // 总共的条目个数
        @Override
        public int getCount() {
            return UrlArrayList.size();//imgs.size();设置一个比较大的值，往右无限滑动
        }

        // 当前条目展示的控件
        // object是instantiateItem返回的对象
        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view == object;
        }

        @Override
        public Object instantiateItem(final ViewGroup container, final int position) {

            View v = LayoutInflater.from(getContext()).inflate(R.layout.viewpageritem, null);
            ImageView igv = (ImageView) v.findViewById(R.id.imagview);

            // Log.d(TAG, "instantiateItem: " + UrlArrayList.get(position));
            Glide.with(getContext()).load(UrlArrayList.get(position)).into(igv);
            container.addView(v);

            container.getChildAt(position).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(getContext(), ViewPagerWebActivity.class);
                    intent.putExtra("Viewpager0", webArrayList.get(position));
                    intent.putExtra("titlename", "头条");
                    startActivity(intent);
                }
            });

            return v;
        }


        // 销毁条目
        // 根据instantiateItem返回的对象删除条目
        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
//            System.out.println("destroyItem:" + position);
            // 把与当前显示的条目不相邻的控件删除
            container.removeView((View) object);//container就是ViewPager
        }
    }

    int preWhitePosition = 0;// 记录上一个白点的位置

    //监听滑动事件
    class MyOnPageChangeListion implements ViewPager.OnPageChangeListener {

        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

        }

        @Override
        public void onPageSelected(int position) {
            position = position % imgs.size();
            // 先把之前的白点变黑色
            points.getChildAt(preWhitePosition).setBackgroundResource(R.mipmap.black);

            // 当滑动到某一页时，把当前位置的点变白色
            points.getChildAt(position).setBackgroundResource(R.mipmap.white);
            // 记录白点的位置
            preWhitePosition = position;
        }

        @Override
        public void onPageScrollStateChanged(int state) {

        }
    }
}
