package com.example.hongtao.myapplicationtest;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.hongtao.myapplicationtest.agent.ProxyActivity;
import com.example.hongtao.myapplicationtest.baobiao.BaoBiaoActivity;
import com.example.hongtao.myapplicationtest.clip.ClipActivity;
import com.example.hongtao.myapplicationtest.fargments.YDHomePage;
import com.example.hongtao.myapplicationtest.linkedlist.TestLinkedList;
import com.example.hongtao.myapplicationtest.popwindow.TestPop;
import com.example.hongtao.myapplicationtest.recyle.TestRecycleViewActivity;
import com.example.hongtao.myapplicationtest.yidao.ViewPageTest2;
import com.example.hongtao.myapplicationtest.yidao.ViewPageTestActivity;
import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.appindexing.Thing;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.data.DataBufferObserver;
import com.meituan.robust.patch.annotaion.Modify;
import org.greenrobot.eventbus.EventBus;
import butterknife.BindView;
import butterknife.ButterKnife;
import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;

import static io.reactivex.Observable.create;

public class MainActivity extends BaseActivity {

    @BindView(R.id.test_layout)
    LinearLayout testLayout;
    LayoutParams mLayoutParams = new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT);
    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    private GoogleApiClient client;
    public void testclick(Class m) {
        Intent mintent = new Intent(this, m);
        startActivity(mintent);
    }
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.content_main);
        ButterKnife.bind(this);
        addBPopWindow();
        initButtonView();
        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client = new GoogleApiClient.Builder(this).addApi(AppIndex.API).build();
    }

    public void initButtonView(){
        addButtonView("ViewPage测试",ViewPageTestActivity.class);
        addButtonView("动态代理测试",ProxyActivity.class);
        addButtonView("ViewPage2  易道8.0.0 首页动画",ViewPageTest2.class);
        addButtonView("Clip 属性测试",ClipActivity.class);
        addButtonView("报表测试",BaoBiaoActivity.class);
        addButtonView("美团热补丁 Roust 测试",RoustActivity.class);
        addButtonView("测试FragMet切换动画,和万能接口",YDHomePage.class);
        addButtonView("测试RecycleView ",TestRecycleViewActivity.class);
    }




    public void addButtonView(String buttonName, final Class mclass){
        Button mButton = new Button(this);
        mButton.setText(buttonName);
        mButton.setLayoutParams(mLayoutParams);
        mButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                testclick(mclass);
            }
        });
        testLayout.addView(mButton);
    }


    public void addButtonView(String buttonName, View.OnClickListener onClickListener){
        Button mButton = new Button(this);
        mButton.setText(buttonName);
        mButton.setLayoutParams(mLayoutParams);
        mButton.setOnClickListener(onClickListener);
        testLayout.addView(mButton);
    }


    /**
     * 测试PopWindow
     */
    public void addBPopWindow() {
        addButtonView("Pop弹窗测试",new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TestPop mBiddingPopWindow = new TestPop(MainActivity.this);
                mBiddingPopWindow.show();
            }
        });
    }

    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
    }

    @Override
    protected void onResume() {
        super.onResume();
        //textLinkList();
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        Log.e("zhenghongtao", "---main------onKeyDown-----------");
        return super.onKeyDown(keyCode, event);
    }

    @Override
    public boolean onKeyUp(int keyCode, KeyEvent event) {
        Log.e("zhenghongtao", "-----main----onKeyUp-----------");
        return super.onKeyUp(keyCode, event);
    }

    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    public Action getIndexApiAction() {
        Thing object = new Thing.Builder()
                .setName("Main Page") // TODO: Define a title for the content shown.
                // TODO: Make sure this auto-generated URL is correct.
                .setUrl(Uri.parse("http://[ENTER-YOUR-URL-HERE]"))
                .build();
        return new Action.Builder(Action.TYPE_VIEW)
                .setObject(object)
                .setActionStatus(Action.STATUS_TYPE_COMPLETED)
                .build();
    }

    @Override
    public void onStart() {
        super.onStart();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client.connect();
        AppIndex.AppIndexApi.start(client, getIndexApiAction());
    }

    @Override
    public void onStop() {
        super.onStop();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        AppIndex.AppIndexApi.end(client, getIndexApiAction());
        client.disconnect();
    }
}
