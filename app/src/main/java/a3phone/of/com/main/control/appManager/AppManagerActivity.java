package a3phone.of.com.main.control.appManager;

import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.callback.ItemDragAndSwipeCallback;
import com.chad.library.adapter.base.listener.OnItemDragListener;

import java.util.ArrayList;
import java.util.List;

import a3phone.of.com.main.R;
import a3phone.of.com.main.base.BaseRecyclerviewActivity;
import a3phone.of.com.main.bean.PicTextBean;
import a3phone.of.com.main.control.ControlMenuChildAdapter;
import a3phone.of.com.main.control.ControlPresent;
import a3phone.of.disabled.basecomponent.mvp.BaseIView;

/**
 * @aouther tobato
 * @description 描述 应用管理
 * @date 2022/6/14 22:02
 */
public class AppManagerActivity extends BaseRecyclerviewActivity<ControlPresent> implements BaseIView, View.OnClickListener {

    /**
     * 常用
     */
    private TextView mAppNameTv;
    private LinearLayout mAppNameLl;
    private RecyclerView mControlAppRv;

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
                // TODO: 2022/6/14 设置App排序

            }
        });
    }

    @Override
    protected View getAdapterHeadView() {
        View view = LayoutInflater.from(mContext).inflate(R.layout.activity_app_manager, null);
        mAppNameTv = (TextView) view.findViewById(R.id.app_name_tv);
        mAppNameLl = (LinearLayout) view.findViewById(R.id.app_name_ll);
        mAppNameLl.setOnClickListener(this);
        mControlAppRv = (RecyclerView) view.findViewById(R.id.control_app_rv);
        ControlMenuDragAdapter  dragAdapter = new ControlMenuDragAdapter(R.layout.control_child_menu,null);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(mContext,4);
        mControlAppRv.setLayoutManager(gridLayoutManager);
        mControlAppRv.setAdapter(dragAdapter);
        List<PicTextBean> arrays = new ArrayList<>();
        for (int i = 0; i < 3; i++) {
            arrays.add(new PicTextBean(R.mipmap.ic_launcher, "测试" + i));
        }
        dragAdapter.setNewData(arrays);


        ItemDragAndSwipeCallback itemDragAndSwipeCallback = new ItemDragAndSwipeCallback(dragAdapter);
        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(itemDragAndSwipeCallback);
        itemTouchHelper.attachToRecyclerView(mControlAppRv);

// 开启拖拽
        dragAdapter.enableDragItem(itemTouchHelper);
        dragAdapter.setOnItemDragListener(new OnItemDragListener() {
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
        List<PicTextBean> arrays = new ArrayList<>();
        for (int i = 0; i < 3; i++) {
            arrays.add(new PicTextBean(R.mipmap.ic_launcher, "测试" + i));
        }
        baseQuickAdapter.setNewData(arrays);
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
        return new ControlMenuChildAdapter(R.layout.control_child_menu);
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            default:
                break;
            case R.id.app_name_ll:
                break;
        }
    }
}
