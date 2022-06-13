package com.juntai.wisdom.project.entrance;


import android.annotation.SuppressLint;

import com.juntai.disabled.basecomponent.base.BaseFragment;
import com.juntai.disabled.basecomponent.mvp.BaseIView;
import com.juntai.disabled.basecomponent.mvp.BasePresenter;
import com.juntai.disabled.basecomponent.mvp.IModel;
import com.juntai.disabled.basecomponent.utils.PubUtil;
import com.juntai.disabled.basecomponent.utils.RxScheduler;
import com.juntai.wisdom.project.AppNetModule;
import com.juntai.wisdom.project.bean.UserBean;

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
    public void login(RequestBody requestBody, String tag) {
        AppNetModule
                .createrRetrofit()
                .login(BaseFragment.BASE_URL,requestBody)
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
