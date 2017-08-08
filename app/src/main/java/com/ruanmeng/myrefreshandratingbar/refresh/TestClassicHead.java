package com.ruanmeng.myrefreshandratingbar.refresh;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.RecyclerView;
import android.widget.LinearLayout;

import com.ruanmeng.myrefreshandratingbar.R;
import com.ruanmeng.myrefreshandratingbar.adapter.BaseRecyclerAdapter;
import com.ruanmeng.myrefreshandratingbar.adapter.RecyclerViewHolder;
import com.ruanmeng.myrefreshandratingbar.adapter.WrapContentLinearLayoutManager;
import com.ruanmeng.myrefreshandratingbar.refresh.myhead.BaseHeader;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.footer.ClassicsFooter;
import com.scwang.smartrefresh.layout.header.ClassicsHeader;
import com.scwang.smartrefresh.layout.listener.OnLoadmoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;

import java.util.ArrayList;
import java.util.List;

/**
 * 经典样式的动画
 */
public class TestClassicHead extends Activity {

    private LinearLayout activityTestBeatyHead;
    private SmartRefreshLayout refreshLayout;
    private RecyclerView rlvMy;
    private MyAdapter adapter = null;
    private List<String> mlist_data = new ArrayList<>();

    ClassicsHeader classicsHeader = null;  // 设置在base中 的 经典动画
    BaseHeader baseHeader = null;  // 设置在 base 中的   帧动画
    private NestedScrollView layScroll;

    ClassicsFooter classicsFooter = null;
    boolean issuccess = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        requestWindowFeature(Window.FEATURE_NO_TITLE);

        setContentView(R.layout.activity_test_classic_head);
        initView();
    }


    private void initView() {
        activityTestBeatyHead = (LinearLayout) findViewById(R.id.activity_test_beaty_head);
        refreshLayout = (SmartRefreshLayout) findViewById(R.id.refreshLayout);
        rlvMy = (RecyclerView) findViewById(R.id.rlv_my);
        for (int i = 0; i < 10; i++) {
            mlist_data.add("iam " + i);
        }
        WrapContentLinearLayoutManager laym01 = new WrapContentLinearLayoutManager(TestClassicHead.this);
        rlvMy.setLayoutManager(laym01);
        rlvMy.setItemAnimator(new DefaultItemAnimator());
        adapter = new MyAdapter(TestClassicHead.this, mlist_data);
        rlvMy.setAdapter(adapter);


        //设置 Header 为 Material风格
        classicsHeader = new ClassicsHeader(this);
//        classicsHeader.setSpinnerStyle(SpinnerStyle.FixedBehind);  // 设置为 底层显示
//        baseHeader = new BaseHeader(this);
        refreshLayout.setRefreshHeader(classicsHeader);
//设置 Footer 为 球脉冲
        classicsFooter = new ClassicsFooter(this);
//        classicsFooter.setSpinnerStyle(SpinnerStyle.FixedBehind);
        refreshLayout.setRefreshFooter(classicsFooter);

        // TODO: 2017/8/8  补救方法 设置 刷新背景色 和 主色调一直  避免出现 顶部的 横条
        // 第一个 是 头的 背景刷新 颜色  第二个参数是  字体的颜色
//        refreshLayout.setPrimaryColorsId(R.color.colorPrimaryDark, android.R.color.white);
//        refreshLayout.getLayout().setBackgroundResource(R.color.mPrimary); //这个应该是 底部刷新的 颜色


        refreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(final RefreshLayout refreshlayout) {
// 延迟15秒

                index = 1;
                new Handler().postDelayed(new Runnable() {
                    public void run() {
                        mlist_data.clear();
                        for (int i = 0; i < 10; i++) {
                            mlist_data.add("iam" + i);
                        }
                        adapter.notifyDataSetChanged();
//                        refreshlayout.finishRefresh();
                        issuccess = !issuccess;
                        refreshlayout.finishRefresh(issuccess);
                    }

                }, 2 * 1000);
            }
        });
        refreshLayout.setOnLoadmoreListener(new OnLoadmoreListener() {
            @Override
            public void onLoadmore(final RefreshLayout refreshlayout) {
                index++;
                new Handler().postDelayed(new Runnable() {
                    public void run() {
                        mlist_data.add("社会王" + index);
                        mlist_data.add("奥特曼" + index);
                        mlist_data.add("加特林" + index);
                        mlist_data.add("印度三哥" + index);
                        adapter.notifyItemRangeInserted(mlist_data.size() - 2, mlist_data.size());
                        adapter.notifyDataSetChanged();
//                        refreshlayout.finishLoadmore();
                        issuccess = !issuccess;
                        refreshlayout.finishLoadmore(issuccess);

                    }
                }, 2 * 1000);

            }
        });


        layScroll = (NestedScrollView) findViewById(R.id.lay_scroll);
        layScroll.setOnScrollChangeListener(new NestedScrollView.OnScrollChangeListener() {
            @Override
            public void onScrollChange(NestedScrollView v, int scrollX, int scrollY, int oldScrollX, int oldScrollY) {
            }
        });
    }

    int index = 0;


    private class MyAdapter extends BaseRecyclerAdapter<String> {

        public MyAdapter(Context ctx, List<String> list) {
            super(ctx, list);
        }

        @Override
        protected int getItemLayoutId(int viewType) {
            return R.layout.item_myrefresh;
        }

        @Override
        protected void bindData(RecyclerViewHolder holder, int position, String item) {

            holder.setText(R.id.tv_item, item);
        }
    }

}
