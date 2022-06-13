package com.juntai.wisdom.project.base;

import android.app.AlertDialog;
import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.text.TextUtils;

import com.baidu.location.BDLocation;
import com.baidu.mapapi.model.LatLng;
import com.juntai.disabled.basecomponent.mvp.BasePresenter;
import com.juntai.disabled.bdmap.utils.NagivationUtils;
import com.juntai.wisdom.project.base.selectPics.BaseSelectPicsActivity;
import com.juntai.wisdom.project.utils.UserInfoManager;

import java.util.List;

import okhttp3.FormBody;

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


    /**
     * 获取builder
     *
     * @return
     */
    public FormBody.Builder getBaseBuilder(String handlerName,String queryType,String sessionId,String parame) {
        FormBody.Builder builder = new FormBody.Builder();
        if (!TextUtils.isEmpty(handlerName)) {
            builder.add("HandlerName",handlerName);
        }
        if (!TextUtils.isEmpty(queryType)) {
            builder.add("QueryType",queryType);
        }
        if (!TextUtils.isEmpty(sessionId)) {
            builder.add("SessionID",sessionId);
        }
        if (!TextUtils.isEmpty(parame)) {
            builder.add("Parameters",parame);
        }
        return builder;
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
