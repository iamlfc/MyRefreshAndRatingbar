package com.ruanmeng.myrefreshandratingbar.refresh;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

import com.ruanmeng.myrefreshandratingbar.R;

public class TestRefresh extends AppCompatActivity {

    private LinearLayout activityTestRefresh;
    private Button btnHead01;
    private Button btnHead02;
    private Button btnHead03;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_refresh);
        initView();
    }

    private void initView() {
        activityTestRefresh = (LinearLayout) findViewById(R.id.activity_test_refresh);
        btnHead01 = (Button) findViewById(R.id.btn_head01);
        btnHead02 = (Button) findViewById(R.id.btn_head02);
        btnHead03 = (Button) findViewById(R.id.btn_head03);
        btnHead01.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(TestRefresh.this, TestClassicHead.class));
            }

        });
        btnHead02.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(TestRefresh.this, TextBaseBeatyHead.class));

            }
        });
        btnHead03.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(TestRefresh.this, TestBeatyHead.class));

            }
        });
    }
}
