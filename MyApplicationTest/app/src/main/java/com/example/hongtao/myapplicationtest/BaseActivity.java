package com.example.hongtao.myapplicationtest;

import android.app.Activity;
import android.os.Bundle;
import android.os.PersistableBundle;

import com.androidfix.hongtao.base_config_lib.MenuUtilTool;

import butterknife.OnClick;

/**
 * Created by hongtao on 17/2/7.
 */

public class BaseActivity extends Activity{
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        MenuUtilTool.initSystemBar(this,R.color.red);
    }
}
