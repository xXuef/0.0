package net.m56.ckkj.mobile.tourism.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
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

public class HotelActivity extends BaseActivity implements View.OnClickListener {


    public ListView listview;
    public HotelActivity.MyRecyclerviewAdapter Adapter;
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

    @Override
    public void initVariable() {
        setContentView(R.layout.activity_hotel);
        ButterKnife.bind(this);

        fordistance = (LinearLayout)findViewById(R.id.fordistance);
        formoney = (LinearLayout)findViewById(R.id.formoney);
        forhots = (LinearLayout)findViewById(R.id.forhots);
        fordistance.setOnClickListener(this);
        forhots.setOnClickListener(this);
        formoney.setOnClickListener(this);
        listview = (ListView) findViewById(R.id.listview);
        recyclerview = (RecyclerView) findViewById(R.id.recyclerview);
        TextView title_name = (TextView) findViewById(R.id.toolbar_title);
        title_name.setText("酒店");
        //设置沉浸式状态栏
        ImmersionBar.with(this).statusBarColor(R.color.stabar_color).fitsSystemWindows(true).init();


    }

    @Override
    public void toLoad(Bundle savedInstanceState) {
        recyclerview.setLayoutManager(new LinearLayoutManager(HotelActivity.this));
        Map<String, Object> map = new HashMap<>();
        map.put("type", "酒店");
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

                for (int i = 0; i < goodFoodBean.message.count; i++) {
                    String shopname = goodFoodBean.message.rows.get(i).shopname;
                    String shopimgurl = goodFoodBean.message.rows.get(i).shopimg;
                    String shopprice = goodFoodBean.message.rows.get(i).shopprice;
                    int recommindex = goodFoodBean.message.rows.get(i).recommindex;
                    int id = goodFoodBean.message.rows.get(i).id;
                    double distance = goodFoodBean.message.rows.get(i).distance;
                    imageViewUrlList.add(shopimgurl);
                    placeNameList.add(shopname);
                    moneyList.add(shopprice);
                    howPopleList.add(recommindex);
                    howFarList.add(distance);
                    idList.add(id);
                }
                Adapter = new MyRecyclerviewAdapter();
                recyclerview.setAdapter(Adapter);
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

                Map<String, Object> map1 = new HashMap<>();
                map1.put("type", "酒店");
                map1.put("class", "distance");
                OkHttpHelper.getInstance().post(Url.ShopSortUrl, map1, new BaseCallback<GoodFoodBean>() {

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

                        for (int i = 0; i < goodFoodBean.message.count; i++) {
                            String shopname = goodFoodBean.message.rows.get(i).shopname;
                            String shopimgurl = goodFoodBean.message.rows.get(i).shopimg;
                            String shopprice = goodFoodBean.message.rows.get(i).shopprice;
                            int recommindex = goodFoodBean.message.rows.get(i).recommindex;
                            int id = goodFoodBean.message.rows.get(i).id;
                            double distance = goodFoodBean.message.rows.get(i).distance;
                            imageViewUrlList.add(shopimgurl);
                            placeNameList.add(shopname);
                            moneyList.add(shopprice);
                            howPopleList.add(recommindex);
                            howFarList.add(distance);
                            idList.add(id);
                        }
                        Adapter.notifyDataSetChanged();
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
                map2.put("type", "酒店");
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

                        for (int i = 0; i < goodFoodBean.message.count; i++) {
                            String shopname = goodFoodBean.message.rows.get(i).shopname;
                            String shopimgurl = goodFoodBean.message.rows.get(i).shopimg;
                            String shopprice = goodFoodBean.message.rows.get(i).shopprice;
                            int recommindex = goodFoodBean.message.rows.get(i).recommindex;
                            int id = goodFoodBean.message.rows.get(i).id;
                            double distance = goodFoodBean.message.rows.get(i).distance;
                            imageViewUrlList.add(shopimgurl);
                            placeNameList.add(shopname);
                            moneyList.add(shopprice);
                            howPopleList.add(recommindex);
                            howFarList.add(distance);
                            idList.add(id);
                        }
                        Adapter.notifyDataSetChanged();
                        showToast("价格排序完成");
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
                map3.put("type", "酒店");
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
                        for (int i = 0; i < goodFoodBean.message.count; i++) {
                            String shopname = goodFoodBean.message.rows.get(i).shopname;
                            String shopimgurl = goodFoodBean.message.rows.get(i).shopimg;
                            String shopprice = goodFoodBean.message.rows.get(i).shopprice;
                            int recommindex = goodFoodBean.message.rows.get(i).recommindex;
                            double distance = goodFoodBean.message.rows.get(i).distance;
                            int id = goodFoodBean.message.rows.get(i).id;
                            imageViewUrlList.add(shopimgurl);
                            placeNameList.add(shopname);
                            moneyList.add(shopprice);
                            howPopleList.add(recommindex);
                            howFarList.add(distance);
                            idList.add(id);
                        }

                        Adapter.notifyDataSetChanged();
                        showToast("热度排序完成");
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


    class ListAdapter extends BaseAdapter {

        @Override
        public int getCount() {
            return placeNameList.size();
        }

        @Override
        public Object getItem(int position) {
            return null;
        }

        @Override
        public long getItemId(int position) {
            return 0;
        }


        class ViewHolder {
            ImageView placeImage;
            TextView placeName;
            TextView price;
            TextView recomm;
            TextView howfar;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            ViewHolder holder;
            if (convertView == null) {
                holder = new ViewHolder();
                convertView = LayoutInflater.from(HotelActivity.this).inflate(R.layout.item_hotel, null);
                holder.placeImage = (ImageView) convertView.findViewById(R.id.imageview);
                holder.placeName = (TextView) convertView.findViewById(R.id.name);
                holder.price = (TextView) convertView.findViewById(R.id.price);
                holder.howfar = (TextView) convertView.findViewById(R.id.howfar);
                holder.recomm = (TextView) convertView.findViewById(R.id.recomm);
                convertView.setTag(holder);
            } else {
                holder = (ViewHolder) convertView.getTag();
            }
            Glide.with(getApplicationContext()).
                    load(imageViewUrlList.get(position)).into(holder.placeImage);
            holder.placeName.setText(placeNameList.get(position));
            holder.price.setText(moneyList.get(position));
            holder.recomm.setText("有" + howPopleList.get(position) + "人去过");
            holder.howfar.setText(howFarList.get(position) + "km");

            return convertView;
        }
    }


    class MyRecyclerviewAdapter extends RecyclerView.Adapter<MyRecyclerviewAdapter.MyViewHolder> {
        class MyViewHolder extends RecyclerView.ViewHolder {
            ImageView shopimage;
            TextView shopname;
            TextView howmoney;
            TextView recomm;
            TextView howfar;


            public MyViewHolder(View itemView) {
                super(itemView);
                shopimage = (ImageView) itemView.findViewById(R.id.imageview);
                shopname = (TextView) itemView.findViewById(R.id.name);
                howmoney = (TextView) itemView.findViewById(R.id.price);
                recomm = (TextView) itemView.findViewById(R.id.recomm);
                howfar = (TextView) itemView.findViewById(R.id.howfar);
            }
        }

        @Override
        public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

            View view = LayoutInflater.from(HotelActivity.this).inflate(R.layout.item_hotel, parent, false);
            MyViewHolder holder = new MyViewHolder(view);
            return holder;
        }

        @Override
        public void onBindViewHolder(MyViewHolder holder, final int position) {

            Glide.with(HotelActivity.this).load(imageViewUrlList.get(position)).into(holder.shopimage);
            holder.shopname.setText(placeNameList.get(position));
            holder.howmoney.setText(moneyList.get(position));
            holder.recomm.setText("有" + howPopleList.get(position) + "人去过");
            holder.howfar.setText(howFarList.get(position) + "km");
            holder.itemView.setTag(position);

            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(HotelActivity.this, PlaceInfoActivity.class);
                    intent.putExtra("placename", placeNameList.get(position));
                    intent.putExtra("placeimage", imageViewUrlList.get(position));
                    intent.putExtra("placehowfar", howFarList.get(position));
                    intent.putExtra("id", idList.get(position));
                    startActivity(intent);
                }
            });
        }

        @Override
        public int getItemCount() {
            return placeNameList.size();
        }


    }
}
