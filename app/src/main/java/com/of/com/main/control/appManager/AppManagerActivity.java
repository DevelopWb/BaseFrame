package com.of.com.main.control.appManager;

import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.of.disabled.basecomponent.mvp.BaseIView;
import com.of.com.main.R;
import com.of.com.main.base.BaseRecyclerviewActivity;
import com.of.com.main.bean.PicTextBean;
import com.of.com.main.control.ControlMenuChildAdapter;
import com.of.com.main.control.ControlPresent;

import java.util.ArrayList;
import java.util.List;

/**
 * @aouther tobato
 * @description 描述 应用管理
 * @date 2022/6/14 22:02
 */
public class AppManagerActivity extends BaseRecyclerviewActivity<ControlPresent> implements BaseIView {

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
        View view = LayoutInflater.from(mContext).inflate(R.layout.activity_app_manager,null);
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
            arrays.add(new PicTextBean(R.mipmap.ic_launcher,"测试"+i));
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
}
