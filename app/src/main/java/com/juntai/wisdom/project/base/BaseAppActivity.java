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
import com.juntai.disabled.basecomponent.utils.GsonTools;
import com.juntai.disabled.bdmap.utils.NagivationUtils;
import com.juntai.wisdom.project.base.selectPics.BaseSelectPicsActivity;
import com.juntai.wisdom.project.bean.RequestBean;
import com.juntai.wisdom.project.utils.UserInfoManager;

import java.util.List;

import okhttp3.FormBody;
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
