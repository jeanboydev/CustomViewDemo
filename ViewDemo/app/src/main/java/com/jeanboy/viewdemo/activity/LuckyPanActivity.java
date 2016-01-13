package com.jeanboy.viewdemo.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;

import com.jeanboy.viewdemo.R;
import com.jeanboy.viewdemo.view.LuckyPanView;

public class LuckyPanActivity extends AppCompatActivity {

    private LuckyPanView mLuckyPan;
    private ImageView mStartBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lucky_pan);
        mLuckyPan = (LuckyPanView) findViewById(R.id.luckyPan);
        mStartBtn = (ImageView) findViewById(R.id.start_btn);

        mStartBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!mLuckyPan.isStart()) {
                    mLuckyPan.luckyStart(1);
                    mStartBtn.setImageResource(R.drawable.stop);
                } else {
                    if (!mLuckyPan.isShouldEnd()) {
                        mLuckyPan.luckyEnd();
                        mStartBtn.setImageResource(R.drawable.start);
                    }
                }
            }
        });
    }

}
