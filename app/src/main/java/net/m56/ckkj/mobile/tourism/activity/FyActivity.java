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

import com.gyf.barlibrary.ImmersionBar;

import net.m56.ckkj.mobile.tourism.Api.Url;
import net.m56.ckkj.mobile.tourism.base.activity.BaseActivity;
import net.m56.ckkj.mobile.tourism.bean.InTangBean;
import net.m56.ckkj.mobile.tourism.okhttputil.BaseCallback;
import net.m56.ckkj.mobile.tourism.okhttputil.OkHttpHelper;
import net.m56.ckkj.tourism.tourism.R;

import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;
import okhttp3.Call;
import okhttp3.Request;
import okhttp3.Response;

public class FyActivity extends BaseActivity {


    public ArrayList<String> webUrlList;
    public List<String> titleList;
    public int[] images;
    public ListView listView;



    @Override
    public void initVariable() {
        setContentView(R.layout.activity_fy);
        //设置沉浸式状态栏
        ImmersionBar.with(this).statusBarColor(R.color.stabar_color).fitsSystemWindows(true).init();
        ButterKnife.bind(this);
        TextView title_name = (TextView) findViewById(R.id.toolbar_title);
        title_name.setText("非物质文化遗产");
        listView = (ListView) findViewById(R.id.listview);
        images = new int[]{R.mipmap.liuli, R.mipmap.wayao, R.mipmap.weilin};
        titleList = new ArrayList<>();
        titleList.add("琉璃咯嘣儿");
        titleList.add("瓦窑陶瓷");
        titleList.add("交城堆绫画");
    }

    @Override
    public void toLoad(Bundle savedInstanceState) {
        OkHttpHelper.getInstance().post(Url.InTanUrl, null, new BaseCallback<InTangBean>() {
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
            public void onSuccess(Response response, InTangBean inTangBean) {
                webUrlList = new ArrayList<>();
                for (int i = 0; i < inTangBean.message.count; i++) {
                    String webUrl = inTangBean.message.rows.get(i).webUrl;
                    webUrlList.add(webUrl);
                }

                listView.setAdapter(new myadapter(getApplicationContext()));
                listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        Intent intent = new Intent(getApplicationContext(), ViewPagerWebActivity.class);
                        intent.putExtra("Viewpager0", webUrlList.get(position));
                        intent.putExtra("titlename", titleList.get(position));
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
    class myadapter extends BaseAdapter {
        private LayoutInflater mInflater;

        public myadapter(Context context) {
            this.mInflater = LayoutInflater.from(getApplicationContext());
        }

        @Override
        public int getCount() {
            return titleList.size();
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
                convertView = mInflater.inflate(R.layout.jc_item, null);
                holder.imag = (ImageView) convertView.findViewById(R.id.imagview);
                holder.placename = (TextView) convertView.findViewById(R.id.jcname);
                convertView.setTag(holder);
            } else {
                holder = (ViewHolder) convertView.getTag();
            }

            holder.imag.setImageResource(images[position]);

            holder.placename.setText(titleList.get(position));

            return convertView;
        }
    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        ImmersionBar.with(this).destroy();
    }

}
