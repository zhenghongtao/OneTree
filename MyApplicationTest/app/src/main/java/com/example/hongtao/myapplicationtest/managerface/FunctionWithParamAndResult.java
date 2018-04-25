package com.example.hongtao.myapplicationtest.managerface;

/**
 * Created by hongtao on 17/10/13.
 */

public abstract class FunctionWithParamAndResult<Result,Param> extends Function {

    public FunctionWithParamAndResult(String function_name) {
        super(function_name);
    }
    public abstract Result function(Param param);
}
