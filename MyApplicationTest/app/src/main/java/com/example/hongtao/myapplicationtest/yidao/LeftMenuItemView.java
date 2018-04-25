package com.example.hongtao.myapplicationtest.yidao;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.util.AttributeSet;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.hongtao.myapplicationtest.R;

import static java.security.AccessController.getContext;

/**
 * Created by hongtao on 17/2/16.
 */

public class LeftMenuItemView extends CardView{
    public LeftMenuItemView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public LeftMenuItemView(Context context, AttributeSet attrs) {
        super(context, attrs,0);
        initView();
        // TODO Auto-generated constructor stub
    }
    public LeftMenuItemView(Context context) {
        super(context,null,0);
        initView();
        // TODO Auto-generated constructor stub
    }
    TextView tv_content;

    public void initView(){
        inflate(getContext(), R.layout.left_menu_item_view, this);
        tv_content= (TextView) findViewById(R.id.tv_content);
        setCardElevation(UiUtils.dip2pxLe(getContext(),2));
        setRadius(UiUtils.dip2pxLe(getContext(),10));
        setBackgroundDrawable(getContext().getResources().getDrawable(R.drawable.vm_item_bg_shadow));
        setCardBackgroundColor(getResources().getColor(R.color.white));
    }

    public void setText(String str){
        tv_content.setText(str);
    }

}
