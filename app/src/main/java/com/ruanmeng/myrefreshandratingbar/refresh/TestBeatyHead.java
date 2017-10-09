package com.ruanmeng.myrefreshandratingbar.refresh;

import android.content.Context;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.RecyclerView;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.ruanmeng.myrefreshandratingbar.R;
import com.ruanmeng.myrefreshandratingbar.adapter.BaseRecyclerAdapter;
import com.ruanmeng.myrefreshandratingbar.adapter.RecyclerViewHolder;
import com.ruanmeng.myrefreshandratingbar.adapter.WrapContentLinearLayoutManager;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.footer.ClassicsFooter;
import com.scwang.smartrefresh.layout.listener.OnLoadmoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;

import java.util.ArrayList;
import java.util.List;

/**
 * 通过布局添加 刷新头帧动画
 */
public class TestBeatyHead extends AppCompatActivity {
    private LinearLayout activityMyTestRefresh;
    private SmartRefreshLayout refreshLayout;
    private RecyclerView rlvMy;
    private MyAdapter adapter = null;
    private List<String> mlist_data = new ArrayList<>();
    private ImageView imgAnimal;
    AnimationDrawable spinner = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_beaty_head);
        initView();
    }
    
    private void initView() {
//        activityMyTestRefresh = (LinearLayout) findViewById(R.id.activity_my_test_refresh);
        refreshLayout = (SmartRefreshLayout) findViewById(R.id.refreshLayout);
        rlvMy = (RecyclerView) findViewById(R.id.rlv_my);
        for (int i = 0; i < 10; i++) {
            mlist_data.add("iam " + i);
        }
        WrapContentLinearLayoutManager laym01 = new WrapContentLinearLayoutManager(TestBeatyHead.this);
        rlvMy.setLayoutManager(laym01);
        rlvMy.setItemAnimator(new DefaultItemAnimator());
        adapter = new MyAdapter(TestBeatyHead.this, mlist_data);
        rlvMy.setAdapter(adapter);


        refreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(final RefreshLayout refreshlayout) {
// 延迟15秒
                // 开始动画
                spinner.start();
                index = 1;
                new Handler().postDelayed(new Runnable() {
                    public void run() {
                        mlist_data.clear();
                        for (int i = 0; i < 10; i++) {
                            mlist_data.add("iam" + i);
                        }
                        adapter.notifyDataSetChanged();
                        refreshlayout.finishRefresh();
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
                        adapter.notifyItemRangeInserted(mlist_data.size() - 2, mlist_data.size());
                        adapter.notifyDataSetChanged();
                        refreshlayout.finishLoadmore();

                        // 开始动画
                        spinner.stop();
                    }
                }, 2 * 1000);

            }
        });

        imgAnimal = (ImageView) findViewById(R.id.img_animal);
        // 获取ImageView上的动画背景
        spinner = (AnimationDrawable) imgAnimal.getBackground();

        refreshLayout.setRefreshFooter(new ClassicsFooter(this));
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
