package a3phone.of.disabled.basecomponent.base;

import android.content.Intent;

import com.google.gson.JsonParseException;
import a3phone.of.disabled.basecomponent.app.BaseApplication;
import a3phone.of.disabled.basecomponent.bean.BaseStreamBean;
import a3phone.of.disabled.basecomponent.mvp.BaseIView;
import a3phone.of.disabled.basecomponent.utils.ActionConfig;
import a3phone.of.disabled.basecomponent.utils.LogUtil;

import org.json.JSONException;

import java.io.InterruptedIOException;
import java.net.ConnectException;
import java.net.UnknownHostException;
import java.text.ParseException;

import io.reactivex.observers.DisposableObserver;
import retrofit2.HttpException;

public abstract class BaseObserver<T> extends DisposableObserver<T> {
    protected BaseIView view;
    /**
     * 解析数据失败
     */
    public static final int PARSE_ERROR = 1001;
    /**
     * 网络问题
     */
    public static final int BAD_NETWORK = 1002;
    /**
     * 连接错误
     */
    public static final int CONNECT_ERROR = 1003;
    /**
     * 连接超时
     */
    public static final int CONNECT_TIMEOUT = 1004;


    public BaseObserver(BaseIView view) {
        this.view = view;
    }

    @Override
    protected void onStart() {
        if (view != null) {
            view.showLoading();
        }
    }

    @Override
    public void onNext(T bean) {
        try {
            BaseResult model = (BaseResult) bean;
            onSuccess(bean);
        } catch (ClassCastException ee) {
            LogUtil.e("数据解析失败" + ee.toString());
            onException(PARSE_ERROR);
        } catch (Exception e) {
            e.printStackTrace();
            LogUtil.e("加载失败" + e.toString());
            onError(e.toString());
        }
    }

    @Override
    public void onError(Throwable e) {
        LogUtil.e("加载失败" + e.toString());
        if (view != null) {
            view.hideLoading();
        }
        if (e instanceof HttpException) {
            //   HTTP错误
            onException(BAD_NETWORK);
        } else if (e instanceof ConnectException
                || e instanceof UnknownHostException) {
            //   连接错误
            onException(CONNECT_ERROR);
        } else if (e instanceof InterruptedIOException) {
            //  连接超时
            onException(CONNECT_TIMEOUT);
        } else if (e instanceof JsonParseException
                || e instanceof JSONException
                || e instanceof ParseException) {
            //  解析错误
            onException(PARSE_ERROR);
        } else {
            if (e != null) {
                onError(e.toString());
            } else {
                onError("未知错误");
            }
        }

    }

    /**
     * 异常
     * @param unknownError
     */
    private void onException(int unknownError) {
        switch (unknownError) {
            case CONNECT_ERROR:
                onError("服务器访问失败");
                break;
            case CONNECT_TIMEOUT:
                onError("连接超时");
                break;
            case BAD_NETWORK:
                onError("当前网络连接不可用，请检查网络设置");
                break;
            case PARSE_ERROR:
                onError("解析数据失败");
                break;
            default:
                break;
        }
    }


    @Override
    public void onComplete() {
        if (view != null) {
            view.hideLoading();
        }

    }

    public abstract void onSuccess(T o);

    public abstract void onError(String msg);
}