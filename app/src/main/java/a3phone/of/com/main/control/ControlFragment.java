package a3phone.of.com.main.control;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.sunfusheng.marqueeview.MarqueeView;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import a3phone.of.com.main.R;
import a3phone.of.com.main.base.BaseRecyclerviewFragment;
import a3phone.of.com.main.bean.ControlGroupBean;
import a3phone.of.com.main.bean.ControlGroupMenuBean;
import a3phone.of.com.main.bean.ControlMenuBean;
import a3phone.of.com.main.bean.SystemNoticeBean;
import a3phone.of.com.main.control.appManager.AppManagerActivity;
import a3phone.of.com.main.net.CmdCallBack;
import a3phone.of.com.main.net.CmdUtil;
import a3phone.of.disabled.basecomponent.base.BaseActivity;
import a3phone.of.disabled.basecomponent.mvp.BaseIView;
import a3phone.of.disabled.basecomponent.utils.GsonTools;
import a3phone.youth.banner.Banner;

/**
 * @Author: tobato
 * @Description: 作用描述
 * @UpdateUser: 更新者
 */
public class ControlFragment extends BaseRecyclerviewFragment<ControlPresent> implements BaseIView, ControlMenuAdapter.OnControlMenuChildClick, View.OnClickListener {
    /**
     * 北京君则科技发展有限公司
     */
    private TextView mOrgNameTv;
    private Banner mControlBanner;
    private MarqueeView mControlSystemNoticeMv;

    @Override
    protected View getAdapterHeadView() {
        View view = LayoutInflater.from(mContext).inflate(R.layout.control_head_view, null);
        mOrgNameTv = (TextView) view.findViewById(R.id.org_name_tv);
        mOrgNameTv.setOnClickListener(this);
        mControlBanner = (Banner) view.findViewById(R.id.control_banner);
        mControlSystemNoticeMv = (MarqueeView) view.findViewById(R.id.control_system_notice_mv);
        mControlSystemNoticeMv.setOnItemClickListener(new MarqueeView.OnItemClickListener() {
            @Override
            public void onItemClick(int position, TextView textView) {
                // TODO: 2022/6/18 跳转到系统公告
            }
        });
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

        /**
         * 获取主控台信息
         */

        CmdUtil.cmd("A3OFAppAdapter", "LoadMainDefine", (Map<String, Object>) null, new CmdCallBack() {
            @Override
            public void onSuccess(JSONObject result) {
                try {
                    initAdapterData(result);
                    initSystemNoticeData(result);

                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }

        });


    }

    /**
     * 系统通知
     *
     * @param result
     * @throws JSONException
     */
    private void initSystemNoticeData(JSONObject result) throws JSONException {
        String systemNoticeStr = result.getString("NOTICELIST");
        JSONObject systemNoticeJb = new JSONObject(systemNoticeStr);
        List<SystemNoticeBean> controlGroupBeans = GsonTools.changeGsonToList(systemNoticeJb.getString("Rows"), SystemNoticeBean.class);
        // : 2022/6/17 滚动控件添加数据
        mControlSystemNoticeMv.startWithList(controlGroupBeans);


    }

    /**
     * 加载适配器数据
     *
     * @param result
     * @throws JSONException
     */
    private void initAdapterData(JSONObject result) throws JSONException {
        String controlGroup = result.getString("GROUPLIST");
        JSONObject groupJb = new JSONObject(controlGroup);
        List<ControlGroupBean> controlGroupBeans = GsonTools.changeGsonToList(groupJb.getString("Rows"), ControlGroupBean.class);

        String allMenus = result.getString("APPLIST");
        JSONObject allMenuJb = new JSONObject(allMenus);
        List<ControlMenuBean> controlMenuBeans = GsonTools.changeGsonToList(allMenuJb.getString("Rows"), ControlMenuBean.class);

        List<ControlGroupMenuBean> data = new ArrayList<>();
        if (controlGroupBeans != null) {
            for (ControlGroupBean controlGroupBean : controlGroupBeans) {
                String guid = controlGroupBean.getGUID();
                List<ControlMenuBean> menuBeans = new ArrayList<>();
                if (controlMenuBeans != null) {
                    for (ControlMenuBean controlMenuBean : controlMenuBeans) {
                        if (controlMenuBean.getGROUPGUID().equals(guid)) {
                            menuBeans.add(controlMenuBean);
                        }
                    }
                }
                data.add(new ControlGroupMenuBean(controlGroupBean, menuBeans));
            }
            baseQuickAdapter.setNewData(data);
        }
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
        startActivityForResult(new Intent(mContext, AppManagerActivity.class), BaseActivity.BASE_REQUEST_RESULT);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == BaseActivity.BASE_REQUEST_RESULT) {
            getRvAdapterData();
        }
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            default:
                break;
            case R.id.org_name_tv:
                break;
        }
    }
}
