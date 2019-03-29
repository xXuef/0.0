package net.m56.ckkj.mobile.tourism.util;

import android.content.Context;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by dragonrong on 2017/7/11.
 */

public class ViewHolder {

    private SparseArray<View> mView;
    private int mPosition;
    private View mConvertView;

    public ViewHolder(Context context, ViewGroup parent, int layoutId, int position){

        this.mPosition=position;
        this.mView=new SparseArray<View>();
        mConvertView= LayoutInflater.from(context).inflate(layoutId,parent,false);
        mConvertView.setTag(this);
    }
    public static ViewHolder get(Context context, View convertView, ViewGroup parent, int layoutId, int position){

        if (convertView==null){
            return new ViewHolder(context,parent,layoutId,position);
        }else {
            ViewHolder viewHolder= (ViewHolder) convertView.getTag();
            viewHolder.mPosition=position;
            return viewHolder;
        }
    }

    public <T extends View> T getView(int viewId){
        View view=mView.get(viewId);
        if (view==null){
            view=mConvertView.findViewById(viewId);
            mView.put(viewId,view);
        }
        return (T) view;
    }

    public View getmConvertView(){
        return mConvertView;
    }
}
