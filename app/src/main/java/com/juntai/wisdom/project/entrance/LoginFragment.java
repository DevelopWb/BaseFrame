package com.juntai.wisdom.project.entrance;

import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.juntai.disabled.basecomponent.mvp.BaseIView;
import com.juntai.disabled.basecomponent.utils.GsonTools;
import com.juntai.disabled.basecomponent.utils.ToastUtils;
import com.juntai.wisdom.project.AppHttpPath;
import com.juntai.wisdom.project.R;
import com.juntai.wisdom.project.base.BaseAppFragment;
import com.juntai.wisdom.project.bean.RequestBean;

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
                mPresenter.getCompanyAccount(GsonTools.createGsonString( new RequestBean(AppHttpPath.GET_COMPANY_ACCOUNT, "GetAccountInfo", "", "{}")), AppHttpPath.GET_COMPANY_ACCOUNT );
                break;
            case R.id.confirm_tv:
                String account = getBaseActivity().getTextViewValue(mUserNameEt);
                String pwd = getBaseActivity().getTextViewValue(mPwdEt);
                if (TextUtils.isEmpty(account)) {
                    ToastUtils.toast(mContext, "请输入登录用户账号");
                    return;
                }
                if (TextUtils.isEmpty(pwd)) {
                    ToastUtils.toast(mContext, "请输入账号密码");
                    return;
                }
                mPresenter.getUserAccount(GsonTools.createGsonString( new RequestBean(AppHttpPath.GET_USER_ACCOUNT, "Login", "", "{}")), AppHttpPath.GET_USER_ACCOUNT );


                break;
        }
    }
}
