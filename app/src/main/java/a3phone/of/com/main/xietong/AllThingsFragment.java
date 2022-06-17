package a3phone.of.com.main.xietong;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.chad.library.adapter.base.BaseQuickAdapter;

import a3phone.of.com.main.R;
import a3phone.of.com.main.base.BaseRecyclerviewFragment;
import a3phone.of.com.main.base.customview.a3levelcombo.A3LevelCombo;
import a3phone.of.disabled.basecomponent.mvp.BaseIView;

/**
 * @Author: tobato
 * @Description: 作用描述
 * @CreateDate: 2022/6/17 21:53
 * @UpdateUser: 更新者
 * @UpdateDate: 2022/6/17 21:53
 */
public class AllThingsFragment extends BaseRecyclerviewFragment<XieTongPresent> implements BaseIView, View.OnClickListener {
    private A3LevelCombo mAllThingsItemLc;
    private ImageView mEditItemIv;

    @Override
    protected View getAdapterHeadView() {
        return null;
    }

    @Override
    protected View getAdapterFootView() {
        return null;
    }

    @Override
    protected int getLayoutRes() {
        return R.layout.xietong_things_fg;
    }

    @Override
    protected LinearLayoutManager getBaseAdapterManager() {
        return null;
    }

    @Override
    protected void getRvAdapterData() {
//        baseQuickAdapter.setNewData(getBaseAppActivity().getTestData());
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
        return new AllThingsAdapter(R.layout.all_tings_item);
    }

    @Override
    protected XieTongPresent createPresenter() {
        return null;
    }


    public void initView(View view) {
        super.initView();
        mAllThingsItemLc = (A3LevelCombo) getView(R.id.all_things_item_lc);
        mAllThingsItemLc.setData(getBaseAppActivity().getTestData());
        mEditItemIv = (ImageView) getView(R.id.edit_item_iv);
        mEditItemIv.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            default:
                break;
            case R.id.edit_item_iv:
                break;
        }
    }
}
