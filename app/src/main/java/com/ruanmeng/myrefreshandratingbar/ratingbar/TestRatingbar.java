package com.ruanmeng.myrefreshandratingbar.ratingbar;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.ruanmeng.myrefreshandratingbar.R;

public class TestRatingbar extends AppCompatActivity {

    private LinearLayout activityTestRatingbar;
    private TextView tv;
    private MyRatingBar ratingbar;
    private TextView tv2;
    private MyRatingBar ratingbar2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_ratingbar);
        initView();
        // TODO: 2017/7/27  如果全屏的话
//        计算屏幕的宽度  -5* 图片的 宽度= 剩余的宽度
//        间隔自动重置为  （2* 图片个数） 的份数    左右 各占一份     两个图片中间 间距为 2份
    }

    private void initView() {
        activityTestRatingbar = (LinearLayout) findViewById(R.id.activity_test_ratingbar);
        tv = (TextView) findViewById(R.id.tv);
        ratingbar = (MyRatingBar) findViewById(R.id.ratingbar);
        tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(TestRatingbar.this, ratingbar.getStar() + "", Toast.LENGTH_SHORT).show();
            }
        });
        tv2 = (TextView) findViewById(R.id.tv2);
        ratingbar2 = (MyRatingBar) findViewById(R.id.ratingbar2);
        tv2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(TestRatingbar.this, ratingbar2.getStar() + "", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
