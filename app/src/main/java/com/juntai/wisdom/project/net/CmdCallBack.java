package com.juntai.wisdom.project.net;


import android.content.Intent;
import android.os.Handler;
import android.os.Looper;
import android.os.Parcelable;


import com.juntai.wisdom.project.entrance.LoginActivity;

import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

/**
 * Created by tony on 16/3/13.
 */
public abstract class CmdCallBack implements Callback
{
    public Object _tag = null;

    public void setTag(Object tag)
    {
        _tag = tag;
    }

    public Object getTag()
    {
        return _tag;
    }

    private Handler mHandler;


    private JSONObject _parameters;

    private CmdCallBack _currInstance;


    public void setParameters( JSONObject parameters)
    {
        _parameters=parameters;
    }
    public void setCurrInstance(CmdCallBack currInstance)
    {
        _currInstance=currInstance;
    }

    public CmdCallBack()
    {
        mHandler = new Handler(Looper.getMainLooper());
    }


    public abstract void onSuccess(String result);
    public abstract void onError(String result);

    public void onFail(String retString)
    {
//        ToastUtil.showToast(A3Application.getInstance(), retString);
    }

    @Override
    public void onFailure(Call call, IOException e)
    {
        Looper.prepare();
        String errorMesage = e.getMessage();
        onFail(errorMesage);
        Looper.loop();
    }

    @Override
    public void onResponse(Call call, Response response) throws IOException
    {
        final String value = response.body().string();
        if (value.toLowerCase().startsWith("error:"))
        {
            if (value.toLowerCase().startsWith("error:0:登录超时！"))
            {
//                HandlerExpieredLogin();
                return;
            }
            else
            {
                mHandler.post(new Runnable()
                {
                    @Override
                    public void run()
                    {
                        onFail(value);
                    }
                });
                return;
            }
        }

        mHandler.post(new Runnable()
        {
            @Override
            public void run()
            {
                onSuccess(value);
            }
        });
    }

//    private void HandlerExpieredLogin() //会话超时处理
//    {
//        LoginHandler loginHandler = new LoginHandler(A3Application.getInstance().getBaseContext())
//        {
//            @Override
//            public void AfterLoginSucessHandler()
//            {
//                CmdUtil.exeCmd(_parameters,_currInstance);
//            }
//        };
//        if (loginHandler.HasPreLoginInfo())
//        {
//            loginHandler.ExePreLogin();
//        }
//        else
//        {
//            AccountHandler accountHandler = new AccountHandler(A3Application.getInstance().getBaseContext())
//            {
//                @Override
//                public void HandlerAccount(List<BookModel> accountList)
//                {
//                    Intent mainIntent = new Intent(A3Application.getInstance().getBaseContext(), LoginActivity.class);
//                    mainIntent.putParcelableArrayListExtra("AccountList", (ArrayList<? extends Parcelable>) accountList);
//                    A3Application.getInstance().getBaseContext().startActivity(mainIntent);
//                }
//            };
//            accountHandler.LoadAccount();
//        }
//    }

}
