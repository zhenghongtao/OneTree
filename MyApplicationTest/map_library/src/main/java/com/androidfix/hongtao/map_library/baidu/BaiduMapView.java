package com.androidfix.hongtao.map_library.baidu;

import android.content.Context;
import android.content.res.TypedArray;
import android.os.Bundle;
import android.support.annotation.AttrRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.StyleRes;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import com.androidfix.hongtao.map_library.R;
import com.androidfix.hongtao.map_library.common.MapView;
import com.androidfix.hongtao.map_library.common.MapViewOptions;

import java.util.zip.Inflater;

/**
 * Created by hongtao on 17/10/25.
 */

public class BaiduMapView extends FrameLayout implements MapView {
    Context mContext;
    public BaiduMapView(@NonNull Context context) {
        super(context);
    }
    public BaiduMapView(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public BaiduMapView(@NonNull Context context, @Nullable AttributeSet attrs, @AttrRes int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public void initView(Context context, AttributeSet attrs){
        mContext = context;
        try {
            inflate(context, R.layout.mapview, this);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    MapViewOptions mapViewOptions;
    @Override
    public void setMapViewOptions(MapViewOptions mapViewOptions){
        this.mapViewOptions=mapViewOptions;
    }

    private void upMapViewOptions(){

    }


}
