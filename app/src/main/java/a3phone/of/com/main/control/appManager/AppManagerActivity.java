package a3phone.of.com.main.control.appManager;

import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.text.TextUtils;
import android.util.ArrayMap;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.callback.ItemDragAndSwipeCallback;
import com.chad.library.adapter.base.listener.OnItemDragListener;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import a3phone.of.com.main.R;
import a3phone.of.com.main.base.BaseRecyclerviewActivity;
import a3phone.of.com.main.bean.ControlGroupBean;
import a3phone.of.com.main.bean.ControlMenuBean;
import a3phone.of.com.main.bean.ControlMenuEditListBean;
import a3phone.of.com.main.control.ControlMenuChildAdapter;
import a3phone.of.com.main.control.ControlPresent;
import a3phone.of.com.main.net.CmdCallBack;
import a3phone.of.com.main.net.CmdUtil;
import a3phone.of.disabled.basecomponent.mvp.BaseIView;
import a3phone.of.disabled.basecomponent.utils.GsonTools;
import a3phone.of.disabled.basecomponent.utils.PickerManager;

/**
 * @aouther tobato
 * @description 描述 应用管理
 * @date 2022/6/14 22:02
 */
public class AppManagerActivity extends BaseRecyclerviewActivity<ControlPresent> implements BaseIView, View.OnClickListener {

    /**
     * 常用
     */
    private TextView mGroupNameTv;
    private LinearLayout mAppNameLl;
    private RecyclerView mControlAppRv;
    private ControlMenuDragAdapter operateDragAdapter;
    private List<ControlGroupBean> groupList;
    private ControlMenuEditListBean.APPLISTBean applistBean;
    private ControlMenuEditListBean.GROUPAPPLISTBean groupapplistBean;
    /**
     * 当前群组的ID
     */
    private String currentGroupGuid = null;

    @Override
    protected ControlPresent createPresenter() {
        return new ControlPresent();
    }

    @Override
    public void initData() {
        super.initData();
        setTitleName("应用管理");
        getTitleRightTv().setText("完成");
        getTitleRightTv().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                /**
                 * 保存主控台APP编辑信息
                 */
                List<ControlMenuBean> controlMenuBeans = operateDragAdapter.getData();
                Map<String,Object>  map = new ArrayMap<>();
                map.put("GROUPAPPLIST",GsonTools.createGsonString(controlMenuBeans));
                map.put("GROUPGUID",currentGroupGuid);
                CmdUtil.cmd("A3OFAppAdapter", "LoadMainEditResult", map, new CmdCallBack() {
                    @Override
                    public void onSuccess(JSONObject result) {
                        initAdapterData(result);

                    }

                });

            }
        });

        baseQuickAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                ControlMenuBean controlMenuBean = (ControlMenuBean) adapter.getItem(position);
                // TODO: 2022/6/16 添加到对应的群组中
                adapter.remove(position);
                // TODO: 2022/6/17 更改菜单的状态
                controlMenuBean.setHANDLERTYPE(ControlMenuBean.ADD);
                operateDragAdapter.addData(controlMenuBean);


            }
        });

    }

    /**
     * guid  群组的guid
     */
    private void initAdapterData(String guid) {
        if (TextUtils.isEmpty(guid)) {
            return;
        }
        if (applistBean != null) {
            List<ControlMenuBean> rowsBeanXList = applistBean.getRows();
            baseQuickAdapter.setNewData(rowsBeanXList);
        }
        if (groupapplistBean != null) {
            List<ControlMenuBean> operateApps = new ArrayList<>();
            List<ControlMenuBean> rowsBeanXXList = groupapplistBean.getRows();
            if (rowsBeanXXList != null) {
                for (ControlMenuBean rowsBeanX : rowsBeanXXList) {
                    if (guid.equals(rowsBeanX.getGROUPGUID())) {
                        operateApps.add(rowsBeanX);
                    }
                }
                operateDragAdapter.setNewData(operateApps);
            }
        }
    }

    @Override
    protected View getAdapterHeadView() {
        View view = LayoutInflater.from(mContext).inflate(R.layout.activity_app_manager, null);
        mGroupNameTv = (TextView) view.findViewById(R.id.app_name_tv);
        mAppNameLl = (LinearLayout) view.findViewById(R.id.app_name_ll);
        mAppNameLl.setOnClickListener(this);
        mControlAppRv = (RecyclerView) view.findViewById(R.id.control_app_rv);
        operateDragAdapter = new ControlMenuDragAdapter(R.layout.control_child_menu, null);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(mContext, 5);
        mControlAppRv.setLayoutManager(gridLayoutManager);
        mControlAppRv.setAdapter(operateDragAdapter);
        ItemDragAndSwipeCallback itemDragAndSwipeCallback = new ItemDragAndSwipeCallback(operateDragAdapter);
        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(itemDragAndSwipeCallback);
        itemTouchHelper.attachToRecyclerView(mControlAppRv);

// 开启拖拽
        operateDragAdapter.enableDragItem(itemTouchHelper);
        operateDragAdapter.setOnItemDragListener(new OnItemDragListener() {
            @Override
            public void onItemDragStart(RecyclerView.ViewHolder viewHolder, int pos) {

            }

            @Override
            public void onItemDragMoving(RecyclerView.ViewHolder source, int from, RecyclerView.ViewHolder target, int to) {

            }

            @Override
            public void onItemDragEnd(RecyclerView.ViewHolder viewHolder, int pos) {

            }
        });

        operateDragAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                // : 2022/6/16 移除App
                ControlMenuBean controlMenuBean = (ControlMenuBean) adapter.getItem(position);
                adapter.remove(position);
                controlMenuBean.setHANDLERTYPE(ControlMenuBean.DELETE);
                baseQuickAdapter.addData(controlMenuBean);

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
        return new GridLayoutManager(mContext, 5);
    }

    @Override
    protected void getRvAdapterData() {
        /**
         * 获取主控台APP编辑信息
         */

        CmdUtil.cmd("A3OFAppAdapter", "LoadMainEditDefine", (Map<String, Object>) null, new CmdCallBack() {
            @Override
            public void onSuccess(JSONObject result) {
                initAdapterData(result);
            }

        });
    }

    private void initAdapterData(JSONObject result) {
        try {
            ControlMenuEditListBean.GROUPLISTBean grouplistBean = GsonTools.changeGsonToBean(result.getString("GROUPLIST"), ControlMenuEditListBean.GROUPLISTBean.class);
            applistBean = GsonTools.changeGsonToBean(result.getString("APPLIST"), ControlMenuEditListBean.APPLISTBean.class);
            groupapplistBean = GsonTools.changeGsonToBean(result.getString("GROUPAPPLIST"), ControlMenuEditListBean.GROUPAPPLISTBean.class);
            if (grouplistBean != null) {
                groupList = grouplistBean.getRows();
                if (groupList != null && groupList.size() > 0) {
                    if (TextUtils.isEmpty(currentGroupGuid)) {
                        mGroupNameTv.setText(groupList.get(0).getNAME());
                        currentGroupGuid = groupList.get(0).getGUID();
                        initAdapterData(groupList.get(0).getGUID());
                    } else {
                        initAdapterData(currentGroupGuid);

                    }

                }
            }


        } catch (JSONException e) {
            e.printStackTrace();
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
        return new ControlMenuUnOwnAdapter(R.layout.control_child_menu);
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            default:
                break;
            case R.id.app_name_ll:
                PickerManager.getInstance().showOptionPicker(mContext, groupList, new PickerManager.OnOptionPickerSelectedListener() {
                    @Override
                    public void onOptionsSelect(int options1, int option2, int options3, View v) {
                        ControlGroupBean groupRowBean = groupList.get(options1);
                        currentGroupGuid = groupRowBean.getGUID();
                        mGroupNameTv.setText(groupRowBean.getNAME());
                        initAdapterData(groupRowBean.getGUID());
                    }
                });
                break;
        }
    }
}
