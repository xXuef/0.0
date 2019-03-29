package net.m56.ckkj.mobile.tourism.activity;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.gyf.barlibrary.ImmersionBar;

import net.m56.ckkj.mobile.tourism.Api.Url;
import net.m56.ckkj.mobile.tourism.base.activity.BaseActivity;
import net.m56.ckkj.mobile.tourism.bean.GoodFoodBean;
import net.m56.ckkj.mobile.tourism.okhttputil.BaseCallback;
import net.m56.ckkj.mobile.tourism.okhttputil.OkHttpHelper;
import net.m56.ckkj.tourism.tourism.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import butterknife.ButterKnife;
import okhttp3.Call;
import okhttp3.Request;
import okhttp3.Response;

public class HappyActivity extends BaseActivity implements View.OnClickListener{


    LinearLayout fordistance;
    LinearLayout formoney;
    LinearLayout forhots;
    RecyclerView recyclerview;

    ArrayList<String> imageList;
    ArrayList<String> nameList;
    ArrayList<String> priceList;
    ArrayList<Double> howfarList;
    ArrayList<Integer> howpepoleList;
    public HappyActivity.myAdapter myAdapter;


    @Override
    protected synchronized void onDestroy() {
        super.onDestroy();
        ImmersionBar.with(this).destroy();
    }

    @Override
    public void initVariable() {
        setContentView(R.layout.activity_happy);
        ButterKnife.bind(this);
        fordistance = (LinearLayout)findViewById(R.id.fordistance);
        formoney = (LinearLayout)findViewById(R.id.formoney);
        forhots = (LinearLayout)findViewById(R.id.forhots);
        fordistance.setOnClickListener(this);
        forhots.setOnClickListener(this);
        formoney.setOnClickListener(this);
        recyclerview = (RecyclerView)findViewById(R.id.recyclerview);
        //设置沉浸式状态栏
        ImmersionBar.with(this).statusBarColor(R.color.stabar_color).fitsSystemWindows(true).init();
        TextView textView = (TextView) findViewById(R.id.toolbar_title);
        textView.setText("娱乐");
    }

    @Override
    public void toLoad(Bundle savedInstanceState) {
        recyclerview.setLayoutManager(new LinearLayoutManager(HappyActivity.this));
        Map<String, Object> map = new HashMap<>();
        map.put("type", "娱乐");
        OkHttpHelper.getInstance().post(Url.NearFourUrl, map, new BaseCallback<GoodFoodBean>() {
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
            public void onSuccess(Response response, GoodFoodBean goodFoodBean) {
                imageList = new ArrayList<String>();
                nameList = new ArrayList<String>();
                priceList = new ArrayList<String>();
                howfarList = new ArrayList<Double>();
                howpepoleList = new ArrayList<Integer>();
                for (int i = 0; i < goodFoodBean.message.count; i++) {
                    String shopimg = goodFoodBean.message.rows.get(i).shopimg;
                    String shopname = goodFoodBean.message.rows.get(i).shopname;
                    String shopprice = goodFoodBean.message.rows.get(i).shopprice;
                    double distance = goodFoodBean.message.rows.get(i).distance;
                    int recommindex = goodFoodBean.message.rows.get(i).recommindex;
                    imageList.add(shopimg);
                    nameList.add(shopname);
                    priceList.add(shopprice);
                    howfarList.add(distance);
                    howpepoleList.add(recommindex);
                }
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
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.fordistance:
                Map<String, Object> map = new HashMap<>();
                map.put("type", "娱乐");
                map.put("class","distance");
                OkHttpHelper.getInstance().post(Url.ShopSortUrl, map, new BaseCallback<GoodFoodBean>() {
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
                    public void onSuccess(Response response, GoodFoodBean goodFoodBean) {
                        imageList = new ArrayList<String>();
                        nameList = new ArrayList<String>();
                        priceList = new ArrayList<String>();
                        howfarList = new ArrayList<Double>();
                        howpepoleList = new ArrayList<Integer>();
                        for (int i = 0; i < goodFoodBean.message.count; i++) {
                            String shopimg = goodFoodBean.message.rows.get(i).shopimg;
                            String shopname = goodFoodBean.message.rows.get(i).shopname;
                            String shopprice = goodFoodBean.message.rows.get(i).shopprice;
                            double distance = goodFoodBean.message.rows.get(i).distance;
                            int recommindex = goodFoodBean.message.rows.get(i).recommindex;
                            imageList.add(shopimg);
                            nameList.add(shopname);
                            priceList.add(shopprice);
                            howfarList.add(distance);
                            howpepoleList.add(recommindex);
                        }
                        myAdapter.notifyDataSetChanged();
                        showToast("距离排序完成");
//                        recyclerview.setAdapter(myAdapter);
                    }


                    @Override
                    public void onError(Response response, int code, Exception e) {

                    }

                    @Override
                    public void onTokenError(Response response, int code) {

                    }
                });

                break;
            case R.id.formoney:
                Map<String, Object> map2 = new HashMap<>();
                map2.put("type", "娱乐");
                map2.put("class","shopprice");
                OkHttpHelper.getInstance().post(Url.ShopSortUrl, map2, new BaseCallback<GoodFoodBean>() {
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
                    public void onSuccess(Response response, GoodFoodBean goodFoodBean) {
                        imageList = new ArrayList<String>();
                        nameList = new ArrayList<String>();
                        priceList = new ArrayList<String>();
                        howfarList = new ArrayList<Double>();
                        howpepoleList = new ArrayList<Integer>();
                        for (int i = 0; i < goodFoodBean.message.count; i++) {
                            String shopimg = goodFoodBean.message.rows.get(i).shopimg;
                            String shopname = goodFoodBean.message.rows.get(i).shopname;
                            String shopprice = goodFoodBean.message.rows.get(i).shopprice;
                            double distance = goodFoodBean.message.rows.get(i).distance;
                            int recommindex = goodFoodBean.message.rows.get(i).recommindex;
                            imageList.add(shopimg);
                            nameList.add(shopname);
                            priceList.add(shopprice);
                            howfarList.add(distance);
                            howpepoleList.add(recommindex);
                        }
                        myAdapter.notifyDataSetChanged();
                        showToast("价格排序完成");
//                        recyclerview.setAdapter(myAdapter);
                    }


                    @Override
                    public void onError(Response response, int code, Exception e) {

                    }

                    @Override
                    public void onTokenError(Response response, int code) {

                    }
                });
                break;
            case R.id.forhots:
                Map<String, Object> map3 = new HashMap<>();
                map3.put("type", "娱乐");
                map3.put("class","recommindex");
                OkHttpHelper.getInstance().post(Url.ShopSortUrl, map3, new BaseCallback<GoodFoodBean>() {
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
                    public void onSuccess(Response response, GoodFoodBean goodFoodBean) {
                        imageList = new ArrayList<String>();
                        nameList = new ArrayList<String>();
                        priceList = new ArrayList<String>();
                        howfarList = new ArrayList<Double>();
                        howpepoleList = new ArrayList<Integer>();
                        for (int i = 0; i < goodFoodBean.message.count; i++) {
                            String shopimg = goodFoodBean.message.rows.get(i).shopimg;
                            String shopname = goodFoodBean.message.rows.get(i).shopname;
                            String shopprice = goodFoodBean.message.rows.get(i).shopprice;
                            double distance = goodFoodBean.message.rows.get(i).distance;
                            int recommindex = goodFoodBean.message.rows.get(i).recommindex;
                            imageList.add(shopimg);
                            nameList.add(shopname);
                            priceList.add(shopprice);
                            howfarList.add(distance);
                            howpepoleList.add(recommindex);
                        }
                        myAdapter.notifyDataSetChanged();
                        showToast("热度排序完成");
//                        recyclerview.setAdapter(myAdapter);
                    }


                    @Override
                    public void onError(Response response, int code, Exception e) {

                    }

                    @Override
                    public void onTokenError(Response response, int code) {

                    }
                });
                break;
        }
    }


    class myAdapter extends RecyclerView.Adapter<myAdapter.mViewHolder> {

        class mViewHolder extends RecyclerView.ViewHolder {
            ImageView imageV;
            TextView name;
            TextView price;
            TextView howfar;
            TextView recomm;

            public mViewHolder(View itemView) {
                super(itemView);
                imageV = (ImageView) itemView.findViewById(R.id.image);
                name = (TextView) itemView.findViewById(R.id.name);
                price = (TextView) itemView.findViewById(R.id.price);
                howfar = (TextView) itemView.findViewById(R.id.howfar);
                recomm = (TextView) itemView.findViewById(R.id.recomm);
            }
        }

        @Override
        public mViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(HappyActivity.this).inflate(R.layout.item_happy, parent, false);
            mViewHolder mViewHolder = new mViewHolder(view);
            return mViewHolder;
        }

        @Override
        public void onBindViewHolder(mViewHolder holder, int position) {

            Glide.with(HappyActivity.this).
                    load(imageList.get(position)).into(holder.imageV);
            holder.name.setText(nameList.get(position));
            holder.price.setText(priceList.get(position));
            holder.howfar.setText(howfarList.get(position) + "km");
            holder.recomm.setText(howpepoleList.get(position) + "人去过");
        }

        @Override
        public int getItemCount() {
            return nameList.size();
        }
    }

}
