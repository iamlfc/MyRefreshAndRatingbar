package com.ruanmeng.myrefreshandratingbar;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

import com.ruanmeng.myrefreshandratingbar.ratingbar.TestRatingbar;
import com.ruanmeng.myrefreshandratingbar.refresh.TestRefresh;

public class MainActivity extends AppCompatActivity {

    private LinearLayout activityMain;
    private Button btnRefresh;
    private Button btnRatarinigbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
    }

    private void initView() {
        activityMain = (LinearLayout) findViewById(R.id.activity_main);
        btnRefresh = (Button) findViewById(R.id.btn_refresh);
        btnRatarinigbar = (Button) findViewById(R.id.btn_ratarinigbar);
        btnRefresh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, TestRefresh.class));
            }
        });
        btnRatarinigbar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, TestRatingbar.class));
            }
        });
    }
}
