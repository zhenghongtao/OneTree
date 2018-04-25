package com.example.hongtao.myapplicationtest.yidao;

import android.animation.ObjectAnimator;
import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.VelocityTracker;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.HorizontalScrollView;

import static android.R.attr.action;
import static android.R.attr.scrollX;

/**
 * Created by hongtao on 17/2/10.
 */

public class MenuHorizontalScrollView extends HorizontalScrollView {


    public static String TAG="TestHorizontalScrollView";
    private Context context;
    private VerticalMenuView oneView;
    private ViewGroup twoView,endView;
    private int twoPoint,endPoint;
    private int oneViewMarginLeft;
    private int oneViewWidth;
    private int twoViewMarginLeft;
    private int twoViewWidth;
    private OnViewChangedListener listener;
    private int windowwidth=0;
    public MenuHorizontalScrollView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);

    }

    public MenuHorizontalScrollView(Context context, AttributeSet attrs,
                                    int defStyle) {
        super(context, attrs, defStyle);
        this.context = context;
        initwidth();
    }

    public MenuHorizontalScrollView(Context context) {
        this(context, null, 0);

    }
    public void initwidth(){
        WindowManager wm = (WindowManager) getContext()
                .getSystemService(Context.WINDOW_SERVICE);
        windowwidth = wm.getDefaultDisplay().getWidth();

    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }

    public void initData(){
        ViewGroup wrapper = (ViewGroup) getChildAt(0);
        oneView = (VerticalMenuView) wrapper.getChildAt(0);
        twoView = (ViewGroup) wrapper.getChildAt(1);
        endView = (ViewGroup) wrapper.getChildAt(2);
        // 获取第一个view的marginleft;
        MarginLayoutParams olp = (MarginLayoutParams) oneView.getLayoutParams();
        oneViewMarginLeft = olp.leftMargin;
        // 获取第一个view的宽度
        oneViewWidth = oneView.getMeasuredWidth();
        MarginLayoutParams olptwoView = (MarginLayoutParams) twoView.getLayoutParams();
        twoViewMarginLeft=olptwoView.leftMargin;
        twoViewWidth=twoView.getWidth();
        // Log.e(tag,"oneV"+oneViewMarginLeft);
        // view的坐标
        twoPoint = (wrapper.getWidth()-windowwidth)/2;
        endPoint = wrapper.getWidth()-windowwidth;
    }
    public void moveToCenter(){
        initData();
        smoothScrollTo(twoPoint, 0);
    }
    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        super.onLayout(changed, l, t, r, b);
    }

    @Override
    protected void onFinishInflate() {
        // TODO Auto-generated method stub
        super.onFinishInflate();
    }

    public void initMove(){
        int currx=getScrollX();
        Log.e(TAG,"currx="+currx);
        if(0<=currx && currx<=twoPoint){
            float animation=((float)currx)/twoPoint;
            Log.e(TAG,"animation="+animation);
            //setalpha(oneView,animation);
            if(listener!=null){
                oneView.setShowMaxHeight(1-animation);
                listener.startFirstShowAnimation(animation);
            }
        }else if(twoPoint<currx && currx<=endPoint){
            float animation=((float)(currx-twoPoint))/(endPoint-twoPoint);
            Log.e(TAG,"animation="+animation);
            if(listener!=null){
                listener.startEndShowAnimation(animation);
            }
        }
    }

    public void setalpha(View view,float end_alpha){
        float alpha=view.getAlpha();
        if(end_alpha<=0.5){
            end_alpha=0;
        }
        ObjectAnimator animator = ObjectAnimator.ofFloat(view, "alpha", alpha, end_alpha/2);
        animator.setDuration(0);
        animator.start();
    }


    float downx=0;
    float movex=0;
    boolean moveright=false;
    VelocityTracker mVelocityTracker=null;
    @Override
    public boolean onTouchEvent(MotionEvent ev) {

        //这里为了解决滑动冲突,如果不需要忽略即可
        requestDisallowInterceptTouchEvent(true);
        int action = ev.getAction();
        switch (action) {
            case MotionEvent.ACTION_MOVE:
                if(mVelocityTracker==null){
                    mVelocityTracker= VelocityTracker.obtain();
                }
                mVelocityTracker.computeCurrentVelocity(200);
                mVelocityTracker.addMovement(ev);

                movex=ev.getX();
                moveright=movex>downx?false:true;
                break;
            case MotionEvent.ACTION_DOWN:
                downx=ev.getX();
                if(mVelocityTracker==null){
                    mVelocityTracker= VelocityTracker.obtain();
                }else{
                    mVelocityTracker.clear();
                }
                mVelocityTracker.addMovement(ev);
                break;
            case MotionEvent.ACTION_CANCEL:
                break;
            case MotionEvent.ACTION_UP:
                //记录滑动的位置
                Log.e("VelocityTracker","mVelocityTracker.getXVelocity()="+mVelocityTracker.getXVelocity());
                float scrollX=getScrollX();
                //如果滑动的位置大于第一个控件的左边距加上宽度的1/3,那么就自动滑到第二个控件
                if(moveright) {

                    float gotomovex = getScrollX()-mVelocityTracker.getXVelocity();
                    if (gotomovex > (oneViewMarginLeft + oneViewWidth + twoViewMarginLeft + twoViewWidth / 3) && scrollX>=twoPoint) {
                        this.smoothScrollTo(endPoint, 0);
                    } else if (gotomovex > (oneViewMarginLeft + oneViewWidth / 3) && scrollX >=0 ) {
                        this.smoothScrollTo(twoPoint, 0);
                        if (listener != null) {
                            listener.secondViewShow();
                        }
                    } else {
                        //反之,就滑到起始的位置
                        this.smoothScrollTo(0, 0);
                        if (listener != null) {
                            listener.firstViewShow();
                        }
                    }
                }else{

                    scrollX=getScrollX()-mVelocityTracker.getXVelocity();
                    if (scrollX <(oneViewMarginLeft + oneViewWidth * 2/3) && scrollX<=twoPoint) {
                        this.smoothScrollTo(0, 0);
                    } else if (scrollX < (oneViewMarginLeft + oneViewWidth + twoViewMarginLeft + twoViewWidth *2/3) && scrollX<endPoint) {
                        this.smoothScrollTo(twoPoint, 0);
                    } else {
                        //反之,就滑到起始的位置
                        this.smoothScrollTo(endPoint, 0);
                    }
                }
                return true;

        }
        return super.onTouchEvent(ev);
    }

    @Override
    protected void onScrollChanged(int l, int t, int oldl, int oldt) {
        super.onScrollChanged(l, t, oldl, oldt);
        initMove();
        if(listener!=null) {
            if (l == 0) {
                listener.firstViewShow();
            } else if (l == twoPoint) {
                listener.secondViewShow();
            }else if(l==endPoint){
                listener.endViewShow();
            }
        }
    }



    public void setOnViewChangedListener(OnViewChangedListener listener) {
        this.listener = listener;
    }

    /** 实现此借口监听view切换 */
    public interface OnViewChangedListener {
        void firstViewShow();
        void secondViewShow();
        void endViewShow();
        void startFirstShowAnimation(float n);
        void startEndShowAnimation(float n);

    }
}
