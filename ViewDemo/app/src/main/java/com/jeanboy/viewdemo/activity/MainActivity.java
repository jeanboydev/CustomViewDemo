package com.jeanboy.viewdemo.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.jeanboy.viewdemo.R;

public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }

    public void simpleView(View v) {
        startActivity(new Intent(this, SimpleViewActivity.class));
    }

    public void qqListDelete(View v) {
        startActivity(new Intent(this, QQListActivity.class));
    }

    public void gestureLock(View v) {
        startActivity(new Intent(this, GestureLockActivity.class));
    }

    public void arcMenu(View v) {
        startActivity(new Intent(this, ArcMenuActivity.class));
    }

    public void luckyPan(View v) {
        startActivity(new Intent(this, LuckyPanActivity.class));
    }

    public void recyclerView(View v) {
        startActivity(new Intent(this, RecyclerViewActivity.class));
    }

    public void weixinui(View v) {
        startActivity(new Intent(this, WeixinUIActivity.class));
    }
}
