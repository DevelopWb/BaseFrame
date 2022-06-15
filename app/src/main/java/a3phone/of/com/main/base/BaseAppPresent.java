package a3phone.of.com.main.base;

import a3phone.of.disabled.basecomponent.mvp.BasePresenter;
import a3phone.of.disabled.basecomponent.mvp.IModel;
import a3phone.of.disabled.basecomponent.mvp.BaseIView;
import a3phone.of.com.main.utils.UserInfoManager;

import okhttp3.MultipartBody;

/**
 * @Author: tobato
 * @Description: 作用描述
 * @CreateDate: 2020/6/3 8:38
 * @UpdateUser: 更新者
 * @UpdateDate: 2020/6/3 8:38
 */
public abstract class BaseAppPresent<M extends IModel, V extends BaseIView> extends BasePresenter<M,V>{

    /**
     * 获取builder
     * @return
     */
    public MultipartBody.Builder getPublishMultipartBody() {
        return new MultipartBody.Builder()
                .setType(MultipartBody.FORM)
                .addFormDataPart("account", UserInfoManager.getUserAccount())
                .addFormDataPart("userId", String.valueOf(UserInfoManager.getUserId()))
                .addFormDataPart("token", UserInfoManager.getUserToken());
    }


}
