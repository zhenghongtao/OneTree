package com.example.hongtao.myapplicationtest.popwindow;

import android.app.Activity;
import android.content.DialogInterface;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.ColorDrawable;
import android.util.Log;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.example.hongtao.myapplicationtest.R;

/**
 * Created by hongtao on 16/7/28.
 */
public class TestPop extends PopupWindow{
    Activity mActivity;
    View contentView;
    RelativeLayout layoutRl;
    public TestPop(Activity mActivity){
        this.mActivity = mActivity;
        contentView = LayoutInflater.from(mActivity).inflate( R.layout.content_main_2, null,true);
        setContentView(contentView);
        layoutRl = (RelativeLayout) contentView.findViewById(R.id.layoutRl);
        setWidth(ViewGroup.LayoutParams.MATCH_PARENT);
        setHeight(ViewGroup.LayoutParams.MATCH_PARENT);
        setOutsideTouchable(true);
        setFocusable(true);
        setTouchable(true);
        contentView.setFocusable(true);
        contentView.setFocusableInTouchMode(true);
        layoutRl.setFocusable(true);
        layoutRl.setFocusableInTouchMode(true);
        ColorDrawable dw = new ColorDrawable(00000);
        setBackgroundDrawable(new BitmapDrawable());
        layoutRl.setOnKeyListener(mOnKeyListener);
        contentView.setOnKeyListener(mOnKeyListener);
        contentView.findViewById(R.id.test1).requestFocus();
        contentView.findViewById(R.id.test1).setOnKeyListener(mOnKeyListener);
        contentView.findViewById(R.id.test2).setOnKeyListener(mOnKeyListener);
    }

    View.OnKeyListener mOnKeyListener=new View.OnKeyListener(){

        @Override
        public boolean onKey(View v, int keyCode, KeyEvent event) {
            return false;
        }
    };
    public void show(){
        if( !mActivity.isFinishing() && !isShowing()){
            super.showAtLocation(mActivity.getWindow().getDecorView(), Gravity.CENTER, 0, 0);
        }
    }
}
