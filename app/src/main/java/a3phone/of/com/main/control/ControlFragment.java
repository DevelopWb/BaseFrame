package a3phone.of.com.main.control;

import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;

import org.json.JSONException;
import org.json.JSONObject;

import a3phone.of.com.main.R;
import a3phone.of.com.main.bean.CommpanyAccountBean;
import a3phone.of.com.main.net.CmdCallBack;
import a3phone.of.com.main.net.CmdUtil;
import a3phone.of.disabled.basecomponent.mvp.BaseIView;
import a3phone.of.com.main.base.BaseRecyclerviewFragment;
import a3phone.of.com.main.control.appManager.AppManagerActivity;
import a3phone.of.disabled.basecomponent.utils.PickerManager;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @Author: tobato
 * @Description: 作用描述
 * @UpdateUser: 更新者
 */
public class ControlFragment extends BaseRecyclerviewFragment<ControlPresent> implements BaseIView, ControlMenuAdapter.OnControlMenuChildClick {
    @Override
    protected View getAdapterHeadView() {
        View view = LayoutInflater.from(mContext).inflate(R.layout.control_head_view, null);
        return view;
    }

    @Override
    protected View getAdapterFootView() {
        return null;
    }

    @Override
    protected LinearLayoutManager getBaseAdapterManager() {
        return null;
    }

    @Override
    protected void getRvAdapterData() {
        List<String> arrays = new ArrayList<>();
        arrays.add("常用");
        arrays.add("全部");
        arrays.add("拓展");
        baseQuickAdapter.setNewData(arrays);

        /**
         * 获取主控台信息
         */

        CmdUtil.cmd("A3OFAppAdapter", "LoadMainDefine", (Map<String, Object>) null, new CmdCallBack() {
            @Override
            public void onSuccess(JSONObject result) {
            }

        });

    }

    @Override
    protected boolean enableRefresh() {
        return false;
    }

    @Override
    protected boolean enableLoadMore() {
        return false;
    }

    @Override
    protected BaseQuickAdapter getBaseQuickAdapter() {
        return new ControlMenuAdapter(R.layout.control_menu_item, this);
    }

    @Override
    protected ControlPresent createPresenter() {
        return new ControlPresent();
    }

    @Override
    public void onMenuClick() {
        startActivity(new Intent(mContext, AppManagerActivity.class));
    }
}
