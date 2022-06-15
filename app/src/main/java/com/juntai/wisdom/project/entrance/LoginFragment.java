package com.juntai.wisdom.project.entrance;

import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.juntai.disabled.basecomponent.mvp.BaseIView;
import com.juntai.disabled.basecomponent.utils.GsonTools;
import com.juntai.disabled.basecomponent.utils.LogUtil;
import com.juntai.disabled.basecomponent.utils.PickerManager;
import com.juntai.disabled.basecomponent.utils.ToastUtils;
import com.juntai.wisdom.project.AppHttpPath;
import com.juntai.wisdom.project.R;
import com.juntai.wisdom.project.base.BaseAppFragment;
import com.juntai.wisdom.project.bean.CommpanyAccountBean;
import com.juntai.wisdom.project.bean.RequestBean;
import com.juntai.wisdom.project.net.CmdCallBack;
import com.juntai.wisdom.project.net.CmdUtil;
import com.king.zxing.util.LogUtils;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import okhttp3.MediaType;
import okhttp3.RequestBody;

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
        switch (tag) {
            case AppHttpPath.GET_COMPANY_ACCOUNT:
                JsonObject jsonStr = (JsonObject) o;
                String str =GsonTools.createGsonString(jsonStr);
                try {
                    JSONObject jsonObject = new JSONObject(str);
                    String jsonArray = jsonObject.getString("AccountInfo");
                    List<CommpanyAccountBean> arrays = GsonTools.changeGsonToList(jsonArray, CommpanyAccountBean.class);
                    if (arrays.size()>0) {
                        PickerManager.getInstance().showOptionPicker(mContext, arrays, new PickerManager.OnOptionPickerSelectedListener() {
                            @Override
                            public void onOptionsSelect(int options1, int option2, int options3, View v) {
                                CommpanyAccountBean accountBean = arrays.get(options1);
                                mCompanyAccountTv.setText(accountBean.getName());
                            }
                        });
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                break;
            default:
                break;
        }
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            default:
                break;
            case R.id.company_account_tv:
                CmdUtil.cmd(AppHttpPath.GET_COMPANY_ACCOUNT, "GetAccountInfo", (JSONObject) null, new CmdCallBack() {
                    @Override
                    public void onSuccess(String result) {
                        try {
                            JSONObject jsonObject = new JSONObject(result);
                            JSONArray jsonArray = jsonObject.getJSONArray("AccountInfo");
                            List<CommpanyAccountBean> arrays = new ArrayList<>();
                            for (int i = 0; i < jsonArray.length(); i++) {
                                JSONObject obj =jsonArray.getJSONObject(i);
                                arrays.add(new CommpanyAccountBean(obj.getString("Code"),obj.getString("Name")));
                            }
                            if (arrays.size()>0) {
                                PickerManager.getInstance().showOptionPicker(mContext, arrays, new PickerManager.OnOptionPickerSelectedListener() {
                                    @Override
                                    public void onOptionsSelect(int options1, int option2, int options3, View v) {
                                        CommpanyAccountBean accountBean = arrays.get(options1);
                                        mCompanyAccountTv.setText(accountBean.getName());
                                    }
                                });
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }

                    @Override
                    public void onError(String result) {

                    }
                });

//                mPresenter.getCompanyAccount(getRequestBody(AppHttpPath.GET_COMPANY_ACCOUNT, "GetAccountInfo", null, null), AppHttpPath.GET_COMPANY_ACCOUNT);
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
//                mPresenter.getUserAccount(GsonTools.createGsonString( new RequestBean(AppHttpPath.GET_USER_ACCOUNT, "Login", "", "{}")), AppHttpPath.GET_USER_ACCOUNT );


                break;
        }
    }
}
