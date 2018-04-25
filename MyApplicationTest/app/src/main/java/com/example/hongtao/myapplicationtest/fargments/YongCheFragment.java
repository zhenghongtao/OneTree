package com.example.hongtao.myapplicationtest.fargments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

import com.example.hongtao.myapplicationtest.R;
import com.example.hongtao.myapplicationtest.animation.AnimationUtil;
import com.example.hongtao.myapplicationtest.animation.UIUtils;
import com.example.hongtao.myapplicationtest.managerface.FunctionManager;
import com.example.hongtao.myapplicationtest.managerface.FunctoinException;

/**
 * Created by hongtao on 17/10/13.
 */

public class YongCheFragment extends BaseFragment {

    public static String GET_NAME= YongCheFragment.class.getName()+"_GETNAME";
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    public void initView(){

    }

    @Override
    public String getTitle() {
        try {
            return FunctionManager.getInstance().inVokeFunction(GET_NAME,"123",String.class);
        } catch (FunctoinException e) {
            e.printStackTrace();
        }
        return  null;
    }
    final int defaultHeight=140;
    final int maxHeight=210;
    @Override
    public void onClick(View v) {
        super.onClick(v);
        switch (v.getId()){
            case R.id.tv_title1:
                AnimationUtil.changeViewHeight(ll_content_layout, UIUtils.dip2px(getContext(),defaultHeight));
                break;
            case R.id.tv_title2:
                AnimationUtil.changeViewHeight(ll_content_layout, UIUtils.dip2px(getContext(),maxHeight));
                break;
        }
    }
}
