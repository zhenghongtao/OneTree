package com.example.hongtao.myapplicationtest.managerface;

import android.text.TextUtils;

import java.util.HashMap;

/**
 * 万能接口管理类
 * Created by hongtao on 17/10/13.
 */

public class FunctionManager {
    static FunctionManager instance;
    public FunctionManager(){
        mFunctionNoParamNoResult=new HashMap<>();
        mFunctionWithParamOnly=new HashMap<>();
        mFunctionWithResultOnly=new HashMap<>();
        mFunctionWithParamAndResult=new HashMap<>();

    }
    public static FunctionManager getInstance(){
        if(instance==null){
            synchronized (FunctionManager.class){
                if(instance==null){
                    instance=new FunctionManager();
                }
            }
        }
        return  instance;
    }
    private HashMap<String, FunctionNoParamNoResult> mFunctionNoParamNoResult;
    private HashMap<String, FunctionWithParamOnly> mFunctionWithParamOnly;
    private HashMap<String, FunctionWithResultOnly> mFunctionWithResultOnly;
    private HashMap<String, FunctionWithParamAndResult> mFunctionWithParamAndResult;

    public FunctionManager addFunction(FunctionNoParamNoResult funtion){
        mFunctionNoParamNoResult.put(funtion.function_name,funtion);
        return this;
    }
    public FunctionManager addFunctionWithResult(FunctionWithResultOnly funtion){
        mFunctionWithResultOnly.put(funtion.function_name,funtion);
        return this;
    }
    public FunctionManager addFunctionWithParam(FunctionWithParamOnly funtion){
        mFunctionWithParamOnly.put(funtion.function_name,funtion);
        return this;
    }
    public FunctionManager addFunctionWithParamAndResult(FunctionWithParamAndResult funtion){
        mFunctionWithParamAndResult.put(funtion.function_name,funtion);
        return this;
    }
    public <Param> void inVokeFunction(String function_name, Param param) throws FunctoinException {
        if(TextUtils.isEmpty(function_name)){
            return;
        }
        if(mFunctionWithParamOnly!=null){
            FunctionWithParamOnly f=mFunctionWithParamOnly.get(function_name);
            if(f!=null){
                f.function(param);
            }else{
                throw  new FunctoinException("no find function :"+function_name);
            }
        }
    }

    public <Result,Param> Result inVokeFunction(String function_name,Param param,Class<Result> c) throws FunctoinException {
        if(TextUtils.isEmpty(function_name)){
            return null;
        }
        if(mFunctionWithParamAndResult!=null){
            FunctionWithParamAndResult f=mFunctionWithParamAndResult.get(function_name);
            if(f!=null){
                if(c!=null) {
                   return c.cast(f.function(param));
                }else{
                    return (Result) f.function(param);
                }
            }else{
                throw  new FunctoinException("no find function :"+function_name);
            }
        }
        return  null;
    }

    public <Result> Result inVokeFunction(String function_name, Class<Result> c) throws FunctoinException {
        if(TextUtils.isEmpty(function_name)){
            return null;
        }
        if(mFunctionWithResultOnly!=null){
            FunctionWithResultOnly f=mFunctionWithResultOnly.get(function_name);
            if(f!=null){
                if(c!=null) {
                    return c.cast(f.function());
                }else{
                    return (Result)f.function();
                }
            }else{
                throw new FunctoinException("no find function :"+function_name);
            }
        }
        return null;
    }


    public void inVokeFunction(String function_name) throws FunctoinException {
        if(TextUtils.isEmpty(function_name)){
            return;
        }
        if(mFunctionNoParamNoResult!=null){
             FunctionNoParamNoResult f=mFunctionNoParamNoResult.get(function_name);
             if(f!=null){
                 f.function();
             }else{
                 throw  new FunctoinException("no find function :"+function_name);
             }
        }
    }
}
