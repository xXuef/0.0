package net.m56.ckkj.mobile.tourism.fragment;

import android.os.Bundle;
import android.view.View;
import android.widget.ListView;

import net.m56.ckkj.mobile.tourism.adapter.ListViewGvJqAdapter;
import net.m56.ckkj.mobile.tourism.base.farment.BaseFragment;
import net.m56.ckkj.mobile.tourism.bean.GvJqBean;
import net.m56.ckkj.tourism.tourism.R;

import java.util.List;

/**
 * Created by dragonrong on 2017/8/8.
 */

public class GvGlFragment extends BaseFragment {

    //ListView
    private ListView listView;
    private ListViewGvJqAdapter mAdapter;
    private List<GvJqBean> mData;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_gv_gl;
    }

    @Override
    protected void initParams(View view, Bundle savedInstanceState) {


    }


}
