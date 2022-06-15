package com.of.com.main.base;


import com.of.disabled.basecomponent.base.BaseMvpFragment;
import com.of.disabled.basecomponent.mvp.IPresenter;
import com.of.disabled.basecomponent.utils.GsonTools;
import com.of.com.main.bean.RequestBean;

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
