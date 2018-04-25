package com.example.hongtao.myapplicationtest.aidltest;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.os.RemoteException;
import android.support.annotation.Nullable;
import android.util.Log;

import com.example.hongtao.myapplicationtest.aidltest.aidl.FromServer;

/**
 * Created by hongtao on 17/6/8.
 */

public class TestAidlService extends Service {
    String tag=TestAidlService.class.getSimpleName();

    TestBinder mTestBinder=new TestBinder();
    Binder mBinder=new FromServer.Stub(){

        @Override
        public void add() throws RemoteException {

        }
    };
    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        Log.e(tag,"onBind");
        return mBinder;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        Log.e(tag,"onCreate");
    }



    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.e(tag,"onStartCommand");
        return Service.START_NOT_STICKY;
    }

    @Override
    public void onDestroy() {
        Log.e(tag,"onDestroy");
        super.onDestroy();
    }

    public class TestBinder extends Binder{
        public TestAidlService getService(){
            Log.e(tag,"getService");
            return TestAidlService.this;

        }
    }
}
