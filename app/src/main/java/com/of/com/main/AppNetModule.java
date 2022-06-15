package com.of.com.main;


import com.of.disabled.basecomponent.net.ApiRetrofit;

/**
 * 网络请求
 */
public class AppNetModule {
    static AppServer appServer ;
    public static AppServer createrRetrofit() {
        if (appServer == null){
            appServer = ApiRetrofit.getInstance().getApiService(AppServer.class);
        }
        return appServer;
    }
}
