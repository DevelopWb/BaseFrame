package a3phone.of.com.main.net;


import android.os.Handler;
import android.os.Looper;
import android.util.ArrayMap;


import com.orhanobut.hawk.Hawk;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.Map;

import a3phone.of.com.main.MyApp;
import a3phone.of.com.main.bean.UserBean;
import a3phone.of.com.main.utils.HawkProperty;
import a3phone.of.com.main.utils.UserInfoManager;
import a3phone.of.disabled.basecomponent.utils.GsonTools;
import a3phone.of.disabled.basecomponent.utils.LogUtil;
import a3phone.of.disabled.basecomponent.utils.ToastUtils;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

/**
 * Created by tony on 16/3/13.
 */
public abstract class CmdCallBack implements Callback {
    public Object _tag = null;

    public void setTag(Object tag) {
        _tag = tag;
    }

    public Object getTag() {
        return _tag;
    }

    private Handler mHandler;


    private JSONObject _parameters;

    private CmdCallBack _currInstance;


    public void setParameters(JSONObject parameters) {
        _parameters = parameters;
    }

    public void setCurrInstance(CmdCallBack currInstance) {
        _currInstance = currInstance;
    }

    public CmdCallBack() {
        mHandler = new Handler(Looper.getMainLooper());
    }


    public abstract void onSuccess(JSONObject result);

    public void onFail(String retString) {
        try {
            JSONObject jsonObject = new JSONObject(retString);
            ToastUtils.error(MyApp.app, jsonObject.getString("error"));
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onFailure(Call call, IOException e) {
        Looper.prepare();
        String errorMesage = e.getMessage();
        onFail(errorMesage);
        Looper.loop();
    }

    @Override
    public void onResponse(Call call, Response response) throws IOException {
        final String value = response.body().string();
        LogUtil.e(value);
        if (value.toLowerCase().startsWith("error:")) {
            if (value.toLowerCase().startsWith("error:未登录的用户信息")) {
                HandlerExpieredLogin();
                return;
            } else {
                mHandler.post(new Runnable() {
                    @Override
                    public void run() {
                        onFail(value);
                    }
                });
                return;
            }
        }

        mHandler.post(new Runnable() {
            @Override
            public void run() {
                JSONObject jsonObject = null;
                try {
                    jsonObject = new JSONObject(value);
                    onSuccess(jsonObject);

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    //会话超时处理
    private void HandlerExpieredLogin () {
        Map<String, Object> map = new ArrayMap<>();
        map.put("AccountCode", UserInfoManager.getAccountCode());
        map.put("UserName", UserInfoManager.getUserName());
        map.put("Pwd", UserInfoManager.getUserPwd());
        CmdUtil.cmd("LoginHandlerAdapter", "Login", map, new CmdCallBack() {
            @Override
            public void onSuccess(JSONObject result) {
                UserBean userBean = GsonTools.changeGsonToBean(result.toString(), UserBean.class);
                Hawk.put(HawkProperty.USER_BEAN_KEY, userBean);
                CmdUtil.exeCmd(_parameters, _currInstance);
            }

        });

    }
}
