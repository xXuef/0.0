package net.m56.ckkj.mobile.tourism.fragment;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.google.gson.Gson;

import net.m56.ckkj.mobile.tourism.Api.Url;
import net.m56.ckkj.mobile.tourism.base.farment.BaseFragment;
import net.m56.ckkj.mobile.tourism.bean.NewsInfoBean;
import net.m56.ckkj.tourism.tourism.R;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * 2017/10/19 16:53
 */

public class SystemInfoFragment extends BaseFragment {

    OkHttpClient client = new OkHttpClient();
    List<String> newsTitle;
    List<String> newsDesc;
    public int itemcount;

    Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);

            switch (msg.what) {
                case 11111:

                    break;
            }
        }
    };

    @Override
    protected int getLayoutId() {
        return R.layout.activityinfofragment;
    }

    @Override
    protected void initParams(View view, Bundle savedInstanceState) {
        final ListView listview = (ListView) view.findViewById(R.id.listview);

        newsTitle = new ArrayList<>();
        newsDesc = new ArrayList<>();
        MultipartBody.Builder builder = new MultipartBody.Builder().setType(MultipartBody.FORM);
        builder.addFormDataPart("type", "公告");
        MultipartBody requesBody = builder.build();
        Request request = new Request.Builder().url(Url.NewsInfo)
                .post(requesBody).build();
        Call call = client.newCall(request);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                System.out.println("异常" + e.getMessage().toString());
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String s = response.body().string();
                Gson gson = new Gson();
                NewsInfoBean newsInfo = gson.fromJson(s, NewsInfoBean.class);

                itemcount = newsInfo.message.count;
                for (int i = 0; i < newsInfo.message.count; i++) {
                    String title = newsInfo.message.rows.get(i).title;
                    String desc = newsInfo.message.rows.get(i).cont;
                    newsTitle.add(title);
                    newsDesc.add(desc);
                }
                //如果请求成功的话 code会是0
                if (newsInfo.code == 0) {
                    getActivity().runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            listview.setAdapter(new BaseAdapter() {
                                @Override
                                public int getCount() {
                                    return itemcount;
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
                                    ImageView imageView;
                                    TextView title;
                                    TextView desc;
                                }

                                @Override
                                public View getView(int position, View convertView, ViewGroup parent) {
                                    ViewHolder holder;
                                    if (convertView == null) {
                                        holder = new ViewHolder();
                                        convertView = LayoutInflater.from(getContext()).inflate(R.layout.infoitem, null);
                                        holder.imageView = (ImageView) convertView.findViewById(R.id.imagview);
                                        holder.title = (TextView) convertView.findViewById(R.id.title);
                                        holder.desc = (TextView) convertView.findViewById(R.id.desc);
                                        convertView.setTag(holder);
                                    } else {
                                        holder = (ViewHolder) convertView.getTag();
                                    }
                                    Glide.with(getContext()).load("http://123.57.247.239/android/smart_tourism/banner/img/banner2.jpg").into(holder.imageView);
                                    holder.title.setText(newsTitle.get(position));
                                    holder.desc.setText(newsDesc.get(position));
                                    return convertView;
                                }
                            });
                        }
                    });
                }
            }
        });
    }
}
