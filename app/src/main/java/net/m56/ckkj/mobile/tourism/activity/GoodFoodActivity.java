package net.m56.ckkj.mobile.tourism.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
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


public class GoodFoodActivity extends BaseActivity implements View.OnClickListener{


    private static final int MSG = 200;
    LinearLayout fordistance;
    LinearLayout formoney;
    LinearLayout forhots;
    RecyclerView recyclerview;

    ArrayList<String> imageViewUrlList;
    ArrayList<String> placeNameList;
    ArrayList<String> moneyList;
    ArrayList<Double> howFarList;
    ArrayList<Integer> howPopleList;
    ArrayList<Integer> idList;
    ArrayList<Double> latList;
    ArrayList<Double> lonList;
    public GoodFoodActivity.MyRecyclerviewAdapter adapter;

    @Override
    public void initVariable() {
        setContentView(R.layout.activity_good_food);
        ButterKnife.bind(this);


        //设置沉浸式状态栏
        ImmersionBar.with(this).statusBarColor(R.color.stabar_color).fitsSystemWindows(true).init();
        TextView title_name = (TextView) findViewById(R.id.toolbar_title);
        title_name.setText("美食");
        fordistance = (LinearLayout)findViewById(R.id.fordistance);
        formoney = (LinearLayout)findViewById(R.id.formoney);
        forhots = (LinearLayout)findViewById(R.id.forhots);
        fordistance.setOnClickListener(this);
        forhots.setOnClickListener(this);
        formoney.setOnClickListener(this);
        recyclerview = (RecyclerView)findViewById(R.id.recyclerview);

        recyclerview.setLayoutManager(new LinearLayoutManager(GoodFoodActivity.this));

    }


    @Override
    public void toLoad(Bundle savedInstanceState) {

        Map<String, Object> map = new HashMap<>();
        map.put("type", "美食");
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
                imageViewUrlList = new ArrayList<String>();
                placeNameList = new ArrayList<String>();
                moneyList = new ArrayList<String>();
                howFarList = new ArrayList<Double>();
                howPopleList = new ArrayList<Integer>();
                idList = new ArrayList<Integer>();
                lonList = new ArrayList<>();
                latList = new ArrayList<>();

                for (int i = 0; i < goodFoodBean.message.count; i++) {
                    String shopname = goodFoodBean.message.rows.get(i).shopname;
                    String shopimgurl = goodFoodBean.message.rows.get(i).shopimg;
                    String shopprice = goodFoodBean.message.rows.get(i).shopprice;
                    int recommindex = goodFoodBean.message.rows.get(i).recommindex;
                    double distance = goodFoodBean.message.rows.get(i).distance;
                    Integer id = goodFoodBean.message.rows.get(i).id;
                    Double longitude = (Double) goodFoodBean.message.rows.get(i).longitude;
                    Double latitude = (Double) goodFoodBean.message.rows.get(i).latitude;
                    imageViewUrlList.add(shopimgurl);
                    placeNameList.add(shopname);
                    moneyList.add(shopprice);
                    howPopleList.add(recommindex);
                    howFarList.add(distance);
                    idList.add(id);
                    lonList.add(longitude);
                    latList.add(latitude);
                }

                adapter = new MyRecyclerviewAdapter();
                recyclerview.setAdapter(adapter);

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
                map.put("type", "美食");
                map.put("class", "distance");
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
                        imageViewUrlList = new ArrayList<String>();
                        placeNameList = new ArrayList<String>();
                        moneyList = new ArrayList<String>();
                        howFarList = new ArrayList<Double>();
                        howPopleList = new ArrayList<Integer>();
                        idList = new ArrayList<Integer>();
                        lonList = new ArrayList<>();
                        latList = new ArrayList<>();

                        for (int i = 0; i < goodFoodBean.message.count; i++) {
                            String shopname = goodFoodBean.message.rows.get(i).shopname;
                            String shopimgurl = goodFoodBean.message.rows.get(i).shopimg;
                            String shopprice = goodFoodBean.message.rows.get(i).shopprice;
                            int recommindex = goodFoodBean.message.rows.get(i).recommindex;
                            double distance = goodFoodBean.message.rows.get(i).distance;
                            Integer id = goodFoodBean.message.rows.get(i).id;
                            Double latitude = (Double) goodFoodBean.message.rows.get(i).latitude;
                            Double longitude = (Double) goodFoodBean.message.rows.get(i).longitude;
                            imageViewUrlList.add(shopimgurl);
                            placeNameList.add(shopname);
                            moneyList.add(shopprice);
                            howPopleList.add(recommindex);
                            howFarList.add(distance);
                            idList.add(id);
                            lonList.add(longitude);
                            latList.add(latitude);
                        }
                        adapter.notifyDataSetChanged();
                        showToast("距离排序完成");

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
                map2.put("type", "美食");
                map2.put("class", "shopprice");
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
                        imageViewUrlList = new ArrayList<String>();
                        placeNameList = new ArrayList<String>();
                        moneyList = new ArrayList<String>();
                        howFarList = new ArrayList<Double>();
                        howPopleList = new ArrayList<Integer>();
                        idList = new ArrayList<Integer>();
                        lonList = new ArrayList<>();
                        latList = new ArrayList<>();

                        for (int i = 0; i < goodFoodBean.message.count; i++) {
                            String shopname = goodFoodBean.message.rows.get(i).shopname;
                            String shopimgurl = goodFoodBean.message.rows.get(i).shopimg;
                            String shopprice = goodFoodBean.message.rows.get(i).shopprice;
                            int recommindex = goodFoodBean.message.rows.get(i).recommindex;
                            double distance = goodFoodBean.message.rows.get(i).distance;
                            Integer id = goodFoodBean.message.rows.get(i).id;
                            Double latitude = (Double) goodFoodBean.message.rows.get(i).latitude;
                            Double longitude = (Double) goodFoodBean.message.rows.get(i).longitude;
                            imageViewUrlList.add(shopimgurl);
                            placeNameList.add(shopname);
                            moneyList.add(shopprice);
                            howPopleList.add(recommindex);
                            howFarList.add(distance);
                            idList.add(id);
                            lonList.add(longitude);
                            latList.add(latitude);
                        }
                        adapter.notifyDataSetChanged();
                        showToast("价格排序完成");
//                        recyclerview.setAdapter(adapter);
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
                map3.put("type", "美食");
                map3.put("class", "recommindex");
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
                        imageViewUrlList = new ArrayList<String>();
                        placeNameList = new ArrayList<String>();
                        moneyList = new ArrayList<String>();
                        howFarList = new ArrayList<Double>();
                        howPopleList = new ArrayList<Integer>();
                        idList = new ArrayList<Integer>();
                        lonList = new ArrayList<>();
                        latList = new ArrayList<>();
                        for (int i = 0; i < goodFoodBean.message.count; i++) {
                            String shopname = goodFoodBean.message.rows.get(i).shopname;
                            String shopimgurl = goodFoodBean.message.rows.get(i).shopimg;
                            String shopprice = goodFoodBean.message.rows.get(i).shopprice;
                            int recommindex = goodFoodBean.message.rows.get(i).recommindex;
                            double distance = goodFoodBean.message.rows.get(i).distance;
                            Integer id = goodFoodBean.message.rows.get(i).id;
                            Double latitude = (Double) goodFoodBean.message.rows.get(i).latitude;
                            Double longitude = (Double) goodFoodBean.message.rows.get(i).longitude;
                            imageViewUrlList.add(shopimgurl);
                            placeNameList.add(shopname);
                            moneyList.add(shopprice);
                            howPopleList.add(recommindex);
                            howFarList.add(distance);
                            idList.add(id);
                            lonList.add(longitude);
                            latList.add(latitude);
                        }
                        adapter.notifyDataSetChanged();
                        showToast("热度排序完成");
                        Log.e("lon", lonList.toString());
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


    class MyRecyclerviewAdapter extends RecyclerView.Adapter<MyRecyclerviewAdapter.MyViewHolder> {
        class MyViewHolder extends RecyclerView.ViewHolder {
            ImageView zuire;
            ImageView shopimage;
            TextView shopname;
            TextView howmoney;
            TextView liuliang;
            TextView howfar;


            public MyViewHolder(View itemView) {
                super(itemView);
                shopimage = (ImageView) itemView.findViewById(R.id.shopiamge);
                zuire = (ImageView) itemView.findViewById(R.id.hotfrist);
                shopname = (TextView) itemView.findViewById(R.id.shopname);
                howmoney = (TextView) itemView.findViewById(R.id.shopmoney);
                liuliang = (TextView) itemView.findViewById(R.id.liuliang);
                howfar = (TextView) itemView.findViewById(R.id.howfar);
            }
        }

        @Override
        public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

            View view = LayoutInflater.from(GoodFoodActivity.this).inflate(R.layout.near_item, parent, false);
            MyViewHolder holder = new MyViewHolder(view);

            return holder;
        }

        @Override
        public void onBindViewHolder(MyViewHolder holder, final int position) {

            Glide.with(GoodFoodActivity.this).load(imageViewUrlList.get(position)).into(holder.shopimage);
            holder.shopname.setText(placeNameList.get(position));
            holder.howmoney.setText(moneyList.get(position));
            holder.liuliang.setText("有" + howPopleList.get(position) + "人去过");
            holder.howfar.setText(howFarList.get(position) + "km");
            holder.itemView.setTag(position);

            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(GoodFoodActivity.this, PlaceInfoActivity.class);
                    intent.putExtra("placename", placeNameList.get(position));
                    intent.putExtra("placeimage", imageViewUrlList.get(position));
                    intent.putExtra("placehowfar", howFarList.get(position));
                    intent.putExtra("id", idList.get(position));
                    intent.putExtra("lon", lonList.get(position));
                    intent.putExtra("lat", latList.get(position));
                    startActivity(intent);
                }
            });
        }

        @Override
        public int getItemCount() {
            return imageViewUrlList.size();
        }


    }

    @Override
    protected synchronized void onDestroy() {
        super.onDestroy();
        ImmersionBar.with(this).destroy();
    }

}
