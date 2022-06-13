package com.juntai.wisdom.project.entrance;

import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.juntai.disabled.basecomponent.mvp.BaseIView;
import com.juntai.wisdom.project.AppHttpPath;
import com.juntai.wisdom.project.R;
import com.juntai.wisdom.project.base.BaseAppFragment;

/**
 * @Author: tobato
 * @Description: 作用描述
 * @CreateDate: 2022/6/12 22:10
 * @UpdateUser: 更新者
 * @UpdateDate: 2022/6/12 22:10
 */
public class LoginFragment extends BaseAppFragment<EntrancePresent> implements BaseIView, View.OnClickListener {
    private TextView mCompanyAccountTv;
    private EditText mUserNameEt;
    private EditText mPwdEt;
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
        return R.layout.fragment_login;
    }

    @Override
    protected void initView() {

        mCompanyAccountTv = (TextView) getView(R.id.company_account_tv);
        mCompanyAccountTv.setOnClickListener(this);
        mUserNameEt = (EditText) getView(R.id.user_name_et);
        mPwdEt = (EditText) getView(R.id.pwd_et);
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
            case R.id.company_account_tv:
                mPresenter.getCompanyAccount(getBaseAppActivity().getBaseBuilder(AppHttpPath.GET_COMPANY_ACCOUNT,"GetAccountInfo",null,null).build(), AppHttpPath.GET_COMPANY_ACCOUNT
                        );
                break;
            case R.id.confirm_tv:
                break;
        }
    }
}
