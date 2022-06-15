package com.of.com.main.base;

import android.os.Bundle;

import com.baidu.location.BDLocation;
import com.of.disabled.basecomponent.mvp.BasePresenter;
import com.of.disabled.basecomponent.utils.GsonTools;
import com.of.com.main.base.selectPics.BaseSelectPicsActivity;
import com.of.com.main.bean.RequestBean;

import java.util.List;

import okhttp3.MediaType;
import okhttp3.RequestBody;

/**
 * @aouther tobato
 * @description 描述
 * @date 2020/4/27 8:48  app的基类
 */
public abstract class BaseAppActivity<P extends BasePresenter> extends BaseSelectPicsActivity<P> {

    @Override
    protected String getUpdateHttpUrl() {
        return "";
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }
    @Override
    protected boolean canCancelLoadingDialog() {
        return false;
    }


    public RequestBody getRequestBody(String handlerName, String queryType, String sessionId , String parameters){
        MediaType JSON = MediaType.parse("application/json; charset=utf-8");
        return RequestBody.create(JSON, GsonTools.createGsonString(new RequestBean(handlerName, queryType, sessionId==null?"":sessionId, parameters==null?"{}":parameters)));
    }



    @Override
    public void onLocationReceived(BDLocation bdLocation) {

    }


    @Override
    public boolean requestLocation() {
        return false;
    }


    @Override
    protected void selectedPicsAndEmpressed(List<String> icons) {

    }


}
