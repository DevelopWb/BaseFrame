package com.juntai.wisdom.project.entrance;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import com.juntai.disabled.basecomponent.mvp.BaseIView;
import com.juntai.wisdom.project.R;
import com.juntai.wisdom.project.base.BaseAppFragment;

/**
 * @Author: tobato
 * @Description: 作用描述
 * @CreateDate: 2022/6/12 22:10
 * @UpdateUser: 更新者
 * @UpdateDate: 2022/6/12 22:10
 */
public class LoginSetFragment extends BaseAppFragment<EntrancePresent> implements BaseIView, View.OnClickListener {
    private EditText mServiceAddrEt;
    private TextView mHisServiceAddrTv;
    private TextView mConfirmTv;

    @Override
    protected EntrancePresent createPresenter() {
        return new EntrancePresent();
    }

    @Override
    protected void lazyLoad() {

    }

    @Override
    protected int getLayoutRes() {
        return R.layout.fragment_login_set;
    }

    @Override
    protected void initView() {

        mServiceAddrEt = (EditText) getView(R.id.service_addr_et);
        mHisServiceAddrTv = (TextView) getView(R.id.his_service_addr_tv);
        mHisServiceAddrTv.setOnClickListener(this);
        mConfirmTv = (TextView) getView(R.id.confirm_tv);
        mConfirmTv.setOnClickListener(this);
    }

    @Override
    protected void initData() {

    }

    @Override
    public void onSuccess(String tag, Object o) {

    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            default:
                break;
            case R.id.his_service_addr_tv:
                break;
            case R.id.confirm_tv:
                break;
        }
    }
}
