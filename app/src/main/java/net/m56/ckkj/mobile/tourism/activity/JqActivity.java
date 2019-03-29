package net.m56.ckkj.mobile.tourism.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.gyf.barlibrary.ImmersionBar;

import net.m56.ckkj.mobile.tourism.Api.Url;
import net.m56.ckkj.mobile.tourism.base.activity.BaseActivity;
import net.m56.ckkj.mobile.tourism.bean.ListBean;
import net.m56.ckkj.mobile.tourism.okhttputil.BaseCallback;
import net.m56.ckkj.mobile.tourism.okhttputil.OkHttpHelper;
import net.m56.ckkj.tourism.tourism.R;

import java.util.ArrayList;

import butterknife.ButterKnife;
import okhttp3.Call;
import okhttp3.Request;
import okhttp3.Response;

public class JqActivity extends BaseActivity {


    ArrayList<String> soptImgUrlArrayList;
    ArrayList<String> soptNameArrayList;
    ArrayList<String> soptPriceArrayList;
    ArrayList<String> recommindexArrayList;
    ArrayList<String> describitionArrayList;
    ArrayList<String> adressArrayList;
    ArrayList<String> idList;
    ArrayList<String> lonArrayList;
    ArrayList<String> latArrayList;
    ListView listview;


    @Override
    public void initVariable() {
        setContentView(R.layout.activity_jq);
        ButterKnife.bind(this);
        listview= (ListView) findViewById(R.id.listview);
        //设置沉浸式状态栏
        ImmersionBar.with(this).statusBarColor(R.color.stabar_color).fitsSystemWindows(true).init();
        TextView title_name = (TextView) findViewById(R.id.toolbar_title);
        title_name.setText("景区");


        OkHttpHelper.getInstance().post(Url.ListUrl, null, new BaseCallback<ListBean>() {
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
            public void onSuccess(Response response, final ListBean listBean) {

                soptImgUrlArrayList = new ArrayList<String>();
                soptNameArrayList = new ArrayList<String>();
                soptPriceArrayList = new ArrayList<>();
                recommindexArrayList = new ArrayList<>();
                describitionArrayList = new ArrayList<>();
                adressArrayList = new ArrayList<>();
                lonArrayList = new ArrayList<>();
                latArrayList = new ArrayList<>();
                idList = new ArrayList<>();
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

                    soptImgUrlArrayList.add(soptimg);
                    soptNameArrayList.add(spotname);
                    soptPriceArrayList.add(spotprice);
                    recommindexArrayList.add(recommindex);
                    describitionArrayList.add(describtion);
                    adressArrayList.add(adress);
                    idList.add(id);

                    lonArrayList.add(longitude);
                    latArrayList.add(latitude);
                }

                listview.setAdapter(new myadapter(JqActivity.this));

                listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        Intent intent = new Intent(JqActivity.this, NearbyInfoActivity.class);
                        intent.putExtra("name", soptNameArrayList.get(position));
                        intent.putExtra("imageurl", soptImgUrlArrayList.get(position));
                        intent.putExtra("desc", describitionArrayList.get(position));
                        intent.putExtra("adress", adressArrayList.get(position));
                        intent.putExtra("id", idList.get(position));
                        intent.putExtra("lon", lonArrayList.get(position));
                        intent.putExtra("lat", latArrayList.get(position));

                        startActivity(intent);
                    }
                });
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
    protected void onDestroy() {
        super.onDestroy();
        ImmersionBar.with(this).destroy();
    }


    class myadapter extends BaseAdapter {
        private LayoutInflater mInflater;

        public myadapter(Context context) {
            this.mInflater = LayoutInflater.from(context);
        }

        @Override
        public int getCount() {
            return soptImgUrlArrayList.size();
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
            public ImageView imag;
            public TextView placename;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            ViewHolder holder = null;
            if (convertView == null) {
                holder = new ViewHolder();
                convertView = mInflater.inflate(R.layout.soptitem, null);
                holder.imag = (ImageView) convertView.findViewById(R.id.imagview);
                holder.placename = (TextView) convertView.findViewById(R.id.jqname);
                convertView.setTag(holder);
            } else {
                holder = (ViewHolder) convertView.getTag();
            }

            RequestOptions requestOptions = new RequestOptions();
            requestOptions.centerCrop()
                    .placeholder(R.mipmap.icon_kefu)
                    .error(R.mipmap.icon_kefu)
            .fallback(R.mipmap.icon_kefu);
            Glide.with(getApplicationContext()).
                    load(soptImgUrlArrayList.get(position)).into(holder.imag);
            holder.placename.setText(soptNameArrayList.get(position));

            return convertView;
        }
    }
}
