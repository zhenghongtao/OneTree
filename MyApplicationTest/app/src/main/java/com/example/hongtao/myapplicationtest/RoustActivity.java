package com.example.hongtao.myapplicationtest;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

import com.meituan.robust.patch.RobustModify;
import com.meituan.robust.patch.annotaion.Add;
import com.meituan.robust.patch.annotaion.Modify;

/**
 * Created by hongtao on 17/7/28.
 */
public class RoustActivity extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        RobustModify.modify();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.content_main);
//        TextView mTex = (TextView) findViewById(R.id.yidao);
//        mTex.setText(newAdd());
    }

    public String getRoustActivityString() {
        //RobustModify.modify();
        return "美团";
    }

    @Add
    public String newAdd(){
        return "测试成功了";
    }


}
