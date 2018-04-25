package com.example.hongtao.myapplicationtest.clip;

import android.os.Bundle;

import com.example.hongtao.myapplicationtest.BaseActivity;
import com.example.hongtao.myapplicationtest.R;

/**
 * Created by hongtao on 17/4/11.
 */

public class ClipActivity extends BaseActivity {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_clip_test);
        //设置 clipChildren  相对布局不起作用
    }
}
