package com.juntai.wisdom.project.base;


import android.text.TextUtils;

import com.juntai.disabled.basecomponent.base.BaseMvpFragment;
import com.juntai.disabled.basecomponent.mvp.IPresenter;
import com.juntai.disabled.basecomponent.utils.GsonTools;
import com.juntai.wisdom.project.AppHttpPath;
import com.juntai.wisdom.project.bean.RequestBean;

import okhttp3.FormBody;
import okhttp3.MediaType;
import okhttp3.RequestBody;

/**
 * @aouther tobato
 * @description 描述  app的fragment的基类
 * @date 2020/7/18 16:43
 */
public abstract class BaseAppFragment<P extends IPresenter> extends BaseMvpFragment<P> {


    /**
     * 获取activity
     *
     * @return
     */
    public BaseAppActivity getBaseAppActivity() {
        return (BaseAppActivity) getActivity();
    }


    @Override
    protected boolean canCancelLoadingDialog() {
        return false;
    }


    public RequestBody getRequestBody(String handlerName,String queryType,String sessionId ,String parameters){
        MediaType JSON = MediaType.parse("application/json; charset=utf-8");
        return RequestBody.create(JSON,GsonTools.createGsonString(new RequestBean(handlerName, queryType, sessionId==null?"":sessionId, parameters==null?"{}":parameters)));
    }

}
