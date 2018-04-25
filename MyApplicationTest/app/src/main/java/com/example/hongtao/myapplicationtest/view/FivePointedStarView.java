package com.example.hongtao.myapplicationtest.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.View;

import com.example.hongtao.myapplicationtest.R;

import static android.graphics.BitmapFactory.decodeResource;

/**
 * Created by hongtao on 16/8/30.
 */
public class FivePointedStarView extends View {
    int mColor=Color.RED;
    Paint paint=new Paint();
    Bitmap  star;
    public FivePointedStarView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        TypedArray a=context.obtainStyledAttributes(attrs, R.styleable.TriangleView);
        mColor = a.getColor(R.styleable.TriangleView_t_color,Color.RED);
        star= BitmapFactory.decodeResource(context.getResources(),R.drawable.evaluate_select_icon);
        a.recycle();
        setColor();

    }
    public FivePointedStarView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }
    public FivePointedStarView(Context context) {
        super(context);
        setColor();
        // TODO Auto-generated constructor stub
    }
    private void setColor(){
        setColor(mColor);
    }
    public void setColor(int color){
        mColor=color;
        paint.setColor(mColor);
    }

    public int dip2px(float dpValue) {
        return (int) (dpValue * getResources().getDisplayMetrics().density + 0.5f);
    }
    @Override
    protected void onDraw(Canvas canvas) {
        // TODO Auto-generated method stub
        super.onDraw(canvas);
        int width=getWidth();
        int height=getHeight();
		/*设置背景为白色*/
        canvas.drawColor(Color.WHITE);

		/*去锯齿*/
        paint.setAntiAlias(true);
		/*设置paint的颜色*/
        paint.setColor(mColor);
		/*设置paint的　style　为STROKE：空心*/
        paint.setStyle(Paint.Style.STROKE);
		/*设置paint的外框宽度*/
        paint.setStrokeWidth(dip2px(1));
        paint.setStyle(Paint.Style.FILL);
        paint.setColor(mColor);
		/*画一个空心三角形*/
        Path path=new Path();
        path.moveTo(0,0);
        path.lineTo(width, 0);
        path.lineTo(width/2,height);
        path.close();
        canvas.drawPath(path, paint);

//		/*设置paint　的style为　FILL：实心*/
//        paint.setStyle(Paint.Style.FILL);
//		/*设置paint的颜色*/
//        paint.setColor(Color.BLUE);
//
//		/*画一个实心三角形*/
//        Path path2=new Path();
//        path2.moveTo(90,330);
//        path2.lineTo(150,330);
//        path2.lineTo(120,270);
//        path2.close();
//        canvas.drawPath(path2, paint);
//
//        Shader mShader=new LinearGradient(0,0,100,100,
//                new int[]{Color.RED,Color.GREEN,Color.BLUE,Color.YELLOW},
//                null, Shader.TileMode.REPEAT);
//        paint.setShader(mShader);
//
//		/*画一个渐变色三角形*/
//        Path path4=new Path();
//        path4.moveTo(170,330);
//        path4.lineTo(230,330);
//        path4.lineTo(200,270);
//        path4.close();
//        canvas.drawPath(path4,paint);
    }

}
