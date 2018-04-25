package com.example.hongtao.myapplicationtest.yidao;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.content.Context;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.view.View;
import android.view.WindowManager;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import com.example.hongtao.myapplicationtest.R;

import java.util.ArrayList;
import java.util.List;
/**
 * Created by hongtao on 17/2/10.
 */

public class VerticalMenuView extends RelativeLayout {
    public VerticalMenuView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        // TODO Auto-generated constructor stub
    }
    public VerticalMenuView(Context context, AttributeSet attrs) {
        super(context, attrs,0);
        // TODO Auto-generated constructor stub
    }
    public VerticalMenuView(Context context) {
        super(context,null,0);
        initView();
        // TODO Auto-generated constructor stub
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
    }
    RelativeLayout all_layout;
    ScrollView scrollView;
    View title_view;
    TextView msg_title_text,small_red_dot;
    List<View> contentView=new ArrayList<>();
    List<Integer>  move_item_y=new ArrayList<>();

    public void initView(){
        inflate(getContext(), R.layout.vertical_menu_view, this);
        initwidth();
        all_layout= (RelativeLayout) findViewById(R.id.all_layout);
        scrollView= (ScrollView) findViewById(R.id.scrollView);
        title_view= findViewById(R.id.title_view);
        msg_title_text= (TextView) findViewById(R.id.msg_title_text);
        small_red_dot= (TextView) findViewById(R.id.small_red_dot);
        initcurrheight();
    }
    int windowwidth;
    int windowheight;
    public void initwidth(){
        WindowManager wm = (WindowManager) getContext()
                .getSystemService(Context.WINDOW_SERVICE);
        windowwidth = wm.getDefaultDisplay().getWidth();
        windowheight= wm.getDefaultDisplay().getHeight();
    }
    boolean show_red_dot;
    public void setShowRedDot(boolean show){
        boolean show_red_dot=show;
        if(show_red_dot){
            small_red_dot.setAlpha(1);
        }else{
            small_red_dot.setAlpha(0);
        }
    }

    public void setShowRedDotText(String str){
        small_red_dot.setText(str);
    }



    /**
     * 设置默认item 显示名称
     */
    public void setDefultText(String text){
        msg_title_text.setText(text);
    }

    public LeftMenuItemView getTestView(String text2){
        LeftMenuItemView m=new LeftMenuItemView(getContext());
        RelativeLayout.LayoutParams lparams = new RelativeLayout.LayoutParams(UiUtils.dip2pxLe(getContext(),250), UiUtils.dip2pxLe(getContext(),100));
        lparams.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM);
        m.setLayoutParams(lparams);
        m.setText(text2);
        return m;
    }

    public void testaddManyView(){
        addView(getTestView("测试  1 "));
        addView(getTestView("测试  2 "));
        List<View> testViews=new ArrayList<View>();
        testViews.add(getTestView("测试  3 "));
        testViews.add(getTestView("测试  4 "));
        testViews.add(getTestView("测试  5"));
        testViews.add(getTestView("测试  6 "));
        testViews.add(getTestView("测试  7 "));
        testViews.add(getTestView("测试  8 "));
        testViews.add(getTestView("测试  9 "));
        addManyViews(testViews);
    }

    public void removeAllview(){
        all_layout.removeAllViews();
        contentView.clear();
        move_item_y.clear();
        setLayoutHeight();
        scrollView.fullScroll(ScrollView.FOCUS_DOWN);
    }

    /**
     * 替换所有的Views
     */
    public void setContentView(List<View> views){
        if(views!=null) {
            removeAllview();
            addManyViews(views);
        }
    }

    public int getSize(){
        return contentView==null?0:contentView.size();
    }
    int maxheight;
    boolean canChangeHeight=false;
    public void setMaxHeight(int maxheight){
        this.maxheight=maxheight;
        canChangeHeight=true;
    }

    /**
     * 添加一个View到父布局
     * view 的父布局只能是Relayout
     * @param view
     */
    public void addView(View view){
        if(view!=null) {
            contentView.add(view);
            all_layout.addView(view, 0);
            addOneViewChangeMoveY();
            scrollView.fullScroll(ScrollView.FOCUS_DOWN);
            invalidate();
        }
    }
    /**
     * 添加多个View到父布局
     * view 的父布局只能是Relayout
     * @param views
     */
    public void addManyViews(List<View> views){
        if(views!=null) {
            contentView.addAll(views);
            for(View mviews:views){
                all_layout.addView(mviews,0);
            }
            addmanyViewChangeMoveY();
            scrollView.fullScroll(ScrollView.FOCUS_DOWN);
            invalidate();
        }
    }


    private void addOneViewChangeMoveY(){
        if(contentView.size()>1){
            LayoutParams mlayout = (LayoutParams) contentView.get(contentView.size()-1).getLayoutParams();
            int m = linespaceheight + mlayout.height;
            move_item_y.add(move_item_y.get(move_item_y.size() - 1) + m);
        }else{
            move_item_y.add(0);
        }
        setLayoutHeight();
    }
    int linespaceheight=UiUtils.dip2pxLe(getContext(),5);


    public void setItemSpaceHeight(int spaceheight){
         this.linespaceheight=spaceheight;
    }
    private void addmanyViewChangeMoveY(){
        move_item_y.clear();
        if(contentView.size()>0) {
            for (int i = 0; i < contentView.size(); i++) {
                if(i==0){
                    move_item_y.add(0);
                }else {
                    LayoutParams mlayout = (LayoutParams) contentView.get(i).getLayoutParams();
                    int m = linespaceheight + mlayout.height;
                    move_item_y.add(move_item_y.get(i - 1) + m);
                }
            }
        }
        setLayoutHeight();
    }

    /**
     * all_layout 和 scrollView的高度
     */
    private void setLayoutHeight(){
        int height=0;
        if(move_item_y.size()>1) {
            height=move_item_y.get(move_item_y.size()-1)+title_view_height;
        }
        FrameLayout.LayoutParams all_layout_Params= (FrameLayout.LayoutParams) all_layout.getLayoutParams();
        all_layout_Params.height=height ;
        all_layout.setLayoutParams(all_layout_Params);
        RelativeLayout.LayoutParams mscrollView_layout= (RelativeLayout.LayoutParams) scrollView.getLayoutParams();
        if(canChangeHeight && maxheight<height){
            mscrollView_layout.height = maxheight;
        }else {
            mscrollView_layout.height = height;
        }
        scrollView.setLayoutParams(mscrollView_layout);
    }
    int  title_view_height;
    private void initcurrheight(){
        RelativeLayout.LayoutParams mscrollView_layout= (RelativeLayout.LayoutParams)title_view.getLayoutParams();
        title_view_height=mscrollView_layout.height;
    }
    int currheight=UiUtils.dip2pxLe(getContext(),100);
    public void setShowMaxHeight(float n){
        int maxheight=currheight;
        if(move_item_y.size()>1){
            maxheight=(int) (currheight+((move_item_y.get(move_item_y.size()-1))-currheight)*n);
        }
        LinearLayout.LayoutParams mscrollView_layout= (LinearLayout.LayoutParams)getLayoutParams();
        mscrollView_layout.height=maxheight;
        setLayoutParams(mscrollView_layout);
    }


    float baifenbi=0.75f;//动画开始的百分比
    public void startAnimation(float n){
        scrollView.fullScroll(ScrollView.FOCUS_DOWN);
        AnimatorSet animSet = new AnimatorSet();
        animSet.setDuration(0);
        if(contentView.size()>0){
            if(n>baifenbi) {
                title_view.setAlpha(n);
                small_red_dot.setAlpha(show_red_dot?n:0);
            }else{
                title_view.setAlpha(0);
                small_red_dot.setAlpha(0);
            }
            float msgView_X = getTranslationX();
            ObjectAnimator m4= ObjectAnimator.ofFloat(this, "translationX", msgView_X, 0);
            AnimatorSet.Builder mBuilder=animSet.play(m4);
            for(int i=0;i<contentView.size();i++){
                float cuur_y_2 = contentView.get(i).getTranslationY();
                ObjectAnimator m2;
                if(n<=baifenbi) {
                    contentView.get(i).setBackgroundDrawable(getContext().getResources().getDrawable(R.drawable.vm_item_bg_shadow));
                    m2 = ObjectAnimator.ofFloat( contentView.get(i), "translationY", cuur_y_2, ((-1*move_item_y.get(i))*(1-n/baifenbi)));
                }else{
                    contentView.get(i).setBackgroundDrawable(getContext().getResources().getDrawable(R.drawable.vm_item_bg_no_shadow));
                    m2 = ObjectAnimator.ofFloat( contentView.get(i), "translationY", 0, 0);
                }
                mBuilder.with(m2);
            }
            animSet.start();
        }else{
            title_view.setAlpha(n);
            small_red_dot.setAlpha(0);
        }

    }
}
