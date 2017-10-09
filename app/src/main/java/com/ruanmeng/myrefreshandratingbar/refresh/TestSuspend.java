package com.ruanmeng.myrefreshandratingbar.refresh;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.ruanmeng.myrefreshandratingbar.R;
import com.ruanmeng.myrefreshandratingbar.adapter.BaseRecyclerAdapter;
import com.ruanmeng.myrefreshandratingbar.adapter.RecyclerViewHolder;
import com.ruanmeng.myrefreshandratingbar.adapter.WrapContentLinearLayoutManager;
import com.ruanmeng.myrefreshandratingbar.toolbar.MyTestToolbar;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;

import java.util.ArrayList;
import java.util.List;

/**
 * 顶部悬浮demo
 */
public class TestSuspend extends AppCompatActivity {

    private SmartRefreshLayout activityTestSuspend;
    private TextView topView;
    private RecyclerView rlvMy;
    private TextView floatView;
    private MyAdapter adapter = null;
    private List<String> mlist_data = new ArrayList<>();
    private NestedScrollView layScroll;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_suspend);
        initView();
    }

    private void initView() {
        activityTestSuspend = (SmartRefreshLayout) findViewById(R.id.activity_test_suspend);
        topView = (TextView) findViewById(R.id.top_view);
        rlvMy = (RecyclerView) findViewById(R.id.rlv_my);
        floatView = (TextView) findViewById(R.id.float_view);

        for (int i = 0; i < 20; i++) {
            mlist_data.add("iam " + i);
        }
        WrapContentLinearLayoutManager laym01 = new WrapContentLinearLayoutManager(TestSuspend.this);
        laym01.setSmoothScrollbarEnabled(true);
        laym01.setAutoMeasureEnabled(true);

        rlvMy.setLayoutManager(laym01);
        rlvMy.setHasFixedSize(true);
        rlvMy.setNestedScrollingEnabled(false);
        rlvMy.setItemAnimator(new DefaultItemAnimator());
        adapter = new MyAdapter(TestSuspend.this, mlist_data);
        rlvMy.setAdapter(adapter);

        rlvMy.setFocusable(false);
//        topView.setFocusable(true);
        layScroll = (NestedScrollView) findViewById(R.id.lay_scroll);
        layScroll.setOnScrollChangeListener(new NestedScrollView.OnScrollChangeListener() {
            @Override
            public void onScrollChange(NestedScrollView v, int scrollX, int scrollY, int oldScrollX, int oldScrollY) {
                Log.d("test", scrollY + "");
                if (topView != null) {
                    if (scrollY > topView.getHeight()) {//Y轴上滑位移大于顶部tab高度时
                        floatView.setVisibility(View.VISIBLE);
                    } else {
                        floatView.setVisibility(View.GONE);
                    }
                }
            }
        });

    }

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
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    startActivity(new Intent(TestSuspend.this, MyTestToolbar.class));
                }
            });
        }
    }
}
