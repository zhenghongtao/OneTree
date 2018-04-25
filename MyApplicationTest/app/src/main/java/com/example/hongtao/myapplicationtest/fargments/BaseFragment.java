package com.example.hongtao.myapplicationtest.fargments;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.widget.TextView;
import com.example.hongtao.myapplicationtest.R;
import com.example.hongtao.myapplicationtest.animation.AnimationUtil;
/**
 * Created by hongtao on 17/10/13.
 */

public class BaseFragment extends Fragment implements View.OnClickListener {
    static boolean move_to_right = false;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }


    TextView tv_title1,tv_title2;
    View ll_content_layout;
    public String getTitle() {
        return "马上用车";
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = LayoutInflater.from(getActivity()).inflate(R.layout.fragment_home_layout, container, false);
        tv_title1 = (TextView) view.findViewById(R.id.tv_title1);
        tv_title1.setOnClickListener(this);
        tv_title1.setText(getTitle());
        tv_title2 = (TextView) view.findViewById(R.id.tv_title2);
        tv_title2.setOnClickListener(this);
        ll_content_layout=view.findViewById(R.id.ll_content_layout);
        initView();
        return view;
    }

    public void initView() {

    }

    public void initData() {

    }


    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initData();
    }

    @Override
    public Animation onCreateAnimation(int transit, boolean enter, int nextAnim) {
        //return super.onCreateAnimation(transit, enter, nextAnim);
        return AnimationUtil.getAnimation(getContext(), enter, move_to_right ? AnimationUtil.ENTER_FROM_LEFT : AnimationUtil.ENTER_FROM_RIGHT);
    }

    @Override
    public void onClick(View v) {

    }
}
