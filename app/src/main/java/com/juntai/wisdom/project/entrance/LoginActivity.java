package com.juntai.wisdom.project.entrance;

import android.app.Activity;
import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.util.SparseArray;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TabHost;
import android.widget.TableLayout;
import android.widget.TextView;

import com.juntai.disabled.basecomponent.base.BaseMvpActivity;
import com.juntai.disabled.basecomponent.bean.UnionidBean;
import com.juntai.disabled.basecomponent.eventbus.EventManager;
import com.juntai.disabled.basecomponent.mvp.BaseIView;
import com.juntai.disabled.basecomponent.utils.ActionConfig;
import com.juntai.disabled.basecomponent.utils.GsonTools;
import com.juntai.disabled.basecomponent.utils.HttpUtil;
import com.juntai.disabled.basecomponent.utils.LogUtil;
import com.juntai.disabled.basecomponent.utils.MD5;
import com.juntai.disabled.basecomponent.utils.PubUtil;
import com.juntai.disabled.basecomponent.utils.ToastUtils;
import com.juntai.wisdom.project.MyApp;
import com.juntai.wisdom.project.R;
import com.juntai.wisdom.project.base.BaseAppActivity;
import com.juntai.wisdom.project.base.BaseTabViewPageActivity;
import com.juntai.wisdom.project.base.sendcode.SendCodeModel;
import com.juntai.wisdom.project.bean.UserBean;
import com.juntai.disabled.basecomponent.utils.AppUtils;
import com.juntai.wisdom.project.utils.UserInfoManager;
import com.orhanobut.hawk.Hawk;

import java.lang.ref.WeakReference;
import java.util.HashMap;

import cn.sharesdk.framework.Platform;
import cn.sharesdk.framework.PlatformActionListener;
import cn.sharesdk.framework.PlatformDb;
import cn.sharesdk.framework.ShareSDK;
import cn.sharesdk.tencent.qq.QQ;
import cn.sharesdk.wechat.friends.Wechat;

/**
 * @aouther tobato
 * @description 描述 登录
 * @date 2022/6/12 22:06
 */
public class LoginActivity extends BaseTabViewPageActivity<EntrancePresent> implements BaseIView,
        View.OnClickListener {

    @Override
    protected SparseArray<Fragment> getFragments() {
        SparseArray<Fragment> arrays = new SparseArray<>();
        arrays.append(0,new LoginFragment());
        arrays.append(1,new LoginSetFragment());
        return arrays;
    }


    @Override
    public void initData() {
        super.initData();
        initToolbarAndStatusBar(false);
        mTabRootLl.setBackgroundResource(R.mipmap.login_bg);
        setMargin(mTabTb,20,0,20,0);
    }

    @Override
    protected String[] getTabTitles() {
        return new String[]{"登录","设置"};
    }


    @Override
    protected EntrancePresent createPresenter() {
        return new EntrancePresent();
    }

    @Override
    public void onSuccess(String tag, Object o) {
        switch (tag) {
            default:
                break;
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            default:
                break;
        }
    }



    @Override
    protected int getTabMode() {
        return TabLayout.MODE_FIXED;
    }

    @Override
    protected int getTabHeadLayout() {
        return R.layout.activity_login;
    }

    @Override
    protected int getTabFootLayout() {
        return 0;
    }

    @Override
    protected String getTitleName() {
        return null;
    }


}
