package net.m56.ckkj.mobile.tourism.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import net.m56.ckkj.mobile.tourism.Api.Url;
import net.m56.ckkj.mobile.tourism.base.farment.BaseFragment;
import net.m56.ckkj.mobile.tourism.bean.GoodFoodBean;
import net.m56.ckkj.mobile.tourism.okhttputil.BaseCallback;
import net.m56.ckkj.mobile.tourism.okhttputil.OkHttpHelper;
import net.m56.ckkj.tourism.tourism.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import okhttp3.Call;
import okhttp3.Request;
import okhttp3.Response;

/**
 * 2017/10/23 14:59
 */

public class ForHots extends BaseFragment {

    public ArrayList<String> imagList;
    public ArrayList<String> nameList;
    public ArrayList<Double> distanceList;
    public ArrayList<Integer> recommList;


    @Override
    protected int getLayoutId() {
        return R.layout.for_distance;
    }

    @Override
    protected void initParams(View view, Bundle savedInstanceState) {
        final ListView listview = (ListView) view.findViewById(R.id.listview);

        Map<String, Object> map= new HashMap<>();
        map.put("type","购物");
        map.put("class","recommindex");
        OkHttpHelper.getInstance().post(Url.ShopSortUrl, map, new BaseCallback<GoodFoodBean>() {
            @Override
            public void onBeforeRequest(Request request) {}
            @Override
            public void onFailure(Call call, Exception e) {}
            @Override
            public void onResponse(Response response) {}
            @Override
            public void onSuccess(Response response, GoodFoodBean goodFoodBean) {
                imagList = new ArrayList<>();
                nameList = new ArrayList<>();
                distanceList = new ArrayList<>();
                recommList = new ArrayList<>();
                for (int i = 0; i < goodFoodBean.message.count; i++) {
                    String shopimg = goodFoodBean.message.rows.get(i).shopimg;
                    String shopname = goodFoodBean.message.rows.get(i).shopname;
                    double distance = goodFoodBean.message.rows.get(i).distance;
                    int recommindex = goodFoodBean.message.rows.get(i).recommindex;
                    imagList.add(shopimg);
                    nameList.add(shopname);
                    distanceList.add(distance);
                    recommList.add(recommindex);
                }
                listview.setAdapter(new ListAdapter());

            }


            @Override
            public void onError(Response response, int code, Exception e) {

            }

            @Override
            public void onTokenError(Response response, int code) {

            }
        });

    }

    class ListAdapter extends BaseAdapter {

        @Override
        public int getCount() {
            return nameList.size();
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
            public ImageView placeimage;
            public TextView placename;
            public TextView popolenummber;
            public TextView howfar;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
           ViewHolder holder;
            if (convertView == null) {
                holder = new ViewHolder();
                convertView = LayoutInflater.from(getContext()).inflate(R.layout.for_distance_item, null);
                holder.placeimage = (ImageView) convertView.findViewById(R.id.shopiamge);
                holder.placename = (TextView) convertView.findViewById(R.id.name);
                holder.popolenummber = (TextView) convertView.findViewById(R.id.howpople);
                holder.howfar = (TextView) convertView.findViewById(R.id.howfar);

                convertView.setTag(holder);
            } else {
                holder = (ViewHolder) convertView.getTag();
            }

            Glide.with(getContext()).
                    load(imagList.get(position)).into(holder.placeimage);
            holder.placename.setText(nameList.get(position));
            holder.popolenummber.setText("有"+recommList.get(position) + "人去过");
            Double aDouble = distanceList.get(position);
            holder.howfar.setText( aDouble+ "km");

            return convertView;
        }
    }
}
