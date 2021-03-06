package com.siyee.oscvpush.oppo;

import android.annotation.SuppressLint;
import android.content.Context;

import com.heytap.msp.push.HeytapPushManager;
import com.heytap.msp.push.callback.ICallBackResultService;
import com.siyee.oscvpush.PushConstants;
import com.siyee.oscvpush.base.IPushCallback;
import com.siyee.oscvpush.base.IPushManager;
import com.siyee.oscvpush.base.PushAdapter;
import com.siyee.oscvpush.model.Target;
import com.siyee.oscvpush.model.Token;
import com.siyee.oscvpush.util.LogUtils;
import com.siyee.oscvpush.util.NullUtils;

/**
 * OPPO推送服务注册
 */
public class OppoPushRegister implements IPushManager {

//    public static final String METADATA_KEY_APPKEY = "com.heytap.push.app_key";

//    public static final String METADATA_KEY_APPSECRET = "com.heytap.push.app_secret";

    @SuppressLint("StaticFieldLeak")
    private static Context mContext;

    @SuppressLint("StaticFieldLeak")
    private volatile static OppoPushRegister mInstance;

    private OppoPushRegister(Context applicationContext) {
        mContext = applicationContext;
    }

    private static IPushCallback mPushCallback;

    public void setPushCallback(IPushCallback pushCallback) {
        if (pushCallback == null) {
            mPushCallback = new PushAdapter();
            return;
        }
        mPushCallback = pushCallback;
    }

    public static IPushCallback getPushCallback() {
        return mPushCallback;
    }

    /**
     * 只需要第一次调用时传入Context
     * @param applicationContext applicationContext
     * @return
     */
    public static OppoPushRegister getInstance(Context applicationContext) {
        if (mInstance == null) {
            synchronized (OppoPushRegister.class) {
                if (mInstance == null) {
                    mInstance = new OppoPushRegister(applicationContext);
                }
            }
        }
        return mInstance;
    }


    /**
     * Oppo推送服务注册
     * @param appkey oppo应用key
     * @param appsecret oppo应用secret
     */
    public void register(String appkey, String appsecret) {
        if (isSupportPush()) {
            LogUtils.e("OPPO is Support Push");
            HeytapPushManager.register(mContext, appkey, appsecret, null);
            HeytapPushManager.setPushCallback(new ICallBackResultService() {
                @Override
                public void onRegister(int i, String s) {
                    OppoPushRegister.getPushCallback().onRegister(i, Token.buildToken(Target.OPPO, s));
                }

                @Override
                public void onUnRegister(int i) {
                    OppoPushRegister.getPushCallback().onUnRegister(i);
                }

                @Override
                public void onSetPushTime(int i, String s) {

                }

                @Override
                public void onGetPushStatus(int i, int i1) {

                }

                @Override
                public void onGetNotificationStatus(int i, int i1) {

                }
            });
        }
    }

    @Override
    public void unregister() {
        HeytapPushManager.unRegister();
    }

    @Override
    public boolean isSupportPush() {
        // OPPO 注册推送服务必须要调用init(...)接口，才能执行后续操作
        HeytapPushManager.init(mContext, PushConstants.DEBUG);
        return HeytapPushManager.isSupportPush();
    }

    @Override
    public void setAliases(String alias) {

    }

    @Override
    public void unsetAliases(String alias) {

    }

    @Override
    public void getAliases() {

    }

    @Override
    public void setTags(String tag) {

    }

    @Override
    public void unsetTags(String tag) {

    }

    @Override
    public void getTags() {

    }

    @Override
    public void turnOnPush() {
        HeytapPushManager.resumePush();
    }

    @Override
    public void turnOffPush() {
        HeytapPushManager.pausePush();
    }

    @Override
    public String getRegId() {
        String regId = HeytapPushManager.getRegisterID();
        if (NullUtils.checkNull(regId)) {
            OppoPushRegister.getPushCallback().onGetRegId(PushConstants.UNKNOWN_CODE, null);
        } else {
            OppoPushRegister.getPushCallback().onGetRegId(PushConstants.SUCCESS_CODE, Token.buildToken(Target.OPPO, regId));
        }
        return regId;
    }
}
