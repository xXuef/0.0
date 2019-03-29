package net.m56.ckkj.mobile.tourism.fragment;

import android.content.Intent;
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

import net.m56.ckkj.mobile.tourism.Api.Url;
import net.m56.ckkj.mobile.tourism.activity.GoodFoodActivity;
import net.m56.ckkj.mobile.tourism.activity.HappyActivity;
import net.m56.ckkj.mobile.tourism.activity.HotelActivity;
import net.m56.ckkj.mobile.tourism.activity.NewsInfoActivity;
import net.m56.ckkj.mobile.tourism.activity.PlaceInfoActivity;
import net.m56.ckkj.mobile.tourism.activity.ShopActivity;
import net.m56.ckkj.mobile.tourism.base.activity.CaptureActivity;
import net.m56.ckkj.mobile.tourism.base.farment.BaseFragment;
import net.m56.ckkj.mobile.tourism.bean.HotRecommed;
import net.m56.ckkj.mobile.tourism.okhttputil.BaseCallback;
import net.m56.ckkj.mobile.tourism.okhttputil.OkHttpHelper;
import net.m56.ckkj.mobile.tourism.util.SpacesItemDecoration;
import net.m56.ckkj.tourism.tourism.R;

import java.util.ArrayList;

import butterknife.ButterKnife;
import okhttp3.Call;
import okhttp3.Request;
import okhttp3.Response;

/**
 * 2017/10/10 17:20
 */

public class ZbFragment extends BaseFragment implements View.OnClickListener{

    ImageView erweima;
    TextView titleName;
    ImageView xiaoxi;
    LinearLayout goodfood;
    LinearLayout hotel;
    LinearLayout shopping;
    LinearLayout happy;
    RecyclerView recyclerview;
    ArrayList<String> shopNameList;
    ArrayList<String> shopImageUrlList;
    ArrayList<String> howMoneyList;
    ArrayList<String> howFarList;
    ArrayList<String> howPeopleList;
    ArrayList<String> idList;
    LinearLayout laayout;

    @Override
    protected int getLayoutId() {
        return R.layout.zbfragment;
    }

    @Override
    protected void initParams(View view, Bundle savedInstanceState) {
        titleName = (TextView) view.findViewById(R.id.title_name);
        titleName.setText("周边");
        erweima = (ImageView)view.findViewById(R.id.erweima);
        xiaoxi = (ImageView)view.findViewById(R.id.xiaoxi);
        goodfood = (LinearLayout)view.findViewById(R.id.goodfood);
        hotel = (LinearLayout)view.findViewById(R.id.hotel);
        shopping = (LinearLayout)view.findViewById(R.id.shopping);
        happy = (LinearLayout)view.findViewById(R.id.happy);
        laayout = (LinearLayout)view.findViewById(R.id.laayout);
        recyclerview = (RecyclerView)view.findViewById(R.id.recyclerview);

        erweima.setImageResource(R.mipmap.icon_saoyisao);
        xiaoxi.setImageResource(R.mipmap.icon_xiaoxi);

        laayout.setFocusable(true);
        laayout.setFocusableInTouchMode(true);
        laayout.requestFocus();
        //禁止recyclerview滑动
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false) {
            @Override
            public boolean canScrollVertically() {
                return false;
            }
        };
        erweima.setOnClickListener(this);
        xiaoxi.setOnClickListener(this);
        goodfood.setOnClickListener(this);
        hotel.setOnClickListener(this);
        shopping.setOnClickListener(this);
        happy.setOnClickListener(this);

        //设置布局管理器
        recyclerview.setLayoutManager(layoutManager);
        recyclerview.addItemDecoration(new SpacesItemDecoration(15));

        OkHttpHelper.getInstance().post(Url.NearUrl, null, new BaseCallback<HotRecommed>() {
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
            public void onSuccess(Response response, HotRecommed hotRecommed) {
                shopNameList = new ArrayList<String>();
                shopImageUrlList = new ArrayList<String>();
                howMoneyList = new ArrayList<String>();
                howPeopleList = new ArrayList<String>();
                howFarList = new ArrayList<String>();
                idList = new ArrayList<String>();
                for (int i = 0; i < hotRecommed.message.count; i++) {
                    String shopname = hotRecommed.message.rows.get(i).shopname;
                    String shopimgurl = hotRecommed.message.rows.get(i).shopimg;
                    String shopprice = hotRecommed.message.rows.get(i).shopprice;
                    String distance = hotRecommed.message.rows.get(i).distance;
                    String recommindex = hotRecommed.message.rows.get(i).recommindex;
                    String id = hotRecommed.message.rows.get(i).id;
                    Object longitude = hotRecommed.message.rows.get(i).longitude;

                    shopNameList.add(shopname);
                    shopImageUrlList.add(shopimgurl);
                    howMoneyList.add(shopprice);
                    howFarList.add(distance);
                    howPeopleList.add(recommindex);
                    idList.add(id);
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
        ButterKnife.unbind(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.erweima:
                startActivity(new Intent(getActivity(), CaptureActivity.class));
                break;
            case R.id.xiaoxi:
                startActivity(new Intent(getContext(), NewsInfoActivity.class));
                break;
            case R.id.goodfood:
                startActivity(new Intent(getContext(), GoodFoodActivity.class));
                break;
            case R.id.hotel:
                startActivity(new Intent(getContext(), HotelActivity.class));
                break;
            case R.id.shopping:
                startActivity(new Intent(getContext(), ShopActivity.class));
                break;
            case R.id.happy:
                startActivity(new Intent(getContext(), HappyActivity.class));
                break;
        }
    }

    class MyRecyclerviewAdapter extends RecyclerView.Adapter<MyRecyclerviewAdapter.MyViewHolder> {
        @Override
        public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

            MyViewHolder holder = new MyViewHolder(LayoutInflater.from(
                    parent.getContext()).inflate(R.layout.near_item, parent,
                    false));
            return holder;
        }

        @Override
        public void onBindViewHolder(MyViewHolder holder, final int position) {

            if (position == 0) {
                holder.zuire.setVisibility(View.VISIBLE);
            }
            Glide.with(getContext()).load(shopImageUrlList.get(position)).into(holder.shopimage);
            holder.shopname.setText(shopNameList.get(position));
            holder.howmoney.setText(howMoneyList.get(position));
            holder.liuliang.setText(howPeopleList.get(position) + "人去过");
            holder.howfar.setText(howFarList.get(position) + "km");
            holder.itemView.setTag(position);

            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(getContext(), PlaceInfoActivity.class);
                    intent.putExtra("placename", shopNameList.get(position));
                    intent.putExtra("placeimage", shopImageUrlList.get(position));
                    intent.putExtra("placehowfar", howFarList.get(position));
                    intent.putExtra("id", idList.get(position));
                    startActivity(intent);
                }
            });
        }

        @Override
        public int getItemCount() {
            return shopImageUrlList.size();
        }

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
    }
}
