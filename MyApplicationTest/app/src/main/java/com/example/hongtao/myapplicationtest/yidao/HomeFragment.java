package com.example.hongtao.myapplicationtest.yidao;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.example.hongtao.myapplicationtest.R;
import java.util.ArrayList;
import java.util.List;
import butterknife.BindView;
import butterknife.ButterKnife;
/**
 * Created by hongtao on 17/2/17.
 */

public class HomeFragment extends Fragment implements IHomePageView{

    final String TAG="HomeFragment";
    @BindView(R.id.msg_title)
    TextView msgTitle;
    @BindView(R.id.msg_title_layout)
    RelativeLayout msgTitleLayout;
    @BindView(R.id.main_layout_bg)
    RelativeLayout mainLayoutBg;
    @BindView(R.id.viewpage)
    ViewPager viewpage;
    @BindView(R.id.top_layout)
    RelativeLayout topLayout;
    @BindView(R.id.right_layout)
    LinearLayout rightLayout;
    HomePagePresenter mHomePagePresenter;
    VerticalMenuView right_menu_view;
    VerticalMenuView left_menu_view;
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = LayoutInflater.from(getActivity()).inflate(R.layout.fragment_home_main_layout, container, false);
        ButterKnife.bind(this, view);
        initwidthheightdata();
        initViewPage();
        mHomePagePresenter=new HomePagePresenter(this,getActivity());
        mHomePagePresenter.initData();
        return view;
    }


    public void initViewPage() {
        left_menu_view= new VerticalMenuView(getActivity());
        left_menu_view.setDefultText("消息中心");
        View viewcenter = View.inflate(getActivity(), R.layout.pageview_center_item, null);
        right_menu_view = new VerticalMenuView(getActivity());
        left_menu_view.setDefultText("业务中心");
        List<View> myview = new ArrayList<>();
        myview.add(left_menu_view);
        myview.add(viewcenter);
        myview.add(right_menu_view);
        MultiplePagerAdapter m = new MultiplePagerAdapter(getActivity(), myview);
        viewpage.setAdapter(m);
        viewpage.setOffscreenPageLimit(3);
        viewpage.setPageMargin(0);
        viewpage.setCurrentItem(1);

        initViewPageHeight();
        viewpage.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                Log.e("view", "position=" + position + ",positionOffset=" + positionOffset + ",positionOffsetPixels=" + positionOffsetPixels);
                if (position == 0) {
                    setViewPageHeight(position,positionOffset);
                    MainPageAnimatorUtils.viewPageMoveAnimator(topLayout, rightLayout,msgTitleLayout, positionOffset);
                    mainLayoutBg.setAlpha(1-positionOffset);
                    left_menu_view.startAnimation(positionOffset);
                } else if (position == 1) {
                    setViewPageHeight(position,1 - positionOffset);
                    right_menu_view.startAnimation(1 - positionOffset);
                    mainLayoutBg.setAlpha(positionOffset);
                    MainPageAnimatorUtils.viewPageMoveAnimator(topLayout, rightLayout,1 - positionOffset);
                }
            }

            @Override
            public void onPageSelected(int position) {
                if (position == 2) {

                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }


    int windowwidth;
    int windowheight;
    int verticalMenumaxHeight=0;
    int viewpage_height = 0;
    public void initwidthheightdata() {
        WindowManager wm = (WindowManager) getActivity().getSystemService(Context.WINDOW_SERVICE);
        windowwidth = wm.getDefaultDisplay().getWidth();
        windowheight = wm.getDefaultDisplay().getHeight();
        RelativeLayout.LayoutParams marginLayoutParams = (RelativeLayout.LayoutParams) msgTitleLayout.getLayoutParams();
        verticalMenumaxHeight=windowheight-marginLayoutParams.topMargin-marginLayoutParams.height;
        Log.e(TAG,"verticalMenumaxHeight="+verticalMenumaxHeight);
    }
    public void initViewPageHeight() {
        RelativeLayout.LayoutParams m = (RelativeLayout.LayoutParams) viewpage.getLayoutParams();
        m .height = UiUtils.dip2pxLe(getContext(),130);
        viewpage_height=m .height;
        viewpage.setLayoutParams(m);

    }
    public void buttonGooutonclick() {
        MainPageAnimatorUtils.pageGoOutAnimator(topLayout, viewpage, rightLayout);
    }


    public void buttononclick() {
        MainPageAnimatorUtils.pageGoInAnimator(topLayout, viewpage, rightLayout);
    }
    public void setViewPageHeight(int postion,float n) {
        RelativeLayout.LayoutParams m = (RelativeLayout.LayoutParams) viewpage.getLayoutParams();
        if(n<1){
            m.height= windowheight;
        }else{
            m.height=viewpage_height;
        }
        if(m.height>verticalMenumaxHeight && postion==0 ){
            m.height= verticalMenumaxHeight;
        }
        viewpage.setLayoutParams(m);
    }

    @Override
    public VerticalMenuView getLeftMenuView() {
          return left_menu_view;
    }

    @Override
    public VerticalMenuView getRightMenuView() {
          return right_menu_view;
    }

    @Override
    public ViewPager getViewPage() {
        return viewpage;
    }
}
