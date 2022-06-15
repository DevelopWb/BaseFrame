package com.of.com.main;


import com.of.disabled.basecomponent.base.BaseResult;

import io.reactivex.Observable;
import retrofit2.http.POST;
import retrofit2.http.Query;


/**
 * responseBody里的数据只能调用(取出)一次，第二次为空。可赋值给新的变量使用
 */
public interface AppServer {


    @POST
    Observable<BaseResult> getSMSCode(@Query("account") String account);




}