package com.juntai.wisdom.project.entrance;


import android.annotation.SuppressLint;
import android.view.View;

import com.google.gson.JsonObject;
import com.juntai.disabled.basecomponent.mvp.BaseIView;
import com.juntai.disabled.basecomponent.mvp.BasePresenter;
import com.juntai.disabled.basecomponent.mvp.IModel;
import com.juntai.disabled.basecomponent.utils.PickerManager;
import com.juntai.disabled.basecomponent.utils.PubUtil;
import com.juntai.disabled.basecomponent.utils.RxScheduler;
import com.juntai.wisdom.project.AppHttpPath;
import com.juntai.wisdom.project.AppNetModule;
import com.juntai.wisdom.project.bean.CommpanyAccountBean;
import com.juntai.wisdom.project.bean.UserBean;
import com.juntai.wisdom.project.net.CmdCallBack;
import com.juntai.wisdom.project.net.CmdUtil;
import com.juntai.wisdom.project.utils.HawkProperty;
import com.orhanobut.hawk.Hawk;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

import io.reactivex.functions.Consumer;
import okhttp3.RequestBody;

/**
 * @Author: tobato
 * @Description: 作用描述
 * @CreateDate: 2020/3/5 15:55
 * @UpdateUser: 更新者
 * @UpdateDate: 2020/3/5 15:55
 */
public class EntrancePresent extends BasePresenter<IModel, BaseIView>  {
    private BaseIView iView;
    public void  setCallBack(BaseIView iView) {
        this.iView = iView;
    }

    @Override
    protected IModel createModel() {
        return null;
    }


    @SuppressLint("CheckResult")
    public void getCompanyAccount(String tag) {
        CmdUtil.cmd(AppHttpPath.GET_COMPANY_ACCOUNT, "GetAccountInfo", (JSONObject) null, new CmdCallBack() {
            @Override
            public void onSuccess(String result) {
                if (getView() != null) {
                    getView().onSuccess(tag,result);
                }
            }

            @Override
            public void onResponseError(String result) {
                if (getView() != null) {
                    getView().onError(tag, PubUtil.ERROR_NOTICE);
                }
            }
        });
    }
    @SuppressLint("CheckResult")
    public void getUserAccount(RequestBody requestBody, String tag) {
        AppNetModule
                .createrRetrofit()
                .getUserAccount(Hawk.get(HawkProperty.CURRENT_SERVICE_ADDRS),requestBody)
                .compose(RxScheduler.ObsIoMain(getView()))
                .subscribe(new Consumer<UserBean>() {
                    @Override
                    public void accept(UserBean userBean) throws Exception {
                        if (getView() != null) {
                            getView().onSuccess(tag, userBean);
                        }
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        if (getView() != null) {
                            getView().onError(tag, PubUtil.ERROR_NOTICE);
                        }
                    }
                });
    }





}
