package net.m56.ckkj.mobile.tourism.fragment;

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

import net.m56.ckkj.mobile.tourism.Api.Url;
import net.m56.ckkj.mobile.tourism.activity.NewsInfoActivity;
import net.m56.ckkj.mobile.tourism.activity.ViewPagerWebActivity;
import net.m56.ckkj.mobile.tourism.base.activity.CaptureActivity;
import net.m56.ckkj.mobile.tourism.base.farment.BaseFragment;
import net.m56.ckkj.mobile.tourism.bean.StrategyBean;
import net.m56.ckkj.mobile.tourism.okhttputil.BaseCallback;
import net.m56.ckkj.mobile.tourism.okhttputil.OkHttpHelper;
import net.m56.ckkj.tourism.tourism.R;

import java.util.ArrayList;

import okhttp3.Call;
import okhttp3.Request;
import okhttp3.Response;

/**
 * 2017/10/10 17:03
 */

public class GlFragment extends BaseFragment {


    public ArrayList<String> titleUrlArrayList;
    public ArrayList<String> webUrlArrayList;
    public ArrayList<String> imUrlArrayList;
    public ListView listview;


    @Override
    protected int getLayoutId() {
        return R.layout.glfragment;
    }

    @Override
    protected void initParams(View view, Bundle savedInstanceState) {

        TextView textView = (TextView) view.findViewById(R.id.title_name);
        textView.setText("旅游攻略");
        listview = (ListView) view.findViewById(R.id.gl_listview);
        ImageView erweima = (ImageView) view.findViewById(R.id.erweima);
        erweima.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(), CaptureActivity.class));
            }
        });
        ImageView xiaoxi = (ImageView) view.findViewById(R.id.xiaoxi);
        xiaoxi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getContext(), NewsInfoActivity.class));
            }
        });
        OkHttpHelper.getInstance().post(Url.StrategyUrl, null, new BaseCallback<StrategyBean>() {
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
            public void onSuccess(Response response, StrategyBean strategyBean) {
                imUrlArrayList = new ArrayList<>();
                webUrlArrayList = new ArrayList<>();
                titleUrlArrayList = new ArrayList<>();
                for (int i = 0; i < strategyBean.message.rows.size(); i++) {
                    String imgUrl = strategyBean.message.rows.get(i).imgUrl;
                    String stratetitle = strategyBean.message.rows.get(i).stratetitle;
                    String webUrl = strategyBean.message.rows.get(i).webUrl;
                    imUrlArrayList.add(imgUrl);
                    titleUrlArrayList.add(stratetitle);
                    webUrlArrayList.add(webUrl);
                }

                listview.setAdapter(new myadapter());
                listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        Intent intent = new Intent(getContext(), ViewPagerWebActivity.class);
                        intent.putExtra("Viewpager0", webUrlArrayList.get(position));
                        intent.putExtra("titlename", "攻略");
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
        @Override
        public int getCount() {
            return imUrlArrayList.size();
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
            public ImageView img;
            public TextView name;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            ViewHolder holder;
            if (convertView == null) {
                holder = new ViewHolder();
                convertView = LayoutInflater.from(getContext()).inflate(R.layout.strategy_item, null);
                holder.img = (ImageView) convertView.findViewById(R.id.imagview);
                holder.name = (TextView) convertView.findViewById(R.id.textView);
                convertView.setTag(holder);
            } else {
                holder = (ViewHolder) convertView.getTag();
            }
            holder.name.setText(titleUrlArrayList.get(position));
            //因为与priceter冲突
            RequestOptions options = new RequestOptions();
            options.placeholder(R.mipmap.lodding);
            //glide
            Glide.with(getContext())
                    .load(imUrlArrayList.get(position))
                    .apply(options)
                    .into(holder.img);
            return convertView;
        }
    }
}
