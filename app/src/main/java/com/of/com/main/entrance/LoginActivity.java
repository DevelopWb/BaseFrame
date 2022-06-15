package com.of.com.main.entrance;

import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.util.SparseArray;
import android.view.View;

import com.of.disabled.basecomponent.mvp.BaseIView;
import com.of.com.main.R;
import com.of.com.main.base.BaseTabViewPageActivity;

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
