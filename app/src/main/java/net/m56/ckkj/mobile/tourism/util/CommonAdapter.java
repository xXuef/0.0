package net.m56.ckkj.mobile.tourism.util;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import java.util.List;

/**
 * Created by dragonrong on 2017/7/12.
 */

public abstract class CommonAdapter<T> extends BaseAdapter {
    private Context context;
    protected List<T> mDatas;
    private int layoutId;

    public CommonAdapter(Context context, List<T> mDatas, int layoutId) {
        this.context = context;
        this.mDatas = mDatas;
        this.layoutId = layoutId;
    }

    @Override
    public int getCount() {
        return mDatas.size();
    }

    @Override
    public T getItem(int position) {
        return mDatas.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent){
        ViewHolder viewHolder = ViewHolder.get(context,convertView,parent,layoutId,position);

        convert(viewHolder,getItem(position));
        return viewHolder.getmConvertView();
    }
    public abstract void convert(ViewHolder viewHolder,T t);
}
