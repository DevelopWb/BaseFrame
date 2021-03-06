package com.juntai.wisdom.project.mine;


import com.juntai.disabled.basecomponent.mvp.IView;
import com.juntai.wisdom.project.bean.MyMenuBean;

import java.util.List;

public interface MyCenterContract {
    String SET_UPDATE_TAG = "setUpdateTag";
    String SET_CLEAR_TAG ="setClearTag";
    //设置相关
    String SET_UPDATE_PSD_TAG = "setUpdatePsdTag";
    String SET_UPDATE_TEL_TAG = "setUpdateTelTag";
    String SET_WEIXIN_TAG ="setWeiXinTag";
    String SET_QQ_TAG = "setQQTag";
    String SET_ABOUT_TAG ="setAboutTag";
    String CENTER_SETTING_TAG ="centerSettingTag";

    interface ICenterView extends IView {
    }

    interface ICenterPresent {

    }
}
