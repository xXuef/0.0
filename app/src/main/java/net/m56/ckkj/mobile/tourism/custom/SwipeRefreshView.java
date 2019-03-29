package net.m56.ckkj.mobile.tourism.custom;

import android.content.Context;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v4.widget.SwipeRefreshLayout;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.widget.AbsListView;
import android.widget.HeaderViewListAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;

import net.m56.ckkj.tourism.tourism.R;


/**
 * Create by 月 on 2017/5/2.
 */

public class SwipeRefreshView extends SwipeRefreshLayout implements AbsListView.OnScrollListener {
    /**
     * 滑动到最下面时的上拉操作
     */
    // private int mTouchSlop;
    /**
     * listview实例
     */
    private ListView mListView;
    /**
     * 上拉监听器, 到了最底部的上拉加载操作
     */
    private OnLoadListener mOnLoadListener;
    /**
     * ListView的加载中footer
     */
    private View mListViewFooter;
    /**
     * 按下时的y坐标
     */
    private int mYDown;
    /**
     * 抬起时的y坐标, 与mYDown一起用于滑动到底部时判断是上拉还是下拉
     */
    private int mLastY;
    /**
     * 是否在加载中 ( 上拉加载更多 )
     */
    private boolean isLoading;
    private int mYUp;

    /**
     * @param context
     */
    public SwipeRefreshView(Context context) {
        this(context, null);
    }

    public SwipeRefreshView(Context context, AttributeSet attrs) {
        super(context, attrs);
        mListViewFooter = LayoutInflater.from(context).inflate(R.layout.view_footer, null,
                false);
    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);
        // 初始化ListView对象
        if (mListView == null) {
            getListView();
        }
    }

    /**
     * 获取ListView对象
     */
    private void getListView() {

        int childs = getChildCount();

        if (childs > 0) {
            for (int i = 0; i < childs; i++) {
                View childView = getChildAt(i);
                if (childView instanceof ListView) {
                    mListView = (ListView) childView;
                    // 设置滚动监听器给ListView, 使得滚动的情况下也可以自动加载
                    //          mListView.setOnScrollListener(this);
//                    if (mListView.getFooterViewsCount() == 0) {
//                       mListView.addFooterView(mListViewFooter,null,false);
//                        mListViewFooter.setVisibility(View.GONE);
//                        mListView.deferNotifyDataSetChanged();
//
//                    }
                    return;
                }
            }
        }
    }

    /*
     * (non-Javadoc)
     * @see android.view.ViewGroup#dispatchTouchEvent(android.view.MotionEvent)
     */
    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    @Override
    public boolean dispatchTouchEvent(MotionEvent event) {
        final int action = event.getAction();
        switch (action) {
            case MotionEvent.ACTION_DOWN:
                // 按下
                mYDown = (int) event.getRawY();
                break;
            case MotionEvent.ACTION_MOVE:
                // 移动
                mLastY = (int) event.getRawY();
                break;
            case MotionEvent.ACTION_UP:
                mYUp = (int) event.getRawY();

                // 抬起
                if (canLoad()) {
                    loadData();
                }
                break;
            default:
                break;
        }
        return super.dispatchTouchEvent(event);
    }

    /**
     * 是否可以加载更多, 条件是到了最底部, listview不在加载中, 且为上拉操作.
     *
     * @return
     */
    private boolean canLoad() {
        Log.d("canLoad", "isBottom: " + isBottom());
        Log.d("canLoad", "isLoading: " + !isLoading);
        Log.d("canLoad", "isPullUp: " + isPullUp());

        return isBottom() && !isLoading && isPullUp() && isItemCount();
    }

    public boolean isRefreshing_load() {
        return isLoading;
    }

    private boolean isItemCount() {


        if (mListView.getAdapter().getCount() >= 20)
            return true;

        return false;
    }

    /**
     * 判断是否到了最底部
     */
    private boolean isBottom() {
        if (mListView != null && mListView.getAdapter() != null) {

            return mListView.getLastVisiblePosition() == (mListView.getAdapter().getCount() - 1);
        }
        return false;


    }

    /**
     * 是否是上拉操作
     *
     * @return
     */
    private boolean isPullUp() {
        Log.d("isPullUp", "mYDown" + mYDown);
        Log.d("isPullUp", "mYUp" + mYUp);

        return mYDown - mYUp >= 600;
    }

    /**
     * 如果到了最底部,而且是上拉操作.那么执行onLoad方法
     */

    private void loadData() {
        Log.d("loadData", "loadData");
        if (mOnLoadListener != null) {
            // 设置状态

            setLoading(true);

            mOnLoadListener.onLoad();
        }
    }

    /**
     * @param loading
     */

    public void setLoading(boolean loading) {
        Log.d("loadData", "loadData");
        isLoading = loading;
        mYDown = 0;
        mLastY = 0;
        mYUp = 0;
        if (loading) {
            if (mListView.getFooterViewsCount() == 0) {
                ListAdapter originalAdapter; //得到之前给listview设置的adapter，如果已经知道，可以不用
                if (mListView.getAdapter() instanceof HeaderViewListAdapter) {
                    HeaderViewListAdapter headerViewListAdapter = (HeaderViewListAdapter) mListView.getAdapter();
                    originalAdapter = headerViewListAdapter.getWrappedAdapter();
                } else {
                    originalAdapter = mListView.getAdapter();
                }
                mListView.addFooterView(mListViewFooter, null, false);
                mListView.setAdapter(originalAdapter);
//                mListView.setTranscriptMode(ListView.TRANSCRIPT_MODE_ALWAYS_SCROLL);//滚动模式
                //   mListView.setStackFromBottom(true);                //顺序的显示方式
//                isStackFromBottom()  检查是否为 从下到上 的方式
            }
//            else {
            //       mListViewFooter.setVisibility(VISIBLE);
            //     mListView.setTranscriptMode(ListView.TRANSCRIPT_MODE_ALWAYS_SCROLL);
            //    mListView.setStackFromBottom(true);
//            }
        } else {
            if (mListView.getFooterViewsCount() != 0)
                mListView.removeFooterView(mListViewFooter);
//            mListViewFooter.setVisibility(GONE);
            //    mListView.setSelection(mListView.getAdapter().getCount()-1);
            //     mListView.setTranscriptMode(ListView.TRANSCRIPT_MODE_ALWAYS_SCROLL);
            //     mListView.setStackFromBottom(true);

        }
    }

    /**
     * @param loadListener
     */
    public void setOnLoadListener(OnLoadListener loadListener) {
        mOnLoadListener = loadListener;
    }

    @Override
    public void onScrollStateChanged(AbsListView view, int scrollState) {
    }


    @Override
    public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount,
                         int totalItemCount) {
        // 滚动时到了最底部也可以加载更多

        if (canLoad()) {
            loadData();
        }
    }

    /**
     * 加载更多的监听器
     *
     * @author mrsimple
     */
    public interface OnLoadListener {
        void onLoad();
    }


}
