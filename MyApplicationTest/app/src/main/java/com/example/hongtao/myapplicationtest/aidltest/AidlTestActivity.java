package com.example.hongtao.myapplicationtest.aidltest;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Binder;
import android.os.IBinder;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.hongtao.myapplicationtest.R;
import com.example.hongtao.myapplicationtest.aidltest.aidl.FromServer;

public class AidlTestActivity extends AppCompatActivity {

    TestAidlService mTestAidlService;
    TestAidlService.TestBinder mtestBinder;
    Boolean binder=false;
    ServiceConnection mServiceConnect=new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            if(service!=null) {
                mtestBinder = (TestAidlService.TestBinder) service;
                mTestAidlService = mtestBinder.getService();
                binder=true;
            }
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            mTestAidlService=null;
            binder=false;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_aidl_test);
        binderService();
    }

    public void binderService(){
        Intent m=new Intent(this,TestAidlService.class);
        bindService(m,mServiceConnect, Context.BIND_AUTO_CREATE);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if(binder){
            unbindService(mServiceConnect);
            binder=false;
        }
    }
}
