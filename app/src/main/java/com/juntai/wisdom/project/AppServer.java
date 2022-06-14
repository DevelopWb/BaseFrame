package com.juntai.wisdom.project;


import com.google.gson.JsonObject;
import com.juntai.disabled.basecomponent.base.BaseFragment;
import com.juntai.disabled.basecomponent.base.BaseResult;
import com.juntai.wisdom.project.bean.UserBean;

import org.json.JSONObject;

import io.reactivex.Observable;
import okhttp3.RequestBody;
import retrofit2.http.Body;
import retrofit2.http.POST;
import retrofit2.http.Query;
import retrofit2.http.Url;


/**
 * responseBody里的数据只能调用(取出)一次，第二次为空。可赋值给新的变量使用
 */
public interface AppServer {
    /**
     * 获取公司账套
     *
     * @return
     */
    @POST
    Observable<JsonObject> getCompanyAccount(@Url String url, @Body  RequestBody requestBody);
    /**
     * 获取用户账户
     *
     * @return
     */
    @POST
    Observable<UserBean> getUserAccount(@Url String url, @Body  RequestBody requestBody);


    @POST
    Observable<BaseResult> getSMSCode(@Query("account") String account);




}