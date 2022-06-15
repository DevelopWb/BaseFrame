package a3phone.of.com.main.entrance;

import android.text.TextUtils;
import android.util.ArrayMap;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import a3phone.of.com.main.R;
import a3phone.of.disabled.basecomponent.mvp.BaseIView;
import a3phone.of.disabled.basecomponent.utils.PickerManager;
import a3phone.of.disabled.basecomponent.utils.ToastUtils;
import a3phone.of.com.main.base.BaseAppFragment;
import a3phone.of.com.main.bean.CommpanyAccountBean;
import a3phone.of.com.main.net.CmdCallBack;
import a3phone.of.com.main.net.CmdUtil;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;
import java.util.Map;

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
    private String accountCode;

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

    public List<CommpanyAccountBean> changeGsonToList(String gsonString) {
        Gson gson = new Gson();
        List<CommpanyAccountBean> list = gson.fromJson(gsonString, new TypeToken<List<CommpanyAccountBean>>() {
        }.getType());
        return list;
    }
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            default:
                break;
            case R.id.company_account_tv:
                CmdUtil.cmd("AccountInfoAdapter", "GetAccountInfo", (JSONObject) null, new CmdCallBack() {
                    @Override
                    public void onSuccess(String result) {
                        try {
                            JSONObject jsonObject = new JSONObject(result);
                            String jsonArray = jsonObject.getString("AccountInfo");
                            List<CommpanyAccountBean> arrays = changeGsonToList(jsonArray);
                            if (arrays.size() > 0) {
                                PickerManager.getInstance().showOptionPicker(mContext, arrays, new PickerManager.OnOptionPickerSelectedListener() {
                                    @Override
                                    public void onOptionsSelect(int options1, int option2, int options3, View v) {
                                        CommpanyAccountBean accountBean = arrays.get(options1);
                                        mCompanyAccountTv.setText(accountBean.getName());
                                        accountCode = accountBean.getCode();
                                    }
                                });
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }

                    @Override
                    public void onResponseError(String result) {
                        onError("",result);
                    }
                });
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
                Map<String,Object>  map = new ArrayMap<>();
                map.put("AccountCode",accountCode);
                map.put("UserName",account);
                map.put("Pwd",pwd);
                CmdUtil.cmd("LoginHandlerAdapter", "Login", map, new CmdCallBack() {
                    @Override
                    public void onSuccess(String result) {
                        try {
                            JSONObject jsonObject = new JSONObject(result);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }

                    @Override
                    public void onResponseError(String result) {
                        onError("",result);
                    }
                });
                break;
        }
    }
}
