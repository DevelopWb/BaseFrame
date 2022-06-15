package com.juntai.wisdom.project.entrance;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import com.juntai.disabled.basecomponent.mvp.BaseIView;
import com.juntai.disabled.basecomponent.utils.PickerManager;
import com.juntai.disabled.basecomponent.utils.ToastUtils;
import com.juntai.wisdom.project.R;
import com.juntai.wisdom.project.base.BaseAppFragment;
import com.juntai.wisdom.project.utils.HawkProperty;
import com.orhanobut.hawk.Hawk;

import java.time.temporal.TemporalUnit;
import java.util.ArrayList;
import java.util.List;

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
        mServiceAddrEt.setText(Hawk.contains(HawkProperty.CURRENT_SERVICE_ADDRS) ? Hawk.get(HawkProperty.CURRENT_SERVICE_ADDRS) : "http://59.110.154.247:8088");
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
                if (!Hawk.contains(HawkProperty.HIS_SERVICE_ADDRS)) {
                    ToastUtils.toast(mContext, "暂无历史记录");
                    return;
                }
                List<String> hisAddrs = Hawk.get(HawkProperty.HIS_SERVICE_ADDRS);
                PickerManager.getInstance().showOptionPicker(mContext, hisAddrs, new PickerManager.OnOptionPickerSelectedListener() {
                    @Override
                    public void onOptionsSelect(int options1, int option2, int options3, View v) {
                        String addr = hisAddrs.get(options1);
                        mServiceAddrEt.setText(addr);
                        mConfirmTv.performClick();
                    }
                });
                break;
            case R.id.confirm_tv:
                String addr = getBaseAppActivity().getTextViewValue(mServiceAddrEt);
                if (TextUtils.isEmpty(addr)) {
                    ToastUtils.toast(mContext,"请输入服务器地址");
                    return;
                }
                Hawk.put(HawkProperty.CURRENT_SERVICE_ADDRS, addr);
                List<String> arrays = Hawk.contains(HawkProperty.HIS_SERVICE_ADDRS) ? Hawk.get(HawkProperty.HIS_SERVICE_ADDRS) : new ArrayList<>();
                if (!arrays.contains(addr)) {
                    arrays.add(addr);
                    Hawk.put(HawkProperty.HIS_SERVICE_ADDRS, arrays);
                }
                ToastUtils.toast(mContext, "保存成功");

                break;
        }
    }
}
