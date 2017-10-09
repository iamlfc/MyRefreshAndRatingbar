package com.ruanmeng.myrefreshandratingbar.toolbar;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.LinearLayout;

import com.ruanmeng.myrefreshandratingbar.R;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;

public class MyTestToolbar extends AppCompatActivity {

    private LinearLayout activityMyTestToolbar;
    private WaterfallToolbar waterfallToolbar;
    private Toolbar toolbar;
    private SmartRefreshLayout layRefresh;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_test_toolbar);
        initView();
    }

    private void initView() {
        activityMyTestToolbar = (LinearLayout) findViewById(R.id.activity_my_test_toolbar);
        waterfallToolbar = (WaterfallToolbar) findViewById(R.id.waterfall_toolbar);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        layRefresh = (SmartRefreshLayout) findViewById(R.id.lay_refresh);
        layRefresh.setEnableRefresh(false);
        layRefresh.setEnableLoadmore(false);
        waterfallToolbar.addMyRefreshLayout(layRefresh)
                .setInitialElevation(3) //初始高度
                .setFinalElevation(5) // 最终
                .setScrollFinalPosition(5);// 滑动最终
//        setSupportActionBar(toolbar);
    }
}
