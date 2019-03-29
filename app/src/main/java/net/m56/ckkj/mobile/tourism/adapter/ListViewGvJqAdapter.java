package net.m56.ckkj.mobile.tourism.adapter;

import android.content.Context;
import android.widget.ImageView;
import android.widget.TextView;

import net.m56.ckkj.mobile.tourism.bean.GvJqBean;
import net.m56.ckkj.mobile.tourism.util.CommonAdapter;
import net.m56.ckkj.mobile.tourism.util.ViewHolder;
import net.m56.ckkj.tourism.tourism.R;

import java.util.List;

/**
 * Created by dragonrong on 2017/6/17.
 */

public class ListViewGvJqAdapter extends CommonAdapter<GvJqBean> {

    public ListViewGvJqAdapter(Context context, List<GvJqBean> datas, int layoutId) {
        super(context,datas,layoutId);
    }

    @Override
    public void convert(ViewHolder viewHolder, GvJqBean gv_jq_bean) {
        ((TextView) viewHolder.getView(R.id.tv_gv_jq)).setText(gv_jq_bean.getName());
        ((ImageView) viewHolder.getView(R.id.image_gv_jq)).setImageResource(gv_jq_bean.getImageId());
    }

}
